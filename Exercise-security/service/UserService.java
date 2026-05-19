package com.study.of.security.lies.service;

import com.study.of.security.lies.dto.UserDTO;
import com.study.of.security.lies.entity.Users;
import com.study.of.security.lies.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(UserDTO userDTO){
            Users users = new Users();
            users.setName(userDTO.getName());
            users.setEmail(userDTO.getEmail());
            users.setPassword(
                    passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(users);
    }

    @Transactional
    public void deleteUser(){

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}


