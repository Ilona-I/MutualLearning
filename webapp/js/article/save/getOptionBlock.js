function getOptionBlock(partId, sequenceNumber, size, isNewElement) {
  let result = " <!-- Початок роботи з блоком опцій редагування-->\n"
      + "      <div class=\"edit_article_option_block\">\n"
      + "\n"
      + "        <!-- Початок роботи з кнопками позиції блоку-->\n"
      + "        <div class=\"row\" style=\"margin-left: 15px;\">\n";

  result += "          <button id='upButton" + partId + "' "
      + "name='upButton' \n"
      + "onclick='upArticlePart(" + partId + ", " + sequenceNumber + ", " + size
      + ")' \n"
      + (sequenceNumber === 1 ? 'class=\'upDisableButton\' disabled'
          : 'class=\'upActiveButton\'')
      + ">\n"
      + "            &#8673;\n"
      + "          </button>\n";
  result += "          <button id='downButton" + partId + "' "
      + "name='downButton' \n"
      + "onclick='downArticlePart(" + partId + ", " + sequenceNumber + ", "
      + size + ")' \n"
      + (sequenceNumber === size - 1 ? 'class=\'downDisableButton\' disabled'
          : 'class=\'downActiveButton\'')
      + ">\n"
      + "            &#8675;\n"
      + "          </button>\n"
      + "        </div>\n"
      + "        <!-- Кінець роботи з кнопками позиції блоку-->\n";
  if (!isNewElement) {
    result += "        <!-- Початок роботи з кнопкою редагування-->\n"
        + "        <button class=\"btn\"\n"
        + "                style=\"border-color: #95E06C; color: #95E06C; height:25px; width: 100px; font-size: small;margin-top: 5px; padding-top: 0; padding-bottom: 1px;\""
        + "                id='editButtonOption" + partId + "'"
        + "                onclick='editBlock(" + partId + ")' localization-key=\"edit\">\n"
        + "          Редагувати\n"
        + "        </button>\n"
        + "        <!-- Кінець роботи з кнопкою редагування-->\n"
        + "        <!-- Початок роботи з кнопкою скасування редагування-->\n"
        + "        <button class=\"btn\"\n"
        + "                style=\"border-color: grey ; color: grey; height:25px; width: 100px; font-size: small;margin-top: 5px; padding-top: 0; padding-bottom: 1px;\""
        + "                id='cancelButtonOption" + partId + "' "
        + "                onclick='cancelEditBlock(" + partId + ")'"
        + "                hidden localization-key=\"cancel\">\n"
        + "          Скасувати\n"
        + "        </button>\n"
        + "        <!-- Кінець роботи з кнопкою скасування редагування-->\n";
  }
  result += "        <!-- Початок блоку з видаленням елементу -->\n"
      + "        <div class=\"dropdown\">\n"
      + "          <button class=\"btn dropdown-toggle\" type=\"button\"\n"
      + "                  style=\"height:25px;  width: 100px; margin-top: 2px; padding-top: 0; padding-bottom: 0; background-color: transparent; color: #ff6e64; border-color:#ff6e64; font-size: small;\"\n"
      + "                  id=\"dropdownMenuButton" + partId
      + "\" data-toggle=\"dropdown\"\n"
      + "                  aria-haspopup=\"true\"\n"
      + "                  aria-expanded=\"false\" localization-key=\"remove\">\n"
      + "            Видалити\n"
      + "          </button>\n"
      + "          <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton"
      + partId + "\"\n"
      + "               style=\"padding: 15px; width: 300px;\">\n"
      + "            <p localization-key=\"confirm_delete_block\">Ви впевнені, що хочете видалити блок?</p>\n"
      + "            <div class=\"row\" style=\"margin-left: 20px; margin-top: 10px;\">\n"
      + "              <button onclick='deleteBlock(\"" + partId
      + "\")' type=\"button\" class=\"btn btn-danger\" localization-key=\"remove\">Видалити\n"
      + "              </button>\n"
      + "              <button type=\"button\" class=\"btn btn-secondary\"\n"
      + "                      style=\"margin-left: 10px; color: gray; border-color: gray; background-color: white;\" "
      + "   localization-key=\"cancel\">\n"
      + "                Скасувати\n"
      + "              </button>\n"
      + "            </div>\n"
      + "          </div>\n"
      + "        </div>\n"
      + "        <!-- Кінець блоку з видаленням елементу -->\n"
      + "      </div>\n"
      + "      <!-- Кінець роботи з блоком опцій редагування-->";
  return result;
}