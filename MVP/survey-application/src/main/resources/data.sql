-- This script pre-fills the h2-database with test data at the beginning
INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE)
VALUES ('2024-06-28', '2024-04-28', 10001, 'Das ist eine Testumfrage', 'Testumfrage 1');

INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE)
VALUES ('2024-06-30', '2024-05-03', 10002, 'Das ist eine weitere Testumfrage', 'Testumfrage 2');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, QUESTION_TYPE, RADIOBUTTON1, RADIOBUTTON2)
VALUES (1, 10001, 'Wie alt bist Du?', 'Radiobutton', '<18', '>=18');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, QUESTION_TYPE)
VALUES (2, 10001, 'Wie heißt Du?', 'Textinput');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, QUESTION_TYPE, CHECKBOX1, CHECKBOX2, CHECKBOX3)
VALUES (3, 10001, 'Welche Farben sind Deine Lieblingsfarben?', 'Checkbox', 'gelb', 'blau', 'rot');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, QUESTION_TYPE, CHECKBOX1, CHECKBOX2, CHECKBOX3, CHECKBOX4)
VALUES (4, 10002, 'Wie lautet Deine Lieblingsprogrammiersprache?', 'Checkbox', 'Java', 'C', 'OCaml', 'C++');
