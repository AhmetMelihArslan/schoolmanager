package com.schoolmanager.schoolmanager.repository;

import com.schoolmanager.schoolmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassroom(String classroom);
}