package com.surveymaster.dataAnalysis;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;

public class AnalysisAlgorithm {

    public static void answersChart(long survey_id) {
        // Create an RCaller instance
        RCaller caller = RCaller.create();
        // Create an RCode instance
        RCode code = RCode.create();

        // Path to the R script file
        String scriptPath = "MVP/survey-application/src/main/R/Algorithm.R";

        // Load and run the R script from the file
        code.clear();
        code.addRCode("source('" + scriptPath + "')");
        code.addRCode("algo_answers(" + survey_id + ")");

        // Set the RCode instance to RCaller
        caller.setRCode(code);

        // Run the R script
        caller.runOnly();
    }

    public static void compareAnswersChart(long question_id_1, long question_id_2) {
        // Create an RCaller instance
        RCaller caller = RCaller.create();
        // Create an RCode instance
        RCode code = RCode.create();

        // Path to the R script file
        String scriptPath = "MVP/survey-application/src/main/R/AlgoCompareAnswers.R";

        // Load and run the R script from the file
        code.clear();
        code.addRCode("source('" + scriptPath + "')");
        code.addRCode("algo_compare_answers(" + question_id_1 + "," + question_id_2 + ")");

        // Set the RCode instance to RCaller
        caller.setRCode(code);

        // Run the R script
        caller.runOnly();
    }
}
