package com.greentrack.controller;

import com.greentrack.dto.UserDTO;
import com.greentrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity<List<UserDTO>> list() { return ResponseEntity.ok(userService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) { return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return userService.update(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { userService.delete(id); return ResponseEntity.noContent().build(); }
}
