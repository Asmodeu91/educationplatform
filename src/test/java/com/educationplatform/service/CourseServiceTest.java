package com.educationplatform.service;

import com.educationplatform.model.Course;
import com.educationplatform.model.User;
import com.educationplatform.repository.CourseRepository;
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
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void shouldCreateCourse() {
        // Given
        User teacher = new User("Alice", "alice@example.com", User.Role.TEACHER);
        teacher.setId(1L);

        Course course = new Course("Java Course", "Learn Java", null, teacher);
        Course savedCourse = new Course("Java Course", "Learn Java", null, teacher);
        savedCourse.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(courseRepository.save(any(Course.class))).thenReturn(savedCourse);

        // When
        Course result = courseService.createCourse(course);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Course", result.getTitle());
        assertEquals(teacher, result.getTeacher());
        verify(userRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void shouldFindCourseById() {
        // Given
        User teacher = new User("Bob", "bob@example.com", User.Role.TEACHER);
        Course course = new Course("Spring Course", "Learn Spring", null, teacher);
        course.setId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // When
        Optional<Course> result = courseService.getCourseById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Spring Course", result.get().getTitle());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldReturnAllCourses() {
        // Given
        User teacher = new User("Carol", "carol@example.com", User.Role.TEACHER);
        Course course1 = new Course("Course1", "Desc1", null, teacher);
        Course course2 = new Course("Course2", "Desc2", null, teacher);

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        // When
        List<Course> result = courseService.getAllCourses();

        // Then
        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void shouldUpdateCourse() {
        // Given
        User teacher = new User("Dave", "dave@example.com", User.Role.TEACHER);
        teacher.setId(1L);

        Course existingCourse = new Course("Old Title", "Old Desc", null, teacher);
        existingCourse.setId(1L);

        User newTeacher = new User("Eve", "eve@example.com", User.Role.TEACHER);
        newTeacher.setId(2L);

        Course courseDetails = new Course("New Title", "New Desc", null, newTeacher);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(userRepository.findById(2L)).thenReturn(Optional.of(newTeacher));
        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Course result = courseService.updateCourse(1L, courseDetails);

        // Then
        assertEquals("New Title", result.getTitle());
        assertEquals("New Desc", result.getDescription());
        assertEquals(newTeacher, result.getTeacher());
        verify(courseRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void shouldDeleteCourse() {
        // Given
        User teacher = new User("Frank", "frank@example.com", User.Role.TEACHER);
        Course course = new Course("To Delete", "Delete Me", null, teacher);
        course.setId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).delete(course);

        // When
        courseService.deleteCourse(1L);

        // Then
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).delete(course);
    }

    @Test
    public void shouldFindCoursesByTeacherId() {
        // Given
        User teacher = new User("Grace", "grace@example.com", User.Role.TEACHER);
        teacher.setId(1L);

        Course course1 = new Course("Course1", "Desc1", null, teacher);
        Course course2 = new Course("Course2", "Desc2", null, teacher);

        when(courseRepository.findByTeacherId(1L)).thenReturn(Arrays.asList(course1, course2));

        // When
        List<Course> result = courseService.getCoursesByTeacherId(1L);

        // Then
        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findByTeacherId(1L);
    }
}