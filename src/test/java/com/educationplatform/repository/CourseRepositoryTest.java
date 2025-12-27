package com.educationplatform.repository;

import com.educationplatform.model.Course;
import com.educationplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveAndFindCourseById() {
        // Given
        User teacher = new User();
        teacher.setName("Alice Johnson");
        teacher.setEmail("alice@example.com");
        teacher.setRole(User.Role.TEACHER);
        User savedTeacher = userRepository.save(teacher);

        Course course = new Course();
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setTeacher(savedTeacher);

        // When
        Course savedCourse = courseRepository.save(course);
        entityManager.flush();
        entityManager.clear();

        var found = courseRepository.findById(savedCourse.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("Java Programming", found.get().getTitle());
        assertEquals(savedTeacher.getId(), found.get().getTeacher().getId());
    }

    @Test
    public void shouldFindCoursesByTeacherId() {
        // Given
        User teacher = new User();
        teacher.setName("Bob Smith");
        teacher.setEmail("bob@example.com");
        teacher.setRole(User.Role.TEACHER);
        User savedTeacher = userRepository.save(teacher);

        Course course1 = new Course();
        course1.setTitle("Spring Boot");
        course1.setDescription("Getting started with Spring");
        course1.setTeacher(savedTeacher);

        Course course2 = new Course();
        course2.setTitle("Hibernate");
        course2.setDescription("Master ORM with Hibernate");
        course2.setTeacher(savedTeacher);

        courseRepository.save(course1);
        courseRepository.save(course2);
        entityManager.flush();
        entityManager.clear();

        // When
        List<Course> courses = courseRepository.findByTeacherId(savedTeacher.getId());

        // Then
        assertEquals(2, courses.size());
        assertTrue(courses.stream().anyMatch(c -> c.getTitle().equals("Spring Boot")));
        assertTrue(courses.stream().anyMatch(c -> c.getTitle().equals("Hibernate")));
    }

    @Test
    public void shouldNotFindAnyCoursesForNonExistingTeacher() {
        // When
        List<Course> courses = courseRepository.findByTeacherId(999L);

        // Then
        assertTrue(courses.isEmpty());
    }
}