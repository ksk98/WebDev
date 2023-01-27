package com.project.ImageGallery.controller;

import com.project.ImageGallery.model.User;
import com.project.ImageGallery.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BaseController {
    protected User verifyUserForOwnerThrowUnauthorized(long targetUserId, UserRepository userRepository) {
        User caller = userRepository.findByUsername(
                ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUsername());

        if (caller.getId().equals(targetUserId))
            return caller;

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
