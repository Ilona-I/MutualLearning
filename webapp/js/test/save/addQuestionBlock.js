function addQuestionBlock(){
  let questionId = getRandomInt();
 let innerHtml = "<div id='questionBlock" + questionId
      + "' style=\"border-color: #949393; margin-top: 10px; border-style: solid; border-width: 1px; padding: 20px;  background-color: #eeeeff\">\n"
      + "        <h6>Питання: </h6>"
      + "        <input name='question' type='text' value='" + questionId + "' hidden> "
      + "        <textarea id='questionText"+questionId+"' style='width: 85%' class=\"form-control input_info\" placeholder=\"Question\"></textarea>\n"
      + "        <button onclick='deleteQuestionBlock(" + questionId
      + ")' class=\"btn btn-danger\" style=\"height: 40px; float: right; margin-top: -40px;\"  localization-key=\"remove\">\n"
      + "          Видалити\n"
      + "        </button>\n"
      + "        <div style=\"margin-top: 10px; margin-left: 5px;\">\n"
      + "          <div onclick='checkOneAnswer("+questionId+")' class=\"custom-control custom-radio custom-control-inline\">\n"
      + "            <input type=\"radio\" id=\"customRadioOne" + questionId
      + "\" name=\"customRadio" + questionId
      + "\" class=\"custom-control-input\"  checked >\n"
      + "            <label class=\"custom-control-label\" for=\"customRadioOne"
      + questionId + "\"  localization-key=\"one_variant\">Один варіант відповіді</label>\n"
      + "          </div>\n"
      + "          <div onclick='checkSeveralAnswers("+questionId+")' class=\"custom-control custom-radio custom-control-inline\">\n"
      + "            <input type=\"radio\" id=\"customRadioSeveral"
      + questionId + "\" name=\"customRadio" + questionId
      + "\" class=\"custom-control-input\">\n"
      + "            <label class=\"custom-control-label\" for=\"customRadioSeveral"
      + questionId + "\"  localization-key=\"several_variants\">Декілька варіантів\n"
      + "              відповіді</label>\n"
      + "          </div>\n"
      + "        </div>"
      + "       <!-- Початок варіантів відповідей -->\n"
      + "        <div style=\"width: 100%; padding: 20px;\">"
      + "          <div id='addAnswerBlockButton"+questionId+"' style=\"margin-top: 20px;\">\n"
      + "            <button onclick='addAnswerBlock("+questionId+")' class=\"btn btn-dark\"  localization-key=\"add_answer\"> Додати варіант відповіді</button>\n"
      + "          </div>\n"
      + "        </div>\n"
      + "        <!-- Кінець  варіантів відповідей -->\n"
      + "      </div>\n"
      + "      <!-- Кінець питання-->";
  let addButton = document.getElementById("addQuestionBlockButton");
  addButton.parentNode.insertBefore(convertStringToHTML(innerHtml).children[0],
      addButton)
}