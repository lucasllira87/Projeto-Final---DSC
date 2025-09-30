package com.greentrack.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserCreateDTO {
    private String username;
    private String email;
    private String password;
    private Double carbonGoal;
}
