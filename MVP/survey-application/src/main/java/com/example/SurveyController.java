package com.example;

import com.example.entity.Survey;
import com.example.repository.QuestionRepository;
import com.example.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    //GetMapping Methode noch aus dem Technologietest
	/*@GetMapping("/survey")
	public String getSurveyForm(Model model) {
		InputForm textInputs = new InputForm();
		textInputs.getTextInputs().add(new TextInput("Test1"));
		textInputs.getTextInputs().add(new TextInput("Test2"));
		model.addAttribute("inputForm", textInputs);
		return "textinputs";
	}

	//PostMapping Methode noch aus dem Technologietest
	@PostMapping("/survey")
	@Transactional
	public String SurveyForm(@ModelAttribute("textinputs") InputForm inputForm, Model model) {

		for(int i = 0; i < inputForm.getTextInputs().size(); i++) {
			var umfrage = new Survey(inputForm.getTextInputs().get(i).getContent());
			surveyRepository.save(umfrage);
		}
		model.addAttribute("inputForm", inputForm);
		return "result";
	}*/


    // GetMapping method for the survey view, generating the screen on localhost:8080
    @GetMapping("/survey-admin")
    public String getSurveyAdmin(Model model) {
        SurveyView surveys = new SurveyView();
        // retrieve all surveys from the surveyRepository and add each found survey to the collection.
        // This line is responsible for dynamically listing the surveys
        surveyRepository.findAll().forEach(survey -> surveys.getSurveys().add(survey));

        model.addAttribute("surveyView", surveys);
        return "surveyView";
    }

    // GetMapping method for the survey-add view, generating the screen on localhost:8080
    @GetMapping("/add-survey")
    public String loadAddSurveyView(Model model) {
        SurveyForm surveyForm = new SurveyForm();
        model.addAttribute("survey", surveyForm);
        return "addSurvey";
    }

    // GetMapping method for the Add-Question screen, loading the Add-Question view
    @GetMapping("/add-question")
    public String loadAddQuestion(Model model) {
        model.addAttribute("addQuestion", new SurveyView());
        return "addQuestion";
    }

    @GetMapping("/button-question-handler")
    public String buttonQuestionHandler(@RequestParam("buttonQuestionHandler") String buttonQuestionHandler, Model model) {
        return "";
    }

    //GetMapping for handling the buttons in the survey view (survey-admin)
    @GetMapping("button-handler")
    public String buttonHandler(@RequestParam("buttonHandler") String buttonHandler, Model model) {
        String surveyId;

        // if statement for button handling
        // delete button
        if (buttonHandler.startsWith(",delete_")) {
            // the ID in HTML is appended with an underscore to the button name. Using substring, the ID is split from the label
            surveyId = buttonHandler.substring(8);
            // invocation of the service method to delete the survey with the corresponding ID from the database
            deleteSurvey(surveyId);
        // edit button
        } else if (buttonHandler.startsWith(",edit_")) {
            // the ID in HTML is appended with an underscore to the button name. Using substring, the ID is split from the label
            surveyId = buttonHandler.substring(6);
            // With the surveyID, the corresponding survey is searched for.
            // If it exists, it is saved in the 'survey' variable; otherwise, a NoSuchElementException is thrown
            var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();

            // Setting the ID and title of the survey in questionView
            QuestionsView questionsView = new QuestionsView();
            questionsView.setSurveyId(survey.getSurveyId());
            questionsView.setTitle(survey.getTitle());

            // The surveyID is also used to search in the questionRepository, as the surveyID is also present in the
            // Question table. Then, the Question is set accordingly
            var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));
            questionsView.setQuestions(questions);

            model.addAttribute("questionsView", questionsView);
            return "questionsView";
        // settings button
        } else if (buttonHandler.startsWith(",settings_")) {
            // the ID in HTML is appended with an underscore to the button name. Using substring, the ID is split from the label
            surveyId = buttonHandler.substring(10);

            // With the surveyID, the corresponding survey is searched for.
            // If it exists, it is saved in the 'survey' variable; otherwise, a NoSuchElementException is thrown
            var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();
            model.addAttribute("survey", survey);
            // calling the HTML script for the survey settings
            return "surveySettings";
        }
        return "redirect:/survey-admin";
    }

    // Upon clicking the Save button in the survey-add view, we redirect to /survey-save,
    // where the data is saved accordingly in the database. Afterwards, we return to the surveys view.
    @PostMapping("/survey-save")
    public String saveSurvey(@ModelAttribute SurveyForm surveyForm, Model model) {
        // fetching the survey data from the SurveyForm
        var survey = new Survey(surveyForm.getTitle(), surveyForm.getStartdate(), surveyForm.getEnddate(), surveyForm.getDescription());

        // writing the data into the database
        surveyRepository.save(survey);
        model.addAttribute("survey", surveyForm);
        return "redirect:/survey-admin";
    }

    // Upon clicking the Cancel button, we redirect back to the survey view (without saving data)
    @PostMapping("/survey-admin")
    public String loadAddSurveyViewAgain(Model model) {
        model.addAttribute("addSurvey", new SurveyView());
        return "redirect:/survey-admin";
    }

    // Current functionality for the two buttons in the Add-Question view
    @PostMapping("/questions-view")
    public String loadQuestionViewAgain(Model model) {
        model.addAttribute("addQuestion", new SurveyView());
        return "redirect:/questions-view";
    }


    // service method for button handling (delete data from database)
    private void deleteSurvey(String id) {
        surveyRepository.deleteById(Long.parseLong(id));
    }
}