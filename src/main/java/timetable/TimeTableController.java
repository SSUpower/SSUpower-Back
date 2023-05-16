package timetable;

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
    @GetMapping("/select")
    public List<Map<String, ?>> getTimeTable() {
        return timetableDAO.selectTimeTable();
    }



    @PostMapping("/insert")
    public void setTimeTable(@RequestBody Map<String, ?> Submit) {
        timetableDAO.insertTimeTable(Submit);
    }
}