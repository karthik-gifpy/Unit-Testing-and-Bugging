package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> getUser(String id) {
        logger.info("Fetching user with id={}", id);
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            logger.debug("User found: {}", user.get().getName());
        } else {
            logger.warn("User not found: {}", id);
        }
        return user;
    }
}
