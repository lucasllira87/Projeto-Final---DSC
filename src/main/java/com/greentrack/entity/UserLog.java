package com.greentrack.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Activity activity;
    private Double amount;
    private LocalDate date;
    private Double calculatedEmissions;
}
