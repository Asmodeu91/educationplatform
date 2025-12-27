package com.educationplatform.service;

import com.educationplatform.model.Lesson;
import com.educationplatform.model.Module;
import com.educationplatform.repository.LessonRepository;
import com.educationplatform.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public Lesson createLesson(Lesson lesson) {
        if (lesson.getModule() != null && lesson.getModule().getId() != null) {
            Module module = moduleRepository.findById(lesson.getModule().getId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            lesson.setModule(module);
        }
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
        lesson.setTitle(lessonDetails.getTitle());
        lesson.setContent(lessonDetails.getContent());
        lesson.setVideoUrl(lessonDetails.getVideoUrl());
        lesson.setResourcesUrl(lessonDetails.getResourcesUrl());

        if (lessonDetails.getModule() != null && lessonDetails.getModule().getId() != null) {
            Module module = moduleRepository.findById(lessonDetails.getModule().getId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            lesson.setModule(module);
        }

        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
        lessonRepository.delete(lesson);
    }

    public List<Lesson> getLessonsByModuleId(Long moduleId) {
        return lessonRepository.findByModuleId(moduleId);
    }
}