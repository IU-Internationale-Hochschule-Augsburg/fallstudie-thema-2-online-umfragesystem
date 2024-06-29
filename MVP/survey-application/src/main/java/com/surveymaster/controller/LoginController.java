package com.surveymaster.controller;

import com.surveymaster.mapper.LoginForm;
import com.surveymaster.mapper.RegisterForm;
import com.surveymaster.service.SurveyService;
import com.surveymaster.mapper.UserSettingsForm;
import com.surveymaster.entity.User;
import com.surveymaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final SurveyService surveyService;

    @GetMapping("/login")
    public String loadLoginScreen(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute(loginForm);
        return "loginScreen";
    }

    @GetMapping("/register")
    public String loadRegistration(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute(registerForm);
        return "registerScreen";
    }

    @PostMapping("/save-registration")
    public String registerNewUser(Model model, @ModelAttribute RegisterForm registerForm) {
        var user = userRepository.findByUsername(registerForm.getUsername());
        var email = userRepository.findByEmail(registerForm.getEmail());

        // check if the email and username are not yet registered
        if (user == null && email == null) {
            // set new dates from the user
            User newUser = new User();
            newUser.setUsername(registerForm.getUsername());
            newUser.setFirstname(registerForm.getFirstname());
            newUser.setSurname(registerForm.getSurname());
            newUser.setEmail(registerForm.getEmail());

            // encryption + check if password and confirm-password are the same
            var encryptedPassword = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());
            if (BCrypt.checkpw(registerForm.getConfirmPassword(), encryptedPassword)) {
                newUser.setPassword(encryptedPassword);
            } else {
                // error handling, if the passwords are not the same
                model.addAttribute(registerForm);
                model.addAttribute("errorMessage", "Die Passwörter stimmen nicht überein!");
                return "filledRegisterScreen";
            }

            userRepository.save(newUser);
            model.addAttribute(newUser);
        } else {
            // error handling, if username or e-mail are already registered
            model.addAttribute(registerForm);
            model.addAttribute("errorMessage", "Benutzername oder E-Mail ist schon vergeben!");
            return "filledRegisterScreen";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/user-actions")
    public String handleUserActions(@RequestParam("userActions") String userActions, Model model) {
        if (userActions.startsWith("logout,")) {
            model.addAttribute(userActions);
            return "redirect:/logout";
        } else if (userActions.startsWith("settings,")) {
            var currUser = surveyService.getCurrentUser();
            UserSettingsForm userSettingsForm = new UserSettingsForm();

            // setting attributes in userSettingForm
            userSettingsForm.setUsername(currUser.getUsername());
            userSettingsForm.setFirstname(currUser.getFirstname());
            userSettingsForm.setSurname(currUser.getSurname());
            userSettingsForm.setEmail(currUser.getEmail());
            userSettingsForm.setUsername(currUser.getUsername());


            model.addAttribute("userSettingsForm", userSettingsForm);
            model.addAttribute(userActions);
            return "userSettings";
        }
        return "redirect:/survey-admin";
    }

    @PostMapping("/save-user-settings")
    public String saveUserSettings(Model model, @ModelAttribute("userSettingsForm") UserSettingsForm userSettingsForm) {
        var currentUser = userRepository.findByUsername(userSettingsForm.getUsername());

        // emails are matching
        if (currentUser.getEmail().equalsIgnoreCase(userSettingsForm.getEmail())) {
            // set new user-data
            currentUser.setFirstname(userSettingsForm.getFirstname());
            currentUser.setSurname(userSettingsForm.getSurname());
        } else {
            // emails are not matching => check, if the new email is already registered
            var currentUserByEmail = userRepository.findByEmail(userSettingsForm.getEmail());
            if (currentUserByEmail == null) {
                // if e-mail is not yet registered, set new user-data
                currentUser.setFirstname(userSettingsForm.getFirstname());
                currentUser.setSurname(userSettingsForm.getSurname());
                currentUser.setEmail(userSettingsForm.getEmail());
            } else {
                // error handling if e-mail is already registered
                model.addAttribute("errorMessage", "Die E-Mail Adresse ist bereits vergeben!");
                return "userSettings";
            }
        }

        // Compare and check passwords, if user typed new passwords
        if (!Objects.equals(userSettingsForm.getPassword(), "") && !Objects.equals(userSettingsForm.getConfirmPassword(), "")) {
            var encryptedPassword = BCrypt.hashpw(userSettingsForm.getPassword(), BCrypt.gensalt());

            // check, if the old password is the same as in the database
            if (BCrypt.checkpw(userSettingsForm.getOldPassword(), currentUser.getPassword())) {
                // check, if the new password and the confirm-password are the same
                if (BCrypt.checkpw(userSettingsForm.getConfirmPassword(), encryptedPassword)) {
                    currentUser.setPassword(encryptedPassword);
                } else {
                    // error handling, if the passwords are not the same
                    model.addAttribute("errorMessage", "Die neuen Passwörter stimmen nicht überein!");
                    return "userSettings";
                }
            } else {
                // error handling, if the old password is not the same as in the database
                model.addAttribute("errorMessage", "Das alte Passwort stimmt nicht mit dem aus der Datenbank überein!");
                return "userSettings";
            }
        }

        // save changes
        userRepository.save(currentUser);
        return "redirect:/survey-admin";
    }
}