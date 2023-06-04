function loadMarks(){
  const url = "http://localhost:8080/marks";
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateLoadMarks;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateLoadMarks() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonMarkListToHTML(xmlHttp.responseText);
    } else {
      document.location = "../html/error.html";
    }
  }
}

function jsonMarkListToHTML(jsonString) {
  document.getElementById("markListArticles").innerHTML = "";
  let jsonObject = JSON.parse(jsonString);
  localStorage.setItem("allMarksArticles", JSON.stringify(jsonObject));
  let innerHtml = "";
  for (const element of jsonObject) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    innerHtml += getMarkHtmlArticles(markMap, "");
  }
  document.getElementById("markListArticles").innerHTML = innerHtml;
}


function getMarkHtmlArticles(markMap, searchParam) {
  let innerHtml = " <div style='margin-left: 40px; '"
      + " id='markBlock" + markMap.get("id") + "'";
  if (!markMap.get("title").toLowerCase().includes(searchParam.toLowerCase())) {
    innerHtml += " hidden ";
  }
  innerHtml += ">\n"
      + "  <input name='markListElement' type=\"checkbox\" value=\"" + markMap.get("id") + "\" "
      + "         id=\"markListCheckboxArticle" + markMap.get("id") + "\" checked='checked'>\n"
      + "  <label for=\"markListCheckboxArticle" + markMap.get("id") + "\"" + ">\n"
      + "     <div class=\"row\" style=\"margin-left: 0;\">\n"
      + "         <div class=\"dropdown\" style='margin: 0;'>\n"
      + "             <button id=\"dropdownMenuButton" + markMap.get("id") + "\""
      + "                     class=\"badge badge-pill ";
  if (markMap.get("type") === "SYSTEM") {
    innerHtml += " badge-success ";
  } else if (markMap.get("type") === "CUSTOM" && markMap.get("creator")
      === localStorage.getItem("userLogin")) {
    innerHtml += " badge-primary ";
  } else {
    innerHtml += " badge-info ";
  }
  innerHtml += " dropdown-toggle\" "
      + "       data-toggle=\"dropdown\" "
      + "       aria-haspopup=\"true\" "
      + "       aria-expanded=\"false\" "
      + "       style=\"border-width: 0;\" "
      + "       data-toggle=\"modal\" "
      + "       data-target=\"#markListModal" +  markMap.get("id") + "\">"
      +           markMap.get("title")
      + "   </button>\n"
      + " <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton"
      + markMap.get("id") + "\" style='padding: 15px;'>"
      + markMap.get("description")
      + " </div></div></div></div></div>";
  return innerHtml;
}

document.addEventListener("DOMContentLoaded", function () {
  loadMarks();
  localStorage.setItem("page", "0");
  localStorage.setItem("size", "10");
  loadArticles();
});

function checkAllMarks(){
  let markList = document.getElementsByName("markListElement");
  for(const element of markList){
    element.checked = "checked";
  }
}

function uncheckAllMarks(){
  let markList = document.getElementsByName("markListElement");
  for(const element of markList){
    element.checked = "";
  }
}

function displayMarksArticles(){
  let searchParam = document.getElementById("searchMarkLineArticles").value.trim();
  let innerHtml = "";
  let jsonObject = JSON.parse(localStorage.getItem("allMarksArticles"));
  for (const element of jsonObject) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    innerHtml += getMarkHtmlArticles(markMap, searchParam);
  }
  document.getElementById("markListArticles").innerHTML = innerHtml;
}