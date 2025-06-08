package com.schoolmanager.schoolmanager.service;

import com.schoolmanager.schoolmanager.entity.Student;
import com.schoolmanager.schoolmanager.exception.ResourceNotFoundException;
import com.schoolmanager.schoolmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedData) {
        Student existing = getStudentById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setClassroom(updatedData.getClassroom());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }

    public List<Student> getStudentsByClassroom(String classroom) {
        return studentRepository.findByClassroom(classroom);
    }
}
