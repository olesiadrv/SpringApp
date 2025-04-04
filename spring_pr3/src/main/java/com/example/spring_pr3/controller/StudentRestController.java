package com.example.spring_pr3.controller;

import com.example.spring_pr3.dto.RequestStudentDTO;
import com.example.spring_pr3.dto.ResponseStudentDTO;
import com.example.spring_pr3.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentRestController {
    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<ResponseStudentDTO>> getAllStudents() {
        log.info("GET /api/students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<ResponseStudentDTO> getStudentById(@PathVariable Long id) {
        log.info("GET /api/students/{}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<ResponseStudentDTO> createStudent(@Valid @RequestBody RequestStudentDTO studentDTO) {
        log.info("POST /api/students - Payload: {}", studentDTO);
        ResponseStudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(201).body(createdStudent);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing student")
    public ResponseEntity<ResponseStudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody RequestStudentDTO studentDTO) {
        log.info("PUT /api/students/{} - Payload: {}", id, studentDTO);
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("DELETE /api/students/{}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}