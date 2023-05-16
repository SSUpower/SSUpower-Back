package timetable;

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

@Repository
public class TimeTableDAO {
    @Autowired
    JdbcTemplate jt;

    public List<Map<String, ?>> selectTimeTable() {

        return jt.query("SELECT * FROM school_db.timetable;", (rs, rowNum) -> {
            Map<String, Object> temptable = new HashMap<>();
            temptable.put("room", '0'+ rs.getString(1) + rs.getString(2));
            //temptable.put("classID", rs.getInt(2));
            temptable.put("subject", rs.getString(3));
            temptable.put("startTime", rs.getString(4));
            temptable.put("endTime", rs.getString(5));
            temptable.put("day", rs.getString(6));

            //temptable.put("_id", rs.getInt(7));
            //temptable.put("building_name", rs.getString(8));
            //temptable.put("latitude", rs.getDouble(9));
            //temptable.put("longitude", rs.getDouble(10));

            return temptable;
        });
    }
    public void insertTimeTable(Map<String, ?> timeTableData) {
        String url = "jdbc:mariadb://svc.sel3.cloudtype.app:31275";
        String username = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO timetable (building_id, class_id, subject, startTime, endTime, day) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            String room = (String)timeTableData.get("room");
            String building_id = room.substring(0, 2);
            String class_id = room.substring(3, 6);
            statement.setString(1, building_id);
            statement.setString(2, class_id);
            statement.setString(3, (String) timeTableData.get("subject"));
            statement.setString(4, (String) timeTableData.get("start"));
            statement.setString(5, (String) timeTableData.get("end"));
            statement.setString(6, (String) timeTableData.get("day"));
            statement.addBatch();
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}