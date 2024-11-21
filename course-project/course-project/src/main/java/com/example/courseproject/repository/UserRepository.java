package com.example.courseproject.repository;

import com.example.courseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Method to find user by username and password
    User findByUsernameAndPassword(String username, String password);
}
