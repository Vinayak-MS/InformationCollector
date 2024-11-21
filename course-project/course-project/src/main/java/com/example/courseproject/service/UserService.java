package com.example.courseproject.service;

import com.example.courseproject.model.Certificate;
import com.example.courseproject.model.CourseEnrollment;
import com.example.courseproject.model.User;
import com.example.courseproject.repository.CertificateRepository;
import com.example.courseproject.repository.CourseEnrollmentRepository;
import com.example.courseproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    // Validate regular user
    public boolean validateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password) != null;
    }

    // Validate admin user
    public boolean validateAdmin(String username, String password) {
        // Fetch the user by username and password
        User user = userRepository.findByUsernameAndPassword(username, password);

        // Return true if the user exists and has the role 'ADMIN'
        return user != null && "admins".equals(user.getRole());
    }


    public void saveCourseEnrollment(CourseEnrollment enrollment) {
        courseEnrollmentRepository.save(enrollment);
    }

    public void saveCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    public List<CourseEnrollment> getAllStudentData() {
        return courseEnrollmentRepository.findAll();
    }

}
