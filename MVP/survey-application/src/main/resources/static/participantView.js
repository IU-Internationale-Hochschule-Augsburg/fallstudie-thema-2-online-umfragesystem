function handleCheckboxClick(optionId) {
    var checkbox = document.getElementById('checkbox' + optionId);
    var antwortOption = document.getElementById('antwortOption' + optionId);

    if (checkbox.checked) {
        antwortOption.value = 'Y';
    } else {
        antwortOption.value = 'N';
    }
}

function handleRadioButtonClick(clickedIndex) {
    for (var i = 1; i <= 10; i++) {
        var input = document.getElementById('antwortOption' + i);
        if (i === clickedIndex) {
            input.value = 'Y';
        } else {
            input.value = 'N';
        }
    }
}