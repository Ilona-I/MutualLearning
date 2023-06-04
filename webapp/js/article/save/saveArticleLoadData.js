let xmlHttp;
const articleTypeQuestion = "QUESTION";

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentArticle() {
  localStorage.setItem("userLogin", "user1")
  let articleId = localStorage.getItem("articleId");
  if (articleId == null) {
    let articleType = localStorage.getItem("articleType");
    setStructure(articleType);
    return;
  }
  const url = "http://localhost:8080/articles/edit?id=" + articleId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChange;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function setStructure(articleType) {
  if (articleType === articleTypeQuestion) {
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
    setTypeQuestion();
  } else {
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
    setTypeArticle()
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
      document.location = "../html/error.html";
    }
  }
}

function jsonToHTML(jsonString) {
  localStorage.removeItem("isQuestionAnswer");
  let questionCreatorLogin = localStorage.getItem("questionCreatorLogin");
  let currentUser = localStorage.getItem("currentUser");
  let isQuestionAnswer = false;
  if(questionCreatorLogin!==null&&currentUser!==questionCreatorLogin){
    document.getElementById("articleTitle").setAttribute("readonly","");
    document.getElementById("markManagementButton").remove();
    isQuestionAnswer=true;
    localStorage.setItem("isQuestionAnswer", "true");
  }

  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let type = dataMap.get("type");
  let marks = dataMap.get("marks");
  document.getElementById("articleTitle").innerText = title.toString();
  setMarks(marks);

  if (type === "QUESTION"&&isQuestionAnswer===false) {
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
    setTypeQuestion();
    return;
  } else {
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
    setTypeArticle();
  }
  let articleParts = dataMap.get("articleParts");
  setArticleBody(articleParts);
  localStorage.removeItem("questionCreatorLogin");
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentArticle()
});
