package com.example.courseproject.repository;

import com.example.courseproject.model.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.courseproject.service.CourseEnrollmentService;  // Import CourseEnrollmentService
import com.example.courseproject.model.CourseEnrollment;  // Import CourseEnrollment model

import java.util.List;


@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    List<CourseEnrollment> findByUsername(String username);
    // JpaRepository already provides a findAll() method
}
