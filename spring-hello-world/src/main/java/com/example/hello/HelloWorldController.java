package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8081/hello
@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World CRA!!";
    }
}
