package timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}