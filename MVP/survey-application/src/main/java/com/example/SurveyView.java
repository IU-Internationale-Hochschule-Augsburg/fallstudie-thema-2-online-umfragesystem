package com.example;

import com.example.entity.Survey;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SurveyView {
    private List<Survey> surveys = new ArrayList<>();
}