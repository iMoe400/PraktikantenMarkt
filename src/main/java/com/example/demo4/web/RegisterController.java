package com.example.demo4.web;

import com.example.demo4.datasource.Company;
import com.example.demo4.datasource.Intern;
import com.example.demo4.datasource.User;
import com.example.demo4.repositories.CompanyRepo;
import com.example.demo4.repositories.InternRepo;
import com.example.demo4.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private InternRepo internRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping("/register/company")
    public String registerCompany(@RequestParam("companyName") String companyName,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("email") String email,
                                  @RequestParam("description") String description,
                                  @RequestParam("lookingForIntern") String lookingForIntern,
                                  Model model) {

        // Prüfen, ob Benutzername bereits existiert
        if (companyRepo.findByCompanyName(username) != null) {
            model.addAttribute("error", true);
            return "register";  // Bei Fehler wird die Registrierung erneut angezeigt
        }
        // Passwort hashen und Benutzer speichern
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashedPassword);
        newUser.setRole("UNTERNEHMEN");
        newUser.setEmail(email);
        newUser.setIsActive(true);
        userRepo.save(newUser);

        Company company = new Company();
        company.setCompanyName(companyName);
        company.setDescription(description);
        company.setLookingForIntern(lookingForIntern.equals("JA"));
        company.setUser(userRepo.findByUsername(username));
        companyRepo.save(company);


        return "login";
    }

    @GetMapping("/register/company")
    public ModelAndView showCompanyForm(Model model) {
        return new ModelAndView("registerCompany");
    }

    @GetMapping("/register/intern")
    public ModelAndView showInternForm(Model model) {
        return new ModelAndView("registerIntern");
    }

    // Verarbeitet die Registrierung
    @PostMapping("/register/intern")
    public String registerIntern(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("birthday") LocalDate birthday,
                                 @RequestParam("lookingForCompany") String lookingForCompany,
                                 Model model) {
        // Prüfen, ob Benutzername bereits existiert (Korrektur)
        if (userRepo.findByUsername(username) != null) {
            model.addAttribute("error", true);
            return "register";  // Bei Fehler wird die Registrierung erneut angezeigt
        }
        // Passwort hashen und Benutzer speichern
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(hashedPassword);
        newUser.setRole("PRAKTIKANT");  // Korrekte Rolle setzen
        newUser.setEmail(email);
        newUser.setIsActive(true);
        userRepo.save(newUser);

        Intern intern = new Intern();
        intern.setFirstName(firstName);
        intern.setLastName(lastName);
        intern.setLookingForCompany(lookingForCompany.equals("JA"));
        intern.setDateOfBirth(birthday);
        intern.setUser(newUser);  // Korrekte Verknüpfung des Interns mit dem User
        internRepo.save(intern);

        return "login";
    }
}
