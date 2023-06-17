const convertStringToHTML = htmlString => {
  const parser = new DOMParser();
  const html = parser.parseFromString(htmlString, 'text/html');
  return html.body;
}

function getRandomInt() {
  return Math.floor(Math.random() * 2147483648);
}

function escapeHTML(html) {
  return html.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g,
      '&gt;');
}

function getDate(timeStamp) {
  let dateFormat = new Date(timeStamp);
  return (dateFormat.getDate() < 10 ? "0" + dateFormat.getDate()
          : dateFormat.getDate()) + "."
      + ((dateFormat.getMonth() + 1) < 10 ? "0" + (dateFormat.getMonth() + 1)
          : (dateFormat.getMonth() + 1)) + "."
      + dateFormat.getFullYear();
}

function getDateTime(timeStamp) {
  let dateFormat = new Date(timeStamp);
  return (dateFormat.getDate() < 10 ? "0" + dateFormat.getDate()
          : dateFormat.getDate()) + "."
      + ((dateFormat.getMonth() + 1) < 10 ? "0"
          + (dateFormat.getMonth() + 1) : (dateFormat.getMonth() + 1)) + "."
      + dateFormat.getFullYear() + " " + dateFormat.getHours() + ":"
      + (dateFormat.getMinutes() < 10 ? "0" + dateFormat.getMinutes()
          : dateFormat.getMinutes()) + ":"
      + (dateFormat.getSeconds() < 10 ? "0" + dateFormat.getSeconds()
          : dateFormat.getSeconds());
}

function selectLink(id) {
  document.getElementById(id).style.color = "blue";
}

function unselectLink(id) {
  document.getElementById(id).style.color = "black";
}

let prevScrollpos = window.pageYOffset;
window.onscroll = function () {
  let menu = document.getElementById("menu");
  if (menu !== null) {
    let currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
      menu.style.top = "0";
    } else {
      menu.getElementById("menu").style.top = "-160px";
    }
    prevScrollpos = currentScrollPos;
  }
}

function onlyLettersAndNumbers(str) {
  return /^[A-Za-z0-9]*$/.test(str);
}

function isEmail(str) {
  const regexExp = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/gi;
  return regexExp.test(str);
}


function setMenuButton(){
  let login = localStorage.getItem("login");
  if(login===null||login.length===0){
    document.getElementById("accountBlock").innerHTML =""
        + "<button onclick=\"document.location='../user/logIn.html'\" "
        + "class=\"btn btn-light menu_button_sign_up\" "
        + "style=\"background-color: #303052; color:#ffffff ;border-width: 0px\"  localization-key=\"to_log_in\">"
        + "Увійти</button>";
  }
}