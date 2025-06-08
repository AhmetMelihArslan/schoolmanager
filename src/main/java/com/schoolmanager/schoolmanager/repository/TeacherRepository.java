package com.schoolmanager.schoolmanager.repository;

import com.schoolmanager.schoolmanager.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Gerekirse ek sorgular ekleyebilirsin
}
