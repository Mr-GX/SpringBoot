package com.spring.boot.server.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${current.env}")
    private String env;

    //localhost:port/my_first_router
    @RequestMapping("/my_first_router")
    public String index() {
        return "hello world!";
    }

    @RequestMapping("/current_env")
    public String env(){
        return env;
    }
}
