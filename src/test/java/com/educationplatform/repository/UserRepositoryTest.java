package com.educationplatform.repository;

import com.educationplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveAndFindUserByEmail() {
        // Given
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setRole(User.Role.STUDENT);

        // When
        User savedUser = userRepository.save(user);
        entityManager.flush();
        entityManager.clear();

        Optional<User> found = userRepository.findByEmail("john.doe@example.com");

        // Then
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
        assertEquals(User.Role.STUDENT, found.get().getRole());
    }

    @Test
    public void shouldNotFindNonExistingUser() {
        // When
        Optional<User> found = userRepository.findByEmail("nonexistent@example.com");

        // Then
        assertFalse(found.isPresent());
    }
}