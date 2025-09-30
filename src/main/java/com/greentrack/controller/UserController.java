package com.greentrack.controller;
import com.greentrack.service.UserService;
import com.greentrack.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDTO>> all(){ return ResponseEntity.ok(userService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id){ return ResponseEntity.ok(userService.findById(id)); }
}
