package com.journalapp.Journal.controller;

import com.journalapp.Journal.entity.User;
import com.journalapp.Journal.service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    User_Service userService;

    @GetMapping("/health-check")
    public String heathCheck(){
        return "Ok";
    }

    @PostMapping("/createUser")
    public void addUser(@RequestBody User user){
        userService.saveNewEntry(user);
    }

}
