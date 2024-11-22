package com.example.courseproject.repository;

import com.example.courseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
