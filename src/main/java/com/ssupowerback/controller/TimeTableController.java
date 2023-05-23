package com.ssupowerback.controller;

import com.ssupowerback.entity.TimeTableDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<List<Map<String, ?>>> getTimeTable(@PathVariable("mId") Integer mId) {
        List<Map<String, ?>> timeTableList = timetableDAO.selectTimeTable(mId);
        if (timeTableList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(timeTableList);
        }
    }


    @PostMapping("/{mId}/insert")
    public ResponseEntity<List<Map<String, ?>>> setTimeTable(@PathVariable("mId") Integer mId, @RequestBody Map<String, Object> Submit) {
        timetableDAO.insertTimeTable(Submit);
        List<Map<String, ?>> timetable = timetableDAO.selectTimeTable(mId);
        return ResponseEntity.ok(timetable);
    }

    @DeleteMapping("/{mId}/{Submit}/delete")
    public ResponseEntity<List<Map<String, ?>>> delTimetable(@PathVariable("mId") Integer mId, @PathVariable("Submit") String Submit) {
        String encodedSubmit = UriUtils.encode(Submit, StandardCharsets.UTF_8);
        timetableDAO.deleteTimetable(encodedSubmit, mId);
        List<Map<String, ?>> timetable = timetableDAO.selectTimeTable(mId);
        return ResponseEntity.ok(timetable);
    }

}
