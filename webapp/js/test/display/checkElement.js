function checkRadio(questionId, answerId){
  let radioInputs = document.getElementsByName("answer"+questionId);
  for(const radio of radioInputs){
    if (radio.value === answerId){
      radio.setAttribute("checked", "checked");
    } else {
      radio.removeAttribute("checked");
    }
  }
}

function checkCheckBox(id) {
  document.getElementById("answer" + id).setAttribute("checked", "checked");
}