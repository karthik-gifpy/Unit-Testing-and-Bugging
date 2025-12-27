package com.example.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.model.User;
import com.example.repository.UserRepository;

class UserServiceTest {
    private UserRepository repository;
    private UserService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    void getUser_returnsUserWhenFound() {
        User u = new User("1", "Alice");
        when(repository.findById("1")).thenReturn(Optional.of(u));

        Optional<User> result = service.getUser("1");

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
        verify(repository, times(1)).findById("1");
    }

    @Test
    void getUser_returnsEmptyWhenNotFound() {
        when(repository.findById("2")).thenReturn(Optional.empty());

        Optional<User> result = service.getUser("2");

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById("2");
    }
}
