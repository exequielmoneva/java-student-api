package com.example.javastudentapi.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository testStudentRepository;

    @AfterEach
    void tearDown(){
        testStudentRepository.deleteAll();
    }

    @Test
    void testSuccessfullyFindStudentByEmail() {
        String email = "test@email.com";
        Student student = new Student("Test Username",
                email,
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);
        testStudentRepository.save(student);


        Optional<Student> response = testStudentRepository.findStudentByEmail(email);
        Student stud;
        stud = response.orElse(null);


        assert stud != null;
        assertThat(stud.getEmail()).isEqualTo(email);
        assertThat(stud.getName()).isEqualTo(student.getName());
        assertThat(stud.getDob()).isEqualTo(student.getDob());
        assertThat(stud.getAge()).isEqualTo(student.getAge());

    }
    @Test
    void testDoesNotFindStudentByEmail() {
        String email = "test@email.com";

        Optional<Student> response = testStudentRepository.findStudentByEmail(email);
        Student stud;
        stud = response.orElse(null);

        assertThat(stud).isNull();

    }
}
