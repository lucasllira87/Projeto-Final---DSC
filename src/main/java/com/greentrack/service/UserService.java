package com.greentrack.service;
import com.greentrack.dto.*;
import com.greentrack.entity.User;
import com.greentrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(u -> UserDTO.builder()
                .id(u.getId()).username(u.getUsername()).email(u.getEmail()).carbonGoal(u.getCarbonGoal()).role(u.getRole()).build())
                .collect(Collectors.toList());
    }
    public UserDTO findById(Long id){
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.builder().id(u.getId()).username(u.getUsername()).email(u.getEmail()).carbonGoal(u.getCarbonGoal()).role(u.getRole()).build();
    }
}
