function getInfoAboutCurrentComments() {
  let articleId = localStorage.getItem("articleId");
  if (articleId === null) {
    document.location = "../error/notFound.html"
  } else {
    const url = "http://localhost:8080/comments/" + articleId;
    createXMLHttpRequest();
    let user = '{"login":"' + localStorage.getItem("login") + '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeComments;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

function handleStateChangeComments() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLDisplayComments(xmlHttp.responseText);
    } else {

    }
  }
}

function jsonToHTMLDisplayComments(jsonString) {
  console.log(jsonString)
  let jsonObject = JSON.parse(jsonString);
  let innerHtml = "";
  for (const element of jsonObject) {
    let comment = JSON.parse(JSON.stringify(element));
    let dataMap = new Map(Object.entries(comment));
    innerHtml += getComment(dataMap)
  }
  document.getElementById("commentList").innerHTML = innerHtml;
}

function getComment(dataMap) {

  let commentId = dataMap.get("id");
  let text = dataMap.get("text");
  let commentDateTime = "";
  let creationDateTime = dataMap.get("creationDateTime");
  let lastUpdateDateTime = dataMap.get("lastUpdateDateTime");
  if (lastUpdateDateTime !== null && lastUpdateDateTime !== "") {
    commentDateTime = "ред. " + getDate(
        parseInt(lastUpdateDateTime.toString()));
  } else {
    commentDateTime = getDate(parseInt(creationDateTime.toString()));
  }
  let member = JSON.parse(JSON.stringify(dataMap.get("member")));
  let memberMap = new Map(Object.entries(member));
  let login = memberMap.get("login");
  let name = memberMap.get("name");
  let info = memberMap.get("info");

  let innerHtml = "<div id='commentBlock" + commentId
      + "' class=\"comment_unit_block\">\n"
      + "    <div>\n"
      + "      <p class=\"datetime_text\">" + commentDateTime + "</p>\n"

      + "       <div class='row'><button  type=\"button\" class=\"btn\" data-toggle=\"modal\" "
      + " data-target=\"#exampleModalComment"+commentId+"\"\n"
      + "\" style=\"height: 25px; padding: 0; font-style: revert;\">\n"
      + "         ";
  if (name !== null && name.trim() !== "") {
    innerHtml += name + " (#" + login + ")";
  } else {
    innerHtml += "#" + login;
  }
  innerHtml += "\n"
      + "        </button>" ;
  if(localStorage.getItem("login")===login){
    innerHtml+="<button onclick='deleteComment("+commentId+")' "
        + " class='btn btn-danger' "
        + " style='margin-left: 20px; height: 20px; font-size: x-small; padding: 1px;'"
        + " localization-key=\"remove\">Видалити коментар</button>";

  }
  innerHtml += "</div> \n"
      + "<div class=\"modal fade\" id=\"exampleModalComment"+commentId+"\" tabindex=\"-1\" role=\"dialog\" "
      + " aria-labelledby=\"exampleModalLabelComment"+commentId+"\" aria-hidden=\"true\">\n"
      + "  <div class=\"modal-dialog\" role=\"document\">\n"
      + "    <div class=\"modal-content\">\n"
      + "      <div class=\"modal-header\">\n"
      + "        <h5 class=\"modal-title\" id=\"exampleModalLabelComment"+commentId+"\">";

  if (name !== null && name.trim() !== "") {
    innerHtml += name + " (#" + login + ")";
  } else {
    innerHtml += "#" + login;
  }
  innerHtml += "</h5>\n"
  + "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
  + "          <span aria-hidden=\"true\">&times;</span>\n"
  + "        </button>\n"
  + "      </div>\n"
  + "      <div class=\"modal-body\">"
  + "                <h6>#" + login + "</h6>\n"
  + "                " + (info != null&& info!=='' ? info : '') + "\n"
      + "              </div>\n"
      + "              <div class=\"modal-footer\">\n"
      + "              </div>\n"
      + "            </div>\n"
      + "          </div>\n"
      + "        </div>\n"
      + "<hr>\n"
      + "    <div>\n"
      + "      <p>" + text + "</p>\n"
      + "    </div>\n"
      + "    </div>\n"
      + "  </div>";
  return innerHtml;
}