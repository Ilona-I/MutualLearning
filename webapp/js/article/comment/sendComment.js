function sendComment() {

  let text = document.getElementById("commentText").value.trim();
  if(text.length===0){
    return;
  }
  let articleId = localStorage.getItem("articleId");
  let resultMap = new Map();

  resultMap.set("articleId", articleId);
  resultMap.set("text", text);
  let json = JSON.stringify(Object.fromEntries(resultMap));

  let url = "http://localhost:8080/comments";
  createXMLHttpRequest();
  xmlHttp.open("POST", url, false);
  let user = '{"login":"' + localStorage.getItem("login") + '"}';
  xmlHttp.onreadystatechange = handleStateChangeSendComment;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send(json);
}

function handleStateChangeSendComment() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLSendComment(xmlHttp.responseText);
    } else {
    }
  }
}

function jsonToHTMLSendComment(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let innerHtml = getComment(dataMap);

  let commentList = document.getElementById("commentList");
  commentList.insertBefore(convertStringToHTML(innerHtml).children[0], commentList.firstChild);
  document.getElementById("commentText").value = '';
  hideBlock('saveComment');
}

function displayBlock(id) {
  document.getElementById(id).removeAttribute("hidden");
}

function hideBlock(id){
  document.getElementById(id).setAttribute("hidden","")
}
