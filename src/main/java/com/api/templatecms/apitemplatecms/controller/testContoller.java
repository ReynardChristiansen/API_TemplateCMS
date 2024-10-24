package com.api.templatecms.apitemplatecms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.templatecms.apitemplatecms.model.MasterUser;
import com.api.templatecms.apitemplatecms.repository.MasterUserRepository;


@RestController
@RequestMapping("/test")
public class testContoller {
    @Autowired
    private MasterUserRepository masterUserRepository;

    // @GetMapping("/users")
    // public List<MasterUser> getAllUsers() {
    //     return masterUserRepository.findAll(); 
    // }

    @GetMapping("/hello")
    public String sayHello() {
        List<MasterUser> users = masterUserRepository.findAll();
        return "Number of users: " + users.size();
    }

    // Endpoint GET dengan parameter
    @GetMapping("/greet")
    public String greetUser(@RequestParam(value = "name", defaultValue = "User") String name) {
        return "Hello, " + name + "!";
    }
}
