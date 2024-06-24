package com.surveymaster;

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

        if (user == null && email == null) {
            User newUser = new User();
            newUser.setUsername(registerForm.getUsername());
            newUser.setFirstname(registerForm.getFirstname());
            newUser.setSurname(registerForm.getSurname());
            newUser.setEmail(registerForm.getEmail());
            var encryptedPassword = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());
            if (BCrypt.checkpw(registerForm.getConfirmPassword(), encryptedPassword)) {
                newUser.setPassword(encryptedPassword);
            } else {
                model.addAttribute(registerForm);
                model.addAttribute("errorMessage", "Die Passwörter stimmen nicht überein!");
                return "filledRegisterScreen";
            }

            userRepository.save(newUser);
            model.addAttribute(newUser);
        } else {
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
        if(userActions.startsWith("logout,")) {
            model.addAttribute(userActions);
            return "redirect:/logout";
        } else if(userActions.startsWith("settings,")) {
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
        var user = userSettingsForm.getUsername();
        var currentUser = userRepository.findByUsername(userSettingsForm.getUsername());

        // emails are matching
        if(currentUser.getEmail().equalsIgnoreCase(userSettingsForm.getEmail())) {
            currentUser.setFirstname(userSettingsForm.getFirstname());
            currentUser.setSurname(userSettingsForm.getSurname());
        } else {
            // emails are not matching => check, if the new email is already registered
            var currentUserByEmail = userRepository.findByEmail(userSettingsForm.getEmail());
            if(currentUserByEmail == null) {
                currentUser.setFirstname(userSettingsForm.getFirstname());
                currentUser.setSurname(userSettingsForm.getSurname());
                currentUser.setEmail(userSettingsForm.getEmail());
            } else {
                // TODO: Error-Message for taken email address
                model.addAttribute("errorMessage", "");
                return "userSettings";
            }
        }

        // TODO: compare passwords and encrypt new password
        /*model.addAttribute("errorMessage", "");
        return "userSettings";*/

        return "redirect:/survey-admin";

    }
}