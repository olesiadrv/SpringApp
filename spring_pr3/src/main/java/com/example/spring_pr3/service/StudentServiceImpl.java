package com.example.spring_pr3.service;

import com.example.spring_pr3.dto.RequestStudentDTO;
import com.example.spring_pr3.dto.ResponseStudentDTO;
import com.example.spring_pr3.model.Student;
import com.example.spring_pr3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ResponseStudentDTO> getAllStudents() {
        log.info("Отримання всіх студентів");
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, ResponseStudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseStudentDTO getStudentById(Long id) {
        log.info("Отримання студента за ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, ResponseStudentDTO.class);
    }

    @Override
    public ResponseStudentDTO createStudent(RequestStudentDTO studentDTO) {
        log.info("Створення нового студента: {}", studentDTO);
        Student student = modelMapper.map(studentDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, ResponseStudentDTO.class);
    }

    @Override
    public ResponseStudentDTO updateStudent(Long id, RequestStudentDTO studentDTO) {
        log.info("Оновлення студента з ID: {}", id);
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        modelMapper.map(studentDTO, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, ResponseStudentDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Видалення студента з ID: {}", id);

        studentRepository.deleteById(id);
    }
}
