let xmlHttp;

function createXMLHttpRequest() {
  if (window.ActiveXObject) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  }
}

function charge(){
  const url = "http://localhost:8080/charge";
  createXMLHttpRequest();
  let user = '{"login":"' + localStorage.getItem("login") + '"}';

  let resultMap = new Map();
  resultMap.set("amount", "10000");
  resultMap.set("stripeEmail", "email@gmail.com");
  resultMap.set("stripeToken", "pk_test_51NGMURC9egQsvr2snhtX9hjIcbw8R84DF1X6mdBrRViJAOVXD1R67ei6rDyaN7S43tXxQGW8tmvCtNCmVo2eo0v500xysrI9vl");
  let json = JSON.stringify(Object.fromEntries(resultMap));
  xmlHttp.open("POST", url, false);
  xmlHttp.setRequestHeader("Content-Type", "application/json");
  xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
  xmlHttp.send(json);
}

