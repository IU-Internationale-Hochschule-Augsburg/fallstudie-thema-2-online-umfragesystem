package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
// Renamed because 'User' is reserved
@Table(name = "SurveyUser")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String surname;

    private String firstname;

    private String email;

    // So far plaintext, still not certain
    private String password;

    public User(){}
}
