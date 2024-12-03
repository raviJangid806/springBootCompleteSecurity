package com.spring.practiceSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("/login","/login");
        return "login";
    }


}
