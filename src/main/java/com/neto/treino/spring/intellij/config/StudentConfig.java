package com.neto.treino.spring.intellij.config;

import com.neto.treino.spring.intellij.model.Student;
import com.neto.treino.spring.intellij.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    /*@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student neto = new Student(
                    "Neto",
                    "antonio@neto.com",
                    LocalDate.of(2002, 8, 9)
            );

            Student test = new Student(
                    "Test",
                    "test@test.com",
                    LocalDate.of(1, 1, 1)
            );

            studentRepository.saveAll(List.of(neto, test));
        };
    }*/
}
