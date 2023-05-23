package com.ssupowerback.controller;

import com.ssupowerback.entity.TimeTableDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/timetable")
@CrossOrigin(origins = "http://localhost:3000")


public class TimeTableController {

    private final TimeTableDAO timetableDAO;

    public TimeTableController(TimeTableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }
    @PostMapping("/{mId}/select")
    public ResponseEntity<List<Map<String, ?>>> getTimeTable(@PathVariable @RequestBody Integer mId) {
        List<Map<String, ?>> timeTableList = timetableDAO.selectTimeTable(mId);
        if (timeTableList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(timeTableList);
        }
    }




    @PostMapping("/{mId}/insert")
    public List<Map<String, ?>> setTimeTable(@PathVariable Integer mId, @RequestBody Map<String, String> Submit) {
        timetableDAO.insertTimeTable(Submit);
        return timetableDAO.selectTimeTable(mId);
    }
    @DeleteMapping("/delete/{mId}/{subject}")
    public List<Map<String, ?>> delTimetable(@PathVariable Integer mId, @PathVariable String subject){
        timetableDAO.deleteTimetable(subject, mId);
        return timetableDAO.selectTimeTable(mId);
    }
}
