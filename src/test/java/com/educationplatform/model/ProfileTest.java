package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    @Test
    public void testProfileCreation() {
        // Given
        User user = new User();
        String bio = "Software developer passionate about education";
        String avatarUrl = "https://example.com/avatar.jpg";
        String phoneNumber = "+1234567890";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        // When
        Profile profile = new Profile(user, bio, avatarUrl, phoneNumber, dateOfBirth);

        // Then
        assertEquals(user, profile.getUser());
        assertEquals(bio, profile.getBio());
        assertEquals(avatarUrl, profile.getAvatarUrl());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(dateOfBirth, profile.getDateOfBirth());
        assertNull(profile.getId());
    }

    @Test
    public void testProfileSettersAndGetters() {
        // Given
        Profile profile = new Profile();
        User user = new User();
        LocalDate dateOfBirth = LocalDate.of(1995, 5, 15);

        // When
        profile.setId(1L);
        profile.setUser(user);
        profile.setBio("Updated bio");
        profile.setAvatarUrl("https://example.com/new-avatar.jpg");
        profile.setPhoneNumber("+0987654321");
        profile.setDateOfBirth(dateOfBirth);

        // Then
        assertEquals(1L, profile.getId());
        assertEquals(user, profile.getUser());
        assertEquals("Updated bio", profile.getBio());
        assertEquals("https://example.com/new-avatar.jpg", profile.getAvatarUrl());
        assertEquals("+0987654321", profile.getPhoneNumber());
        assertEquals(dateOfBirth, profile.getDateOfBirth());
    }
}