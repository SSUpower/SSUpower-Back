package com.ssupowerback.controller;

import com.ssupowerback.entity.MapDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public List<Map<String, ?>> getMap() {

        return mapDAO.selectAll();
    }
}