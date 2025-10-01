package com.greentrack.service;

import com.greentrack.dto.UserDTO;
import com.greentrack.entity.User;
import com.greentrack.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO create(UserDTO dto) {
        User u = modelMapper.map(dto, User.class);
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(u);
        return modelMapper.map(saved, UserDTO.class);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(u -> modelMapper.map(u, UserDTO.class));
    }

    public Optional<UserDTO> update(Long id, UserDTO dto) {
        return userRepository.findById(id).map(u -> {
            u.setUsername(dto.getUsername());
            u.setEmail(dto.getEmail());
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                u.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            u.setCarbonGoal(dto.getCarbonGoal());
            return modelMapper.map(userRepository.save(u), UserDTO.class);
        });
    }

    public void delete(Long id) { userRepository.deleteById(id); }

    public Optional<User> findEntityByEmail(String email) { return userRepository.findByEmail(email); }
}
