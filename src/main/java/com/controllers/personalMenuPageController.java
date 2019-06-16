package com.controllers;

import com.models.Post;
import com.models.User;
import com.repositories.PostRepository;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class personalMenuPageController {

    @Autowired
    private UserRepository uRep;

    @Autowired
    private PostRepository pRep;

    @GetMapping("/personalPage")
    public String main(@RequestParam(name = "event", required = false, defaultValue = "") String event,  Map<String, Object> model)
    {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        model.put("currentUser", currentUser.getName());
        System.out.println(event);
        if(event.equals("Подробнее"))
        {

            return "redirect:/storyPage";
        }

        User user = uRep.findByUsername(currentUser.getName());
        List<Post> post = pRep.findByAuthor(user);
        model.put("post",post);

        return "personalPage";
    }

}
