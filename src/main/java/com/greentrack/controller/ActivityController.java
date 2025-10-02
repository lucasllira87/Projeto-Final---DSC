package com.greentrack.controller;

import com.greentrack.dto.ActivityDTO;
import com.greentrack.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    // ===========================
    // Criar nova atividade
    // ===========================
    @PostMapping
    public ResponseEntity<ActivityDTO> create(@RequestBody ActivityDTO dto) {
        ActivityDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    // ===========================
    // Listar todas as atividades
    // ===========================
    @GetMapping
    public ResponseEntity<List<ActivityDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ===========================
    // Buscar atividade por ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ===========================
    // Atualizar atividade
    // ===========================
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> update(@PathVariable Long id, @RequestBody ActivityDTO dto) {
        ActivityDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ===========================
    // Deletar atividade
    // ===========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===========================
    // Buscar por categoria (opcional)
    // ===========================
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ActivityDTO>> findByCategory(@PathVariable String category) {
        // Verifique se você adicionou findByCategory no ActivityService e no ActivityRepository
        List<ActivityDTO> activities = service.findByCategory(category);
        return ResponseEntity.ok(activities);
    }
}

