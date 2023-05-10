package timetable;

import map.MapDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/timetable")
public class TimeTableController {

    private final TimeTableDAO timetableDAO;

    public TimeTableController(TimeTableDAO timetableDAO) {
        this.timetableDAO= timetableDAO;
    }

    @GetMapping("/select")
    public List<Map<String, ?>> getMessage() {
        return timetableDAO.selectTimeTable();
    }
}