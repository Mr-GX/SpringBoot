package com.spring.boot.server.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Value("${current.env}")
    private String env;

    @RequestMapping("/")
    public String index() {
        return "index"; //return模板文件的名称
    }

    @RequestMapping("/error_test")
    public String error() throws Exception {
        throw new Exception("页面访问异常输出测试！");
    }

    //localhost:port/my_first_router
    @RequestMapping("/my_first_router")
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/current_env")
    public String env() {
        return env;
    }
}
