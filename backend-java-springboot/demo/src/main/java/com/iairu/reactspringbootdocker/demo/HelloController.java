package com.iairu.reactspringbootdocker.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello.json")
    public String hello() {
        return "Hello, World!";
    }
}
