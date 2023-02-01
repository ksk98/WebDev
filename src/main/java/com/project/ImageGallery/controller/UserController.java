package com.project.ImageGallery.controller;

import com.project.ImageGallery.model.User;
import com.project.ImageGallery.schema.LoginResponseSchema;
import com.project.ImageGallery.schema.MessageResponseSchema;
import com.project.ImageGallery.schema.UserSchema;
import com.project.ImageGallery.service.TokenService;
import com.project.ImageGallery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponseSchema> register(@RequestBody UserSchema payload) {
        userService.createUser(payload.getUsername(), payload.getPassword());

        return new ResponseEntity<>(MessageResponseSchema.builder().message("Success.").success(true).build(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseSchema> login(Authentication authentication) {
        String token = tokenService.generateToken(authentication);

        return new ResponseEntity<>(LoginResponseSchema.builder().username(authentication.getName()).token(token).build(), HttpStatus.OK);
    }
}
