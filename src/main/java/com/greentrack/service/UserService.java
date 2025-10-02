package com.greentrack.service;

import com.greentrack.dto.UserDTO;
import com.greentrack.entity.User;
import com.greentrack.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ===========================
    // Conversões manuais
    // ===========================
    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCarbonGoal(user.getCarbonGoal());
        // Não colocamos a senha no DTO
        return dto;
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setCarbonGoal(dto.getCarbonGoal());
        // A senha será codificada na criação/update
        return user;
    }

    // ===========================
    // CRUD
    // ===========================

    public UserDTO create(UserDTO dto) {
        User user = toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return toDTO(saved);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(this::toDTO);
    }

    public Optional<UserDTO> update(Long id, UserDTO dto) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            user.setCarbonGoal(dto.getCarbonGoal());
            User updated = userRepository.save(user);
            return toDTO(updated);
        });
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // ===========================
    // Métodos auxiliares
    // ===========================
    public Optional<User> findEntityByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
