function handleCheckboxClick(optionId) {
    var checkbox = document.getElementById('checkbox' + optionId);
    var antwortOption = document.getElementById('antwortOption' + optionId);

    if (checkbox.checked) {
        antwortOption.value = 'Y';
    } else {
        antwortOption.value = 'N';
    }
}

document.addEventListener('DOMContentLoaded', function () {
    var labels = document.querySelectorAll('label.container2');
    labels.forEach(function (label) {
        var checkbox = label.querySelector('input[type="checkbox"]');
        var answerOptionInput = label.querySelector('input[type="hidden"]');
        if (!answerOptionInput || !answerOptionInput.value.trim()) {
            label.style.display = 'none';
        }
    });
});