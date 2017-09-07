package ru.cheb.intercity.bus.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/good")
    public String hello(){
        return "Hello";
    }
}
