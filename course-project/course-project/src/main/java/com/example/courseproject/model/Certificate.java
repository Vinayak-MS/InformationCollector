package com.example.courseproject.model;

import jakarta.persistence.*;

//@Entity
//public class Certificate {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String username; // Ensure this field exists if you're referencing it
//    private String courseTitle;
//    private String issuingOrganization;
//    private String dateOfCompletion;
//    private String certificateNumber;
//    private String certificatePath; // Path where the certificate is stored
//    private String fileName;        // Original file name
//    private String fileType;        // MIME type
//    private byte[] fileContent;     // Actual file content
//
//    // Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getCourseTitle() {
//        return courseTitle;
//    }
//
//    public void setCourseTitle(String courseTitle) {
//        this.courseTitle = courseTitle;
//    }
//
//    public void setFileContent(byte[] fileContent) {
//        this.fileContent = fileContent;
//    }
//
//    public String getIssuingOrganization() {
//        return issuingOrganization;
//    }
//
//    public void setIssuingOrganization(String issuingOrganization) {
//        this.issuingOrganization = issuingOrganization;
//    }
//
//    public String getDateOfCompletion() {
//        return dateOfCompletion;
//    }
//
//    public void setDateOfCompletion(String dateOfCompletion) {
//        this.dateOfCompletion = dateOfCompletion;
//    }
//
//    public String getCertificateNumber() {
//        return certificateNumber;
//    }
//
//    public void setCertificateNumber(String certificateNumber) {
//        this.certificateNumber = certificateNumber;
//    }
//
//    public String getCertificatePath() {
//        return certificatePath;
//    }
//
//    public void setCertificatePath(String certificatePath) {
//        this.certificatePath = certificatePath;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getFileType() {
//        return fileType;
//    }
//
//    public void setFileType(String fileType) {
//        this.fileType = fileType;
//    }
//
//    public byte[] getFileContent() {
//        return fileContent;
//    }
//
//}
@Entity
public class Certificate {

    @Id
    @GeneratedValue
    private Long id;
    private String username; // Ensure this field exists if you're referencing it
    private String courseTitle;
    private String issuingOrganization;
    private String dateOfCompletion;
    private String certificateNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    private String fileName;        // Original file name
    private String fileType;        // MIME type
    @Lob // Use @Lob for storing large files
    @Column(name = "file_content", columnDefinition = "LONGBLOB")
    private byte[] fileContent;     // Actual file content


    // Getters and Setters
    // No changes needed if your previous getters/setters are already in place
}
