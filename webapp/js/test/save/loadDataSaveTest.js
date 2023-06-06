let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentTestToUpdate() {
  let currentLogin = localStorage.getItem("login")
  let currentRole = localStorage.getItem("role");
  let currentStatus = localStorage.getItem("status");
  if (currentLogin === null) {
    document.location = '../user/logIn.html'
  } else if (!(currentRole === "USER" || currentRole === "PREMIUM_USER")|| currentStatus !== "ACTIVE") {
    document.location = '../error/forbidden.html'
  } else {
    let testId = localStorage.getItem("testId");
    if (testId === null) {
      return;
    }
    const url = "http://localhost:8080/test/update/" + testId;
    createXMLHttpRequest();
    let user = '{"login":"' + currentLogin + '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeGetTestToUpdate;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

function handleStateChangeGetTestToUpdate() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLGetTestToUpdate(xmlHttp.responseText);
    } else {
      document.location = '../error/forbidden.html'
    }
  }
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentTestToUpdate();
});

function jsonToHTMLGetTestToUpdate(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let questions = dataMap.get("questions");
  document.getElementById("testTitle").value = title.toString();
  let innerHtml = "";
  for (const element of questions) {
    let question = JSON.parse(JSON.stringify(element));
    let questionMap = new Map(Object.entries(question));
    let questionId = questionMap.get("questionId");
    let questionText = questionMap.get("text");
    let questionType = questionMap.get("type");

    innerHtml += "<div id='questionBlock" + questionId
        + "' style=\"border-color: #949393; margin-top: 10px; border-style: solid; border-width: 1px; padding: 20px;  background-color: #eeeeff\">\n"
        + "        <h6>Питання: </h6>"
        + "        <input name='question' type='text' value='" + questionId
        + "' hidden> "
        + "        <textarea id='questionText" + questionId
        + "' style='width: 85%' class=\"form-control input_info\" placeholder=\"Question\">"
        + questionText + "</textarea>\n"
        + "        <button onclick='deleteQuestionBlock(" + questionId
        + ")' class=\"btn btn-danger\" style=\"height: 40px; float: right; margin-top: -40px;\">\n"
        + "          Видалити\n"
        + "        </button>\n"
        + "        <div style=\"margin-top: 10px; margin-left: 5px;\">\n"
        + "          <div onclick='checkOneAnswer(" + questionId
        + ")' class=\"custom-control custom-radio custom-control-inline\">\n"
        + "            <input type=\"radio\" id=\"customRadioOne" + questionId
        + "\" name=\"customRadio" + questionId
        + "\" class=\"custom-control-input\"\n";
    if (questionType === "ONE_ANSWER") {
      innerHtml += " checked ";
    }

    innerHtml += ">\n"
        + "            <label class=\"custom-control-label\" for=\"customRadioOne"
        + questionId + "\">Один варіант відповіді</label>\n"
        + "          </div>\n"
        + "          <div onclick='checkSeveralAnswers(" + questionId
        + ")' class=\"custom-control custom-radio custom-control-inline\">\n"
        + "            <input type=\"radio\" id=\"customRadioSeveral"
        + questionId + "\" name=\"customRadio" + questionId
        + "\" class=\"custom-control-input\"";
    if (questionType === "SEVERAL_ANSWERS") {
      innerHtml += " checked ";
    }
    innerHtml += ">\n"
        + "            <label class=\"custom-control-label\" for=\"customRadioSeveral"
        + questionId + "\">Декілька варіантів\n"
        + "              відповіді</label>\n"
        + "          </div>\n"
        + "        </div>"
        + "       <!-- Початок варіантів відповідей -->\n"
        + "        <div style=\"width: 100%; padding: 20px;\">";

    let answers = questionMap.get("answers");
    for (const elementAnswer of answers) {
      let answer = JSON.parse(JSON.stringify(elementAnswer));
      let answerMap = new Map(Object.entries(answer));
      let answerId = answerMap.get("answerId");
      let answerText = answerMap.get("text");
      let answerMark = answerMap.get("mark");

      innerHtml += "  <!-- Початок варіанту відповіді -->\n"
          + "          <div id='answerBlock" + answerId
          + "' class=\"row\" style=\"width: 100%; margin-top: 10px; background-color: #e4e7ec; \">\n"
          + "          <input name='answer" + questionId
          + "' type='text' value='" + answerId + "' hidden>"
          + "            <div style=\"width: 80%; padding: 20px;\">\n"
          + "              <h6>Варіант відповіді:</h6>\n"
          + "              <div>\n"
          + "                <p>Текст варіанту відповіді:</p>\n"
          + "                <textarea id='answerText" + answerId
          + "' class=\"form-control input_info\" placeholder=\"Answer\"> "
          + answerText + "</textarea>\n"
          + "              </div>\n"
          + "              <div class=\"row\" style=\"margin-top: 20px; margin-left: 10px;\">\n"
          + "                <p>Оцінка: </p>\n"
          + "                <input id='answerMark" + answerId
          + "' class=\"form-control input_info\" min=\"0\" type=\"number\" value=\""
          + answerMark + "\"\n"
          + "                       style=\"width: 100px; margin-left: 20px; margin-top: -10px;\">\n"
          + "              </div>\n"
          + "\n"
          + "            </div>\n"
          + "            <div\n"
          + "                style=\"padding: 20px; border-left-color: #d2cece; border-left-style: solid; border-left-width: 1px;\">\n"
          + "              <button onclick='deleteAnswerBlock(" + answerId
          + ")' class=\"btn btn-danger\" style=\"height: 40px;\">Видалити</button>\n"
          + "            </div>\n"
          + "\n"
          + "          </div>\n"
          + "          <!-- Кінець варіанту відповіді -->";
    }

    innerHtml += "\n"
        + "          <div id='addAnswerBlockButton" + questionId
        + "' style=\"margin-top: 20px;\">\n"
        + "            <button onclick='addAnswerBlock(" + questionId
        + ")' class=\"btn btn-dark\">Додати варіант відповіді</button>\n"
        + "          </div>\n"
        + "        </div>\n"
        + "        <!-- Кінець  варіантів відповідей -->\n"
        + "      </div>\n"
        + "      <!-- Кінець питання-->";
  }
  document.getElementById("questions").innerHTML = innerHtml;
}