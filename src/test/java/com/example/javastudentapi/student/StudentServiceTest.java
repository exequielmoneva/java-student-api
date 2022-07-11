package com.example.javastudentapi.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock private StudentRepository testStudentRepository;
    private StudentService testStudentService;

    @BeforeEach
    void setUp() {
        testStudentService = new StudentService(testStudentRepository);
    }

    @Test
    void testGetStudents() {
        testStudentService.getStudents();

        verify(testStudentRepository).findAll();
    }

    @Test
    void getSingleStudent() {
    }

    @Test
    void addNewStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }
}