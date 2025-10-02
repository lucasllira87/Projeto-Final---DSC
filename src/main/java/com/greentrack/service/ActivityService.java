package com.greentrack.service;

import com.greentrack.dto.ActivityDTO;
import com.greentrack.entity.Activity;
import com.greentrack.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // ===========================
    // Conversões manuais
    // ===========================

    public ActivityDTO toDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        dto.setId(activity.getId());
        dto.setName(activity.getName());
        dto.setDescription(activity.getDescription());
        dto.setCarbonValue(activity.getCarbonValue());
        // Adicione outros campos conforme sua entidade
        return dto;
    }

    public Activity toEntity(ActivityDTO dto) {
        Activity activity = new Activity();
        activity.setId(dto.getId());
        activity.setName(dto.getName());
        activity.setDescription(dto.getDescription());
        activity.setCarbonValue(dto.getCarbonValue());
        // Adicione outros campos conforme sua entidade
        return activity;
    }

    public List<ActivityDTO> toDTOList(List<Activity> activities) {
        return activities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ===========================
    // CRUD básico usando DTO
    // ===========================

    public ActivityDTO create(ActivityDTO dto) {
        Activity activity = toEntity(dto);
        Activity saved = activityRepository.save(activity);
        return toDTO(saved);
    }

    public List<ActivityDTO> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return toDTOList(activities);
    }

    public Optional<ActivityDTO> findById(Long id) {
        return activityRepository.findById(id)
                .map(this::toDTO);
    }

    public ActivityDTO update(Long id, ActivityDTO dto) {
        Activity existing = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        // Atualiza manualmente os campos
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setCarbonValue(dto.getCarbonValue());
        // Atualize outros campos conforme necessário

        Activity updated = activityRepository.save(existing);
        return toDTO(updated);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }
}

