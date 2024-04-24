package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
// Umbenannt, da "User" reserviert ist
@Table(name = "SurveyUser")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String surname;

    private String firstname;

    private String email;

    // Bisher Klartext, ist noch nicht sicher
    private String password;

    public User(){}
}
