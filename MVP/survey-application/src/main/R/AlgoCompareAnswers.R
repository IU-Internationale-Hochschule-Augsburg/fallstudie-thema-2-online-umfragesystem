source("MVP\\survey-application\\src\\main\\R\\DatabaseConection.R")
source("MVP\\survey-application\\src\\main\\R\\AnswerList.R")




algo_compare_answers <- function(question_id_1, question_id_2) {
    library(dplyr)
    library(plotly)

    connection <- database_connection()
    questions <- data_questions(connection)
    answers <- data_answers(connection)
    database_disconnection(connection)


    question_1 <- subset(questions, question_id == question_id_1)
    question_2 <- subset(questions, question_id == question_id_2)

    for (x in seq_len(nrow(answers))) {
        answers_questionid_1 <- subset(answers, question_id == question_id_1)
        answers_questionid_2 <- subset(answers, question_id == question_id_2)
    }

    if (nrow(answers_questionid_1) != 0) {
        answers_questionid_1 <- checkbox_list(answers_questionid_1)
        answers_questionid_1 <- answer_clos_value(answers_questionid_1)

        answers_description_1 <- answer_options_description_checkbox(question_1)
        answers_description_1 <- answers_description_clos_value(answers_description_1, answers_questionid_1)
    }
    if (nrow(answers_questionid_2) != 0) {
        answers_questionid_2 <- checkbox_list(answers_questionid_2)
        answers_questionid_2 <- answer_clos_value(answers_questionid_2)

        answers_description_2 <- answer_options_description_checkbox(question_2)
        answers_description_2 <- answers_description_clos_value(answers_description_2, answers_questionid_2)
    }

    if (nrow(answers_questionid_2) != 0 && nrow(answers_questionid_1) != 0) {
        zero_matrix <- matrix(0, nrow = ncol(answers_questionid_1), ncol = ncol(answers_questionid_2))

        rownames(zero_matrix) <- answers_description_1
        colnames(zero_matrix) <- answers_description_2


        for (r1 in seq_len(nrow(answers_questionid_1))) {
            for (c1 in seq_len(ncol(answers_questionid_1))) {
                if (answers_questionid_1[r1, c1] == "Y") {
                    for (r2 in seq_len(nrow(answers_questionid_2))) {
                        for (c2 in seq_len(ncol(answers_questionid_2))) {
                            if (answers_questionid_2[r2, c2] == "Y") {
                                zero_matrix[c1, c2] <- zero_matrix[c1, c2] + 1
                            }
                        }
                    }
                }
            }
        }

        if (ncol(zero_matrix) > 1 && nrow(zero_matrix) > 1) {
            output_path <- paste("MVP\\survey-application\\src\\main\\resources\\static\\images\\", question_id_1, question_id_2, ".png", sep = "")
            png(output_path, width = 1000, height = 700)

            heatmap(
                zero_matrix,
                Colv = NA,
                Rowv = NA,
                scale = "none",
                col = colorRampPalette(c("#d2cfa3", "#02a702"))(15),
                breaks = seq(1, 15, length.out = 16),
                margins = c(13, 7)
            )

            dev.off()
        }
    }
}

library(dplyr)


# algo_compare_answers(30007,30001)


# for (i in seq(from = 1, to = (nrow(questions) - 1))) {
#     for (m in seq(from = 1, to = (nrow(questions) - 1))) {
#         if (i != m) {
#             row1 <- questions[i, ]
#             row2 <- questions[m, ]
#             if (row1$survey_id == row2$surveys_id) {
#                 algo_compare_answers(row1$question_id, row2$question_id)
#             }
#         }
#     }
# }
