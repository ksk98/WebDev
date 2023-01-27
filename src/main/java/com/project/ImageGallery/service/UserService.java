package com.project.ImageGallery.service;

import com.project.ImageGallery.model.User;
import com.project.ImageGallery.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String login, String password) {
        if (login == null || password == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (userRepository.existsByUsername(login))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken.");

        User user = User.builder().username(login).password(passwordEncoder.encode(password)).build();
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        return user;
    }
}
