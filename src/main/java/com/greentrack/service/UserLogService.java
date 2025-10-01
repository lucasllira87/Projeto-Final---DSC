package com.greentrack.service;

import com.greentrack.dto.UserLogDTO;
import com.greentrack.entity.Activity;
import com.greentrack.entity.User;
import com.greentrack.entity.UserLog;
import com.greentrack.repository.ActivityRepository;
import com.greentrack.repository.UserLogRepository;
import com.greentrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserLogService {
    private final UserLogRepository logRepo;
    private final UserRepository userRepo;
    private final ActivityRepository activityRepo;

    public UserLogService(UserLogRepository logRepo, UserRepository userRepo, ActivityRepository activityRepo) {
        this.logRepo = logRepo;
        this.userRepo = userRepo;
        this.activityRepo = activityRepo;
    }

    public List<UserLogDTO> findByUserId(Long userId) {
        return logRepo.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserLogDTO create(Long userId, UserLogDTO dto) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = activityRepo.findById(dto.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found"));
        UserLog log = new UserLog();
        log.setUser(user);
        log.setActivity(activity);
        log.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());
        log.setQuantity(dto.getQuantity());
        return toDto(logRepo.save(log));
    }

    public Optional<UserLogDTO> update(Long userId, Long logId, UserLogDTO dto) {
        return logRepo.findById(logId).filter(l -> l.getUser().getId().equals(userId)).map(l -> {
            Activity activity = activityRepo.findById(dto.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found"));
            l.setActivity(activity);
            l.setDate(dto.getDate());
            l.setQuantity(dto.getQuantity());
            return toDto(logRepo.save(l));
        });
    }

    public void delete(Long userId, Long logId) {
        logRepo.findById(logId).filter(l -> l.getUser().getId().equals(userId)).ifPresent(logRepo::delete);
    }

    private UserLogDTO toDto(UserLog l) {
        UserLogDTO dto = new UserLogDTO();
        dto.setId(l.getId());
        dto.setActivityId(l.getActivity().getId());
        dto.setDate(l.getDate());
        dto.setQuantity(l.getQuantity());
        return dto;
    }
}
