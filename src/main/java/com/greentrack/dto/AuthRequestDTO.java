package com.greentrack.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthRequestDTO {
    private String username;
    private String password;
}
