package com.greentrack.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActivityDTO {
    private Long id;
    private String name;
    private String category;
    private String unit;
    private Double emissionFactor;
    private String description;
}
