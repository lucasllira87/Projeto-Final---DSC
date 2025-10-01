package com.greentrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    private Double carbonGoal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Double getCarbonGoal() { return carbonGoal; }
    public void setCarbonGoal(Double carbonGoal) { this.carbonGoal = carbonGoal; }
}
