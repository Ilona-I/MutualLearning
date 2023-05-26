let xmlHttp;
const articleTypeArticle = "article";
const articleTypeQuestion = "question";

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function getInfoAboutCurrentArticle() {
  let articleId = localStorage.getItem("articleId");
  articleId = 1;
  if (articleId == null) {
    let articleType = localStorage.getItem("articleType");
    setStructure(articleType);
    return;
  }
  const url = "http://localhost:8080/articles/edit?id=" + articleId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("GET", url, false);
  xmlHttp.onreadystatechange = handleStateChange;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function setStructure(articleType) {
  if (articleType === articleTypeQuestion) {
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
    setTypeQuestion();
  } else {
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
    setTypeArticle()
  }
}

function setTypeArticle() {
  document.getElementById("articleBody").hidden = false;
}

function setTypeQuestion() {
  document.getElementById("articleBody").hidden = true;

}

function handleStateChange() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTML(xmlHttp.responseText);
    } else {
      document.location = "../html/error.html";
    }
  }
}

function setMarks(marks) {
  let innerHTML = "";
  for (const element of marks) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    innerHTML += " <div style='margin: 3px;'>\n"
        + "        <button class=\"badge badge-pill badge-light\" style=\"border-width: 0;\" data-toggle=\"modal\" data-target=\"#exampleModal"
        + markMap.get("id") + "\">"
        + markMap.get("title") + "</button>\n"
        + "        <div class=\"modal fade\" id=\"exampleModal" + markMap.get(
            "id")
        + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel"
        + markMap.get("id") + "\" aria-hidden=\"true\">\n"
        + "          <div class=\"modal-dialog\" role=\"document\">\n"
        + "            <div class=\"modal-content\">\n"
        + "              <div class=\"modal-header\">\n"
        + "                <h5 class=\"modal-title\" id=\"exampleModalLabel"
        + markMap.get("id") + "\">"
        + markMap.get("title") + "</h5>\n"
        + "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
        + "                  <span aria-hidden=\"true\">&times;</span>\n"
        + "                </button>\n"
        + "              </div>\n"
        + "              <div class=\"modal-body\">\n"
        + "                " + markMap.get("description") + "\n"
        + "              </div>\n"
        + "              <div class=\"modal-footer\">\n"
        + "                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n"
        + "              </div>\n"
        + "            </div>\n"
        + "          </div>\n"
        + "        </div>\n"
        + "      </div>"
  }
  document.getElementById("markList").innerHTML = innerHTML;
}

function getAddNewElementButtonBlock(partId, sequenceNumber) {
  return "    <!-- Початок роботи з блоком додавання елементу -->\n"
      + "    <div id= 'prevNode" + partId
      + "'class=\"row\" style=\"width: 105%;\">\n"
      + "      <div class=\"dropdown\" style=\"margin-top: 5px; margin-left: -70px; \">\n"
      + "        <button class=\"btn btn-light dropdown-toggle\" type=\"button\"\n"
      + "                style=\"height: 28px; padding-right: 0; padding-top: 0;\" id=\"dropdownMenuButtonAdd"
      + partId + "\"\n"
      + "                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
      + "          +\n"
      + "        </button>\n"
      + "        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButtonAdd"
      + partId + "\">\n"
      + "          <button class=\"dropdown-item\" onclick='addText(" + partId
      + ", "
      + sequenceNumber
      + ")'>Додати текст</button>\n"
      + "          <button class=\"dropdown-item\" onclick='addImage(" + partId
      + ", "
      + sequenceNumber
      + ")'>Додати фото</button>\n"
      + "          <button class=\"dropdown-item\" onclick='addCode(" + partId
      + ", "
      + sequenceNumber
      + ")'>Додати код</button>\n"
      + "          <button class=\"dropdown-item\" onclick='addFile(" + partId
      + ", "
      + sequenceNumber
      + ")'>Додати файл</button>\n"
      + "          <button class=\"dropdown-item\" onclick='addLink(" + partId
      + ", "
      + sequenceNumber
      + ")'>Додати файл</button>\n"
      + "        </div>\n"
      + "      </div>\n"
      + "      <div style=\"width: 100%;\">\n"
      + "        <hr>\n"
      + "      </div>\n"
      + "<p style='color: lightgray; margin-top: 2px;'>|</p>"
      + "    </div>\n"
      + "    <!-- Кінець роботи з блоком додавання елементу -->";
}

function getOptionBlock(partId, isNewElement) {
  let result = " <!-- Початок роботи з блоком опцій редагування-->\n"
      + "      <div class=\"edit_article_option_block\">\n"
      + "\n"
      + "        <!-- Початок роботи з кнопками позиції блоку-->\n"
      + "        <div class=\"row\" style=\"margin-left: 15px;\">\n"
      + "          <button\n"
      + "              style=\"padding: 0; background-color: transparent; border-width: 0; color: #68B684; font-size: xx-large\">\n"
      + "            &#8673;\n"
      + "          </button>\n"
      + "          <button\n"
      + "              style=\"margin-left: 30px; padding: 0; background-color: transparent; border-width: 0;  color: #68B684; font-size: xx-large\">\n"
      + "            &#8675;\n"
      + "          </button>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з кнопками позиції блоку-->\n";
  if (!isNewElement) {
    result += "        <!-- Початок роботи з кнопкою редагування-->\n"
        + "        <button class=\"btn\"\n"
        + "                style=\"border-color: #95E06C; color: #95E06C; height:25px; width: 100px; font-size: small;margin-top: 5px; padding-top: 0; padding-bottom: 1px;\""
        + "                id='editButtonOption" + partId + "'"
        + "                onclick='editBlock(" + partId + ")'>\n"
        + "          Редагувати\n"
        + "        </button>\n"
        + "        <!-- Кінець роботи з кнопкою редагування-->\n"
        + "        <!-- Початок роботи з кнопкою скасування редагування-->\n"
        + "        <button class=\"btn\"\n"
        + "                style=\"border-color: grey ; color: grey; height:25px; width: 100px; font-size: small;margin-top: 5px; padding-top: 0; padding-bottom: 1px;\""
        + "                id='cancelButtonOption" + partId + "' "
        + "                onclick='cancelEditBlock(" + partId + ")'"
        + "                hidden>\n"
        + "          Скасувати\n"
        + "        </button>\n"
        + "        <!-- Кінець роботи з кнопкою скасування редагування-->\n";
  }
  result += "        <!-- Початок блоку з видаленням елементу -->\n"
      + "        <div class=\"dropdown\">\n"
      + "          <button class=\"btn dropdown-toggle\" type=\"button\"\n"
      + "                  style=\"height:25px;  width: 100px; margin-top: 2px; padding-top: 0; padding-bottom: 0; background-color: transparent; color: #ff6e64; border-color:#ff6e64; font-size: small;\"\n"
      + "                  id=\"dropdownMenuButton" + partId
      + "\" data-toggle=\"dropdown\"\n"
      + "                  aria-haspopup=\"true\"\n"
      + "                  aria-expanded=\"false\">\n"
      + "            Видалити\n"
      + "          </button>\n"
      + "          <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton"
      + partId + "\"\n"
      + "               style=\"padding: 15px; width: 300px;\">\n"
      + "            <p>Ви впевнені, що хочете видалити блок?</p>\n"
      + "            <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
      + "              <button type=\"button\" class=\"btn btn-danger\">Видалити\n"
      + "              </button>\n"
      + "              <button type=\"button\" class=\"btn btn-secondary\"\n"
      + "                      style=\"margin-left: 10px; color: gray; border-color: gray; background-color: white;\">\n"
      + "                Скасувати\n"
      + "              </button>\n"
      + "            </div>\n"
      + "          </div>\n"
      + "        </div>\n"
      + "        <!-- Кінець блоку з видаленням елементу -->\n"
      + "      </div>\n"
      + "      <!-- Кінець роботи з блоком опцій редагування-->";
  return result;
}

function getTextBlock(partMap) {
  return "<div style=\"width: 77%; margin-left: 15px;\">\n"
      + "        <!-- Початок роботи з блоком попереднього перегляду-->\n"
      + "        <div id='prevBlock" + partMap.get("id") + "'>\n"
      + "          <p class=\"article_text\">\n"
      + "            " + partMap.get("text") + "\n"
      + "          </p>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + "        <!-- Початок роботи з блоком редагування-->\n"
      + "        <div id='editBlock" + partMap.get("id") + "' hidden>\n"
      + "<textarea class=\"form-control input_info article_text\" >\n"
      + partMap.get("text") + "\n"
      + "</textarea>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком редагування-->\n"
      + "      </div>";
}

function getImageBlock(partMap) {
  return " <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "        <!-- Початок роботи з блоком попереднього перегляду-->\n"
      + "        <div id='prevBlock" + partMap.get("id") + "'>\n"
      + "          <img src=\"../../file/profile.png\" alt=\"Фото\">\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + "        <!-- Початок роботи з блоком редагування-->\n"
      + "        <div id='editBlock" + partMap.get("id") + "' "
      + "             style=\"margin-top: 10px;\""
      + "             hidden>\n"
      + "          <input type=\"file\" accept=\"image/*\" "
      + "                 onchange=\"loadFile(event, 'output" + partMap.get(
          "id") + "')\" "
      + "                 id=\"actual-img-btn" + partMap.get("id")
      + "\" hidden>\n"
      + "          <label for=\"actual-img-btn" + partMap.get("id")
      + "\" class=\"btn btn-light\">Оберіть зображення</label>\n"
      + "          <img id=\"output" + partMap.get("id")
      + "\" style=\"max-width: 100%;\"/>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком редагування-->\n"
      + "      </div>\n";
}

function getCodeBlock(partMap) {
  return " <div style=\"width: 77%; margin-left: 15px;\""
      + "         id='prevBlock" + partMap.get("id") + "'>\n"
      + "      <p>\n"
      + "      <pre>\n"
      + "<code>\n"
      + escapeHTML(partMap.get("text"))
      + "</code>\n"
      + "    </pre>\n"
      + "      </p>\n"
      + "    </div>"
      + "<div style=\"width: 77%; margin-left: 15px;\""
      + "      id='editBlock" + partMap.get("id") + "' hidden>"
      + "<textarea onkeydown=\"if(event.keyCode===9){var v=this.value,s=this.selectionStart,"
      + "e=this.selectionEnd;this.value=v.substring(0, s)+'\\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;"
      + "return false;}\" "
      + " id='textareaCode" + partMap.get("id")
      + "' style=\"width: 100%; height: 300px; font-family: 'Courier New'\"> "
      + escapeHTML(partMap.get("text"))
      + "</textarea>"
      + "</div>";
}

function getFileBlock(partMap) {
  return "<div style=\"width: 77%; margin-left: 15px;\">\n"
      + "         <div id='prevBlock" + partMap.get("id") + "'>"
      + "               <p><a href=\"../../file/1.pdf\">" + partMap.get("text")
      + "</a></p>\n"
      + "         </div>"
      + "         <div id='editBlock" + partMap.get("id") + "' hidden> "
      + "             <p>Текст відображення:</p>"
      + "           <input type='text' class=\"form-control input_info article_text\" value='"
      + partMap.get("text") + "'>"
      + "             <br>"
      + "          <label for=\"file" + partMap.get("id")
      + "\" class=\"drop-container\">\n"
      + "  <input type=\"file\" id=\"file" + partMap.get("id")
      + "\" accept=\"*/*\" required>\n"
      + "</label>"
      + "         </div>  "
      + "       </div>";
}

function getLink(partMap) {
  return " <div style=\"width: 77%; margin-left: 15px;\""
      + "         id='prevBlock" + partMap.get("id") + "'>\n"
      + " <a href='" + partMap.get("link") + "'>" + partMap.get("text")
      + "</a>"
      + "    </div>"
      + "<div style=\"width: 77%; margin-left: 15px;\""
      + "      id='editBlock" + partMap.get("id") + "' hidden>"
      + "   <p>Текст посилання</p>"
      + "           <input type='text' class=\"form-control input_info article_text\" value='"
      + partMap.get("text") + "'>"
      + "<br>"
      + "   <p>Посилання</p>"
      + "           <input type='text' class=\"form-control input_info article_text\" value='"
      + partMap.get("link") + "'>"
      + "</div>";
}

function setArticleBody(articleParts) {
  let innerHTML = "<div id='articleBlock0'></div>"
  innerHTML += getAddNewElementButtonBlock(0, 0);
  innerHTML += "<input type='number' "
      + "      name='sequenceNumber'"
      + "      value='0' hidden>"
  for (const element of articleParts) {
    let part = JSON.parse(JSON.stringify(element));
    let partMap = new Map(Object.entries(part));
    let partId = partMap.get("id");
    let sequenceNumber = partMap.get("sequenceNumber");
    let partType = partMap.get("type");
    innerHTML += "<div id='articleBlock" + partId + "'>"
        + "<input type='number' "
        + "      name='sequenceNumber'"
        + "      id='sequenceNumber" + partId + "' "
        + "      value='" + sequenceNumber + "' hidden>"
        + "<input type='text' "
        + "      name='type'"
        + "      id='type" + partId + "' "
        + "      value='" + partType + "' hidden>"
        + "<div class=\"row\" style=\"width: 130%;\">";
    if (partType === "text") {
      innerHTML += getTextBlock(partMap);
    } else if (partType === "image") {
      innerHTML += getImageBlock(partMap);
    } else if (partType === "code") {
      innerHTML += getCodeBlock(partMap);
    } else if (partType === "file") {
      innerHTML += getFileBlock(partMap);
    } else if (partType === "link") {
      innerHTML += getLink(partMap);
    }
    innerHTML += getOptionBlock(partId, false);
    innerHTML += "</div></div>";
    innerHTML += getAddNewElementButtonBlock(partId, sequenceNumber);
  }
  document.getElementById("articleBody").innerHTML = innerHTML;
}

function escapeHTML(html) {
  return html.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g,
      '&gt;');
}

function jsonToHTML(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let dataMap = new Map(Object.entries(jsonObject));
  let title = dataMap.get("title");
  let type = dataMap.get("type");
  let marks = dataMap.get("marks");
  document.getElementById("articleTitle").innerText = title;
  setMarks(marks);

  if (type === "question") {
    document.getElementById("articleTypeQuestionCheckbox").checked = true;
    setTypeQuestion();
    return;
  } else {
    document.getElementById(
        "articleTypeArticleCheckbox").checked = true;
    setTypeArticle();
  }
  let articleParts = dataMap.get("articleParts");
  setArticleBody(articleParts);
}

document.addEventListener("DOMContentLoaded", function () {
  getInfoAboutCurrentArticle()
});

function editBlock(partId) {
  let prevBlock = "prevBlock" + partId;
  let editBlock = "editBlock" + partId;
  let editButtonOption = "editButtonOption" + partId;
  let cancelButtonOption = "cancelButtonOption" + partId;
  document.getElementById(prevBlock).hidden = true;
  document.getElementById(editBlock).hidden = false;
  document.getElementById(editButtonOption).hidden = true;
  document.getElementById(cancelButtonOption).hidden = false;
}

function cancelEditBlock(partId) {
  let prevBlock = "prevBlock" + partId;
  let editBlock = "editBlock" + partId;
  let editButtonOption = "editButtonOption" + partId;
  let cancelButtonOption = "cancelButtonOption" + partId;
  document.getElementById(prevBlock).hidden = false;
  document.getElementById(editBlock).hidden = true;
  document.getElementById(editButtonOption).hidden = false;
  document.getElementById(cancelButtonOption).hidden = true;
}

function addText(partId, prevSequenceNumberValue) {
  let blockContent =
      "<input type='text' "
      + "      name='type'"
      + "      id='type" + partId + "' "
      + "      value='text' hidden>"
      + "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "           <textarea class=\"form-control input_info article_text\" >\n"
      + "           </textarea>\n"
      + "       </div>";
  addNewBlock(blockContent, partId, prevSequenceNumberValue);
}

function addImage(partId, prevSequenceNumberValue) {
  let blockContent = "<input type='text' "
      + "      name='type'"
      + "      id='type" + partId + "' "
      + "      value='image' hidden>"
      + "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "          <input type=\"file\" accept=\"image/*\" "
      + "                 onchange=\"loadFile(event, 'output" + partId + "')\" "
      + "                 id=\"actual-img-btn" + partId
      + "\" hidden>\n"
      + "          <label for=\"actual-img-btn" + partId
      + "\" class=\"btn btn-light\">Оберіть зображення</label>\n"
      + "          <img id=\"output" + partId
      + "\" style=\"max-width: 100%;\"/>\n"
      + "       </div>";
  addNewBlock(blockContent, partId, prevSequenceNumberValue);
}

function addCode(partId, prevSequenceNumberValue) {
  let blockContent =
      "<input type='text' "
      + "      name='type'"
      + "      id='type" + partId + "' "
      + "      value='code' hidden>"
      + "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "<textarea onkeydown=\"if(event.keyCode===9){var v=this.value,s=this.selectionStart,"
      + "e=this.selectionEnd;this.value=v.substring(0, s)+'\\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;"
      + "return false;}\" "
      + "style=\"width: 100%; height: 300px; font-family: 'Courier New'\"> "
      + "</textarea>"
      + "       </div>";
  addNewBlock(blockContent, partId, prevSequenceNumberValue);
}

function addFile(partId, prevSequenceNumberValue) {

}

function addLink(partId, prevSequenceNumberValue) {

}

function addNewBlock(blockContent, partId, prevSequenceNumberValue) {
  let allSequenceNumbers = document.getElementsByName("sequenceNumber");
  let codeAfter = "";
  let cElement;
  let cElementValue;
  let id;
  for (const element of allSequenceNumbers) {
    let elementValue = parseInt(element.value);
    if (elementValue > prevSequenceNumberValue) {
      element.value = (elementValue + 1);
    } else if (elementValue === prevSequenceNumberValue) {
      id = getRandomInt();
      cElementValue = elementValue;
      codeAfter += "<div id='articleBlock" + id + "'>"
          + "<input type='number' "
          + "      name='sequenceNumber'"
          + "      id='sequenceNumber" + id + "' "
          + "      value='" + (elementValue + 1) + "' hidden>";
      codeAfter += blockContent;
      codeAfter += getOptionBlock(id, true);
      codeAfter += "</div>"
          + "</div>";
      codeAfter += getAddNewElementButtonBlock(id, cElementValue);
    }
  }
  cElement = document.getElementById("prevNode" + partId)
  cElement.parentNode.insertBefore(convertStringToHTML(codeAfter),
      cElement.nextSibling);
}

const convertStringToHTML = htmlString => {
  const parser = new DOMParser();
  const html = parser.parseFromString(htmlString, 'text/html');
  return html.body;
}

function getRandomInt() {
  return Math.floor(Math.random() * 90071992547409);
}
