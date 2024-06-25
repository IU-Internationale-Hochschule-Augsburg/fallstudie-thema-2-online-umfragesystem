package com.surveymaster;

import lombok.Data;

@Data
public class UserSettingsForm {
    private String username;

    private String surname;

    private String firstname;

    private String email;

    private String oldPassword;

    private String password;

    private String confirmPassword;
}
