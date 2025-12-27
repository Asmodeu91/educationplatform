package com.educationplatform.service;

import com.educationplatform.model.Profile;
import com.educationplatform.model.User;
import com.educationplatform.repository.ProfileRepository;
import com.educationplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile createProfile(Long userId, String bio, String avatarUrl, String phoneNumber, LocalDate dateOfBirth) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Profile> existing = profileRepository.findByUserId(userId);
        if (existing.isPresent()) {
            throw new RuntimeException("Profile already exists for this user");
        }

        Profile profile = new Profile(user, bio, avatarUrl, phoneNumber, dateOfBirth);
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long userId, String bio, String avatarUrl, String phoneNumber, LocalDate dateOfBirth) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user " + userId));

        if (bio != null) {
            profile.setBio(bio);
        }
        if (avatarUrl != null) {
            profile.setAvatarUrl(avatarUrl);
        }
        if (phoneNumber != null) {
            profile.setPhoneNumber(phoneNumber);
        }
        if (dateOfBirth != null) {
            profile.setDateOfBirth(dateOfBirth);
        }

        return profileRepository.save(profile);
    }

    public void deleteProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user " + userId));
        profileRepository.delete(profile);
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }
}