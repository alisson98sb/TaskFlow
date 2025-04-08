package com.example.taskFlow.controller;

import com.example.taskFlow.entity.User;
import com.example.taskFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<User> list() { return userService.list(); }

    @PostMapping
    ResponseEntity<List<User>> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body( (userService.create(user)) );
    }
}
