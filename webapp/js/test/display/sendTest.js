function sendTest() {
  let questionsId = document.getElementsByName("question");
  let answersList = new Array();
  for (const questionIdInput of questionsId) {
    let questionId = questionIdInput.value;
    let answerInputs = document.getElementsByName("answer" + questionId);
    for (const answerInput of answerInputs) {
      if (answerInput.checked === true) {
        answersList.push(answerInput.value);
      }
    }
  }
  let resultMap = new Map();
  resultMap.set("answersId", answersList)
  let json = JSON.stringify(Object.fromEntries(resultMap));
  let testId = localStorage.getItem("testId");
  let url = "http://localhost:8080/test/" + testId;
  createXMLHttpRequest();
  xmlHttp.open("POST", url, false);
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.onreadystatechange = handleStateChangeCheckTest;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send(json);
}

function handleStateChangeCheckTest() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      document.location = "testInfo.html";
    } else {

    }
  }
}