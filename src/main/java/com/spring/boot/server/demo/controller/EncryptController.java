package com.spring.boot.server.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class EncryptController {

    @Value("${current.env}")
    private String env;

    //localhost:port/my_first_router
    @RequestMapping("/my_first_router")
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/current_env")
    public String env() {
        return env;
    }

    @RequestMapping("/encrypted_password")
    public String encrypted_password() {
        return new BCryptPasswordEncoder().encode("12345678");
    }

    @RequestMapping("/compare_pwd")
    public boolean compare(){
        return new BCryptPasswordEncoder().matches("1234567","$2a$10$ul3eNTrlqdB6A.tp7dUpBuITgNz3ei30mz5OAOiqMcAw3zwGwVW1e");
    }
}
