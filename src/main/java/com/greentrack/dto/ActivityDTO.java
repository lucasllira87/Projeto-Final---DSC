package com.greentrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ActivityDTO {
    private Long id;
    @NotBlank
    private String name;
    private String category;
    @NotNull
    private Double emissionFactor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getEmissionFactor() { return emissionFactor; }
    public void setEmissionFactor(Double emissionFactor) { this.emissionFactor = emissionFactor; }
}
