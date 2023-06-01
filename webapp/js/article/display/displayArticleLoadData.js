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

function getInfoAboutCurrentDisplayedArticle() {
  localStorage.setItem("articleId", "1")
  let articleId = localStorage.getItem("articleId");
  localStorage.setItem("userLogin", "user1")

  const url = "http://localhost:8080/articles/" + articleId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChangeDisplay;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function setTypeArticleDisplay() {
  document.getElementById("giveAnswer").remove();
}

function setTypeQuestionDisplay() {
  document.getElementById("articleBody").remove();
}

function handleStateChangeDisplay() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      console.log(38)
      jsonToHTMLDisplay(xmlHttp.responseText);
    } else {
      document.location = "../html/error.html";
    }
  }
}

function jsonToHTMLDisplay(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let type = dataMap.get("type");
  let creationDateTime = dataMap.get("creationDateTime");
  let lastUpdateDateTime = dataMap.get("lastUpdateDateTime");
  let articleDateTime = document.getElementById("articleDateTime");
  if(lastUpdateDateTime!==null&&lastUpdateDateTime!==""){
    articleDateTime.innerText = "ред." + getDate(parseInt(lastUpdateDateTime.toString()));
  } else {
    articleDateTime.innerText = getDate(parseInt(creationDateTime.toString()));
  }
  let marks = dataMap.get("marks");
  document.getElementById("title").innerText = title.toString();
  setMarksDisplay(marks);

  if (type === "question") {
    setTypeQuestionDisplay();
    return;
  } else {
    setTypeArticleDisplay();
  }
  let articleParts = dataMap.get("articleParts");
  setArticleBodyDisplay(articleParts);
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentDisplayedArticle()
});
