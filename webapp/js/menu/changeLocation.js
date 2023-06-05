function goToCreateArticle(link) {
  localStorage.removeItem("articleId");
  localStorage.setItem("articleType", "ARTICLE");
  document.location = link;
}

function goToCreateQuestion(link) {
  localStorage.removeItem("articleId");
  localStorage.setItem("articleType", "CREATE_QUESTION");
  document.location = link;
}