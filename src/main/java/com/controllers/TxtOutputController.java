package com.controllers;


import com.models.Chapters;
import com.models.Post;
import com.repositories.ChapterRepository;
import com.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TxtOutputController {

    @Autowired
    private PostRepository pRep;

    @Autowired
    private ChapterRepository cRep;

    @GetMapping("/storyPage/{id}")
    public String main(@PathVariable int id, Map<String,Object> model, @RequestParam(name = "event", required = false, defaultValue = "") String event)
    {
        System.out.println(id);
        Post post = pRep.findById(id);
        model.put("title",post.getTitle());
        model.put("annotation", post.getAnnotation());



        if(event.equals("Добавить главу"))
            return "redirect:/newChapter/{id}";


        List<Chapters> chapters = cRep.findByPost(post);
        model.put("chapters",chapters);
        return "storyPage";
    }

}
