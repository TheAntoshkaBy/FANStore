package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String main(Map<String, Object> model){

        return  "login";
    }
}
