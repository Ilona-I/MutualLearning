let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getTestInfo() {
  localStorage.setItem("testId", "1");
  let testId = localStorage.getItem("testId");

  const url = "http://localhost:8080/test/info/" + testId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChangeGetTestInfo;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateChangeGetTestInfo() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLGetTestInfo(xmlHttp.responseText);
    } else {

    }
  }
}

document.addEventListener("DOMContentLoaded", function () {
  getTestInfo()
});

function jsonToHTMLGetTestInfo(jsonString) {
  console.log(jsonString)
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let maxMark = dataMap.get("maxMark");
  let ownPreviousAttempts = dataMap.get("ownPreviousAttempts");
  document.getElementById("testTitle").innerText = title.toString();
  document.getElementById("maxMark").innerText = maxMark.toString();
  document.getElementById(
      "ownAttemptsNumber").innerText = ownPreviousAttempts.length.toString();
  let ownAttemptsTableBody = "";
  let i = ownPreviousAttempts.length;
  for (const element of ownPreviousAttempts) {
    let attempt = JSON.parse(JSON.stringify(element));
    let attemptMap = new Map(Object.entries(attempt));
    let dateTime = attemptMap.get("dateTime");
    let mark = attemptMap.get("mark");
    ownAttemptsTableBody += "    <tr>\n"
        + "        <th scope=\"row\">" + i + "</th>\n"
        + "        <td>" + getDateTime(parseInt(dateTime.toString())) + "</td>\n"

        + "        <td>" + mark + "/" + maxMark + "</td>\n"
        + "      </tr>";
    i = i - 1;
  }
  document.getElementById(
      "ownAttemptsTableBody").innerHTML = ownAttemptsTableBody;

  let role = dataMap.get("role");
  if (role === "CREATOR") {
    let userCount = dataMap.get("userCount");
    let userAverageMark = dataMap.get("userAverageMark");
    document.getElementById("userCount").innerText = userCount.toString();
    document.getElementById(
        "userAverageMark").innerText = userAverageMark.toString();
    let usersAttemptsResponse = dataMap.get("usersAttemptsResponse");
    if (usersAttemptsResponse === null) {
      let premiumButton = "<button class='btn btn-success'>Оформити преміум для перегляду спроб користувачів</button>";
      document.getElementById("creatorInfo").innerHTML = premiumButton;
    } else if (usersAttemptsResponse.length >= 0) {
      let table = "        <div style=\"width: 100%;\">\n"
          + "          <div class=\"row\" style=\"width: 95%; margin-left: 5px;\">\n"
          + "            <div class=\"table_border\">\n"
          + "              <h6>Користувач</h6>\n"
          + "            </div>\n"
          + "            <div class=\"table_border\">\n"
          + "              <h6>Номер спроби</h6>\n"
          + "            </div>\n"
          + "            <div class=\"table_border\">\n"
          + "              <h6>Дата й час проходження</h6>\n"
          + "            </div>\n"
          + "            <div class=\"table_border\">\n"
          + "              <h6>Оцінка</h6>\n"
          + "            </div>\n"
          + "          </div>\n";
      for (const element of usersAttemptsResponse) {
        let attempt = JSON.parse(JSON.stringify(element));
        let attemptMap = new Map(Object.entries(attempt));
        let userLogin = attemptMap.get("userLogin");
        table += "          <div class=\"row\" style=\"width: 95%; margin-left: 5px;\">\n"
            + "            <div class=\"table_border\">\n"
            + "              <p>" + userLogin + "</p>\n"
            + "            </div>\n"
            + "            <div style=\"width: 75%\">\n";
        let previousAttempts = attemptMap.get("previousAttempts");
        let t = previousAttempts.length;
        for (const e of previousAttempts) {
          let pAttempt = JSON.parse(JSON.stringify(e));
          let pAttemptMap = new Map(Object.entries(pAttempt));
          let dateTime = pAttemptMap.get("dateTime");
          let mark = pAttemptMap.get("mark");

          table += "              <div class=\"row\" style=\"margin-left: 0; width: 100%;\">\n"
              + "                <div class=\"table_border_body\">\n"
              + "                  <p>" + t + "</p>\n"
              + "                </div>\n"
              + "                <div class=\"table_border_body\">\n"
              + "                  <p>" + getDateTime(parseInt(dateTime.toString()))
              + "</p>\n"
              + "                </div>\n"
              + "                <div class=\"table_border_body\">\n"
              + "                  <p>" + mark + "</p>\n"
              + "                </div>\n"
              + "              </div>\n";
          t = t - 1;
        }
        table += "            </div>\n"
            + "\n"
            + "          </div>\n";

      }
      table += "        </div>\n"
      document.getElementById("creatorInfo").innerHTML =table;
    }

  } else {
    document.getElementById("creatorBlock").remove();

  }
}
