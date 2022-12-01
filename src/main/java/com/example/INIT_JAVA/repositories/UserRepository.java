package com.example.INIT_JAVA.repositories;

import com.example.INIT_JAVA.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String email);
}
