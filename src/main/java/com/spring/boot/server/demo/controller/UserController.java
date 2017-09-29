package com.spring.boot.server.demo.controller;

import com.spring.boot.server.demo.advice.ApiAdviceHandler;
import com.spring.boot.server.demo.model.User;
import com.spring.boot.server.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository users;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ApiAdviceHandler<?> getUserList() {
        // This returns a JSON or XML with the user
        return new ApiAdviceHandler<>(HttpStatus.OK.value(), env.getProperty("success"), users.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ApiAdviceHandler<?> createUser(@ModelAttribute User user) {
        Logger.getLogger("test").info(user.toString());
        User new_user = new User();
        new_user.setMobile(user.getMobile());
        new_user.setAge(user.getAge());
        User save = users.save(new_user);
        if (save != null)
            return new ApiAdviceHandler<>(HttpStatus.OK.value(), env.getProperty("success"), save.getId());
        else
            return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "save failed!", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiAdviceHandler<?> getUserInfo(@PathVariable Long id) {
        User one = users.findOne(id);
        if (one != null)
            return new ApiAdviceHandler<>(HttpStatus.OK.value(), env.getProperty("success"), one);
        else
            return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "user not exist!", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ApiAdviceHandler<?> updateUser(@PathVariable Long id, @ModelAttribute User user) {
        Logger.getLogger("test").info(user.toString());
        User u = users.findOne(id);
        if (u == null)
            return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "user not exist!", null);
        u.setMobile(user.getMobile());
        u.setAge(user.getAge());
        User save = users.save(u);
        Logger.getLogger("test").info(u.toString());
        if (save != null)
            return new ApiAdviceHandler<>(HttpStatus.OK.value(), env.getProperty("success"), u.getId());
        else
            return new ApiAdviceHandler<>(HttpStatus.NOT_FOUND.value(), "update failed!", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiAdviceHandler<?> delUser(@PathVariable Long id) {
        users.delete(id);
        return new ApiAdviceHandler<>(HttpStatus.OK.value(), env.getProperty("success"), null);
    }
}
