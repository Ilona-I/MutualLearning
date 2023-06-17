let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentDisplayedArticle() {
  setMenuButton()
  let articleId = localStorage.getItem("articleId");
  if (articleId === null) {
    document.location = "../error/notFound.html"
  } else {
    const url = "http://localhost:8080/articles/" + articleId;
    createXMLHttpRequest();
    let user = '{"login":"' + localStorage.getItem("login") + '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeDisplay;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

function removeTestsBlock() {
  document.getElementById("testsBlock").remove();
}

function removeGiveAnswerButton(){
  document.getElementById("giveAnswer").remove();
}

function handleStateChangeDisplay() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLDisplay(xmlHttp.responseText);
    } else {

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
  let members = dataMap.get("members");
  document.getElementById("members").innerHTML = getMembers(members);
  if (type==="ARTICLE"){
    let tests = dataMap.get("tests");
    document.getElementById("tests").innerHTML = getTests(tests);
  }
  if (lastUpdateDateTime !== null && lastUpdateDateTime !== "") {
    articleDateTime.innerText = "ред. " + getDate(
        parseInt(lastUpdateDateTime.toString()));
  } else {
    articleDateTime.innerText = getDate(parseInt(creationDateTime.toString()));
  }
  let marks = dataMap.get("marks");
  document.getElementById("title").innerText = title.toString();
  setMarksDisplay(marks);

  if (type === "QUESTION" || type === "ANSWERED_QUESTION") {
    removeTestsBlock();
  }
  if (type === "ARTICLE" || type === "ANSWERED_QUESTION") {
    removeGiveAnswerButton();
  }
  let articleParts = dataMap.get("articleParts");
  setArticleBodyDisplay(articleParts);
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentDisplayedArticle();
  getInfoAboutCurrentComments();
});

function getTests(tests) {
  let innerHtml = "<div>";
  for (const element of tests) {
    let test = JSON.parse(JSON.stringify(element));
    let testMap = new Map(Object.entries(test));
    let id = testMap.get("id");
    let title = testMap.get("title")
    innerHtml += "<button class='btn btn-link' onclick='goToTheTest(" + id
        + ")'>" + title + "</button>"
  }
  innerHtml += "</div>";
  return innerHtml;
}

function goToTheTest(id) {
  localStorage.setItem("testId", id.toString());
  document.location = "../test/testInfo.html";
}

function getMembers(members) {
  let currentUserLogin = localStorage.getItem("login");
  let innerHtml = "<div>";
  for (const element of members) {
    let id = getRandomInt();
    let member = JSON.parse(JSON.stringify(element));
    let memberMap = new Map(Object.entries(member));
    let login = memberMap.get("login");
    let name = memberMap.get("name");
    let info = memberMap.get("info");
    let role = memberMap.get("articleRole");
    if (currentUserLogin === login) {
      let editArticleButton = document.getElementById("editArticleButton");
      if (editArticleButton !== null) {
        let button = "<button class='btn btn-light' style='margin-bottom: 20px; width: 100%;' onclick='goToEditArticle()'>Редагувати</button>"
        editArticleButton.innerHTML = button;
      }
      let createTestButton = document.getElementById("createTestButton");
      if (createTestButton !== null) {
        let button = "<button class='btn btn-light' style='margin-bottom: 20px; width: 100%;' onclick='createTest()'>Створити тест</button>"
        createTestButton.innerHTML = button;
      }
    }
    innerHtml += "        <div>\n"
        + "          <button type=\"button\" class=\"badge\" style = \"background-color: transparent; border-width: 1px; ";
    if (role === "ARTICLE_CREATOR") {
      innerHtml += " color: green; border-color: green; \" "
    } else if (role === "QUESTION_CREATOR") {
      innerHtml += " color: orange; border-color: orange; \" "
    } else if (role === "QUESTION_ANSWERER") {
      innerHtml += " color: dodgerblue;  border-color: dodgerblue; \" "
    }
    innerHtml += "\" data-toggle=\"modal\"\n"
        + "                  data-target=\"#exampleModalScrollable" + id
        + "\">\n";
    if (name !== null && name.trim() !== "") {
      innerHtml += name + " (#" + login + ")";
    } else {
      innerHtml += "#" + login;
    }
    innerHtml += "          </button>\n"
        + "\n"
        + "          <div class=\"modal fade\" id=\"exampleModalScrollable" + id
        + "\" tabindex=\"-1\" role=\"dialog\"\n"
        + "               aria-labelledby=\"exampleModalScrollableTitle\" aria-hidden=\"true\">\n"
        + "            <div class=\"modal-dialog modal-dialog-scrollable\" role=\"document\">\n"
        + "              <div class=\"modal-content\">\n"
        + "                <div class=\"modal-header\">\n"
        + "                  <h5 class=\"modal-title\" id=\"exampleModalScrollableTitle"
        + id + "\">";

    if (name !== null && name.trim() !== "") {
      innerHtml += name;
    } else {
      innerHtml += login;
    }
    innerHtml += " </h5><span style=\"margin-left: 20px;\" class=\"badge ";
    if (role === "ARTICLE_CREATOR") {
      innerHtml += " badge-success\" localization-key=\"article_creator\">Творець статті "
    } else if (role === "QUESTION_CREATOR") {
      innerHtml += " badge-danger\">Задав питання "
    } else if (role === "QUESTION_ANSWERER") {
      innerHtml += " badge-info \">Відповів на питання"
    }
    innerHtml += "                       </span>\n"
        + "                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
        + "                    <span aria-hidden=\"true\">&times;</span>\n"
        + "                  </button>\n"
        + "                </div>\n"
        + "                <div class=\"modal-body\">\n"
        + "                  <h6>#" + login + "</h6>\n"
        + info
        + "                </div>\n"
        + "                <div class=\"modal-footer\">\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>\n"
        + "          </div>\n"
        + "        </div>\n"
  }
  innerHtml += "</div><hr>";
  return innerHtml;

}

function goToEditArticle() {
  document.location = "saveArticle.html";
}

function createTest(){
  localStorage.removeItem("testId");
  document.location='../test/saveTest.html';
}