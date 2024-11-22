package com.example.courseproject.service;

import com.example.courseproject.model.CourseEnrollment;
import com.example.courseproject.repository.CourseEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CourseEnrollmentService {

    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;

    // Save a new course enrollment
    public void saveCourseEnrollment(CourseEnrollment enrollment) {
        courseEnrollmentRepository.save(enrollment);  // Save the entity in the database
    }

    // Fetch all course enrollments
    public List<CourseEnrollment> getAllEnrollments() {
        return courseEnrollmentRepository.findAll();
    }

    // Add this method in CourseEnrollmentService
    public CourseEnrollment getCourseEnrollmentById(Long studentId) {
        return courseEnrollmentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
    }


    // Fetch course enrollments for a specific user
    public List<CourseEnrollment> getEnrollmentsByUsername(String username) {
        return courseEnrollmentRepository.findByUsername(username);
    }

}
