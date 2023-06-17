function getAddNewElementButtonBlock(partId, sequenceNumber) {
  return "    <!-- Початок роботи з блоком додавання елементу -->\n"
      + "    <div id= 'prevNode" + partId
      + "'class=\"row\" style=\"width: 105%;\">\n"
      + "      <div class=\"dropdown\" style=\"margin-top: 5px; margin-left: -70px; \">\n"
      + "        <button class=\"btn btn-light dropdown-toggle\" type=\"button\"\n"
      + "                style=\"height: 28px; padding-right: 0; padding-top: 0;\" id=\"dropdownMenuButtonAdd"
      + partId + "\"\n"
      + "                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
      + "          +\n"
      + "        </button>\n"
      + "        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButtonAdd"
      + partId + "\">\n"
      + "          <button id='addText" + partId
      + "' class=\"dropdown-item\" onclick='addText(" + partId
      + ", "
      + sequenceNumber
      + ")' localization-key=\"add_text\">Додати текст</button>\n"
      + "          <button id='addImage" + partId
      + "' class=\"dropdown-item\" onclick='addImage(" + partId
      + ", "
      + sequenceNumber
      + ")' localization-key=\"add_photo\">Додати фото</button>\n"
      + "          <button id='addCode" + partId
      + "' class=\"dropdown-item\" onclick='addCode(" + partId
      + ", "
      + sequenceNumber
      + ")' localization-key=\"add_code\">Додати код</button>\n"
      + "          <button id='addFile" + partId
      + "' class=\"dropdown-item\" onclick='addFile(" + partId
      + ", "
      + sequenceNumber
      + ")' localization-key=\"add_file\">Додати файл</button>\n"
      + "          <button id='addLink" + partId
      + "' class=\"dropdown-item\" onclick='addLink(" + partId
      + ", "
      + sequenceNumber
      + ")' localization-key=\"add_link\">Додати посилання</button>\n"
      + "        </div>\n"
      + "      </div>\n"
      + "      <div style=\"width: 100%;\">\n"
      + "        <hr>\n"
      + "      </div>\n"
      + "<p style='color: lightgray; margin-top: 2px;'>|</p>"
      + "    </div>\n"
      + "    <!-- Кінець роботи з блоком додавання елементу -->";
}