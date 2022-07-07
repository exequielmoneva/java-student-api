package com.example.javastudentapi.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class health {

    @GetMapping
    public String apiController(){
        return "API is up!";
    }
}
