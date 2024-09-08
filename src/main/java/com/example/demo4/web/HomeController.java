package com.example.demo4.web;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Principal principal, RedirectAttributes redirectAttributes) {

        if(principal == null) {
            redirectAttributes.addFlashAttribute("error", "You are not logged in");
            return "redirect:/login";
        }
        return "home";
    }

}
