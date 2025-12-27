package com.educationplatform.service;

import com.educationplatform.model.User;
import com.educationplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUser() {
        // Given
        User user = new User("John Doe", "john.doe@example.com", User.Role.STUDENT);
        User savedUser = new User("John Doe", "john.doe@example.com", User.Role.STUDENT);
        savedUser.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When
        User result = userService.createUser(user);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals(User.Role.STUDENT, result.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldFindUserById() {
        // Given
        User user = new User("Jane Doe", "jane.doe@example.com", User.Role.TEACHER);
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.getUserById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Jane Doe", result.get().getName());
        assertEquals(User.Role.TEACHER, result.get().getRole());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldReturnAllUsers() {
        // Given
        User user1 = new User("User1", "user1@example.com", User.Role.STUDENT);
        User user2 = new User("User2", "user2@example.com", User.Role.TEACHER);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldUpdateUser() {
        // Given
        User existingUser = new User("Old Name", "old@example.com", User.Role.STUDENT);
        existingUser.setId(1L);

        User userDetails = new User("New Name", "new@example.com", User.Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        User result = userService.updateUser(1L, userDetails);

        // Then
        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals(User.Role.ADMIN, result.getRole());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldDeleteUser() {
        // Given
        User user = new User("To Delete", "delete@example.com", User.Role.STUDENT);
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        // When
        userService.deleteUser(1L);

        // Then
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }
}