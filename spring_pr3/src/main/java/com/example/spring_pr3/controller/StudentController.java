package com.example.spring_pr3.controller;

import com.example.spring_pr3.dto.RequestStudentDTO;
import com.example.spring_pr3.dto.ResponseStudentDTO;
import com.example.spring_pr3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        List<ResponseStudentDTO> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students"; // students.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new RequestStudentDTO());
        return "add-student"; // add-student.html
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("student") RequestStudentDTO studentDTO,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-student";
        }
        studentService.createStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ResponseStudentDTO student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student"; // edit-student.html
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") RequestStudentDTO studentDTO,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-student";
        }
        studentService.updateStudent(id, studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
