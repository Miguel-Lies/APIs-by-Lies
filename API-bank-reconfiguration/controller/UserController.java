package com.enterprise.bank_lies.controller;

import com.enterprise.bank_lies.dto.UserDTO;
import com.enterprise.bank_lies.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> toCreateUser(
            @RequestBody UserDTO userDTO) {
         userService.toCreateAccount(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        userService.deleteInformation(token);
        return ResponseEntity.ok().build();
    }
}
