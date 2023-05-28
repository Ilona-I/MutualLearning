function getInfoAboutMarks() {
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

function jsonMarksToHTML(jsonString) {
  let jsonObject = JSON.parse(jsonString);
  let innerHtml = "";
  for (const element of jsonObject) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    innerHtml += " <div class=\"form-check\">\n"
        + "                  <input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"defaultCheck1\">\n"
        + "                  <div class=\"form-check-label\" for=\"defaultCheck1\">\n"
        + "                    <div class=\"row\" style=\"margin-left: 2px; width: 90%;\">\n"
        + " <div style='margin: 3px;'>\n"
        + "        <button class=\"badge badge-pill ";
    if (markMap.get("type") === "system") {
      innerHtml += "badge-success";
    } else if (markMap.get("type") === "custom" && markMap.get("creator")
        === localStorage.getItem("userLogin")) {
      innerHtml += "badge-primary";
    } else {
      innerHtml += "badge-info";
    }
    innerHtml += "\"";
    innerHtml += " style=\"border-width: 0;\" data-toggle=\"modal\" data-target=\"#markListModal" + markMap.get("id") + "\">"
        + markMap.get("title")
        + "</button>\n"
        + "        <div class=\"modal fade\" id=\"markListModal" + markMap.get(
            "id")
        + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"markListModalLabel"
        + markMap.get("id") + "\" aria-hidden=\"true\">\n"
        + "          <div class=\"modal-dialog\" role=\"document\">\n"
        + "            <div class=\"modal-content\">\n"
        + "              <div class=\"modal-header\">\n"
        + "                <h5 class=\"modal-title\" id=\"markListModalLabel" +  markMap.get("id") + "\">"
        + "                   <span class=\"badge badge-pill ";
                                  if (markMap.get("type") === "system") {
                                    innerHtml += "badge-success";
                                  } else if (markMap.get("type") === "custom" && markMap.get("creator")
                                      === localStorage.getItem("userLogin")) {
                                    innerHtml += "badge-primary";
                                  } else {
                                    innerHtml += "badge-info";
                                  }
                                  innerHtml += "\"";
                               innerHtml += ">" + markMap.get("title")
        + "                   </span> "
        + "                 </h5>\n"
        + "                <button type=\"button\" class=\"close\"  data-toggle=\"modal\" aria-label=\"Close\">\n"
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

    if (markMap.get("creator")
        === localStorage.getItem("userLogin")) {
      innerHtml += "                      <!-- Початок блоку із редагуванням мітки -->\n"
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
          + "                          <input type=\"text\" class=\"form-control input_info\"\n"
          + "                                 placeholder=\"Mark name\" value='"
          + markMap.get("title") + "'>\n"
          + "                          <textarea class=\"form-control input_info\"\n"
          + "                                    placeholder=\"Description\">"
          + markMap.get("description") + "</textarea>\n"
          + "\n"
          + "                          <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
          + "                            <button type=\"button\" class=\"btn\"\n"
          + "                                    style=\"color: #68B684; border-color: #68B684; background-color: white;\">\n"
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
          + "                                aria-expanded=\"false\">\n"
          + "                          Видалити\n"
          + "                        </button>\n"
          + "                        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButtonDeleteMark"
          + markMap.get("id") + "\"\n"
          + "                             style=\"padding: 15px; width: 300px;\">\n"
          + "                          <p>Ви впевнені, що хочете видалити мітку?</p>\n"
          + "                          <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
          + "                            <button type=\"button\" class=\"btn btn-danger\">Видалити\n"
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

  }

  document.getElementById("markCheckboxList").innerHTML = innerHtml;
}
