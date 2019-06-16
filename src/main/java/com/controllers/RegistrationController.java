package com.controllers;

import com.models.Role;
import com.models.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    private final String reistrationPage = "/registration";

    @GetMapping(reistrationPage)

    public String registration() {

        return "registration";

    }

    @PostMapping(reistrationPage)
    public String addUser(User user, Map<String, Object> model){

        User userName = userRepository.findByUsername(user.getUsername());

        if(userName != null){
            model.put("message", "Username exists!");
            return "registration";
        }

            user.setBlock(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

        return "redirect:/login";
    }
}
