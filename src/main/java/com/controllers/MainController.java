package com.controllers;

import com.models.Role;
import com.models.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository uRep;

    boolean isAdmin = false;
    Iterable<User> users;


    @GetMapping("/main")
    public String userManager(@RequestParam(name = "event", required = false, defaultValue = "") String event,
                           @RequestParam(name = "id", required = false, defaultValue = "") String[] id,
                           Map<String, Object> model,
                           User user) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB;


        userFromDB = uRep.findByUsername(currentUser.getName());
        if(!userFromDB.getRoles().contains(Role.ADMIN))
        {
            return "redirect:/personalPage";
        }

        boolean currentUserDisabled = false;


            for (String retval : id) {
                if (uRep.findById(Long.parseLong(retval)).isPresent()) {
                    userFromDB = uRep.findById(Long.parseLong(retval)).get();
                    if(event.equals("Активация/Дизактивация")){
                        blockUnblock(userFromDB);
                        //if(currentUserIsBanned(userFromDB, currentUser)) currentUserDisabled=true;
                    }
                    else if(event.equals("Удаление")){
                        delete(userFromDB);

                        //if(currentUserIsDeleted(userFromDB, currentUser)) currentUserDisabled=true;
                    }else if(event.equals("Назначить Одменом/Снять Одменку"))
                    {
                        odmenNeOdmen(userFromDB);
                    }

                    //uRep.save(userFromDB);
                }
            }




        users = uRep.findAll();
        model.put("currentUser", currentUser.getName());
        model.put("users", users);


        if (currentUserDisabled) {
            return "redirect:/login?logout";
        }
        return "main";
    }


    @PostMapping()
    public String showUsers(Map<String, Object> model) {
        Iterable<User> users = uRep.findAll();
        model.put("users", users);
        return "redirect:/users";
    }

    private void delete(User userFromDB) {
        System.out.println(userFromDB.getUsername() + "IS DELETE!");
        uRep.delete(userFromDB);
    }

    private boolean currentUserIsDeleted(User userFromDB, Authentication currentUser){
        if (currentUser.getName().equals(userFromDB.getUsername())) {
            return true;
        }
        return false;
    }

    private void blockUnblock(User userFromDB) {
        if (userFromDB.getBlock()) {
            userFromDB.setBlock(false);
        } else {
            userFromDB.setBlock(true);
        }
        uRep.save(userFromDB);
    }

    private void odmenNeOdmen(User userFromDB)
    {
        if(userFromDB.getRoles().contains(Role.ADMIN))
        {
            userFromDB.getRoles().remove(Role.ADMIN);
        }else {
            userFromDB.setRoles(Collections.singleton(Role.ADMIN));
        }
        //uRep.save(userFromDB);
        //Вопрос куратору
    }
}

