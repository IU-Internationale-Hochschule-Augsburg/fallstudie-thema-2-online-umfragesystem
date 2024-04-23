package com.example.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    private Long surveyId;
    private String title;

    private Date startDate;

    private Date endDate;

    //Dieses Attribut ist noch aus dem Technologietest übrig
    //private String textInput;



    protected Survey(){}

    public Long getSurveyId() {
        return surveyId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /*public Survey(String textInput) {
        this.textInput = textInput;
    }*/

    /*public String getTextInput() {
        return textInput;
    }*/
}
