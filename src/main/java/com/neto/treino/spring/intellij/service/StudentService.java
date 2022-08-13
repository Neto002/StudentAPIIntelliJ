package com.neto.treino.spring.intellij.service;

import com.neto.treino.spring.intellij.model.Student;
import com.neto.treino.spring.intellij.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        studentRepository.save(student);
        return student;
    }

    public Optional<Student> deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new IllegalStateException("Student not found");
        }

        studentRepository.deleteById(id);

        return student;
    }
}
