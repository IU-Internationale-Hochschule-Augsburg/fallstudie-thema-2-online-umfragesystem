package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
public class Survey {
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long surveyId;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    //Dieses Attribut ist noch aus dem Technologietest übrig
    //private String textInput;


    public Survey() {
    }

    public Survey(String title) {
        this.title = title;
    }

    public Survey(String title, LocalDate startDate, LocalDate endDate, String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
