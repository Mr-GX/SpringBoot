package com.spring.boot.server.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index"; //return模板文件的名称
    }

    @RequestMapping("/error_test")
    public String error() throws Exception {
        throw new Exception("页面访问异常输出测试！");
    }

}
