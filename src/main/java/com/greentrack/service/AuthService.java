package com.greentrack.service;
import com.greentrack.dto.*;
import com.greentrack.entity.User;
import com.greentrack.repository.UserRepository;
import com.greentrack.config.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponseDTO register(UserCreateDTO dto){
        User u = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .carbonGoal(dto.getCarbonGoal())
                .role("USER")
                .build();
        userRepository.save(u);
        String token = jwtUtil.generateToken(u.getUsername());
        return AuthResponseDTO.builder().token(token).username(u.getUsername()).build();
    }

    public AuthResponseDTO login(AuthRequestDTO dto){
        Optional<User> opt = userRepository.findByUsername(dto.getUsername());
        if(opt.isEmpty()) throw new RuntimeException("Usuário não encontrado");
        User u = opt.get();
        if(!passwordEncoder.matches(dto.getPassword(), u.getPassword())) throw new RuntimeException("Senha inválida");
        String token = jwtUtil.generateToken(u.getUsername());
        return AuthResponseDTO.builder().token(token).username(u.getUsername()).build();
    }
}
