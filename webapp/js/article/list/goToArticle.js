function goToTheArticle(id) {
  localStorage.setItem("articleId", id);
  document.location = "article.html";
}

function answerTheQuestion(id, questionCreatorLogin) {
  localStorage.setItem("questionCreatorLogin", questionCreatorLogin)
  localStorage.setItem("articleId", id);
  document.location = "saveArticle.html";
}

