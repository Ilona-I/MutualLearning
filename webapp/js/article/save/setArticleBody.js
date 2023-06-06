function getTextBlock(partMap) {
  return "<div style=\"width: 77%; margin-left: 15px;\">\n"
      + "        <!-- Початок роботи з блоком попереднього перегляду-->\n"
      + "        <div id='prevBlock" + partMap.get("id") + "'>\n"
      + "          <p id='displayTextType"+partMap.get("id")+"' class=\"article_text\">\n"
      + "            " + partMap.get("text") + "\n"
      + "          </p>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + "        <!-- Початок роботи з блоком редагування-->\n"
      + "        <div id='editBlock" + partMap.get("id") + "' hidden>\n"
      + "<textarea style='height: 400px;' id='editTextType"+partMap.get("id")+"' class=\"form-control input_info article_text\" >\n"
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
      + "           <input id='articleImageLink"+partMap.get("id")+"' type='text' value='"+partMap.get("link")+"' hidden>"
      + "          <img id='articleImage"+partMap.get("id")+"' src=\"\" alt=\"Фото\">\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + "        <!-- Початок роботи з блоком редагування-->\n"
      + "        <div id='editBlock" + partMap.get("id") + "' "
      + "             style=\"margin-top: 10px;\""
      + "             hidden>\n"
      + "          <input type=\"file\" accept=\"image/*\" "
      + "                onchange=\"document.getElementById('output"
      + partMap.get("id")
      + "').src = window.URL.createObjectURL(this.files[0])\" "
      + "                 id=\"actual-img-btn" + partMap.get("id")
      + "\" hidden>\n"
      + "          <label for=\"actual-img-btn" + partMap.get("id")
      + "\" class=\"btn btn-light\">Оберіть зображення</label><br>\n"
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
      + "<code id='displayCodeType"+partMap.get("id")+"'>\n"
      + escapeHTML(partMap.get("text"))
      + "</code>\n"
      + "    </pre>\n"
      + "      </p>\n"
      + "    </div>"
      + "<div style=\"width: 77%; margin-left: 15px;\""
      + "      id='editBlock" + partMap.get("id") + "' hidden>"
      + "<textarea  style='height: 400px;' id='editCodeType"+partMap.get("id")+"' onkeydown=\"if(event.keyCode===9){var v=this.value,s=this.selectionStart,"
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
      + "           <input id='displayFileTypeLink"+partMap.get("id")+"' type='text' value='"+partMap.get("link")+"' hidden>"
      + "               <p><button id='displayFileType"+partMap.get("id")+"' class='loadFileButton' onclick=\"downloadFile('"+partMap.get("link")+"')\">" + partMap.get("text")
      + "</button></p>\n"
      + "         </div>"
      + "         <div id='editBlock" + partMap.get("id") + "' hidden> "
      + "             <p>Текст відображення:</p>"
      + "           <input id='editFileTypeTitle"+partMap.get("id")+"' type='text' class=\"form-control input_info article_text\" value='"
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
      + " <a id='displayLinkType"+partMap.get("id")+"' href='" + partMap.get("link") + "'>" + partMap.get("text")
      + "</a>"
      + "    </div>"
      + "<div style=\"width: 77%; margin-left: 15px;\""
      + "      id='editBlock" + partMap.get("id") + "' hidden>"
      + "   <p>Текст посилання</p>"
      + "           <input id='editLinkTypeTitle"+partMap.get("id")+"' type='text' class=\"form-control input_info article_text\" value='"
      + partMap.get("text") + "'>"
      + "<br>"
      + "   <p>Посилання</p>"
      + "           <input id='editLinkTypeLink"+partMap.get("id")+"' type='text' class=\"form-control input_info article_text\" value='"
      + partMap.get("link") + "'>"
      + "</div>";
}

function setArticleBody(articleParts) {
  let innerHTML = "<div id='articleBlock0'>"
      + "<input type='number' "
      + "      id='sequenceNumber0'"
      + "      name='sequenceNumber'"
      + "      value='0' hidden>"
      + "</div>"
  innerHTML += getAddNewElementButtonBlock(0, 0);
  let images = [];
  let size = articleParts.length;
  for (const element of articleParts) {
    let part = JSON.parse(JSON.stringify(element));
    let partMap = new Map(Object.entries(part));
    let partId = partMap.get("id");
    let sequenceNumber = partMap.get("sequenceNumber");
    let partType = partMap.get("type");
    innerHTML += "<div onclick='cleanWrongData(\"articleBlock" + partId + "\")' id='articleBlock" + partId + "'>"
        + "<input type='number' "
        + "      name='sequenceNumber'"
        + "      id='sequenceNumber" + partId + "' "
        + "      value='" + sequenceNumber + "' hidden>"
        + "<input type='text' "
        + "      name='type'"
        + "      id='type" + partId + "' "
        + "      value='" + partType + "' hidden>"
        + "<input type='text' "
        + "      name='newOld'"
        + "      id='newOld" + partId + "' "
        + "      value='old' hidden>"
        + "<div class=\"row\" style=\"width: 130%;\">";
    if (partType === "TEXT") {
      innerHTML += getTextBlock(partMap);
    } else if (partType === "IMAGE") {
      images.push(partMap);
      innerHTML += getImageBlock(partMap);
    } else if (partType === "CODE") {
      innerHTML += getCodeBlock(partMap);
    } else if (partType === "FILE") {
      innerHTML += getFileBlock(partMap);
    } else if (partType === "LINK") {
      innerHTML += getLink(partMap);
    }
    innerHTML += getOptionBlock(partId, sequenceNumber, size + 1, false);
    innerHTML += "</div></div>";
    innerHTML += getAddNewElementButtonBlock(partId, sequenceNumber);
  }
  document.getElementById("articleBody").innerHTML = innerHTML;
  for(const element of images){
    localStorage.setItem("currentImageId", element.get("id"));
    downloadImage(element.get("link"));
  }
}