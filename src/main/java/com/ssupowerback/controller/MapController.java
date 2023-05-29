package com.ssupowerback.controller;

import com.ssupowerback.entity.MapDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{mId}/select")
    public ResponseEntity<List<Map<String, ?>>> getMap(@PathVariable("mId")  Integer mId) {
        List<Map<String, ?>> mapList = mapDAO.selectAll(mId);
        return ResponseEntity.ok().body(mapList);
    }

}
