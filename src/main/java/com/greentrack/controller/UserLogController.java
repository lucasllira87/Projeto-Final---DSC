package com.greentrack.controller;
import com.greentrack.entity.UserLog;
import com.greentrack.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/logs")
@RequiredArgsConstructor
public class UserLogController {
    private final UserLogService userLogService;
    @PostMapping
    public ResponseEntity<UserLog> create(@PathVariable Long userId, @RequestBody com.greentrack.dto.UserLogCreateDTO dto){
        LocalDate date = dto.getDate() == null ? LocalDate.now() : dto.getDate();
        UserLog l = userLogService.create(userId, dto.getAmount(), dto.getActivityId(), date);
        return ResponseEntity.status(201).body(l);
    }
    @GetMapping
    public ResponseEntity<List<UserLog>> list(@PathVariable Long userId){ return ResponseEntity.ok(userLogService.findByUser(userId)); }
}
