package com.greentrack.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserLogDTO {
    private Long id;
    @NotNull
    private Long activityId;
    private LocalDate date;
    @NotNull
    private Double quantity;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
}
