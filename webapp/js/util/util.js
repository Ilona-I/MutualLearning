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
  return dateFormat.getDate() + "." + dateFormat.getMonth() + "."
      + dateFormat.getFullYear()
}

function selectLink(id){
  document.getElementById(id).style.color = "blue";
}

function unselectLink(id){
  document.getElementById(id).style.color = "black";
}

let prevScrollpos = window.pageYOffset;
window.onscroll = function() {
  let currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById("menu").style.top = "0";
  } else {
    document.getElementById("menu").style.top = "-160px";
  }
  prevScrollpos = currentScrollPos;
}