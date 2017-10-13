package com.spring.boot.server.demo.repository;

import com.spring.boot.server.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM USER WHERE mobile = ?1",nativeQuery = true)
    User findByMobile(String mobile);
}
