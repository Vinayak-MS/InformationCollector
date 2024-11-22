package com.example.courseproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Table(name = "students")
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String name;
    private String courseEnrolled;
    private String email;
    private String phoneNumber;
    private String fileName;  // Original file name
    private String fileType;  // MIME type of the file

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(String courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getDocument() {
        return document_path;
    }

    public void setDocument(byte[] document) {
        this.document_path = document;
    }

    private String username;
    public Long getStudentId() {
        return student_id;
    }
    public void setStudentId(Long studentId) {
        this.student_id = studentId;
    }
    @Getter
    @Lob
    @Column(name = "document_path", columnDefinition = "LONGBLOB")
    private byte[] document_path;  // Store the document as a byte array

    // Getters and setters

    public void setDocument(MultipartFile document) throws IOException {
        this.document_path = document.getBytes();  // Convert MultipartFile to byte array
    }


    // Getter and setter for fileName
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getDocument_path() {
        return document_path;
    }

    public void setDocument_path(byte[] document_path) {
        this.document_path = document_path;
    }
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

    // Getter and setter for fileType
    public String getFileType() {
        return fileType;
    }
    // Getters and setters...
}
