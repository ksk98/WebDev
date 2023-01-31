package com.project.ImageGallery.controller;

import com.project.ImageGallery.schema.UserSchema;
import com.project.ImageGallery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081/", allowedHeaders = "*")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserSchema payload) {
        userService.createUser(payload.getUsername(), payload.getPassword());

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
