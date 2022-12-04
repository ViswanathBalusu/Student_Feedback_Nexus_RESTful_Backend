package com.swe645.assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swe645.assignment3.StudentDTO;
import com.swe645.assignment3.StudentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/responses")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        try {
            List<StudentDTO> students = new ArrayList<StudentDTO>();

            studentRepository.findAll().forEach(students::add);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/submitResponse")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        try {
            StudentDTO _student = studentRepository
                    .save(student);
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}