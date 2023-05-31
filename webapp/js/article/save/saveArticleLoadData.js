let xmlHttp;
const articleTypeArticle = "article";
const articleTypeQuestion = "question";

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentArticle() {
  let articleId = localStorage.getItem("articleId");
  localStorage.setItem("userLogin", "user1")
  articleId = 1;
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
  document.getElementById("articleBody").hidden = false;
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
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let type = dataMap.get("type");
  let marks = dataMap.get("marks");
  document.getElementById("articleTitle").innerText = title.toString();
  setMarks(marks);

  if (type === "question") {
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
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentArticle()
});
