package com.educationplatform.service;

import com.educationplatform.model.Module;
import com.educationplatform.model.Course;
import com.educationplatform.repository.ModuleRepository;
import com.educationplatform.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public Module createModule(Module module) {
        if (module.getCourse() != null && module.getCourse().getId() != null) {
            Course course = courseRepository.findById(module.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            module.setCourse(course);
        }
        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, Module moduleDetails) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found with id " + id));
        module.setTitle(moduleDetails.getTitle());
        module.setDescription(moduleDetails.getDescription());
        module.setOrderIndex(moduleDetails.getOrderIndex());

        if (moduleDetails.getCourse() != null && moduleDetails.getCourse().getId() != null) {
            Course course = courseRepository.findById(moduleDetails.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            module.setCourse(course);
        }

        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found with id " + id));
        moduleRepository.delete(module);
    }

    public List<Module> getModulesByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }
}