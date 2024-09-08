package com.example.demo4.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@ModelAttribute("error") String error, Model model) {
        if(error != null) {
            model.addAttribute("error", error);
        }
        return "login";
    }
}
