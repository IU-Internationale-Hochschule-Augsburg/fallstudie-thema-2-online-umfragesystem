document.addEventListener("DOMContentLoaded", function () {
    // When loading, all question types are initially hidden
    function hideAllQuestionTypes() {
        var questionTypes = document.querySelectorAll('.col-75');
        questionTypes.forEach(function (type) {
            type.style.display = "none";
        });
    }
    hideAllQuestionTypes();

    // This function displays the corresponding div based on the question type
    function showQuestionType() {
        hideAllQuestionTypes();
        var questionType = document.getElementById("questionType").value;

        if (questionType === "checkbox") {
            document.getElementById("checkboxes").style.display = "block";
        } else if (questionType === "radiobutton") {
            document.getElementById("radiobuttons").style.display = "block";
        } else if (questionType === "open text response") {
            document.getElementById("openTextResponse").style.display = "block";
        }
    }
    showQuestionType();
});


// This function hides all answer options that the survey creator has not filled in for the checkbox question type
document.addEventListener("DOMContentLoaded", function () {
    for (var i = 1; i <= 10; i++) {
        var label = document.getElementById('label' + i);
        var content = label.textContent.trim();

        if (content === "") {
            /* If the answer option is not filled, it will be hidden, and an empty string will be written into it.
            The reason for this is that the variables for the checkbox answer options are initialized with 'N' by default. */
            label.style.display = "none";
            document.getElementById('checkboxAntwortOption' + i).value = ' ';
        } else {
            // If the answer option is filled, it will be displayed
            label.style.display = "block";
        }
    }
});

// This function hides all answer options that the survey creator has not filled in for the radiobutton question type
document.addEventListener("DOMContentLoaded", function () {
    for (var i = 1; i <= 10; i++) {
        var label = document.getElementById('radio' + i);
        var content = label.textContent.trim();

        if (content === "") {
            label.style.display = "none";
        } else {
            label.style.display = "block";
        }
    }
});

// This function writes a 'Y' for checked answer options and 'N' for unchecked available options
function handleCheckboxClick(optionId) {
    var checkbox = document.getElementById('checkbox' + optionId);
    var antwortOption = document.getElementById('checkboxAntwortOption' + optionId);

    if (checkbox.checked) {
        antwortOption.value = 'Y';
    } else {
        antwortOption.value = 'N';
    }
}


/* The function writes a 'Y' for Yes into an answer option if it has been selected. For radio buttons, all other available
answer options are filled with 'N' for No. Options that the Survey Creator has not filled are hidden and filled
with an empty string */
function handleRadioButtonClick(clickedIndex) {
    for (var i = 1; i <= 10; i++) {
        var input = document.getElementById('radiobuttonAntwortOption' + i);
        var label = document.getElementById('radio' + i);
        var text = label ? label.textContent : '';

        if (i === clickedIndex) {
            input.value = 'Y';
        } else {
            // here we distinguish again between an empty answer option and not checked
            if(text.trim() === '') {
                input.value = ' ';
            } else {
                input.value = 'N';
            }

        }
    }
}