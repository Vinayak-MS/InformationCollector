package com.example.courseproject.controller;

import com.example.courseproject.model.CourseEnrollment;
import com.example.courseproject.model.*;
import com.example.courseproject.service.CertificateService;
import com.example.courseproject.service.CourseEnrollmentService;
import com.example.courseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseEnrollmentService courseEnrollmentService;
    @Autowired
    private CertificateService certificateService; // Inject CertificateService

    // Display the admin login page
    @GetMapping("/login")
    public String adminLogin() {
        return "admin_login";
    }

    // Handle admin login form submission (POST request)
    @PostMapping("/login")
    public String adminLoginPost(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateAdmin(username, password)) {
            model.addAttribute("students", userService.getAllStudentData());
            return "redirect:/admin/dashboard"; // Ensure redirect to /admin/dashboard
        }
        model.addAttribute("error", "Invalid Credentials");
        return "admin_login"; // Return to the login page with error
    }

    // Display the admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Use the appropriate service based on your requirement
        List<CourseEnrollment> students = userService.getAllStudentData();
        model.addAttribute("students", students);
        return "admin_dashboard";
    }

    // Download a specific document from CourseEnrollment
//    @GetMapping("/download-document/{id}")
//    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
//        System.out.println("Requested Course Enrollment Document ID: " + id);
//
//        try {
//            // Fetch the CourseEnrollment record for the given student ID
//            CourseEnrollment enrollment = courseEnrollmentService.getCourseEnrollmentById(id);
//
//            if (enrollment == null || enrollment.getDocument() == null) {
//                System.out.println("Document not found for ID: " + id);
//                return ResponseEntity.notFound().build();
//            }
//
//            // Set the appropriate headers for file download
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"document_" + id + "\"");
//            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf"); // Assuming PDF, change if needed
//
//            System.out.println("Returning file for download with ID: " + id);
//            return new ResponseEntity<>(enrollment.getDocument(), headers, HttpStatus.OK);
//
//        } catch (RuntimeException e) {
//            System.out.println("Error fetching document for ID: " + id + ", error: " + e.getMessage());
//            return ResponseEntity.notFound().build();
//        }
//    }
    // View student details by ID
    @GetMapping("/view-student/{id}")
    public String viewStudentDetail(@PathVariable Long id, Model model) {
        // Fetch the student details using the service layer
        CourseEnrollment student = courseEnrollmentService.getCourseEnrollmentById(id);

        // Add the student details to the model to be used in the Thymeleaf template
        model.addAttribute("student", student);

        // Return the view page for displaying student details
        return "student_detail";
    }

    @GetMapping("/download-document/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        System.out.println("Requested Document ID: " + id);

        try {
            // Fetch the document and its metadata for the given student ID
            CourseEnrollment enrollment = courseEnrollmentService.getCourseEnrollmentById(id);

            byte[] fileContent = enrollment.getDocument();
            if (fileContent == null) {
                System.out.println("File content is null for ID: " + id);
                return ResponseEntity.notFound().build();
            }

            // Use original file name and MIME type if available
            String fileName = enrollment.getFileName() != null ? enrollment.getFileName() : "document_" + id;
            String contentType = enrollment.getFileType() != null ? enrollment.getFileType() : "application/octet-stream";

            // Set the appropriate headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".pdf\"");
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            System.out.println("Returning file for download with ID: " + id);
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            System.out.println("Error fetching document for ID: " + id + ", error: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/download-certificate/{id}")
    public ResponseEntity<?> downloadCertificate(@PathVariable Long id) {
        try {
            // Fetch the certificate for the given student ID
            Certificate certificate = certificateService.getCertificateById(id);

            if (certificate == null || certificate.getFileContent() == null) {
                // If no certificate is found, return an error message
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "text/html");
                String errorMessage = """
    <html>
        <head>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f8f9fa;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    margin: 0;
                }
                .error-container {
                    background-color: #ffffff;
                    padding: 40px;
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    text-align: center;
                    max-width: 600px;
                    width: 100%;
                }
                .error-heading {
                    font-size: 24px;
                    color: #dc3545; /* Red color for error */
                    margin-bottom: 20px;
                }
                .error-icon {
                    font-size: 48px;
                    color: #dc3545;
                    margin-bottom: 10px;
                }
                .error-message {
                    font-size: 16px;
                    color: #6c757d;
                    margin-bottom: 20px;
                }
                .btn-back {
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #007bff;
                    color: #ffffff;
                    text-decoration: none;
                    border-radius: 5px;
                    font-size: 16px;
                }
                .btn-back:hover {
                    background-color: #0056b3;
                }
            </style>
        </head>
        <body>
            <div class="error-container">
                <div class="error-icon">&#9888;</div> <!-- Unicode warning icon -->
                <div class="error-heading">Certificate Not Found</div>
                <div class="error-message">We couldn't find a certificate for the provided ID. Please check the ID and try again.</div>
                <a href="/admin/dashboard" class="btn-back">Go Back to Dashboard</a>
            </div>
        </body>
    </html>
""";
                return new ResponseEntity<>(errorMessage, headers, HttpStatus.NOT_FOUND);
            }

            // Get file details
            String fileName = certificate.getFileName() != null ? certificate.getFileName() : "certificate_" + id;
            String contentType = certificate.getFileType() != null ? certificate.getFileType() : "application/octet-stream";

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            return new ResponseEntity<>(certificate.getFileContent(), headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            // In case of any other exceptions, return a general error message
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "text/html");
            String errorMessage = "<html><body><h3>An unexpected error occurred while processing the request.</h3></body></html>";
            return new ResponseEntity<>(errorMessage, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/view-student-certificate/{id}")
    public String viewStudentCertificate(@PathVariable Long id, Model model) {
        System.out.println("Fetching certificate for ID: " + id);  // Debug log

        Certificate certificate = certificateService.getCertificateById(id);

        if (certificate == null) {
            model.addAttribute("error", "No certificate found for the provided ID.");
            return "error_page";  // Ensure that `error_page.html` exists
        }

        model.addAttribute("certificate", certificate);
        return "view_student_certificate";  // Ensure that `view_student_certificate.html` exists


    }
}
