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
  let jsonObject = JSON.parse(jsonString);
  let innerHtml = "";
  for (const element of jsonObject) {
    let article = JSON.parse(JSON.stringify(element));
    let dataMap = new Map(Object.entries(article));
    innerHtml += getArticle(dataMap)


  }
  document.getElementById("articleList").innerHTML = innerHtml
}

function getArticle(dataMap) {
  let innerHtml = "";
  let type = dataMap.get("type");
  let articleDateTime = "";
  let creationDateTime = dataMap.get("creationDateTime");
  let lastUpdateDateTime = dataMap.get("lastUpdateDateTime");
  if (lastUpdateDateTime !== null && lastUpdateDateTime !== "") {
    articleDateTime = "ред." + getDate(parseInt(lastUpdateDateTime.toString()));
  } else {
    articleDateTime = getDate(parseInt(creationDateTime.toString()));
  }
  if (type === "question") {
    innerHtml += " <div id='articleBlock" + dataMap.get("id")
        + "' class=\"article_block_question\" style=\"padding: 10px 20px; margin-top: 10px;\" >\n"
        + "    <h4 id=\"articleTitle" + dataMap.get("id") + "\">" + dataMap.get(
            "title") + "</h4>\n"
        + "    <p id=\"articleDateTime" + dataMap.get("id")
        + "\" class=\"datetime_text\" style=\"margin-top: -40px;\">"
        + articleDateTime + "</p>"
        + "    <div id=\"markList" + dataMap.get("id")
        + "\" class=\"row article_marks\" style=\"margin-left: 0\">";

    let marks = dataMap.get("marks");
    innerHtml += getMarksArticles(marks);
    innerHtml += "</div>";
    innerHtml += "<div> <button style='padding-top:0; padding-bottom: 1px; height: 30px;' onclick='answerTheQuestion("
        + dataMap.get("id")
        + ")' class='btn btn-success'>Написати відповідь на питання</button></div>";
  } else {
    innerHtml += " <div id='articleBlock" + dataMap.get("id")
        + "' class=\"article_block\" style=\"padding: 10px 20px 0; margin-top: 10px;\" >\n"
        + "    <a onmouseover='selectLink(\"articleTitle"+dataMap.get("id")+"\")' onmouseout='unselectLink(\"articleTitle"+dataMap.get("id")+"\")'onclick='goToTheArticle(" + dataMap.get("id")
        + ")'><h4 id=\"articleTitle" + dataMap.get("id") + "\">" + dataMap.get(
            "title") + "</h4></a>\n"
        + "    <p id=\"articleDateTime" + dataMap.get("id")
        + "\" class=\"datetime_text\" style=\"margin-top: -40px;\">"
        + articleDateTime + "</p>"
        + "    <div id=\"markList" + dataMap.get("id")
        + "\" class=\"row article_marks\" style=\"margin-left: 0\">";
    let marks = dataMap.get("marks");
    innerHtml += getMarksArticles(marks);
    innerHtml += "</div>";
    //innerHtml +=  "<div> <button style='padding-top:0; padding-bottom: 1px; height: 30px;' onclick='goToTheArticle("+dataMap.get("id")+")' class='btn btn-info'>Перейти до статті</button></div>";
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
  loadArticles();
}

function searchArticles() {
  if (document.getElementById("searchArticle").value.trim() !== "") {
    document.getElementById("cancelSearchArticles").hidden = false;
    loadArticles();
  }
}


