// function to hide the +Answer button and remember the question types
// function to prepopulate the input fields with 'radiobutton'
window.onload = function() {
    updateQuestionType('radiobutton');
}

// function for the cancel button
function goBack() {
    window.history.back();
}

function updateQuestionType(qType) {
    // to remember radio buttons by default
    var addAnswerButton = document.getElementById('addAnswerButton');
    var answerOptionsDiv = document.getElementById('answerOptions');

    var selectedRadiobutton = document.querySelector('input[type=radio]:checked').value;
    document.getElementById('questionType').value = selectedRadiobutton;

    // hide the +Answer button if the type is free text. Otherwise, by default, display one answer option
    if(qType === 'radiobutton' || qType === 'checkbox') {
        addAnswerButton.style.display = 'block';

        if(answerCount === 0) {
            answerCount = 1;
        }
    } else {
        addAnswerButton.style.display = 'none';
        while(answerOptionsDiv.firstChild) {
            answerOptionsDiv.removeChild(answerOptionsDiv.firstChild);
        }
        answerCount = 0;
    }
}

var answerCount = 1;

// function to add an answer option; the function copies the answer options template
function addAnswerOption() {
    // if-clause to limit the number of options that can be added. You can create a maximum of 10 options
    if(answerCount < 11) {
        // clone the answer options template
        var tempElement = document.getElementById('answerOptionTemplate');
        var temp = tempElement.cloneNode(true);
        temp.id = 'answerOption' + answerCount;
        temp.style.display = 'block';

        // change label
        var label = temp.querySelector('label');
        label.innerText = 'Antwortoption ' + answerCount + ' ';
        document.getElementById('answerOptions').appendChild(temp);

        answerCount++;
    }
}

// function to delete the selected answer option
function deleteAnswerOption(button) {
    // if clause to limit the delete function
    if (answerCount > 3) {
        var answerDiv = button.parentElement.parentElement;
        answerDiv.remove();
        answerCount--;
    }
}

// if the +Answer button is clicked a new screen should load
document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('addAnswerButton').addEventListener('click', function(event) {
        event.preventDefault();
        window.location.href = '/answers-view';
    });
});