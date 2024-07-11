source("MVP\\survey-application\\src\\main\\R\\BarChart.R")
source("MVP\\survey-application\\src\\main\\R\\BarGraph.R")
source("MVP\\survey-application\\src\\main\\R\\PieChart.R")
source("MVP\\survey-application\\src\\main\\R\\DatabaseConection.R")
source("MVP\\survey-application\\src\\main\\R\\AnswerList.R")

library(dplyr)



algo_answers <- function(surveys_id) {
    library(dplyr)
    connection <- database_connection()
    questions <- data_questions(connection)
    answers <- data_answers(connection)
    database_disconnection(connection)

    survey_questions <- subset(questions, survey_id == surveys_id)

    for (i in seq_len(nrow(survey_questions))) {
        row <- survey_questions[i, ]
        row$question_id
        if (row$question_type == "radiobutton") {
            for (x in seq_len(nrow(answers))) {
                answers_questionid <- subset(answers, question_id == row$question_id)
            }
            print(1)

            if (nrow(answers_questionid) != 0) {
                answers_radio_button <- radio_button_list(answers_questionid)
                answers_description <- answer_options_description_radio_button(row)
                answers_radio_button <- answer_clos_value(answers_radio_button)
                answers_description <- answers_description_clos_value(answers_description, answers_radio_button)

                # Berechnen Sie die Gesamtanzahl jeder Antwortoption
                answers_radio_button_counts <- colSums(answers_radio_button == "Y")

                select_Chart(row$question_id, row$question_text, answers_description, answers_radio_button_counts)


                # Datenaufbereitung für Kreisdiagramm
                content_vector <- apply(answers_description, 2, paste, collapse = " ")
                data <- data.frame("category" = content_vector, "amount" = answers_radio_button_counts)
                # Zeilennamen entfernen
                rownames(data) <- NULL

                # piechart(data, row$question_text, output_path)
            }
        } else if (row$question_type == "checkbox") {
            for (x in seq_len(nrow(answers))) {
                answers_questionid <- subset(answers, question_id == row$question_id)
            }
            print(2)

            if (nrow(answers_questionid) != 0) {
                answers_checkbox <- checkbox_list(answers_questionid)
                answers_description_checkbox <- answer_options_description_checkbox(row)
                answers_checkbox <- answer_clos_value(answers_checkbox)
                answers_description_checkbox <- answers_description_clos_value(answers_description_checkbox, answers_checkbox)

                # Berechnen Sie die Gesamtanzahl jeder Antwortoption
                answers_checkbox_counts <- colSums(answers_checkbox == "Y")

                select_Chart(row$question_id, row$question_text, answers_description_checkbox, answers_checkbox_counts)

                # barGraphDiagram(row$question_text, answers_description_checkbox, "Anzahl", answers_checkbox_counts)

                # barChartDiagram(
                #   row$question_text,
                #  answers_description_checkbox,
                # "Anzahl",
                # answers_checkbox_counts
                # )
            }
        } else if (row$question_type == "open text response") {
            for (x in seq_len(nrow(answers))) {
                answers_questionid <- subset(answers, question_id == row$question_id)
            }
            print(3)

            answers_text <- open_text_response_list(answers_questionid)
            if (nrow(answers_questionid) != 0) {
                tab <- matrix(unique(answers_text), ncol = 1, byrow = TRUE)
                colnames(tab) <- row$question_text
                tab <- as.table(tab)
                # print(tab)
            }
        }
    }
}


select_Chart <- function(question_id, question_text, answers_description, answers_counts) {
    output_path <- paste("MVP\\survey-application\\src\\main\\resources\\static\\images\\", question_id, ".png", sep = "")

    if (ncol(answers_description) < 5) {
        # Datenaufbereitung für Kreisdiagramm
        content_vector <- apply(answers_description, 2, paste, collapse = " ")
        data <- data.frame("category" = content_vector, "amount" = answers_counts)
        # Zeilennamen entfernen
        rownames(data) <- NULL
        piechart(data, question_text, output_path)
    } else if (ncol(answers_description) < 8 || max(answers_counts) > 15) {
        output_path <- paste("MVP\\survey-application\\src\\main\\resources\\static\\images\\", question_id, ".png", sep = "")
        png(output_path)

        barGraphDiagram(
            question_text,
            answers_description,
            "Anzahl",
            answers_counts
        )

        dev.off()
    } else {
        output_path <- paste("MVP\\survey-application\\src\\main\\resources\\static\\images\\", question_id, ".png", sep = "")
        png(output_path)

        barChartDiagram(
            question_text,
            answers_description,
            "Anzahl",
            answers_counts
        )

        dev.off()
    }
}


connection <- database_connection()
questions <- data_questions(connection)
answers <- data_answers(connection)
database_disconnection(connection)

algo_answers(30002)








################################################################################
# Algorithm:
#
# 1.Unterscheidung nach Fragetyp
#   a) radiobutton => bis 10 Antworten, nur 1 möglich
#       ~ Kreisgiagramm
#       ~ Säulendiagramm
#       ~ Balkendiagramm
#   b) checkbox => bis 10 Antworten, mehrere möglich
#       ~ Säulendiagramm
#       ~ Balkendiagramm
#       ~ (Liniendiagramm)
#   c) open text response => Texteingabe
#       ~ nur Tabelle
# 2.Zusätzliche berrechnungen
#   a) Nominalskala => zusammenfassen von gleichem
#       ~ bei radiobutton immer für Anzahl der Antwortoptionen
#       ~ bei checkbox immer für Anzahl der Antwortoptionen
#       ~ bei open text response
#   b) Moduls => häufigste Antwort
#   c) Median => genau die Mitte der Anzahl von allen Antworten (änlich wie Mittelwer)
#
