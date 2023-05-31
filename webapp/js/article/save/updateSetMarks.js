function updateSetMarks() {
  let allMarks = JSON.parse(localStorage.getItem("allMarks"));
  let articleMarksId = [];
  let articleMarks = [];

  for (const element of allMarks) {
    let mark = JSON.parse(JSON.stringify(element));
    let markMap = new Map(Object.entries(mark));
    let isChecked = document.getElementById(
        "defaultCheckMarkList" + markMap.get("id")).checked === true;
    if (isChecked === true) {
      articleMarks.push(element);
      articleMarksId.push(markMap.get("id"));
    }
  }
  setMarks(articleMarks);
  localStorage.setItem("articleMarksId", JSON.stringify(articleMarksId));
}