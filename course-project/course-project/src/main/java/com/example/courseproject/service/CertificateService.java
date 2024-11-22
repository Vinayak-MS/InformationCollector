package com.example.courseproject.service;

import com.example.courseproject.model.Certificate;
import com.example.courseproject.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    // Save a new certificate
    public void saveCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    // Fetch all certificates
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // Fetch certificates for a specific user
    public List<Certificate> getCertificatesByUsername(String username) {
        return certificateRepository.findByUsername(username);
    }

    // Fetch certificate by ID

    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id).orElse(null);
    }
}

