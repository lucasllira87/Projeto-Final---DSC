package com.greentrack.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Double carbonGoal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLog> logs = new ArrayList<>();

    private String role = "USER";

    public User() {}

    // getters and setters
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
    public List<UserLog> getLogs() { return logs; }
    public void setLogs(List<UserLog> logs) { this.logs = logs; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
