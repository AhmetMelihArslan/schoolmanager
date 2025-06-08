package com.schoolmanager.schoolmanager.repository;

import com.schoolmanager.schoolmanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
}
