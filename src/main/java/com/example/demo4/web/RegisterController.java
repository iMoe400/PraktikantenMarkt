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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register/decision")
    public String showDecisionForm() {

        return "registerChoose";
    }


    @PostMapping("/register")
    public String confirmRegisterForm(Model model,
                                      @RequestParam(value = "companyName", required = false) String companyName,
                                      @RequestParam(value = "usernamePrakti", required = false) String usernamePrakti,
                                      @RequestParam(value = "passwordPrakti", required = false) String passwordPrakti,
                                      @RequestParam(value = "emailPrakti", required = false) String emailPrakti,
                                      @RequestParam(value = "usernameUnternehmi", required = false) String usernameUnternehmi,
                                      @RequestParam(value = "passwordUnternehmi", required = false) String passwordUnternehmi,
                                      @RequestParam(value = "emailUnternehmi", required = false) String emailUnternehmi,
                                      @RequestParam(value = "descriptionUnternehmi", required = false) String description,
                                      @RequestParam(value = "lookingForIntern", required = false) String lookingForIntern,
                                      @RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam("birthday") LocalDate birthday,
                                      @RequestParam(value = "lookingForCompany", required = false) String lookingForCompany,
                                      @RequestParam(value = "decisionPrakti", required = false) boolean decisionPrakti,
                                      @RequestParam(value = "decisionUnternehmi", required = false) boolean decisionUnternehmi,
                               RedirectAttributes redirectAttributes) {


        // Hier RedirectAttributes setzen
        // error, wenn RollenSpezifische Daten fehlen, back to login!


        if (userRepo.findByUsername(usernamePrakti) == null) {
            if (decisionPrakti) {
                String hashedPassword = passwordEncoder.encode(passwordPrakti);
                User newUser = new User();
                newUser.setUsername(usernamePrakti);
                newUser.setPasswordHash(hashedPassword);
                newUser.setRole("PRAKTIKANT");
                newUser.setEmail(emailPrakti);
                newUser.setIsActive(true);
                userRepo.save(newUser);
                Intern intern = new Intern();
                intern.setFirstName(firstName);
                intern.setLastName(lastName);
                intern.setLookingForCompany(lookingForCompany.equals("JA"));
                intern.setDateOfBirth(birthday);
                intern.setUser(newUser);  // Korrekte Verknüpfung des Interns mit dem User
                internRepo.save(intern);
                redirectAttributes.addAttribute("username", usernamePrakti);
                redirectAttributes.addAttribute("password", passwordPrakti);
            } else if (decisionUnternehmi) {
                Company company = new Company();
                company.setCompanyName(companyName);
                company.setDescription(description);
                company.setLookingForIntern(lookingForIntern.equals("JA"));
                company.setUser(userRepo.findByUsername(usernameUnternehmi));
                companyRepo.save(company);
                String hashedPassword = passwordEncoder.encode(passwordUnternehmi);
                User newUser = new User();
                newUser.setUsername(usernameUnternehmi);
                newUser.setPasswordHash(hashedPassword);
                newUser.setRole("UNTERNEHMEN");
                newUser.setEmail(emailUnternehmi);
                newUser.setIsActive(true);
                userRepo.save(newUser);
                redirectAttributes.addAttribute("username", usernameUnternehmi);
                redirectAttributes.addAttribute("password", passwordUnternehmi);
            }
            model.addAttribute("error", true);
            return "registerChoose";  // Bei Fehler wird die Registrierung erneut angezeigt
        }
        return "redirect:/login";
    }

    @PostMapping("/register/create")
    public String showRegisterForm( @RequestParam(value = "decisionPrakti", required = false, defaultValue = "false") boolean decisionPrakti,
                                    @RequestParam(value = "decisionUnternehmi", required = false, defaultValue = "false") boolean decisionUnternehmi,
                                    RedirectAttributes redirectAttributes,
                                  Model model) {

        if(decisionPrakti) {
            model.addAttribute("decisionPrakti", decisionPrakti);
            redirectAttributes.addFlashAttribute("decisionPrakti", decisionPrakti);
        } else {
            model.addAttribute("decisionUnternehmi", decisionUnternehmi);
            redirectAttributes.addFlashAttribute("decisionUnternehmi", decisionUnternehmi);
        }
        return "registerCreate";
    }
}
