package com.educationplatform.controller;

import com.educationplatform.model.Profile;
import com.educationplatform.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(profile -> ResponseEntity.ok().body(profile))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId)
                .map(profile -> ResponseEntity.ok().body(profile))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Profile> createProfile(
            @PathVariable Long userId,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) String avatarUrl,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate dateOfBirth) {
        Profile createdProfile = profileService.createProfile(userId, bio, avatarUrl, phoneNumber, dateOfBirth);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long userId,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) String avatarUrl,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate dateOfBirth) {
        Profile updatedProfile = profileService.updateProfile(userId, bio, avatarUrl, phoneNumber, dateOfBirth);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        profileService.deleteProfile(userId);
        return ResponseEntity.noContent().build();
    }
}