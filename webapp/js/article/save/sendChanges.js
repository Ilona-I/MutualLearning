function sendArticleChanges() {
  localStorage.setItem("articleId", "1");
  let resultMap = new Map();
  let articleTypeQuestionCheckbox = document.getElementById(
      "articleTypeQuestionCheckbox");
  let articleType = "";
  let articleTitle = document.getElementById("articleTitle").value;
  let articleMarks = localStorage.getItem("articleMarksId");
  let sequenceNumbers = document.getElementsByName("sequenceNumber");
  resultMap.set("title", articleTitle);
  if (articleMarks.length > 2) {
    resultMap.set("marksId",
        articleMarks.substring(1, articleMarks.length - 1).split(","));
  } else {
    resultMap.set("marksId", new Array(0))
  }
  if (articleTypeQuestionCheckbox.checked === true) {
    articleType = "QUESTION";
  } else {
    if (localStorage.getItem("isQuestionAnswer") === "true") {
      articleType = "QUESTION_ANSWER";
    } else {
      articleType = "ARTICLE";
      let articleParts = [];
      for (const element of sequenceNumbers) {
        let elementMap = new Map();
        let text = "";
        let link = ""
        let type = "";
        if (element.value === "0") {
          continue;
        }
        let elementId = element.id.replace("sequenceNumber", '');
        type = document.getElementById("type" + elementId).value;
        let newOld = document.getElementById("newOld" + elementId).value;
        if (type === "TEXT" || type === "text") {
          if (newOld === "new") {
            text = document.getElementById(
                "newTextType" + elementId).value.trim();
            if (text.length === 0) {
              wrongData("articleBlock" + elementId);
              return;
            }
          } else {
            let prevBlock = document.getElementById("prevBlock" + elementId);
            if (prevBlock.hidden === false) {
              text = document.getElementById(
                  "displayTextType" + elementId).innerHTML;
            } else {
              text = document.getElementById(
                  "editTextType" + elementId).value.trim();
              if (text.length === 0) {
                wrongData("articleBlock" + elementId);
                return;
              }
            }
          }
        } else if (type === "CODE" || type === "code") {
          if (newOld === "new") {
            text = document.getElementById(
                "newCodeType" + elementId).value.trim();
            if (text.length === 0) {
              wrongData("articleBlock" + elementId);
              return;
            }
          } else if (newOld === "old") {
            let prevBlock = document.getElementById("prevBlock" + elementId);
            if (prevBlock.hidden === false) {
              text = document.getElementById(
                  "displayCodeType" + elementId).innerText;
            } else {
              text = document.getElementById(
                  "editCodeType" + elementId).value.trim();
              if (text.length === 0) {
                wrongData("articleBlock" + elementId);
                return;
              }
            }
          }
        } else if (type === "IMAGE" || type === "image") {
          if (newOld === "new") {
            let fileInput = document.getElementById(
                "actual-img-btn" + elementId);
            let file = fileInput.files[0];
            if (file !== null && file !== undefined) {
              link = file.name;
            } else {
              wrongData("articleBlock" + elementId);
              return;
            }
          } else if (newOld === "old") {
            let prevBlock = document.getElementById("prevBlock" + elementId);
            if (prevBlock.hidden === false) {
              link = document.getElementById(
                  "articleImageLink" + elementId).value;
            } else {
              let fileInput = document.getElementById(
                  "actual-img-btn" + elementId);
              let file = fileInput.files[0];
              if (file !== null && file !== undefined) {
                link = file.name;
              }
            }
          }
        } else if (type === "FILE" || type === "file") {
          if (newOld === "new") {
            text = document.getElementById("newFileTypeText" + elementId).value;
            let fileInput = document.getElementById("file" + elementId);
            let file = fileInput.files[0];
            if (file !== null && file !== undefined) {
              link = file.name;
            } else {
              wrongData("articleBlock" + elementId);
              return;
            }
            if (text.length === 0) {
              wrongData("articleBlock" + elementId);
              return;
            }

          } else if (newOld === "old") {
            let prevBlock = document.getElementById("prevBlock" + elementId);
            if (prevBlock.hidden === false) {
              text = document.getElementById(
                  "displayFileType" + elementId).innerText.trim();
              ;
              link = document.getElementById(
                  "displayFileTypeLink" + elementId).value.trim();
              ;
            } else {
              text = document.getElementById(
                  "editFileTypeTitle" + elementId).value;
              let fileInput = document.getElementById("file" + elementId);
              let file = fileInput.files[0];
              if (file !== null && file !== undefined) {
                link = file.name;
              } else {
                link = document.getElementById(
                    "displayFileTypeLink" + elementId).value.trim();
              }
              if (text.length === 0) {
                wrongData("articleBlock" + elementId);
                return;
              }
            }
          }
        } else if (type === "LINK" || type === "link") {
          if (newOld === "new") {
            text = document.getElementById(
                "newLinkTypeTitle" + elementId).value.trim();
            link = document.getElementById(
                "newLinkTypeLink" + elementId).value.trim();
            if (text.length === 0) {
              wrongData("articleBlock" + elementId);
              return;
            }
            if (link.length === 0) {
              wrongData("articleBlock" + elementId);
              return;
            }
          } else if (newOld === "old") {
            let prevBlock = document.getElementById("prevBlock" + elementId);
            if (prevBlock.hidden === false) {
              let el = document.getElementById("displayLinkType" + elementId);
              text = el.innerText;
              link = el.href;
            } else {
              text = document.getElementById(
                  "editLinkTypeTitle" + elementId).value.trim();
              link = document.getElementById(
                  "editLinkTypeLink" + elementId).value.trim();
              if (text.length === 0) {
                wrongData("articleBlock" + elementId);
                return;
              }
              if (link.length === 0) {
                wrongData("articleBlock" + elementId);
                return;
              }
            }
          }
        }
        elementMap.set("id", elementId);
        elementMap.set("sequenceNumber", element.value);
        elementMap.set("text", text);
        elementMap.set("link", link);
        elementMap.set("type", type.toUpperCase());
        articleParts.push(Object.fromEntries(elementMap));

      }
      resultMap.set("articleParts", articleParts);
    }
  }
  resultMap.set("type", articleType.toUpperCase());

  let json = JSON.stringify(Object.fromEntries(resultMap));
  let articleId = localStorage.getItem("articleId");
  let url;
  if (articleId !== null) {
    url = "http://localhost:8080/articles/" + articleId;
    createXMLHttpRequest();
    xmlHttp.open("PUT", url, false);
  } else {
    url = "http://localhost:8080/articles";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
  }

  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.onreadystatechange = handleStateChangeSaveArticle;
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send(json);
}

function handleStateChangeSaveArticle() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      jsonToHTMLSaveArticle(xmlHttp.responseText);
    } else {
    }
  }
}

function jsonToHTMLSaveArticle(jsonString) {
  console.log(jsonString);
  let jsonObject = JSON.parse(jsonString);
  for (const element of jsonObject) {
    let file = JSON.parse(JSON.stringify(element));
    let fileMap = new Map(Object.entries(file));
    let id = fileMap.get("id");
    let type = fileMap.get("type");
    let link = fileMap.get("link");
    let input;
    if (type === "IMAGE") {
      input = document.getElementById("actual-img-btn" + id);
    } else {
      input = document.getElementById("file" + id);
    }

    if (input !== null && input.parentElement.hidden === false
        && input.parentElement.parentElement.hidden === false) {
      let formData = new FormData();
      formData.append("file", input.files[0]);
      const url = "http://localhost:8080/uploadFile/" + link;
      createXMLHttpRequest();
      let user = '{"login":"' + localStorage.getItem("login") + '}';
      xmlHttp.open("POST", url, false);
      xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
      xmlHttp.send(formData);
    }
  }

  document.location = "article.html"
}

function wrongData(id) {
  document.getElementById("myDialog").showModal();
  document.getElementById(id).style.backgroundColor = "#ffc5c5";
}

function closeDialog() {
  document.getElementById("myDialog").close()
}

function cleanWrongData(id) {
  document.getElementById(id).style.backgroundColor = "white";
}