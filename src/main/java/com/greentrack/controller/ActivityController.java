package com.greentrack.controller;

import com.greentrack.dto.ActivityDTO;
import com.greentrack.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService service;

    public ActivityController(ActivityService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> list() { return ResponseEntity.ok(service.findAll()); }

    @PostMapping
    public ResponseEntity<ActivityDTO> create(@Valid @RequestBody ActivityDTO dto) { return ResponseEntity.ok(service.create(dto)); }

    @GetMapping("/search")
    public ResponseEntity<List<ActivityDTO>> findByCategory(@RequestParam String category) { return ResponseEntity.ok(service.findByCategory(category)); }
}
