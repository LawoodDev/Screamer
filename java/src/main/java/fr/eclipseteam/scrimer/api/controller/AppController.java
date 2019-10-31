package fr.eclipseteam.scrimer.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @GetMapping("/")
    @RequestMapping
    public String login(){
        return "index";
    }
}
