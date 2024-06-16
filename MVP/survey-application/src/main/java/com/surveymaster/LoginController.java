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

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

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

        if (user == null || email == null) {
            User newUser = new User();
            newUser.setUsername(registerForm.getUsername());
            newUser.setFirstname(registerForm.getFirstname());
            newUser.setSurname(registerForm.getSurname());
            newUser.setEmail(registerForm.getEmail());
            var encryptedPassword = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());
            if (BCrypt.checkpw(registerForm.getConfirmPassword(), encryptedPassword)) {
                newUser.setPassword(encryptedPassword);
            } // TODO: else case/error handling is missing

            userRepository.save(newUser);
            model.addAttribute(newUser);
        }

        return "redirect:/login";
    }
}