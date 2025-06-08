package com.schoolmanager.schoolmanager.service;

import com.schoolmanager.schoolmanager.entity.Course;
import com.schoolmanager.schoolmanager.exception.ResourceNotFoundException;
import com.schoolmanager.schoolmanager.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedData) {
        Course existing = getCourseById(id);
        existing.setName(updatedData.getName());
        existing.setCredit(updatedData.getCredit());
        return courseRepository.save(existing);
    }

    public void deleteCourse(Long id) {
        Course existing = getCourseById(id);
        courseRepository.delete(existing);
    }
}
