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
public class ChapterController {


    @Autowired
    private ChapterRepository cRep;

    private int counter;
    private String check;

    @GetMapping("/chapter/{id}")
    public String main(@PathVariable int id, Map<String,Object> model,  @RequestParam(name = "event", required = false, defaultValue = "") String event)
    {
        Chapters chapters = cRep.findById(id + counter);

        model.put("next", "Далее");
        if(event.equals("Личный кабинет"))
        {
            counter = 0;
            return "redirect:/personalPage";
        }

        check = nextOrPrev(event,chapters,model);
        if(!check.isEmpty())
            return check;

        Chapters chapter = cRep.findById(id + counter);
        model.put("body",chapter.getBody());
        model.put("name", chapter.getName());
        return "chapter";
    }

    private String nextOrPrev(String event, Chapters chapters, Map <String,Object> model )
    {
        List<Chapters> cp = cRep.findByPost(chapters.getPost());
        if( chapters.getNumber()<=(cp.size()-1)) {
            model.put("next", "Далее");
            if(event.equals("Далее"))
                counter++;
        }else {
            model.put("next", "Выход");
            if (event.equals("Выход"))
            {
                counter = 0;
                return "redirect:/personalPage";
            }
        }
        if( chapters.getNumber()>1)
        {
            if(event.equals("Назад"))
                counter--;
        }else {
            if(event.equals("Назад")) {
                counter = 0;
                return "redirect:/personalPage";
            }
        }
        return "";
    }
}
