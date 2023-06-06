function addAnswerBlock(questionId){
  let answerId = getRandomInt();
  let innerHtml = "  <!-- Початок варіанту відповіді -->\n"
      + "          <div id='answerBlock" + answerId
      + "' class=\"row\" style=\"width: 100%; margin-top: 10px; background-color: #e4e7ec; \">\n"
      + "          <input name='answer" + questionId
      + "' type='text' value='" + answerId + "' hidden>"
      + "            <div style=\"width: 80%; padding: 20px;\">\n"
      + "              <h6>Варіант відповіді:</h6>\n"
      + "              <div>\n"
      + "                <p>Текст варіанту відповіді:</p>\n"
      + "                <textarea id='answerText"+answerId+"' class=\"form-control input_info\" placeholder=\"Answer\"></textarea>\n"
      + "              </div>\n"
      + "              <div class=\"row\" style=\"margin-top: 20px; margin-left: 10px;\">\n"
      + "                <p>Оцінка: </p>\n"
      + "                <input id='answerMark"+answerId+"' class=\"form-control input_info\" min=\"0\" type=\"number\" value=\"0\"\n"
      + "                       style=\"width: 100px; margin-left: 20px; margin-top: -10px;\">\n"
      + "              </div>\n"
      + "\n"
      + "            </div>\n"
      + "            <div\n"
      + "                style=\"padding: 20px; border-left-color: #d2cece; border-left-style: solid; border-left-width: 1px;\">\n"
      + "              <button onclick='deleteAnswerBlock(" + answerId
      + ")' class=\"btn btn-danger\" style=\"height: 40px;\">Видалити</button>\n"
      + "            </div>\n"
      + "\n"
      + "          </div>\n"
      + "          <!-- Кінець варіанту відповіді -->";
  let addButton = document.getElementById("addAnswerBlockButton"+questionId);
  addButton.parentNode.insertBefore(convertStringToHTML(innerHtml).children[0],
      addButton)
}