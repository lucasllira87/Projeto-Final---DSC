package com.greentrack.service;

import com.greentrack.dto.ActivityDTO;
import com.greentrack.entity.Activity;
import com.greentrack.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository repo;
    private final ModelMapper mapper;

    public ActivityService(ActivityRepository repo, ModelMapper mapper) { this.repo = repo; this.mapper = mapper; }

    public List<ActivityDTO> findAll() {
        return repo.findAll().stream().map(a -> mapper.map(a, ActivityDTO.class)).collect(Collectors.toList());
    }
    public ActivityDTO create(ActivityDTO dto) {
        Activity a = mapper.map(dto, Activity.class);
        return mapper.map(repo.save(a), ActivityDTO.class);
    }
    public List<ActivityDTO> findByCategory(String category) {
        return repo.findByCategory(category).stream().map(a -> mapper.map(a, ActivityDTO.class)).collect(Collectors.toList());
    }
}
