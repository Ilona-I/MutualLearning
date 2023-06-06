function deleteTest(){
  let testId = localStorage.getItem("testId");
  const url = "http://localhost:8080/test/info/" + testId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '"}';
  xmlHttp.open("DELETE", url, false);
  xmlHttp.onreadystatechange = handleStateChangeDeleteTest;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();

}

function handleStateChangeDeleteTest() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      document.location = '../article/article.html'
    } else {
      document.location = '../error/forbidden.html'
    }
  }
}