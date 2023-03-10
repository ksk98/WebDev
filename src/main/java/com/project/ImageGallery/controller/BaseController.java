package com.project.ImageGallery.controller;

import com.project.ImageGallery.model.User;
import com.project.ImageGallery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class BaseController {
    private final UserRepository userRepository;

    protected User verifyUserForOwnerThrowUnauthorized(long targetUserId) {
        User caller = getCaller();

        if (caller.getId().equals(targetUserId))
            return caller;

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    protected User getCaller() {
        return userRepository.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

    protected User getCallerThrowUnauthorizedIfNull() {
        User caller = getCaller();

        if (caller == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return caller;
    }
}
