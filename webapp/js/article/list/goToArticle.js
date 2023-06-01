function goToTheArticle(id){
    localStorage.setItem("articleId", id);
    document.location="article.html";
}

function answerTheQuestion(id){
    localStorage.setItem("articleId", id);
    document.location="saveArticle.html";
}

