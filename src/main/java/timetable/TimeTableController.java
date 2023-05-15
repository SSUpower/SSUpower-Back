package timetable;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/timetable")

public class TimeTableController {

    private final TimeTableDAO timetableDAO;

    public TimeTableController(TimeTableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }
    @GetMapping("/select")
    public List<Map<String, ?>> getTimeTable() {
        return timetableDAO.selectTimeTable();
    }

//    @GetMapping("/insert")
//    public List<Map<String, ?>> setTimeTable(@RequestBody Map<String, ?> Submit) {
//        //day, StartTime, EndTime, Subject, Room
//        return;
//    }
}
