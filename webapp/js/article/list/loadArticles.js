let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function loadArticles() {
  let requestParamLine = getRequestParamLine();
  const url = "http://localhost:8080/articles?" + requestParamLine;
  createXMLHttpRequest();
  console.log(url);
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChangeArticles;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateChangeArticles() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonArticlesToHTML(xmlHttp.responseText);
    } else {
      // document.location = "../html/error.html";
    }
  }
}

function jsonArticlesToHTML(jsonString) {
  console.log(jsonString)
  let jsonObject = JSON.parse(jsonString);
  let innerHtml = "";
  for (const element of jsonObject) {
    let article = JSON.parse(JSON.stringify(element));
    let dataMap = new Map(Object.entries(article));
    innerHtml += getArticle(dataMap)
  }
  document.getElementById("articleList").innerHTML = innerHtml;
  if (jsonObject.length === parseInt(localStorage.getItem("size"))) {
    loadArticles();
  }
}

function getArticle(dataMap) {
  let innerHtml = "";
  let type = dataMap.get("type");
  let articleDateTime = "";
  let creationDateTime = dataMap.get("creationDateTime");
  let lastUpdateDateTime = dataMap.get("lastUpdateDateTime");
  if (lastUpdateDateTime !== null && lastUpdateDateTime !== "") {
    articleDateTime = "ред. " + getDate(
        parseInt(lastUpdateDateTime.toString()));
  } else {
    articleDateTime = getDate(parseInt(creationDateTime.toString()));
  }
  let members = dataMap.get("members");
  let membersHtml = getMembers(members);
  if (type === "question" || type === "QUESTION") {
    innerHtml += " <div id='articleBlock" + dataMap.get("id")
        + "' class=\"article_block_question\" style=\"padding: 10px 20px; margin-top: 10px;\" >\n"
        + membersHtml
        + "    <h4 style='margin-top: -10px;' id=\"articleTitle" + dataMap.get("id") + "\">" + dataMap.get(
            "title") + "</h4>\n"
        + "    <p id=\"articleDateTime" + dataMap.get("id")
        + "\" class=\"datetime_text\" style=\"margin-top: -40px;\">"
        + articleDateTime + "</p>"
        + "    <div id=\"markList" + dataMap.get("id")
        + "\" class=\"row article_marks\" style=\"margin-left: 0\">";

    let marks = dataMap.get("marks");
    innerHtml += getMarksArticles(marks);
    innerHtml += "</div>";
    innerHtml += "<div> <button "
        + "style='padding-top:0; padding-bottom: 1px; height: 30px; background-color: transparent; border-style: solid; border-width: 1px; border-color: #95E06C; color:#95E06C;' "
        + "onclick='answerTheQuestion(\""
        + dataMap.get("id")+"\", \""+getQuestionCreator(members)
        + "\")' class='btn'>Написати відповідь на питання</button></div>";
  } else {
    innerHtml += " <div id='articleBlock" + dataMap.get("id")
        + "' class=\"article_block\" style=\"padding: 10px 20px 0; margin-top: 10px;\" >\n"
        + membersHtml
        + "    <a onmouseover='selectLink(\"articleTitle" + dataMap.get("id")
        + "\")' onmouseout='unselectLink(\"articleTitle" + dataMap.get("id")
        + "\")'onclick='goToTheArticle(" + dataMap.get("id")
        + ")'><h4 style='margin-top: -10px;'  id=\"articleTitle" + dataMap.get("id") + "\">" + dataMap.get(
            "title") + "</h4></a>\n"
        + "    <p id=\"articleDateTime" + dataMap.get("id")
        + "\" class=\"datetime_text\" style=\"margin-top: -40px;\">"
        + articleDateTime + "</p>"
        + "    <div id=\"markList" + dataMap.get("id")
        + "\" class=\"row article_marks\" style=\"margin-left: 0\">";
    let marks = dataMap.get("marks");
    innerHtml += getMarksArticles(marks);
    innerHtml += "</div>";
  }

  innerHtml += "</div></div>";
  return innerHtml;
}

function manageOwnerBlock() {
  let user = localStorage.getItem("userLogin");
  if (user === null) {
    document.getElementById("blockOwner").remove();
  }
}

document.addEventListener("DOMContentLoaded", function () {
  manageOwnerBlock();
});

function cancelSearchArticles() {
  document.getElementById("searchArticle").value = "";
  document.getElementById("cancelSearchArticles").hidden = true;
  setFilter();
}

function searchArticles() {
  if (document.getElementById("searchArticle").value.trim() !== "") {
    document.getElementById("cancelSearchArticles").hidden = false;
    setFilter();
  }
}

function setFilter() {
  localStorage.setItem("page", "0");
  loadArticles();
}

function getQuestionCreator(members){
  for (const element of members) {
    let member = JSON.parse(JSON.stringify(element));
    let memberMap = new Map(Object.entries(member));
    let login = memberMap.get("login");
    let role = memberMap.get("articleRole");
    if(role === "QUESTION_CREATOR"){
      return login;
    }
  }
 return "";
}

function getMembers(members) {
  let innerHtml = "<div  class='row' style='margin-top: -5px; margin-bottom: -5px;'>";
  for (const element of members) {
    let id = getRandomInt();
    let member = JSON.parse(JSON.stringify(element));
    let memberMap = new Map(Object.entries(member));
    let login = memberMap.get("login");

    let name = memberMap.get("name");
    let info = memberMap.get("info");
    let role = memberMap.get("articleRole");
    innerHtml += "        <div style='margin-left: 10px;'>\n"
        + "          <button type=\"button\" class=\"badge\" style = \"font-size: xx-small; background-color: transparent; border-width: 1px;;";
    if (role === "ARTICLE_CREATOR") {
      innerHtml += " color: green; border-color: green; \" "
    } else if (role === "QUESTION_CREATOR") {
      innerHtml += " color: orange; border-color: orange; \" "
    } else if (role === "QUESTION_ANSWERER") {
      innerHtml += " color: dodgerblue;  border-color: dodgerblue; \" "
    }
    innerHtml += "\" data-toggle=\"modal\"\n"
        + "                  data-target=\"#exampleModalScrollable"+id+"\">\n";
    if (name !== null && name.trim() !== "") {
      innerHtml += name +" (#"+login+")";
    } else {
      innerHtml += "#"+login;
    }
    innerHtml += "          </button>\n"
        + "\n"
        + "          <div class=\"modal fade\" id=\"exampleModalScrollable"+id+"\" tabindex=\"-1\" role=\"dialog\"\n"
        + "               aria-labelledby=\"exampleModalScrollableTitle\" aria-hidden=\"true\">\n"
        + "            <div class=\"modal-dialog modal-dialog-scrollable\" role=\"document\">\n"
        + "              <div class=\"modal-content\">\n"
        + "                <div class=\"modal-header\">\n"
        + "                  <h5 class=\"modal-title\" id=\"exampleModalScrollableTitle"+id+"\">";

    if (name !== null && name.trim() !== "") {
      innerHtml += name;
    } else {
      innerHtml += login;
    }
    innerHtml +=  " </h5><span style=\"margin-left: 20px;\" class=\"badge ";
    if (role === "ARTICLE_CREATOR") {
      innerHtml += " badge-success\">Творець статті "
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
        + "                  <button type=\"button\" class=\"btn btn-light\">Переглянути статті та питання\n"
        + "                    користувача\n"
        + "                  </button>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>\n"
        + "          </div>\n"
        + "        </div>\n"
  }
  innerHtml += "</div><hr>";
return innerHtml;
}

