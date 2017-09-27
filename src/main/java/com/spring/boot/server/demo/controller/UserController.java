package com.spring.boot.server.demo.controller;

import com.spring.boot.server.demo.model.User;
import com.spring.boot.server.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository users;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<User> getUserList() {
        // This returns a JSON or XML with the users
        return users.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) {
        users.save(user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable Long id) {
        return users.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.findOne(id);
        u.setMobile(user.getMobile());
        u.setAge(user.getAge());
        users.save(u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable Long id) {
        users.delete(id);
        return "success";
    }
}
