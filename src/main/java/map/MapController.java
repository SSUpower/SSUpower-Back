package map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/map")
public class MapController {

    private final MapDAO mapDAO;

    public MapController(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @GetMapping("/select")
    public List<Map<String, ?>> getMessage() {
        return mapDAO.selectAll();
    }
}
