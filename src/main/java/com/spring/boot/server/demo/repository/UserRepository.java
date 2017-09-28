package com.spring.boot.server.demo.repository;

import com.spring.boot.server.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
