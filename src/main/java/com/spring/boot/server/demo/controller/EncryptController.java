package com.spring.boot.server.demo.controller;

import com.spring.boot.server.demo.advice.ApiAdviceHandler;
import com.spring.boot.server.demo.model.Authority;
import com.spring.boot.server.demo.model.User;
import com.spring.boot.server.demo.repository.UserRepository;
import com.spring.boot.server.demo.service.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class EncryptController {

    @Autowired
    private UserRepository users;
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
    public boolean compare() {
        return new BCryptPasswordEncoder().matches("12345678", users.findOne(1L).getPwd());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiAdviceHandler login(@RequestBody User user) {
        User byMobile = users.findByMobile(user.getMobile());
        if (byMobile != null)
            if (new BCryptPasswordEncoder().matches(user.getPwd(), byMobile.getPwd())) {
                ArrayList<Authority> authorities = new ArrayList<>();
                authorities.add(new Authority("ROLE_ADMIN"));
                authorities.add(new Authority("ROLE_USER"));
                TokenAuthService auth = new TokenAuthService();
                String token = auth.createToken(user.getMobile(), authorities);
                return new ApiAdviceHandler<>(HttpStatus.OK.value(), "success", token);
            } else
                return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "password is wrong", false);
        else
            return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "can not find this user", false);
    }

    @RequestMapping(value = "/login_form", method = RequestMethod.POST)
    public String login(@RequestParam(name = "mobile") String mobile, @RequestParam(name = "pwd") String pwd) {
        return mobile + pwd;
    }
}
