function sendTestChanges() {
  let resultMap = new Map();
  let title = document.getElementById("testTitle").value;
  resultMap.set("title", title);
  let questionsId = document.getElementsByName("question");
  let questionsList = new Array();
  for (const questionIdInput of questionsId) {
    let questionId = questionIdInput.value;
    let questionsMap = new Map();
    let questionText = document.getElementById(
        "questionText" + questionId).value;
    let questionType = getQuestionType(questionId);

    let answersId = document.getElementsByName("answer" + questionId);
    let answersList = new Array();
    for (const answerIdInput of answersId) {
      let answerId = answerIdInput.value;
      let answerText = document.getElementById("answerText" + answerId).value;
      let answerMark = document.getElementById("answerMark" + answerId).value;
      let answersMap = new Map();
      answersMap.set("answerId", answerId);
      answersMap.set("text", answerText);
      answersMap.set("mark", answerMark);
      answersList.push(Object.fromEntries(answersMap));
    }
    questionsMap.set("questionId", questionId);
    questionsMap.set("text", questionText);
    questionsMap.set("type", questionType);
    questionsMap.set("answers", answersList);
    questionsList.push(Object.fromEntries(questionsMap));
  }
  resultMap.set("questions", questionsList);

  let json = JSON.stringify(Object.fromEntries(resultMap));
  console.log(json);
  let testId = localStorage.getItem("testId");
  let url;
  if (testId !== null) {
    url = "http://localhost:8080/test/" + testId;
    createXMLHttpRequest();
    xmlHttp.open("PUT", url, false);
  } else {
    url = "http://localhost:8080/test";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
  }
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.onreadystatechange = handleStateChangeSaveTest;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send(json);
}
function handleStateChangeSaveTest() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
       document.location="../article/article.html";
    } else {
      //document.location = "../html/error.html";
    }
  }
}

function getQuestionType(questionId) {
  let customRadioOne = document.getElementById("customRadioOne" + questionId);
  if (customRadioOne.checked === true) {
    return "ONE_ANSWER";
  }
  return "SEVERAL_ANSWERS";
}