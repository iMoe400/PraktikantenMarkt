package com.example.demo4.web;

import com.example.demo4.datasource.Company;
import com.example.demo4.datasource.Intern;
import com.example.demo4.datasource.User;
import com.example.demo4.repositories.CompanyRepo;
import com.example.demo4.repositories.InternRepo;
import com.example.demo4.repositories.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InternRepo internRepo;

    @Autowired
    private CompanyRepo companyRepo;


    @GetMapping("/show")
    public String showProfile(Model model, Principal principal, @ModelAttribute("error") String error, RedirectAttributes redirectAttributes) {


        if(principal == null) {
            redirectAttributes.addFlashAttribute("error", "You are not logged in");
            return "redirect:/login";
        }
        User user = userRepo.findByUsername(principal.getName());
        model.addAttribute("user", user);



        if(error!=null){
            model.addAttribute("error", error);
        }
        if ("PRAKTIKANT".equals(user.getRole())) {
            Optional<Intern> intern = internRepo.findByUserId(user.getId());
            if(intern.isPresent()) {
                Intern internModel = intern.get();

                model.addAttribute("benutzerProfil", internModel);
            } else {
                model.addAttribute("benutzerProfil", new Intern());
            }
            return "showProfile";
        } else if ("UNTERNEHMEN".equals(user.getRole())) {
            Optional<Company> company = companyRepo.findByUserId(user.getId());
            if(company.isPresent()) {
                Company companyModel = company.get();
                model.addAttribute("benutzerProfil", companyModel);
            } else {
                model.addAttribute("benutzerProfil", new Company());
            }
            return "showProfile";
        }

        return "error";
    }


    @GetMapping("/edit")
    public String editProfile(Model model, Principal principal, RedirectAttributes redirectAttributes) {

        if(principal == null) {
            redirectAttributes.addFlashAttribute("error", "You are not logged in");
            return "redirect:/login";
        }
        User user = userRepo.findByUsername(principal.getName());






        model.addAttribute("user", user);
        if ("PRAKTIKANT".equals(user.getRole())) {
            Optional<Intern> intern = internRepo.findByUserId(user.getId());
            if(intern.isPresent()) {
                Intern internModel = intern.get();

                model.addAttribute("intern", internModel);
            } else {
                model.addAttribute("intern", new Intern());
            }
            return "editProfile";
        } else if ("UNTERNEHMEN".equals(user.getRole())) {
            Optional<Company> company = companyRepo.findByUserId(user.getId());
            if(company.isPresent()) {
                Company companyModel = company.get();
                model.addAttribute("company", companyModel);
            } else {
                model.addAttribute("company", new Company());
            }
            return "editProfile";
        }

        return "error";
    }



    @PostMapping("/update")
    public String updateProfile(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "currentPassword", required = false) String currentPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "dateOfBirth", required = false) LocalDate dateOfBirth,
            @RequestParam(value = "profileDescription", required = false) String profileDescription,
            @RequestParam(value = "resumeLink", required = false) String linkToCv,
            @RequestParam(value = "lookingFor", required = false) boolean lookingFor,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "companyName", required = false) String companyName,
            Principal principal, Model model) {

        User user = userRepo.findByUsername(principal.getName());
        if (user.getRole().equals("PRAKTIKANT")){
            Optional<Intern> intern = internRepo.findByUserId(user.getId());
            if(intern.isPresent()) {
                Intern internModel = intern.get();
                internModel.setFirstName(firstName);
                internModel.setLastName(lastName);
                internModel.setDateOfBirth(dateOfBirth);
                internModel.setLookingForCompany(lookingFor);
                internModel.setResumeLink(linkToCv);
                internModel.setProfileDescription(profileDescription);
                internRepo.save(internModel);
                model.addAttribute("benutzerProfil", intern);
            }
        } else if ("UNTERNEHMEN".equals(user.getRole())) {
            Optional<Company> company = companyRepo.findByUserId(user.getId());
            if(company.isPresent()) {
                Company companyModel = company.get();
                companyModel.setCompanyName(companyName);
                companyModel.setDescription(profileDescription);
                companyModel.setLookingForIntern(lookingFor);
                companyRepo.save(companyModel);
                model.addAttribute("benutzerProfil", company);

            }

        }


        if(!username.equals(user.getUsername()) || !email.equals(user.getEmail()) || !newPassword.isEmpty()) {
            if(!user.getPasswordHash().equals(currentPassword)) {
                redirectAttributes.addFlashAttribute("error", "Invalid password");
                return "redirect:/profile/show";
            } else {
                user.setEmail(email);
                user.setUsername(username);
                user.setEmail(email);
                user.setUsername(username);
                user.setPasswordHash(currentPassword);
                log.info("{} hat sein USER - Profil bearbeitet: {}_{}_{}_{}", user.getUsername(), user.getRole(), email, user.getId(), newPassword);
            }
        }
        userRepo.save(user);


        return "redirect:/profile/show";
    }



}
