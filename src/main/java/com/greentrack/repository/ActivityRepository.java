package com.greentrack.repository;
import com.greentrack.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByCategoryContainingIgnoreCase(String category);
}
