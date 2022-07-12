package com.example.javastudentapi.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
    void successfullyGetSingleStudent() {
        Student student = new Student("Test Username",
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);
        student.setId(1L);

        when(testStudentRepository.existsById(student.getId())).thenReturn(true);

        testStudentService.getSingleStudent(student.getId());
        verify(testStudentRepository).findById(student.getId());


    }

    @Test
    void failToGetSingleStudent(){
        long studentId = 1L;

        assertThatThrownBy(() -> testStudentService.getSingleStudent(studentId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exist");
    }
    @Test
    void successfullyAddNewStudent() {
        Student student = new Student("Test Username",
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);

        testStudentService.addNewStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(testStudentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void FailAddNewStudent() {
        Student student = new Student("Test Username",
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);

        given(testStudentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));

        assertThatThrownBy(() -> testStudentService.addNewStudent(student))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("The email provided is already taken");

        verify(testStudentRepository, never()).save(any());

    }

    @Test
    void successfullyDeleteStudent() {
        Student student = new Student("Test Username",
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);
        student.setId(1L);

        when(testStudentRepository.existsById(student.getId())).thenReturn(true);

        testStudentService.deleteStudent(student.getId());

        verify(testStudentRepository).deleteById(student.getId());
    }
    @Test
    void failToDeleteStudent() {
        long studentId = 1L;

        assertThatThrownBy(() -> testStudentService.deleteStudent(studentId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exist");

    }

    @Test
    void updateStudent() {
        Student student = new Student("Test Username",
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        student.setAge(20);
        student.setId(1L);

        String newEmail = "";
        String newName = "Tested Username";

        Student newStudent = new Student(newName,
                "test@email.com",
                LocalDate.parse("1986-08-02")
        );
        newStudent.setAge(20);
        newStudent.setId(1L);

        given(testStudentRepository.findById(student.getId())).willReturn(Optional.of(student));
        given(testStudentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(new Student()));

        Student stud = testStudentService.updateStudent(student.getId(), newName, newEmail);

        assertThat(stud.getName()).isEqualTo(newStudent.getName());
        verify(testStudentRepository).findById(student.getId());
    }

    @Test
    void failToUpdateStudent() {
        long studentId = 1L;
        String name = "Failed Case";
        String email = "should@fail.com";
        assertThatThrownBy(() -> testStudentService.updateStudent(studentId, name, email))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exist");
    }
}