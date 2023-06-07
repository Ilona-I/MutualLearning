function updatePassword() {
  let login = localStorage.getItem("login");
  let currentPassword = document.getElementById("currentPassword").value.trim();
  let newPassword = document.getElementById("newPassword").value.trim();
  let repeatedPassword = document.getElementById(
      "repeatedPassword").value.trim();
  let wrongData = new Array();
  if (currentPassword.length < 5 || onlyLettersAndNumbers(currentPassword)
      === false) {
    wrongData.push("wrongCurrentPassword")
  }
  if (newPassword.length < 5 || onlyLettersAndNumbers(newPassword)
      === false) {
    wrongData.push("wrongNewPassword")
  }
  if (repeatedPassword.length < 5 || onlyLettersAndNumbers(repeatedPassword)
      === false || repeatedPassword !== newPassword) {
    wrongData.push("wrongRepeatedPassword")
  }
  if (wrongData.length > 0) {
    setWrongData(wrongData);
  } else {
    let passwordData = new Map();
    passwordData.set("currentPassword",
        btoa(encodeURIComponent(currentPassword)));
    passwordData.set("newPassword", btoa(encodeURIComponent(newPassword)));
    passwordData.set("repeatedPassword",
        btoa(encodeURIComponent(repeatedPassword)));

    let json = JSON.stringify(Object.fromEntries(passwordData));
    let url = "http://localhost:8080/users/password";
    createXMLHttpRequest();
    xmlHttp.open("PUT", url, false)
    let user = '{"login":"' + login + '"}';
    xmlHttp.onreadystatechange = handleStateChangeUpdatePassword;
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.send(json);
  }
}

function handleStateChangeUpdatePassword() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      cleanPasswords();
    } else {
      setWrongData(JSON.parse(xmlHttp.responseText));
    }
  }
}

function cleanPasswords() {
  document.getElementById("currentPassword").value = '';
  document.getElementById("newPassword").value = '';
  document.getElementById("repeatedPassword").value = '';
  hideBlock('passwordBlock');
}

function setWrongData(wrongData) {
  for (const wd of wrongData) {
    if (wd.toString() === "wrongCurrentPassword") {
      document.getElementById("wrongCurrentPassword").innerText
          = '* неправильний поточний пароль';
    } else if (wd.toString() === "wrongNewPassword") {
      document.getElementById("wrongNewPassword").innerText
          = '* неправильний новий пароль';
    } else if (wd.toString() === "wrongRepeatedPassword") {
      document.getElementById("wrongRepeatedPassword").innerText
          = '* паролі не співпадають';
    }
  }
}

function displayBlock(id) {
  document.getElementById(id).removeAttribute("hidden");
}
