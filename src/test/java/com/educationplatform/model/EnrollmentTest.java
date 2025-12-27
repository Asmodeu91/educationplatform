package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentTest {

    @Test
    public void testEnrollmentCreation() {
        // Given
        User student = new User();
        Course course = new Course();

        // When
        Enrollment enrollment = new Enrollment(student, course);

        // Then
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertEquals(Enrollment.EnrollmentStatus.ACTIVE, enrollment.getStatus());
        assertNotNull(enrollment.getEnrollDate());
        assertNull(enrollment.getCompletedAt());
        assertNull(enrollment.getId());
    }

    @Test
    public void testEnrollmentSettersAndGetters() {
        // Given
        Enrollment enrollment = new Enrollment();
        User student = new User();
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime completedAt = now.plusDays(30);

        // When
        enrollment.setId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(Enrollment.EnrollmentStatus.COMPLETED);
        enrollment.setEnrollDate(now);
        enrollment.setCompletedAt(completedAt);

        // Then
        assertEquals(1L, enrollment.getId());
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertEquals(Enrollment.EnrollmentStatus.COMPLETED, enrollment.getStatus());
        assertEquals(now, enrollment.getEnrollDate());
        assertEquals(completedAt, enrollment.getCompletedAt());
    }

    @Test
    public void testEnrollmentStatusValues() {
        // When
        Enrollment.EnrollmentStatus[] statuses = Enrollment.EnrollmentStatus.values();

        // Then
        assertEquals(3, statuses.length);
        assertEquals(Enrollment.EnrollmentStatus.ACTIVE, statuses[0]);
        assertEquals(Enrollment.EnrollmentStatus.COMPLETED, statuses[1]);
        assertEquals(Enrollment.EnrollmentStatus.DROPPED, statuses[2]);
    }
}