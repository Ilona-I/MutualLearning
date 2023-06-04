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
  return dateFormat.getDate() + "." + (dateFormat.getMonth() + 1) + "."
      + dateFormat.getFullYear()
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
  if (menu!==null){
    let currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
      menu.style.top = "0";
    } else {
      menu.getElementById("menu").style.top = "-160px";
    }
    prevScrollpos = currentScrollPos;
  }
}