package com.surveymaster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String loadLoginScreen(Model model) {
        //model.addAttribute("loginScreen", loginScreen);
        return "loginScreen";
    }
}
