function deleteComment(id) {
  document.getElementById("commentBlock" + id).remove();
  let url = "http://localhost:8080/comments/" + id;
  createXMLHttpRequest();
  xmlHttp.open("DELETE", url, false);
  let user = '{"login":"' + localStorage.getItem("login") + '"}';
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}
