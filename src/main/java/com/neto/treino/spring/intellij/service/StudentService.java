package com.neto.treino.spring.intellij.service;

import com.neto.treino.spring.intellij.model.Student;
import com.neto.treino.spring.intellij.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                String.format("Student with id %d not found", id)));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException(String.format("Email %s already in use by another student", email));
            }

            student.setEmail(email);
        }

        return student;
    }

    @Transactional
    public Student updateStudent(Long id, Student studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                String.format("Student with id %d not found", id)));

        if (studentRequest.getName() != null && studentRequest.getName().length() > 0 &&
                !Objects.equals(student.getName(), studentRequest.getName())) {
            student.setName(studentRequest.getName());
        }

        if (studentRequest.getEmail() != null && studentRequest.getEmail().length() > 0 &&
                !Objects.equals(student.getEmail(), studentRequest.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentRequest.getEmail());

            if (studentOptional.isPresent()) {
                throw new IllegalStateException(String.format("Email %s already in use by another student",
                        studentRequest.getEmail()));
            }

            student.setEmail(studentRequest.getEmail());
        }

        return student;
    }
}
