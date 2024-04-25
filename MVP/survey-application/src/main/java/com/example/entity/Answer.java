package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table
@Data

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    private Long questionId;

    private Long userId;

    private String radiobutton;

    private String checkbox1;
    private String checkbox2;
    private String checkbox3;
    private String checkbox4;
    private String checkbox5;
    private String checkbox6;
    private String checkbox7;
    private String checkbox8;
    private String checkbox9;
    private String checkbox10;

    private String textinput;

    public Answer() {
    }
}
