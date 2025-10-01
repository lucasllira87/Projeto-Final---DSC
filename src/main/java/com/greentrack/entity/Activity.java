package com.greentrack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String unit;
    private Double emissionFactor;
    private String description;
}
