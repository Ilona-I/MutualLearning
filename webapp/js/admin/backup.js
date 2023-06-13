let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function backUp() {
    const url = "http://localhost:8080/database/backup";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = handleStateChange;
    let user = '{"login":"' + localStorage.getItem("login") + '"}';
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
}

function restore() {
    const url = "http://localhost:8080/database/restore";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = handleStateChange;
    let user = '{"login":"' + localStorage.getItem("login") + '"}';
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
}

function handleStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            window.alert("Success!")
        }
    }
}