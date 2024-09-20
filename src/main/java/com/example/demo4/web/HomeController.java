package com.example.demo4.web;

import com.example.demo4.datasource.Company;
import com.example.demo4.datasource.Intern;
import com.example.demo4.repositories.CompanyRepo;
import com.example.demo4.repositories.InternRepo;
import com.example.demo4.repositories.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserRepo userRepo;
    private final InternRepo internRepo;
    private final CompanyRepo companyRepo;

    public HomeController(UserRepo userRepo, InternRepo internRepo, CompanyRepo companyRepo) {
        this.userRepo = userRepo;
        this.internRepo = internRepo;
        this.companyRepo = companyRepo;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, RedirectAttributes redirectAttributes, Model model) {

        if (!authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("error", "You are not logged in");
            return "redirect:/login";
        }

        Optional<Intern> possibleIntern = internRepo.findByUserId(userRepo.findByUsername(authentication.getName()).getId());
        Optional<Company> possibleCompany = companyRepo.findByUserId(userRepo.findByUsername(authentication.getName()).getId());
        model.addAttribute("user", userRepo.findByUsername(authentication.getName()));
        if(possibleIntern.isPresent()) {
            model.addAttribute("info", possibleIntern.get());
            return "home";
        } else if(possibleCompany.isPresent()) {
            model.addAttribute("info", possibleCompany.get());
            return "home";
        } else {
            model.addAttribute("error", true);
            return "error";
        }
    }

}
