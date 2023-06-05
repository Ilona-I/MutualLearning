let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function logIn(){
  let logInLogin = document.getElementById("logInLogin").value.trim();
  let logInPassword = document.getElementById("logInPassword").value.trim();
  let wrongData = new Array();
  if (logInLogin.length < 5 || onlyLettersAndNumbers(logInLogin) === false) {
    wrongData.push("wrongLogin")
  }
  if (logInPassword.length < 5 || onlyLettersAndNumbers(logInPassword)
      === false) {
    wrongData.push("wrongPassword")
  }
  if (wrongData.length > 0) {
    setWrongLogInData(wrongData);

  } else {
    let logInData = new Map();
    logInData.set("login", logInLogin);
    logInData.set("password", btoa(encodeURIComponent(logInPassword)));

    let json = JSON.stringify(Object.fromEntries(logInData));
    console.log(json);
    let url = "http://localhost:8080/users/logIn";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false)
    xmlHttp.onreadystatechange = handleStateChangeLogIn;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.send(json);
  }
}

function handleStateChangeLogIn() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      setUser(xmlHttp.responseText);
      document.location='../article/articles.html';
    } else {
      setWrongLogInData(JSON.parse(xmlHttp.responseText));
    }
  }
}

function setUser(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let login = dataMap.get("login");
  let role = dataMap.get("role");
  localStorage.setItem("login", login.toString());
  localStorage.setItem("role", role.toString());

}

function setWrongLogInData(wrongData) {
  for (const wd of wrongData) {
    console.log(wd)
    if (wd.toString() === "wrongLogin") {
      document.getElementById("wrongLogin").innerText
          = '* неправильний логін';
    } else if (wd.toString() === "wrongPassword") {
      document.getElementById("wrongPassword").innerText
          = '* неправильний пароль';
    }
  }
}

function cleanWrongData(id) {
  document.getElementById(id).innerText = '';
}

document.addEventListener("DOMContentLoaded", function () {
  localStorage.removeItem("login");
  localStorage.removeItem("role");
});