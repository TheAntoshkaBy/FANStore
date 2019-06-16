package com.controllers;


import com.models.Post;
import com.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class TxtOutputController {

    @Autowired
    private PostRepository pRep;

    @GetMapping("/storyPage/{id}")
    public String main(@PathVariable int id, Map<String,Object> model)
    {
        System.out.println(id);
        Post post = pRep.findById(id);
        model.put("title",post.getTitle());
        model.put("annotation", post.getAnnotation());
        return "storyPage";
    }

}
