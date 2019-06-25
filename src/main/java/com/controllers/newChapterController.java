package com.controllers;

import com.models.Chapters;
import com.models.Post;
import com.repositories.ChapterRepository;
import com.repositories.PostRepository;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class newChapterController {

    private Authentication currentUser;

    @Autowired
    private ChapterRepository cRep;

    @Autowired
    private PostRepository pRep;


    @GetMapping("/newChapter/{id}")
    public String main (@PathVariable int id, Map<String, Object> model)
    {
        currentUser = SecurityContextHolder.getContext().getAuthentication();
        model.put("currentUser", currentUser.getName());
        model.put("id",id);
        return "newChapter";
    }

    @PostMapping("/newChapter/{id}")
    public String addChapter(@PathVariable int id,@RequestParam String name, @RequestParam String body,Map<String, Object> model)
    {
        currentUser = SecurityContextHolder.getContext().getAuthentication();
        model.put("currentUser", currentUser.getName());
        Post post = pRep.findById(id);
        List<Chapters> chapters = cRep.findByPost(post);
        Chapters chapter = new Chapters();
        body = body.substring(3,body.length()-4);
        chapter.setBody(body);
        chapter.setName(name);
        chapter.setNumber(chapters.size()+1);
        chapter.setPost(post);
        cRep.save(chapter);
        System.out.println(chapter.getBody() + " "+ chapter.getName() + " " + chapter.getNumber());
        return "/newChapter";
    }
}
