package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/server")
public class ServerController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getData() {
        System.out.println("Returning data from server");
        return "Hello from server";
    }
}