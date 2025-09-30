package com.greentrack.repository;
import com.greentrack.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    List<UserLog> findByUserId(Long userId);
    List<UserLog> findByUserIdAndDate(Long userId, LocalDate date);
}
