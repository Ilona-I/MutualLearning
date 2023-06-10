function setMarksDisplay(marks) {
  let innerHTML = "";
  let marksId = [];
  for (const element of marks) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    marksId.push(markMap.get("id"));
    innerHTML += " <div style='margin: 3px;'>\n"
        + "        <button "
        + "class=\"badge badge-pill ";
    if (markMap.get("type") === "system") {
      innerHTML += "badge-success";
    } else if (markMap.get("type") === "custom" && markMap.get("creator")
        === localStorage.getItem("userLogin")) {
      innerHTML += "badge-primary";
    } else {
      innerHTML += "badge-info";
    }
    innerHTML += "\"";
    innerHTML += " style=\"border-width: 0;\" data-toggle=\"modal\" data-target=\"#exampleModal"
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
        + "<span class=\"badge badge-pill ";
    if (markMap.get("type") === "system") {
      innerHTML += "badge-success";
    } else if (markMap.get("type") === "custom" && markMap.get("creator")
        === localStorage.getItem("userLogin")) {
      innerHTML += "badge-primary";
    } else {
      innerHTML += "badge-info";
    }
    innerHTML += "\"";
    innerHTML += ">" + markMap.get("title") + "</span> </h5>\n"
        + "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
        + "                  <span aria-hidden=\"true\">&times;</span>\n"
        + "                </button>\n"
        + "              </div>\n"
        + "              <div class=\"modal-body\">\n"
        + "                " + markMap.get("description") + "\n"
        + "              </div>\n"
        + "              <div class=\"modal-footer\">\n"
        + "                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Закрити</button>\n"
        + "              </div>\n"
        + "            </div>\n"
        + "          </div>\n"
        + "        </div>\n"
        + "      </div>"
  }
  document.getElementById("markList").innerHTML = innerHTML;
}