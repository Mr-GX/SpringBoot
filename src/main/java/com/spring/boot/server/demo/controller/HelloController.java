package com.spring.boot.server.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //localhost:8888/my_first_router
    @RequestMapping("/my_first_router")
    public String index() {
        return "hello world!";
    }
}
