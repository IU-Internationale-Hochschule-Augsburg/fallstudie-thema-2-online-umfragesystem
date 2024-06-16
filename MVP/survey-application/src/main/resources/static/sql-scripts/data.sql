-- This script pre-fills the h2-database with test data at the beginning
INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE, USER_ID)
VALUES ('2024-06-28', '2024-04-28', 10001, 'Das ist eine Testumfrage', 'Testumfrage 1', 0);

INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE, USER_ID)
VALUES ('2024-06-30', '2024-05-03', 10002, 'Das ist eine weitere Testumfrage', 'Testumfrage 2', 0);

INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE, USER_ID)
VALUES ('2024-06-30', '2024-05-23', 10003, 'Das ist eine weitere Testumfrage für Checkboxes', 'Checkbox-Test', 0);

INSERT INTO SURVEY (END_DATE, START_DATE, SURVEY_ID, DESCRIPTION, TITLE, USER_ID)
VALUES ('2024-05-31', '2024-05-29', 10004, 'Diese Umfrage dient zum Testen des MVPs.', 'Leons Testumfrage', 0);

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10001, 10001, 'Wie heißt Du?', '', 'open text response', '', '', '', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10002, 10001, 'Wie alt bist Du?', '', 'radiobutton', '<18', '>=18', '', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10003, 10001, 'Welche Farben sind Deine Lieblingsfarben?', '', 'checkbox', 'gelb', 'blau', 'rot', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10004, 10002, 'Wie lautet Deine Lieblingsprogrammiersprache?', '', 'radiobutton', 'Java', 'C', 'OCaml', 'C++', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10005, 10003, 'Welche Automarken kennst Du?', '', 'checkbox', 'Mercedes Benz', 'BMW', 'BYD', 'Renault', '', 'Chevrolet', 'Mini', 'MG', 'Opel', 'Hyundai');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10006, 10004, 'Auf einer Skala von 1 bis 5: Wie motiviert bist Du?', '', 'radiobutton', '1', '2', '3', '4', '5', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10007, 10004, 'Wie lautet Dein Motivationsspruch?', '', 'open text response', '', '', '', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10008, 10004, 'Welche Umlaute und Sonderzeichen kennst Du denn so?', 'Probiere ruhig ein paar aus :)', 'open text response', '', '', '', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10009, 10004, 'Welche Farben gefallen Dir am besten?', '', 'checkbox', 'gelb', 'blau', 'rot', 'grün', 'orange', 'türkis', 'weiß', ' ', 'Baum', 'lila-blass-blau');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10010, 10004, 'Welche Automarken kennst Du?', '', 'checkbox', 'Mercedes Benz', 'BMW', 'BYD', 'Renault', '', 'Chevrolet', 'Mini', 'MG', 'Opel', 'Hyundai');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT, DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10011, 10004, 'Welche Sonderzeichen machen Probleme?', '', 'radiobutton', '?', 'ß', 'ö', 'Ü', 'Ä', '_', '$%', '\', '*', '$');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT,DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10012, 10004, 'Probiere Mal Tab und/oder Leerzeichen abzuschicken:', '', 'open text response', '', '', '', '', '', '', '', '', '', '');

INSERT INTO QUESTION (QUESTION_ID, SURVEY_ID, QUESTION_TEXT,DESCRIPTION, QUESTION_TYPE, ANSWER_OPTION1, ANSWER_OPTION2, ANSWER_OPTION3, ANSWER_OPTION4, ANSWER_OPTION5, ANSWER_OPTION6, ANSWER_OPTION7, ANSWER_OPTION8, ANSWER_OPTION9, ANSWER_OPTION10)
VALUES (10013, 10004, 'Hier kannst Du einfach noch Eingaben testen:', '', 'open text response', '', '', '', '', '', '', '', '', '', '');