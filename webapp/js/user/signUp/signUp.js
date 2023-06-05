let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function signUp() {
  let signUpLogin = document.getElementById("signUpLogin").value.trim();
  let signUpPassword = document.getElementById("signUpPassword").value.trim();
  let signUpRepeatedPassword = document.getElementById(
      "signUpRepeatedPassword").value.trim();
  let signUpName = document.getElementById("signUpName").value.trim();
  let signUpEmail = document.getElementById("signUpEmail").value.trim();
  let signUpInfo = document.getElementById("signUpInfo").innerText.trim();
  let wrongData = new Array();
  if (signUpLogin.length < 5 || onlyLettersAndNumbers(signUpLogin) === false) {
    wrongData.push("wrongLogin")
  }
  if (signUpPassword.length < 5 || onlyLettersAndNumbers(signUpPassword)
      === false) {
    wrongData.push("wrongPassword")
  }
  if (signUpPassword !== signUpRepeatedPassword) {
    wrongData.push("wrongRepeatedPassword")
  }
  if(signUpEmail.length<5||isEmail(signUpEmail)===false){
    wrongData.push("wrongEmail")

  }

  if (wrongData.length > 0) {
    setWrongData(wrongData);

  } else {
    let signUpData = new Map();
    signUpData.set("login", signUpLogin);
    signUpData.set("password", btoa(encodeURIComponent(signUpPassword)));
    signUpData.set("repeatedPassword",
        btoa(encodeURIComponent(signUpRepeatedPassword)));
    signUpData.set("name", signUpName);
    signUpData.set("email", signUpEmail);
    signUpData.set("info", signUpInfo);
    console.log(signUpData);
     let json = JSON.stringify(Object.fromEntries(signUpData));
     let url = "http://localhost:8080/users/signUp";
     createXMLHttpRequest();
     xmlHttp.open("POST", url, false)
     xmlHttp.onreadystatechange = handleStateChangeSignUp;
     xmlHttp.setRequestHeader("Content-Type", "application/json");
     xmlHttp.send(json);
  }
}

function handleStateChangeSignUp() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      setUser(xmlHttp.responseText);
      document.location='../article/articles.html';
    } else {
      setWrongData(JSON.parse(xmlHttp.responseText));
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

function setWrongData(wrongData) {
  for (const wd of wrongData) {
    console.log(wd)
    if (wd.toString() === "wrongLogin") {
      document.getElementById("wrongSignUpLogin").innerText
          = '*логін має бути більше 5 символів та містити лише латинські літери';
    } else if (wd.toString() === "wrongPassword") {
      document.getElementById("wrongSignUpPassword").innerText
          = '* пароль  має бути більше 5 символів та містити лише латинські літери';
    } else if (wd.toString() === "wrongRepeatedPassword" ||wd === "passwordsNotEqual") {
      document.getElementById("wrongSignUpRepeatedPassword").innerText
          = '* паролі не співпадають';
    } else if (wd.toString() === "userWithThisLoginAlreadyExist") {
      document.getElementById("wrongSignUpLogin").innerText
          = '* цей логін вже зайнятий';
    } else if (wd.toString() === "userWithThisEmailAlreadyExist") {
      document.getElementById("wrongSignUpEmail").innerText
          = '* користувач з такою електронною адресою вже існує';
    } else if (wd.toString() === "wrongEmail") {
      document.getElementById("wrongSignUpEmail").innerText
          = '* неправильний e-mail';
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