package com.swe645.assignment3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe645.assignment3.StudentDTO;

public interface StudentRepository extends JpaRepository<StudentDTO, Long> {
}
