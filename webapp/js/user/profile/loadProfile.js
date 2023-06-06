let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getProfile() {
  let currentLogin = localStorage.getItem("login")
  if (currentLogin === null) {
    document.location = '../user/logIn.html'
  } else {
    const url = "http://localhost:8080/users/profile";
    createXMLHttpRequest();
    let user = '{"login":"' + currentLogin + '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeGetProfile;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
  }
}

document.addEventListener("DOMContentLoaded", function () {
  getProfile();
});

function handleStateChangeGetProfile() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLGetProfile(xmlHttp.responseText);
    } else {
      document.location = '../error/error.html'
    }
  }
}

function jsonToHTMLGetProfile(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let login = dataMap.get("login");
  let name = dataMap.get("name");
  let email = dataMap.get("email");
  let role = dataMap.get("role");
  let info = dataMap.get("info");
  let status = dataMap.get("status");
  let currentRole = "Звичайний";
  if (role.toString() === "PREMIUM_USER") {
    currentRole = "Преміум"
  }
  document.getElementById("login").innerText = login.toString();
  document.getElementById("name").value = name.toString();
  document.getElementById("email").value = email.toString();
  document.getElementById("role").innerText = currentRole;
  document.getElementById("info").innerText = info.toString();

  if (role.toString() === "ADMIN"||role.toString() === "MODERATOR") {
    document.getElementById("type").setAttribute("hidden","")
  }

  if(status==="BLOCKED"){
    document.getElementById("blocked").innerText="Ваш акаунт заблоковано";
    document.getElementById("profile").setAttribute("readonly","")
  }
}

function hideBlock(id){
  document.getElementById(id).setAttribute("hidden","")
}
