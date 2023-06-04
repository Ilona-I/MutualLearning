function getRequestParamLine() {
  let page = localStorage.getItem("page");
  if (page === null) {
    page = 0;
  }
  let marks = getSelectedMarksId();
  let articleType = getSelectedType();
  let ownerType = getOwnerType();
  let sortType = getSortType();
  let searchLine = getSearchLine();
  let searchType = getSearchType();
  let requestParamLine = "";
  if (marks.length > 0) {
    requestParamLine = "marksId=";
    for (let i = 0; i < marks.length; i++) {
      requestParamLine += marks[i];
      if (i < marks.length - 1) {
        requestParamLine += ",";
      }
    }
  }

  if (searchLine !== '') {
    requestParamLine += "&searchLine=" + searchLine;
    requestParamLine += "&searchType=" + searchType;
  }
  requestParamLine += "&articleType=" + articleType;
  requestParamLine += "&ownerType=" + ownerType;
  requestParamLine += "&sortType=" + sortType;
  requestParamLine += "&page=" + page;
  requestParamLine += "&size=" + localStorage.getItem("size");
  localStorage.setItem("page", (1 + parseInt(page + "")).toString())
  return requestParamLine;
}

function getSelectedMarksId() {
  let selectedMarks = [];
  let markList = document.getElementsByName("markListElement");
  for (const element of markList) {
    if (element.checked === true) {
      selectedMarks += element.value;
    }
  }
  return selectedMarks;
}

function getSelectedType() {
  if (document.getElementById("inlineRadio2ArticleTypeArticle").getAttribute(
      "checked") != null) {
    return "article";
  }
  if (document.getElementById("inlineRadio3ArticleTypeQuestion").getAttribute(
      "checked") != null) {
    return "question";
  }
  return "all";
}

function getOwnerType() {
  if (document.getElementById("inlineRadio2OwnerOwn").getAttribute("checked")!=null) {
    return "own";
  }
  if (document.getElementById("inlineRadio3OwnerSaved").getAttribute("checked")!=null) {
    return "saved";
  }
  return "all";
}

function getSortType() {
  return document.getElementById("sort").value;
}

function getSearchLine() {
  return document.getElementById("searchArticle").value.trim();
}

function getSearchType() {
  return document.getElementById("searchType").value;
}