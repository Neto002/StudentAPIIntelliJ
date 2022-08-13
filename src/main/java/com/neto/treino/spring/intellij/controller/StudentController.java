package com.neto.treino.spring.intellij.controller;

import com.neto.treino.spring.intellij.model.Student;
import com.neto.treino.spring.intellij.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public Optional<Student> deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}
