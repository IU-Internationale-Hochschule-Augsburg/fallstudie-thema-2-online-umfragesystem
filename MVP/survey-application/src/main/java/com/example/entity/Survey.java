package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    private Date startDate;

    private Date endDate;

    private String description;

    //Dieses Attribut ist noch aus dem Technologietest übrig
    //private String textInput;


    public Survey() {
    }

    public Survey(String title) {
        this.title = title;
    }
}
