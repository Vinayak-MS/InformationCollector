package com.example.courseproject.controller;

import com.example.courseproject.model.CourseEnrollment;
import com.example.courseproject.model.Certificate;
import com.example.courseproject.service.CourseEnrollmentService;
import com.example.courseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseEnrollmentService courseEnrollmentService;  // Injecting CourseEnrollmentService

    // Display the login page
    @GetMapping("/login")
    public String studentLogin() {
        return "student_login";
    }

    // Handle student login (POST request)
    @PostMapping("/login")
    public String studentLoginPost(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateUser(username, password)) {
            model.addAttribute("username", username);
            return "redirect:/student/dashboard";  // Redirect to dashboard after successful login
        }
        model.addAttribute("error", "Invalid Credentials");
        return "student_login";  // Return to login page if credentials are invalid
    }

    // Show student dashboard
    @GetMapping("/dashboard")
    public String studentDashboard() {
        return "student_dashboard";
    }

    // Show the course enrollment form
    @GetMapping("/course-enrollment")
    public String courseEnrollment() {
        return "course_enrollment";
    }

    // Handle course enrollment submission (POST request)
    @PostMapping("/course-enrollment")
    public String submitCourseEnrollment(@RequestParam String name,
                                         @RequestParam String dob,
                                         @RequestParam String course,
                                         @RequestParam String email,
                                         @RequestParam String phone,
                                         @RequestParam String username,
                                         @RequestParam MultipartFile document) {
        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setName(name);
        enrollment.setCourseEnrolled(course);
        enrollment.setEmail(email);
        enrollment.setPhoneNumber(phone);
        enrollment.setUsername(username);
        try {
            enrollment.setDocument(document);  // This will save the document bytes
        } catch (IOException e) {
            e.printStackTrace();
        }

        courseEnrollmentService.saveCourseEnrollment(enrollment);
        return "redirect:/student/dashboard";
    }


    // Show the upload certificate form
    @GetMapping("/upload-certificate")
    public String uploadCertificate() {
        return "upload_certificate";
    }

    // Handle certificate upload submission
    @PostMapping("/upload-certificate")
    public String submitCertificate(@RequestParam("courseTitle") String courseTitle,
                                    @RequestParam("organization") String organization,
                                    @RequestParam("completionDate") String completionDate,
                                    @RequestParam("certificateNumber") String certificateNumber,
                                    @RequestParam("certificate") MultipartFile file,
                                    @RequestParam("username") String username) {

        Certificate certificate = new Certificate();
        certificate.setCourseTitle(courseTitle);
        certificate.setIssuingOrganization(organization);
        certificate.setDateOfCompletion(completionDate);
        certificate.setCertificateNumber(certificateNumber);
        certificate.setUsername(username);

        try {
            // Handle file details
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            certificate.setFileName(fileName);
            certificate.setFileType(file.getContentType());
            certificate.setFileContent(file.getBytes());
            // Save the certificate to the database
            userService.saveCertificate(certificate);

        } catch (IOException e) {
            e.printStackTrace();
            // Add error handling here if needed
        }

        return "redirect:/student/dashboard";
    }

}

