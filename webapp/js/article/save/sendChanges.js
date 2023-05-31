function sendArticleChanges() {
  let resultMap = new Map();
  let articleTypeArticleCheckbox = document.getElementById(
      "articleTypeArticleCheckbox");
  let articleTypeQuestionCheckbox = document.getElementById(
      "articleTypeQuestionCheckbox");
  let articleType = "";
  let articleTitle = document.getElementById("articleTitle").value;
  let articleMarks = localStorage.getItem("articleMarksId");
  let sequenceNumbers = document.getElementsByName("sequenceNumber");
  resultMap.set("title", articleTitle);
  resultMap.set("marksId",
      articleMarks.substring(1, articleMarks.length - 1).split(","));
  if (articleTypeQuestionCheckbox.checked === true) {
    articleType = "question";
  } else {
    articleType = "article";
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
      if (type === "text") {
        if (newOld === "new") {
          text = document.getElementById("newTextType" + elementId).value;
        } else if (newOld === "old") {
          let prevBlock = document.getElementById("prevBlock" + elementId);
          if (prevBlock.hidden === false) {
            console.log(35);
            text = document.getElementById(
                "displayTextType" + elementId).innerText;
            console.log(text)
          } else {
            text = document.getElementById(
                "editTextType" + elementId).value;
          }
        }
      } else if (type === "code") {
        if (newOld === "new") {
          text = document.getElementById("newCodeType" + elementId).value;
        } else if (newOld === "old") {
          let prevBlock = document.getElementById("prevBlock" + elementId);
          if (prevBlock.hidden === false) {
            text = document.getElementById(
                "displayCodeType" + elementId).innerText;
          } else {
            text = document.getElementById(
                "editCodeType" + elementId).value;
          }
        }
      } else if (type === "image") {
        if (newOld === "new") {
          let fileInput = document.getElementById("actual-img-btn" + elementId);
          let file = fileInput.files[0];
          if (file !== null) {
            link = file.name;
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
            if (file !== null) {
              link = file.name;
            }
          }
        }
      } else if (type === "file") {
        if (newOld === "new") {
          text = document.getElementById("newFileTypeText" + elementId).value;
          let fileInput = document.getElementById("file" + elementId);
          let file = fileInput.files[0];
          if (file !== null) {
            link = file.name;
          }
        } else if (newOld === "old") {
          let prevBlock = document.getElementById("prevBlock" + elementId);
          if (prevBlock.hidden === false) {
            text = document.getElementById(
                "displayFileType" + elementId).innerText;
            link = document.getElementById(
                "displayFileTypeLink" + elementId).value;
          } else {
            text = document.getElementById(
                "editFileTypeTitle" + elementId).value;
            let fileInput = document.getElementById("file" + elementId);
            let file = fileInput.files[0];
            if (file !== null) {
              link = file.name;
            }
          }
        }
      } else if (type === "link") {
        if (newOld === "new") {
          text = document.getElementById(
              "newLinkTypeTitle" + elementId).value;
          link = document.getElementById("newLinkTypeLink" + elementId).value;
        } else if (newOld === "old") {
          let prevBlock = document.getElementById("prevBlock" + elementId);
          if (prevBlock.hidden === false) {
            let el = document.getElementById("displayLinkType" + elementId);
            text = el.innerText;
            link = el.href;
          } else {
            text = document.getElementById(
                "editLinkTypeTitle" + elementId).value;
            link = document.getElementById(
                "editLinkTypeLink" + elementId).value;
          }
        }
      }
      elementMap.set("id", elementId);
      elementMap.set("sequenceNumber", element.value);
      elementMap.set("text", text);
      elementMap.set("link", link);
      elementMap.set("type", type);
      articleParts.push(Object.fromEntries(elementMap));
    }
    resultMap.set("articleParts", articleParts);
  }
  resultMap.set("type", articleType);

  let json = JSON.stringify(Object.fromEntries(resultMap));
  console.log(json);
  let articleId = localStorage.getItem("articleId");
  const url = "http://localhost:8080/articles/" + articleId;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '}';
  xmlHttp.open("PUT", url, false);
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
      //document.location = "../html/error.html";
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
    if (type === "image") {
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