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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class InputTextController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private Authentication currentUser;

    @GetMapping("/inputStory")
    public String main( Map<String, Object> model )
    {
        currentUser = SecurityContextHolder.getContext().getAuthentication();

        model.put("currentUser",currentUser.getName());
        return "inputStory";
    }

    @PostMapping("/inputStory")
    public String addPost(@RequestParam String title, @RequestParam String annotation, Map<String,Object> model)
    {


        annotation = annotation.substring(3, annotation.length() -4);
        currentUser = SecurityContextHolder.getContext().getAuthentication();
        model.put("currentUser",currentUser.getName());
        User userFromDB;
        userFromDB = userRepository.findByUsername(currentUser.getName());

        Post post = new Post();
        post.setAuthor(userFromDB);
        post.setTitle(title);
        post.setAnnotation(annotation);
        postRepository.save(post);
        return "inputStory";
    }
}
