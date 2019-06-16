package com.controllers;

import com.models.Role;
import com.models.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainPageController {

    private Authentication currentUser;
    @Autowired
    private UserRepository uRep;

    private String commandForButtonLoginHref;
    private String commandForNameOfButtonLogin;
    private String registrationButtonName;
    private String registrationHref;

    @GetMapping()
    public String main(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model)
    {

        currentUser = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB;

        userFromDB = uRep.findByUsername(currentUser.getName());
        if(!currentUser.getName().equals("anonymousUser"))
        {
            commandForButtonLoginHref = "/personalPage";
            commandForNameOfButtonLogin = "Личный Кабинет";
            if(userFromDB.getRoles().contains(Role.ADMIN)){
                registrationButtonName= "Вальхала";
                registrationHref = "/main";
            }else {
            registrationButtonName= "";
            registrationHref="#";
            }
        }else {
            commandForButtonLoginHref = "/login";
            commandForNameOfButtonLogin = "Вход";
            registrationButtonName= "Регистрация";
            registrationHref = "/registration";
            }


            model.put("command",commandForButtonLoginHref);
            model.put("commandLogIn",commandForNameOfButtonLogin);
            model.put("registrationButtonName",registrationButtonName);
            model.put("registrationHref",registrationHref);
            model.put("name",name);

        return "mainPage";
    }

}
