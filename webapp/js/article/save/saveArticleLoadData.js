let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentArticle() {
  let currentLogin = localStorage.getItem("login")
  let currentRole = localStorage.getItem("role");
  let currentStatus = localStorage.getItem("status");
  if (currentLogin === null) {
    document.location = '../user/logIn.html'
  } else if (!(currentRole === "USER" || currentRole === "PREMIUM_USER")
      || currentStatus !== "ACTIVE") {
    document.location = '../error/forbidden.html'
  } else {
    let articleId = localStorage.getItem("articleId");
    if (articleId == null) {
      let articleType = localStorage.getItem("articleType");
      setStructure(articleType);
      return;
    }
    const url = "http://localhost:8080/articles/edit?id=" + articleId;
    createXMLHttpRequest();
    let user = '{"login":"' + currentLogin + '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

function setStructure(articleType) {
  if (articleType === "CREATE_QUESTION") {
    document.getElementById("displayMessage").innerText = "Створення запитання";
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
    setTypeQuestion();
    localStorage.removeItem("CREATE_QUESTION")
  } else {
    document.getElementById("displayMessage").innerText = "Створення статті";
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
    setTypeArticle()
    let innerHTML = "<div id='articleBlock0'>"
        + "<input type='number' "
        + "      id='sequenceNumber0'"
        + "      name='sequenceNumber'"
        + "      value='0' hidden>"
        + "</div>"
    innerHTML += getAddNewElementButtonBlock(0, 0);
    document.getElementById("articleBody").innerHTML = innerHTML;
  }
}

function setTypeArticle() {
  document.getElementById("articleBody").removeAttribute("hidden");
}

function setTypeQuestion() {
  document.getElementById("articleBody").hidden = true;
}

function handleStateChange() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTML(xmlHttp.responseText);
    } else {
      document.location = '../error/forbidden.html'
    }
  }
}

function jsonToHTML(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let type = dataMap.get("type");
  let marks = dataMap.get("marks");
  document.getElementById("articleTitle").innerText = title.toString();
  setMarks(marks);
  if (type === "QUESTION") {
    document.getElementById(
        "displayMessage").innerText = "Надання відповіді на запитання";
    document.getElementById("articleTitle").setAttribute("readonly", "");
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
  } else {
    document.getElementById("displayMessage").innerText = "Створення статті";
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
  }
  setTypeArticle();
  let articleParts = dataMap.get("articleParts");
  setArticleBody(articleParts);
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentArticle()
});
