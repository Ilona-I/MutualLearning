function downloadFile(link) {
  localStorage.setItem("currentFileName", link)
  const url = "http://localhost:8080//downloadFile/" + link;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '"}';
  xmlHttp.open("GET", url, true);
  xmlHttp.responseType = "arraybuffer";
  xmlHttp.onreadystatechange = handleStateChangeDownloadFile;
  xmlHttp.setRequestHeader("Content-Type", "application/octet-stream");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateChangeDownloadFile() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      const blob = new Blob([xmlHttp.response],
          {type: 'application/octet-stream'});
      let link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      let fileName = localStorage.getItem("currentFileName");
      let startOfFileName = fileName.lastIndexOf("/")
      if (startOfFileName !== null) {
        fileName = fileName.substring(startOfFileName);
      }
      link.download = fileName;
      link.click();
      localStorage.removeItem("currentFileName");
    }
  }
}

function downloadImage(link) {
  const url = "http://localhost:8080//downloadFile/" + link;
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '"}';
  xmlHttp.open("GET", url, true);
  xmlHttp.responseType = "arraybuffer";
  xmlHttp.onreadystatechange = handleStateChangeDownloadImage;
  xmlHttp.setRequestHeader("Content-Type", "application/octet-stream");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send();
}

function handleStateChangeDownloadImage() {
  if (xmlHttp.readyState == 4) {
    if (xmlHttp.status == 200) {
      let imageOutputId = "articleImage" + localStorage.getItem(
          "currentImageId");
      const blob = new Blob([xmlHttp.response],
          {type: 'application/octet-stream'});
      let imageOutput = document.getElementById(imageOutputId);
      imageOutput.src = window.URL.createObjectURL(blob);
    }
  }
}
