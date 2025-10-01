package com.greentrack.controller;

import com.greentrack.dto.UserLogDTO;
import com.greentrack.service.UserLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/logs")
public class UserLogController {
    private final UserLogService service;

    public UserLogController(UserLogService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<UserLogDTO>> list(@PathVariable Long userId) { return ResponseEntity.ok(service.findByUserId(userId)); }

    @PostMapping
    public ResponseEntity<UserLogDTO> create(@PathVariable Long userId, @Valid @RequestBody UserLogDTO dto) { return ResponseEntity.ok(service.create(userId, dto)); }

    @PutMapping("/{logId}")
    public ResponseEntity<UserLogDTO> update(@PathVariable Long userId, @PathVariable Long logId, @Valid @RequestBody UserLogDTO dto) {
        return service.update(userId, logId, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long logId) { service.delete(userId, logId); return ResponseEntity.noContent().build(); }
}
