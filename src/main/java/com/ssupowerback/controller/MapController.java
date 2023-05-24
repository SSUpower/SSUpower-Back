package com.ssupowerback.controller;

import com.ssupowerback.entity.MapDAO;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> deltimetable

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/map")
public class MapController {

    private final MapDAO mapDAO;

    public MapController(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @PostMapping("/{mId}/select")

    public List<Map<String, ?>> getMap(@PathVariable("mId") Integer mId) {

        return mapDAO.selectAll(mId);

    }

}
