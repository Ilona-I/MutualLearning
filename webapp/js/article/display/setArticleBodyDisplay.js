function getTextBlockDisplay(partMap) {
  return "<div >\n"
      + "        <!-- Початок роботи з блоком попереднього перегляду-->\n"
      + "          <p id='displayTextType" + partMap.get("id")
      + "' class=\"article_text\">\n"
      + "            " + partMap.get("text") + "\n"
      + "          </p>\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + " </div>";
}

function getImageBlockDisplay(partMap) {
  return " <div>\n"
      + "        <!-- Початок роботи з блоком попереднього перегляду-->\n"
      + "          <input id='articleImageLink" + partMap.get("id")
      + "' type='text' value='" + partMap.get("link") + "' hidden>"
      + "          <img id='articleImage" + partMap.get("id")
      + "' src=\"\" alt=\"Фото\">\n"
      + "        <!-- Кінець роботи з блоком попереднього перегляду-->\n"
      + "  </div>\n";
}

function getCodeBlockDisplay(partMap) {
  return " <div>\n"
      + "      <p>\n"
      + "      <pre>\n"
      + "<code id='displayCodeType" + partMap.get("id") + "'>\n"
      + escapeHTML(partMap.get("text"))
      + "</code>\n"
      + "    </pre>\n"
      + "      </p>\n"
      + "    </div>";
}

function getFileBlockDisplay(partMap) {
  return "<div>\n"
      + "           <input id='displayFileTypeLink" + partMap.get("id")
      + "' type='text' value='" + partMap.get("link") + "' hidden>"
      + "               <p><button id='displayFileType" + partMap.get("id")
      + "' class='loadFileButton' onclick=\"downloadFile('" + partMap.get(
          "link") + "')\">" + partMap.get("text")
      + "</button></p>\n"
      + "</div>";
}

function getLinkDisplay(partMap) {
  return " <div>\n"
      + " <a id='displayLinkType" + partMap.get("id") + "' href='"
      + partMap.get("link") + "'>" + partMap.get("text")
      + "</a>"
      + " </div>";
}

function setArticleBodyDisplay(articleParts) {
  localStorage.removeItem("currentImageId");
  let innerHTML = "";
  let images = [];
  for (const element of articleParts) {
    let part = JSON.parse(JSON.stringify(element));
    let partMap = new Map(Object.entries(part));
    let partType = partMap.get("type");
    if (partType === "TEXT") {
      innerHTML += getTextBlockDisplay(partMap);
    } else if (partType === "IMAGE") {
      images.push(partMap);
      innerHTML += getImageBlockDisplay(partMap);
    } else if (partType === "CODE") {
      innerHTML += getCodeBlockDisplay(partMap);
    } else if (partType === "FILE") {
      innerHTML += getFileBlockDisplay(partMap);
    } else if (partType === "LINK") {
      innerHTML += getLinkDisplay(partMap);
    }
    innerHTML += "</div>";
  }
  console.log(images)
  document.getElementById("articleBody").innerHTML = innerHTML;
  for(const element of images){
    localStorage.setItem("currentImageId", element.get("id"));
    downloadImage(element.get("link"));
  }
}