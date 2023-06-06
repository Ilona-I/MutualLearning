function updateProfile() {
  let login = localStorage.getItem("login");
  let name = document.getElementById("name").value.trim();
  let email = document.getElementById("email").value.trim();
  let info = document.getElementById("info").innerText.trim();
  let wrongData = new Array();
  if (email.length < 5 || isEmail(email) === false) {
    wrongData.push("wrongEmail")
  }
  if (wrongData.length > 0) {
    setWrongDataProfile(wrongData);

  } else {
    let profileData = new Map();
    profileData.set("login", login);
    profileData.set("name", name);
    profileData.set("email", email);
    profileData.set("info", info);
    console.log(profileData);
    let json = JSON.stringify(Object.fromEntries(profileData));
    let url = "http://localhost:8080/users/profile";
    createXMLHttpRequest();
    xmlHttp.open("PUT", url, false)
    let user = '{"login":"' + login + '"}';
    xmlHttp.onreadystatechange = handleStateChangeUpdateProfile;
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.send(json);
  }
}

function handleStateChangeUpdateProfile() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {

    } else {
      setWrongDataProfile(JSON.parse(xmlHttp.responseText));
    }
  }
}

function setWrongDataProfile(wrongData) {
  for (const wd of wrongData) {
    if (wd.toString() === "userWithThisEmailAlreadyExist") {
      document.getElementById("wrongEmail").innerText
          = '* користувач з такою електронною адресою вже існує';
    } else if (wd.toString() === "wrongEmail") {
      document.getElementById("wrongEmail").innerText
          = '* неправильний e-mail';
    }
  }
}

function cleanWrongData(id) {
  document.getElementById(id).innerText = '';
}

