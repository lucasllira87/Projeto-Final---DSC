package com.greentrack.dto;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserLogCreateDTO {
    private Long activityId;
    private Double amount;
    private LocalDate date;
}
