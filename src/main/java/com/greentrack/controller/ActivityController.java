package com.greentrack.controller;
import com.greentrack.entity.Activity;
import com.greentrack.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    @GetMapping
    public ResponseEntity<List<Activity>> all(){ return ResponseEntity.ok(activityService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Activity> get(@PathVariable Long id){ return ResponseEntity.ok(activityService.findById(id)); }
    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody Activity a){ return ResponseEntity.status(201).body(activityService.save(a)); }
}
