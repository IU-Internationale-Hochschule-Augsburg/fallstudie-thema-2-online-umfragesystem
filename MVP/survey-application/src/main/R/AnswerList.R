library(dplyr)



# Entfernt die Spalten (option3-10, id, text) und schiebt die 10 hinter die 9
radio_button_list <- function(answers) {
    answers <- answers %>% relocate(answer_option10, .after = answer_option9)
    cols_to_keep <- c(
        TRUE, TRUE, TRUE, TRUE, TRUE,
        TRUE, TRUE, TRUE, TRUE, TRUE,
        FALSE, FALSE, FALSE, FALSE
    )
    answers <- answers[, cols_to_keep]
    return(answers)
}

# Entfernt die Spalten (id, text) und schiebt die 10 hinter die 9
checkbox_list <- function(answers) {
    answers <- answers %>% relocate(answer_option10, .after = answer_option9)
    cols_to_keep <- c(
        TRUE, TRUE, TRUE, TRUE, TRUE,
        TRUE, TRUE, TRUE, TRUE, TRUE,
        FALSE, FALSE, FALSE, FALSE
    )
    answers <- answers[, cols_to_keep]
    return(answers)
}

# Entfernt die Spalten (id, option1-10) und schiebt die 10 hinter die 9
open_text_response_list <- function(answers) {
    cols_to_keep <- c(
        FALSE, FALSE, FALSE, FALSE, FALSE,
        FALSE, FALSE, FALSE, FALSE, FALSE,
        FALSE, FALSE, FALSE, TRUE
    )
    answers <- answers[, cols_to_keep]
    return(answers)
}


# Entfernt die Spalten (id, type, description, text) und schiebt die 10 hinter die 9
answer_options_description_checkbox <- function(question) {
    question <- question %>% relocate(answer_option10, .after = answer_option9)
    cols_to_keep <- c(
        FALSE, FALSE, FALSE, TRUE, TRUE,
        TRUE, TRUE, TRUE, TRUE, TRUE,
        TRUE, TRUE, TRUE, FALSE, FALSE
    )
    question <- question[, cols_to_keep]
    return(question)
}

# Entfernt die Spalten (id, type, option3-10, description, text) und schiebt die 10 hinter die 9
answer_options_description_radio_button <- function(question) {
    question <- question %>% relocate(answer_option10, .after = answer_option9)
    cols_to_keep <- c(
        FALSE, FALSE, FALSE, TRUE, TRUE,
        TRUE, TRUE, TRUE, TRUE, TRUE,
        TRUE, TRUE, TRUE, FALSE, FALSE
    )
    question <- question[, cols_to_keep]
    return(question)
}








answer_clos_value <- function(answers) {
    cols_to_keep <- c(
        if ((answers$answer_option1[1] == "Y") || (answers$answer_option1[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option2[1] == "Y") || (answers$answer_option2[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option3[1] == "Y") || (answers$answer_option3[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option4[1] == "Y") || (answers$answer_option4[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option5[1] == "Y") || (answers$answer_option5[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option6[1] == "Y") || (answers$answer_option6[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option7[1] == "Y") || (answers$answer_option7[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option8[1] == "Y") || (answers$answer_option8[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option9[1] == "Y") || (answers$answer_option9[1] == "N")) {
            TRUE
        } else {
            FALSE
        },
        if ((answers$answer_option10[1] == "Y") || (answers$answer_option10[1] == "N")) {
            TRUE
        } else {
            FALSE
        }
    )
    answers <- answers[, cols_to_keep]
    return(answers)
}


answers_description_clos_value <- function(question, answers) {
    # Vektor mit TRUE-Werten erstellen
    result <- rep(TRUE, ncol(answers))

    # Anzahl der zusätzlichen FALSE-Werte ermitteln
    fehlende_eintraege <- 10 - length(result)

    # Einen Vektor mit FALSE-Werten erstellen
    zusaetzliche_false <- rep(FALSE, fehlende_eintraege)

    # Kombiniere TRUE- und FALSE-Vektoren, um das endgültige Ergebnis zu erhalten
    cols_to_keep <- c(result, zusaetzliche_false)

    question <- question[, cols_to_keep]
    return(question)
}
