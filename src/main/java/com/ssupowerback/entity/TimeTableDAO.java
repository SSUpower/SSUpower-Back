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

    public List<Map<String, ?>> selectTimeTable(String mId) {
        return jt.query("SELECT * FROM school_db.timetable WHERE mId = ?;", new Object[]{mId}, (rs, rowNum) -> {
            Map<String, Object> temptable = new HashMap<>();
            temptable.put("room", '0' + rs.getString(1) + rs.getString(2));
            //temptable.put("classID", rs.getInt(2));
            temptable.put("subject", rs.getString(3));
            temptable.put("startTime", rs.getString(4));
            temptable.put("endTime", rs.getString(5));
            temptable.put("day", rs.getString(6));
            temptable.put("mId", rs.getString(7));

            return temptable;
        });
    }

    public void insertTimeTable(Map<String, ?> timeTableData) {
        String query = "INSERT INTO school_db.timetable (school_db.timetable.bId, school_db.timetable.cId, school_db.timetable.object, school_db.timetable.start, school_db.timetable.end, school_db.timetable.day, school_db.timetable.mId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jt.update(query, (PreparedStatement preparedStatement) -> {
            String room = (String) timeTableData.get("room");

            String class_id = "";
            String building_id = "";

            if (room.length() == 5 ) {//
                class_id = room.substring(2, 5);
                building_id = room.substring(0, 2);
            } else if (room.length() == 6) { //021 303
                class_id = room.substring(3, 6);
                building_id = room.substring(0, 3);
            }

            preparedStatement.setString(1, building_id);
            preparedStatement.setString(2, class_id);
            preparedStatement.setString(3, (String) timeTableData.get("subject"));
            preparedStatement.setString(4, (String) timeTableData.get("startTime"));
            preparedStatement.setString(5, (String) timeTableData.get("endTime"));
            preparedStatement.setString(6, (String) timeTableData.get("day"));
            preparedStatement.setString(7, (String) timeTableData.get("mId"));


            // preparedStatement 실행
            preparedStatement.execute();
        });
    }
    public void deleteTimetable(String subject, String mId) {
        String queryString = "DELETE FROM school_db.timetable WHERE school_db.timetable.subject = ? and school_db.timetable.mId = ?";
        jt.update(queryString, subject);
    }
}
