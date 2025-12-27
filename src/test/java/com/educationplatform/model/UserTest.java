package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        // Given
        String name = "John Doe";
        String email = "john.doe@example.com";
        User.Role role = User.Role.STUDENT;

        // When
        User user = new User(name, email, role);

        // Then
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
        assertNull(user.getId());
        assertNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
    }

    @Test
    public void testUserSettersAndGetters() {
        // Given
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        // When
        user.setId(1L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setRole(User.Role.TEACHER);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // Then
        assertEquals(1L, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals(User.Role.TEACHER, user.getRole());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    public void testUserRoleValues() {
        // When
        User.Role[] roles = User.Role.values();

        // Then
        assertEquals(3, roles.length);
        assertEquals(User.Role.STUDENT, roles[0]);
        assertEquals(User.Role.TEACHER, roles[1]);
        assertEquals(User.Role.ADMIN, roles[2]);
    }
}