function handleCheckboxClick(optionId) {
    var checkbox = document.getElementById('checkbox' + optionId);
    var antwortOption = document.getElementById('antwortOption' + optionId);

    if (checkbox.checked) {
        antwortOption.value = 'Y';
    } else {
        antwortOption.value = 'N';
    }
}