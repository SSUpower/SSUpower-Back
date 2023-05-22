package com.ssupowerback.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MapDAO {
    @Autowired
    JdbcTemplate jt;

    public List<Map<String, ?>> selectAll() {

        return jt.query("SELECT * FROM school_db.timetable, school_db.building where school_db.timetable.bId = school_db.building.bId;", (rs, rowNum) -> {
            Map<String, Object> mss = new HashMap<>();
            mss.put("buildingId", rs.getInt(1));
            mss.put("classID", rs.getString(2));
            mss.put("object", rs.getString(3));
            mss.put("start", rs.getString(4));
            mss.put("end", rs.getString(5));
            mss.put("day", rs.getString(6));
            mss.put("_id", rs.getInt(7));
            mss.put("building_name", rs.getString(8));
            mss.put("latitude", rs.getDouble(9));
            mss.put("longitude", rs.getDouble(10));
            return mss;
        });
    }
}