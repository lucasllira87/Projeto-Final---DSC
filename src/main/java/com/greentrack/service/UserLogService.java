package com.greentrack.service;
import com.greentrack.entity.*;
import com.greentrack.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLogService {
    private final UserLogRepository userLogRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public UserLog create(Long userId, Double amount, Long activityId, LocalDate date){
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Activity a = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));
        double emissions = (a.getEmissionFactor() == null ? 0.0 : a.getEmissionFactor()) * (amount == null ? 0.0 : amount);
        UserLog log = UserLog.builder().user(u).activity(a).amount(amount).date(date).calculatedEmissions(emissions).build();
        return userLogRepository.save(log);
    }
    public List<UserLog> findByUser(Long userId){ return userLogRepository.findByUserId(userId); }
}
