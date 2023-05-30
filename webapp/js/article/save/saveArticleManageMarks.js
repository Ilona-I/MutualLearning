function getInfoAboutMarks() {
  setDefaultFilters();
  let articleId = localStorage.getItem("articleId");
  articleId = 1;
  const url = "http://localhost:8080/marks";
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChangeMarks;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateChangeMarks() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonMarksToHTML(xmlHttp.responseText);
    } else {
      document.location = "../html/error.html";
    }
  }
}

function getMarkHtml(markMap, searchParam, isOnlyOwn, isOnlyChecked) {
  let innerHtml = " <div id='markBlock" + markMap.get("id")
      + "' style='width: 100%;'";
  let currentMark = document.getElementById("defaultCheck" + markMap.get("id"));
  if (isOnlyOwn === true && localStorage.getItem("userLogin") !== markMap.get(
          "creator")
      || isOnlyChecked === true && (currentMark !== null && currentMark.checked
          === false)
      || !markMap.get("title").toLowerCase().includes(
          searchParam.toLowerCase())) {
    console.log(markMap.get("id"));
    innerHtml += " hidden ";
  }
  innerHtml += ">\n"
      + "                  <input type=\"checkbox\" value=\"" + markMap.get(
          "id") + "\" id=\"defaultCheck"
      + markMap.get("id") + "\"";
  if (isArticleMark(markMap.get("id")) && (currentMark === null
          || currentMark.checked === true) || currentMark !== null
      && currentMark.checked === true) {
    innerHtml += " checked='checked' ";
  }
  innerHtml += ">\n"
      + "                  <label style='width: 90%;' for=\"defaultCheck"
      + markMap.get("id") + "\""
      + ">\n"
      + "                    <div class=\"row\" style=\"margin-left: 2px; width: 90%;\">\n"
      + "<div class=\"dropdown\" style='margin: 3px;'>\n"
      + "        <button id=\"dropdownMenuButton" + markMap.get("id")
      + "\" class=\"badge badge-pill ";
  if (markMap.get("type") === "system") {
    innerHtml += "badge-success";
  } else if (markMap.get("type") === "custom" && markMap.get("creator")
      === localStorage.getItem("userLogin")) {
    innerHtml += "badge-primary";
  } else {
    innerHtml += "badge-info";
  }
  innerHtml += " dropdown-toggle\"";
  innerHtml += " data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" "
      + " style=\"border-width: 0;\" data-toggle=\"modal\" data-target=\"#markListModal"
      + markMap.get("id") + "\">"
      + markMap.get("title")
      + "</button>\n"
      + " <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton"
      + markMap.get("id") + "\" style='padding: 15px;'>"
      + markMap.get("description")
      + " </div>"
      + "      </div>"

  if (markMap.get("creator")
      === localStorage.getItem("userLogin")) {
    innerHtml += "                    <!-- Початок блоку із редагуванням мітки -->\n"
        + "                      <div class=\"dropdown\" style=\"margin-left: 5px;\">\n"
        + "                        <button class=\"btn dropdown-toggle\" type=\"button\"\n"
        + "                                style=\"margin-left:10px; height:25px; background-color: transparent; color: #C3F73A; border-color: #C3F73A; font-size: small; padding-top: 0; padding-bottom: 0;\"\n"
        + "                                id=\"dropdownMenuButtonEditMark"
        + markMap.get("id") + "\" data-toggle=\"dropdown\"\n"
        + "                                aria-haspopup=\"true\"\n"
        + "                                aria-expanded=\"false\">\n"
        + "                          Редагувати\n"
        + "                        </button>\n"
        + "                        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButtonEditMark"
        + markMap.get("id") + "\"\n"
        + "                             style=\"padding: 10px; width: 400px;\">\n"
        + "                          <input id='updateMarkTitle" + markMap.get(
            "id") + "' type=\"text\" class=\"form-control input_info\"\n"
        + "                                 placeholder=\"Mark name\" value='"
        + markMap.get("title") + "'>\n"
        + "                          <textarea id='updateMarkDescription"
        + markMap.get("id") + "' class=\"form-control input_info\"\n"
        + "                                    placeholder=\"Description\">"
        + markMap.get("description") + "</textarea>\n"
        + "\n"
        + "                          <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
        + "                            <button type=\"button\" class=\"btn\"\n"
        + "                                    style=\"color: #68B684; border-color: #68B684; background-color: white;\""
        + "                                    onclick=\"updateMark('"
        + markMap.get("id") + "', '" + markMap.get("title") + "')\">\n"
        + "                              Зберегти\n"
        + "                            </button>\n"
        + "                            <button type=\"button\" class=\"btn btn-secondary\"\n"
        + "                                    style=\"margin-left: 10px; color: gray; border-color: gray; background-color: white;\">\n"
        + "                              Скасувати\n"
        + "                            </button>\n"
        + "                          </div>\n"
        + "                        </div>\n"
        + "                      </div>\n"
        + "                      <!-- Кінець блоку із редагуванням мітки -->\n"
        + "\n"
        + "                      <!-- Початок блоку з видаленням мітки -->\n"
        + "                      <div class=\"dropdown\" style=\"margin-left: 5px;\">\n"
        + "                        <button class=\"btn dropdown-toggle\" type=\"button\"\n"
        + "                                style=\"height:25px; margin-left: 5px; margin-top: 2px; padding-top: 0; padding-bottom: 0; background-color: transparent; color: #ff6e64; border-color:#ff6e64; font-size: small;\"\n"
        + "                                id=\"dropdownMenuButtonDeleteMark"
        + markMap.get("id") + "\" data-toggle=\"dropdown\"\n"
        + "                                aria-haspopup=\"true\"\n"
        + "                                aria-expanded=\"false\""
        + "                                >\n"
        + "                          Видалити\n"
        + "                        </button>\n"
        + "                        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButtonDeleteMark"
        + markMap.get("id") + "\"\n"
        + "                             style=\"padding: 15px; width: 300px;\">\n"
        + "                          <p>Ви впевнені, що хочете видалити мітку?</p>\n"
        + "                          <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
        + "                            <button type=\"button\" class=\"btn btn-danger\" "
        + "                                     onclick=\"deleteMark('"
        + markMap.get("id") + "', '" + markMap.get("title") + "')\""
        + "                                     >Видалити\n"
        + "                            </button>\n"
        + "                            <button type=\"button\" class=\"btn btn-secondary\"\n"
        + "                                    style=\"margin-left: 10px; color: gray; border-color: gray; background-color: white;\">\n"
        + "                              Скасувати\n"
        + "                            </button>\n"
        + "                          </div>\n"
        + "                        </div>\n"
        + "                      </div>\n"
        + "                      <!-- Кінець блоку з видаленням мітки -->\n";
  }
  innerHtml += "                    </div>\n"
      + "                  </div>\n"
      + "                </div>";
  return innerHtml;
}

function jsonMarksToHTML(jsonString) {
  hideCreateForm();
  document.getElementById("markCheckboxList").innerHTML = "";
  let jsonObject = JSON.parse(jsonString);
  localStorage.setItem("allMarks", JSON.stringify(jsonObject));
  let markTitles = [];
  let innerHtml = "";
  for (const element of jsonObject) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    markTitles.push(markMap.get("title"));
    innerHtml += getMarkHtml(markMap, "", false, false);
  }
  localStorage.setItem("allMarkTitles", JSON.stringify(markTitles));
  document.getElementById("markCheckboxList").innerHTML = innerHtml;
}

function displayMarks() {
  let searchParam = document.getElementById("searchMarkLine").value.trim();
  let isOnlyOwn = document.getElementById("ownMarks").checked === true;
  let isOnlyChecked = document.getElementById("checkedMarks").checked === true;
  let innerHtml = "";
  let jsonObject = JSON.parse(localStorage.getItem("allMarks"));
  for (const element of jsonObject) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    innerHtml += getMarkHtml(markMap, searchParam, isOnlyOwn,
        isOnlyChecked);
  }
  document.getElementById("markCheckboxList").innerHTML = innerHtml;
}

function setDefaultFilters() {
  document.getElementById("searchMarkLine").value = "";
  document.getElementById("inlineRadio1").checked = true;
  document.getElementById("ownMarks").checked = false;
  document.getElementById("inlineRadio2").checked = true;
  document.getElementById("ownMarks").checked = false;
}

function isArticleMark(markId) {
  return localStorage.getItem("articleMarksId").includes(markId);
}

function createMark() {
  let titleInput = document.getElementById("createMarkTitle");
  let title = titleInput.value;
  let allMarkTitles = localStorage.getItem("allMarkTitles");
  if (title.trim() === "") {
    let wrongTitleHtml = "<div id='wrongTitleCreateMark'><p style='color: darkred'>*напишіть заголовок</p></div>";
    titleInput.parentNode.insertBefore(
        convertStringToHTML(wrongTitleHtml).children[0],
        titleInput.nextSibling);
    return;
  }
  if (JSON.stringify(allMarkTitles).includes(title)) {
    let wrongTitleHtml = "<div id='wrongTitleCreateMark'><p style='color: darkred'>* мітка з цією назвою вже існує</p></div>";
    titleInput.parentNode.insertBefore(
        convertStringToHTML(wrongTitleHtml).children[0],
        titleInput.nextSibling);
    return;
  }
  let descriptionTextarea = document.getElementById("createMarkDescription");
  let description = descriptionTextarea.value;
  if (description.trim() === "") {
    let wrongDescriptionHtml = "<div id='wrongDescriptionCreateMark'><p style='color: darkred'>*напишіть опис</p></div>";
    descriptionTextarea.parentNode.insertBefore(
        convertStringToHTML(wrongDescriptionHtml).children[0],
        descriptionTextarea.nextSibling);
  }

  const url = "http://localhost:8080/marks";
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("POST", url, false);
  xmlHttp.onreadystatechange = handleStateCreateMark;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  let createMarkBody = '{"title": "' + title + '",'
      + '"description" : "' + description + '"}';
  xmlHttp.send(createMarkBody);

}

function handleStateCreateMark() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      displayCreatedMark(xmlHttp.responseText);
    } else {

    }
  }
}

function displayCreatedMark(jsonString) {
  hideCreateForm();
  let allMarkTitles = JSON.parse(localStorage.getItem("allMarkTitles"));
  let allMarks = JSON.parse(localStorage.getItem("allMarks"));

  let mark = JSON.parse(jsonString);
  let markMap = new Map(Object.entries(mark));

  allMarkTitles.push(markMap.get("title"));
  allMarks.push(mark);

  localStorage.setItem("allMarkTitles", JSON.stringify(allMarkTitles));
  localStorage.setItem("allMarks", JSON.stringify(allMarks));
  displayMarks();
}

function deleteCreateMarkTitleErrors() {
  let element = document.getElementById("wrongTitleCreateMark");
  if (element !== null) {
    element.remove();
  }
}

function deleteCreateMarkDescriptionErrors() {
  let element = document.getElementById("wrongDescriptionCreateMark");
  if (element !== null) {
    element.remove();
  }
}

function updateMark(markId, markTitle) {
  let titleInput = document.getElementById("updateMarkTitle" + markId);
  let title = titleInput.value;
  let allMarkTitles = JSON.stringify(localStorage.getItem("allMarkTitles"));
  if (title.trim() === "") {
    let wrongTitleHtml = "<div id='wrongTitleUpdateMark'><p style='color: darkred'>*напишіть заголовок</p></div>";
    titleInput.parentNode.insertBefore(
        convertStringToHTML(wrongTitleHtml).children[0],
        titleInput.nextSibling);
    return;
  }
  if (allMarkTitles.indexOf(title) !== allMarkTitles.lastIndexOf(title)) {
    let wrongTitleHtml = "<div id='wrongTitleUpdateMark'><p style='color: darkred'>* мітка з цією назвою вже існує</p></div>";
    titleInput.parentNode.insertBefore(
        convertStringToHTML(wrongTitleHtml).children[0],
        titleInput.nextSibling);
    return;
  }
  let descriptionTextarea = document.getElementById(
      "updateMarkDescription" + markId);
  let description = descriptionTextarea.value;
  if (description.trim() === "") {
    let wrongDescriptionHtml = "<div id='wrongDescriptionUpdateMark'><p style='color: darkred'>*напишіть опис</p></div>";
    descriptionTextarea.parentNode.insertBefore(
        convertStringToHTML(wrongDescriptionHtml).children[0],
        descriptionTextarea.nextSibling);
  }

  localStorage.setItem("currentUpdatedMarkTitle", markTitle);
  const url = "http://localhost:8080/marks";
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("PUT", url, false);
  xmlHttp.onreadystatechange = handleStateUpdateMark;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  let updateMarkBody = '{"id": "'+markId+'",'
      + '"title": "' + title + '",'
      + '"description" : "' + description + '"}';
  xmlHttp.send(updateMarkBody);
}

function handleStateUpdateMark() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      displayUpdatedMark(xmlHttp.responseText);
    } else {

    }
  }
  localStorage.removeItem("currentUpdatedMarkTitle")
}

function displayUpdatedMark(jsonString){
  let markTitle = localStorage.getItem("currentUpdatedMarkTitle");
  let allMarkTitles = JSON.parse(localStorage.getItem("allMarkTitles"));
  let allMarks = JSON.parse(localStorage.getItem("allMarks"));

  let mark = JSON.parse(jsonString);
  let markMap = new Map(Object.entries(mark));

  let markIndex = allMarkTitles.indexOf(markTitle);
  allMarkTitles.splice(markIndex, 1);
  allMarks.splice(markIndex, 1);

  allMarkTitles.push(markMap.get("title"));
  allMarks.push(mark);

  localStorage.setItem("allMarkTitles", JSON.stringify(allMarkTitles));
  localStorage.setItem("allMarks", JSON.stringify(allMarks));
  displayMarks();
}

function deleteMark(markId, markTitle) {
  let markBlock = document.getElementById("markBlock" + markId);
  let allMarkTitles = JSON.parse(localStorage.getItem("allMarkTitles"));
  let allMarks = JSON.parse(localStorage.getItem("allMarks"));
  let markIndex = allMarkTitles.indexOf(markTitle);
  allMarkTitles.splice(markIndex, 1);
  allMarks.splice(markIndex, 1);
  localStorage.setItem("allMarkTitles", JSON.stringify(allMarkTitles));
  localStorage.setItem("allMarks", JSON.stringify(allMarks));
  markBlock.remove();

  const url = "http://localhost:8080/marks/" + markId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("DELETE", url, false);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function showCreateForm() {
  document.getElementById("createMarkForm").hidden = false;
}

function hideCreateForm() {
  document.getElementById("createMarkForm").hidden = true;
  deleteCreateMarkTitleErrors();
  deleteCreateMarkDescriptionErrors();
}