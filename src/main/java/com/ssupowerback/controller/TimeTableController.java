package com.ssupowerback.controller;

import com.ssupowerback.entity.TimeTableDAO;
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
    @GetMapping("/{mId}/select")
    public List<Map<String, ?>> getTimeTable() {
        return timetableDAO.selectTimeTable();
    }

    @PostMapping("/{mId}/insert")
    public List<Map<String, ?>> setTimeTable(@RequestBody Map<String, String> Submit) {
        timetableDAO.insertTimeTable(Submit);
        return timetableDAO.selectTimeTable();
    }
    @DeleteMapping("/delete/{mId}/{subject}")
    public List<Map<String, ?>> delTimetable(@PathVariable String subject){
        timetableDAO.deleteTimetable(subject);
        return timetableDAO.selectTimeTable();
    }
}
