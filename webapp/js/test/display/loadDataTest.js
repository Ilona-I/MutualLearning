let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentTest() {
  let currentLogin = localStorage.getItem("login")
  let currentRole = localStorage.getItem("role");
  let currentStatus = localStorage.getItem("status");
  if (currentLogin === null) {
    document.location = '../user/logIn.html'
  } else if (!(currentRole === "USER" || currentRole === "PREMIUM_USER") || currentStatus !== "ACTIVE") {
    document.location = '../error/forbidden.html'
  } else {
    let testId = localStorage.getItem("testId");
    const url = "http://localhost:8080/test/" + testId;
    createXMLHttpRequest();
    let user = '{"login":"' + currentLogin + '}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeGetTest;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

function handleStateChangeGetTest() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLGetTest(xmlHttp.responseText);
    } else {
      document.location = '../error/forbidden.html'
    }
  }
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentTest();
});

function jsonToHTMLGetTest(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let questions = dataMap.get("questions");
  document.getElementById("testTitle").innerText = title.toString();
  let innerHtml = "";
  let i = 1;
  for (const element of questions) {
    let question = JSON.parse(JSON.stringify(element));
    let questionMap = new Map(Object.entries(question));
    let questionId = questionMap.get("questionId");
    let questionText = questionMap.get("text");
    let questionType = questionMap.get("type");

    innerHtml += "<div id='questionBlock" + questionId
        + "' style=\"margin-top: 10px;padding: 20px;  background-color: #f3f3fd\">\n"
        + "        <h6>Питання " + i + ": </h6>"
        + "        <input name='question' type='text' value='" + questionId
        + "' hidden> "
        + "        <p id='questionText" + questionId + "'>" + questionText
        + "</p>\n"
        + "       <!-- Початок варіантів відповідей -->\n"
        + "        <div style=\"width: 100%; padding: 20px;\">";

    let answers = questionMap.get("answers");
    for (const elementAnswer of answers) {
      let answer = JSON.parse(JSON.stringify(elementAnswer));
      let answerMap = new Map(Object.entries(answer));
      let answerId = answerMap.get("answerId");
      let answerText = answerMap.get("text");
      innerHtml += "  <!-- Початок варіанту відповіді -->\n"
          + "          <div id='answerBlock" + answerId
          + "' class=\"row\" style=\"width: 100%; margin-top: 10px;\" ";
      if (questionType === "SEVERAL_ANSWERS") {
        innerHtml += " onclick=\"checkCheckBox(" + answerId + ")\" ";
      } else {
        innerHtml += " onclick=\"checkRadio('" + questionId + "','"
            + answerId + "')\" ";
      }
      innerHtml += ">\n"
          + "          <input id='answer" + answerId + "'"
          + "                 value='" + answerId + "'"
          + "                 name='answer" + questionId + "' "
          + "                 type='";
      if (questionType === "SEVERAL_ANSWERS") {
        innerHtml += "checkbox";
      } else {
        innerHtml += "radio";
      }
      innerHtml += "'>"
          + "                <label style='margin-left: 10px;' for='answer"
          + answerId + "' > "
          + answerText + "</label>\n"
          + "          </div>\n"
          + "          <!-- Кінець варіанту відповіді -->";
    }
    innerHtml += "\n"
        + "        </div>\n"
        + "        <!-- Кінець  варіантів відповідей -->\n"
        + "      </div>\n"
        + "      <!-- Кінець питання-->";
    i = i + 1;
  }
  document.getElementById("questions").innerHTML = innerHtml;
}