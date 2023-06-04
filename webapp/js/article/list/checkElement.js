function checkAllArticleTypes(){
  document.getElementById("inlineRadio1ArticleTypeAll").setAttribute("checked", "checked");
  document.getElementById("inlineRadio2ArticleTypeArticle").removeAttribute("checked");
  document.getElementById("inlineRadio3ArticleTypeQuestion").removeAttribute("checked");
}

function checkArticles(){
  document.getElementById("inlineRadio1ArticleTypeAll").removeAttribute("checked");
  document.getElementById("inlineRadio2ArticleTypeArticle").setAttribute("checked", "checked");
  document.getElementById("inlineRadio3ArticleTypeQuestion").removeAttribute("checked");
}

function checkQuestions(){
  document.getElementById("inlineRadio1ArticleTypeAll").removeAttribute("checked");
  document.getElementById("inlineRadio2ArticleTypeArticle").removeAttribute("checked");
  document.getElementById("inlineRadio3ArticleTypeQuestion").setAttribute("checked", "checked");
}

function checkAllOwners(){
  document.getElementById("inlineRadio1OwnerAll").setAttribute("checked", "checked");
  document.getElementById("inlineRadio2OwnerOwn").removeAttribute("checked");
  document.getElementById("inlineRadio3OwnerSaved").removeAttribute("checked");
}

function checkOwn(){
  document.getElementById("inlineRadio1OwnerAll").removeAttribute("checked");
  document.getElementById("inlineRadio2OwnerOwn").setAttribute("checked", "checked");
  document.getElementById("inlineRadio3OwnerSaved").removeAttribute("checked");
}

function checkLiked(){
  document.getElementById("inlineRadio1OwnerAll").removeAttribute("checked");
  document.getElementById("inlineRadio2OwnerOwn").removeAttribute("checked");
  document.getElementById("inlineRadio3OwnerSaved").setAttribute("checked", "checked");
}