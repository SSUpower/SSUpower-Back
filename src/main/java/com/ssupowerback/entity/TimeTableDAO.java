package com.ssupowerback.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.engine.jdbc.Size.length;

@Repository
public class TimeTableDAO {
    @Autowired
    JdbcTemplate jt;

    public List<Map<String, ?>> selectTimeTable(Integer mId) {
        String sql = "SELECT CONCAT('0', school_db.timetable.bId, school_db.timetable.cId) AS room, object, start, end, day, mId FROM school_db.timetable WHERE mId = ?";

        return jt.query(sql, new Object[]{mId}, (rs, rowNum) -> {
            Map<String, Object> timeTable = new HashMap<>();
            timeTable.put("room", rs.getString(1));
            timeTable.put("subject", rs.getString(2));
            timeTable.put("startTime", rs.getString(3));
            timeTable.put("endTime", rs.getString(4));
            timeTable.put("day", rs.getString(5));
            timeTable.put("mId", rs.getInt(6));

            return timeTable;
        });
    }


    public void insertTimeTable(Map<String, ?> timeTableData) {
        String query = "INSERT INTO school_db.timetable (school_db.timetable.bId, school_db.timetable.cId, school_db.timetable.object, school_db.timetable.start, school_db.timetable.end, school_db.timetable.day, school_db.timetable.mId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jt.update(query, (PreparedStatement preparedStatement) -> {
            String room = (String) timeTableData.get("room");

            String class_id = "";
            Integer building_id = 0;

            if (room.length() == 5) {//
                class_id = room.substring(2, 5);
                building_id = Integer.parseInt(room.substring(0, 2));
            } else if (room.length() == 6) { //021 303
                class_id = room.substring(3, 6);
                building_id = Integer.parseInt(room.substring(0, 3));
            }

            preparedStatement.setInt(1, building_id);
            preparedStatement.setString(2, class_id);
            preparedStatement.setString(3, (String) timeTableData.get("subject"));
            preparedStatement.setString(4, (String) timeTableData.get("startTime"));
            preparedStatement.setString(5, (String) timeTableData.get("endTime"));
            preparedStatement.setString(6, (String) timeTableData.get("day"));
            preparedStatement.setInt(7, (Integer) timeTableData.get("userId"));


            // preparedStatement 실행
            preparedStatement.execute();
        });
    }

    public void deleteTimetable(Map<String, ?> timeTableData, Integer mId) {
        String queryString = "DELETE FROM school_db.timetable WHERE school_db.timetable.object = ? AND school_db.timetable.mId = ? AND school_db.timetable.day = ?";
        String subject = (String)timeTableData.get("subject");
        String day = (String)timeTableData.get("day");
        jt.update(queryString, subject, mId, day);
    }

}

