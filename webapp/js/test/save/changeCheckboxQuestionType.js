function checkOneAnswer(questionId) {
  document.getElementById("customRadioOne" + questionId).setAttribute("checked",
      "checked");
  document.getElementById("customRadioSeveral" + questionId).removeAttribute(
      "checked");
}

function checkSeveralAnswers(questionId) {
  document.getElementById("customRadioOne" + questionId).removeAttribute(
      "checked");
  document.getElementById("customRadioSeveral" + questionId).setAttribute(
      "checked", "checked");
}
