package com.controllers.ErrorsPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ErrorPageController {
    @GetMapping("/errorPage")
    public String main (Map<String, Object> model)
    {
        return "/main/resources/templates/errorPages/errorPage.mustache";
    }
}
