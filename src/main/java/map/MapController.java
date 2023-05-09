package map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
public class MapController {
    @Autowired
    MapDAO smd;

    @GetMapping("/select")
    public List<Map<String, ?>> getMessage() {
        return smd.selectAll();
    }
}
