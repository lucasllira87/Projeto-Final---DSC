package com.greentrack.service;
import com.greentrack.entity.Activity;
import com.greentrack.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    public List<Activity> findAll(){ return activityRepository.findAll(); }
    public Activity findById(Long id){ return activityRepository.findById(id).orElseThrow(() -> new RuntimeException("Activity not found")); }
    public Activity save(Activity a){ return activityRepository.save(a); }
}
