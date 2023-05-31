function addText(partId, sequenceNumberValue) {
  let id = getRandomInt();
  let blockContent = "<div class=\"row\" style=\"width: 130%;\">\n"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "           <textarea id='newTextType"+id+"' class=\"form-control input_info article_text\" >\n"
      + "           </textarea>\n"
      + "       </div>\n";
  addNewBlock(blockContent, partId, sequenceNumberValue, "text", id);
}

function addImage(partId, sequenceNumberValue) {
  let id = getRandomInt();
  let blockContent = "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "          <input type=\"file\" accept=\"image/*\" "
      + "                 onchange=\"document.getElementById('output" + id
      + "').src = window.URL.createObjectURL(this.files[0])\" "
      + "                 id=\"actual-img-btn" + id
      + "\" hidden>\n"
      + "          <label for=\"actual-img-btn" + id
      + "\" class=\"btn btn-light\">Оберіть зображення</label><br>\n"
      + "          <img id=\"output" + id
      + "\" style=\"max-width: 100%;\"/>\n"
      + "       </div>";
  addNewBlock(blockContent, partId, sequenceNumberValue, "image", id);
}

function addCode(partId, sequenceNumberValue) {
  let id = getRandomInt();
  let blockContent = "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "<textarea id='newCodeType"+id+"' onkeydown=\"if(event.keyCode===9){var v=this.value,s=this.selectionStart,"
      + "e=this.selectionEnd;this.value=v.substring(0, s)+'\\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;"
      + "return false;}\" "
      + "style=\"width: 100%; height: 300px; font-family: 'Courier New'\"> "
      + "</textarea>"
      + "       </div>";
  addNewBlock(blockContent, partId, sequenceNumberValue, "code", id);
}

function addFile(partId, sequenceNumberValue) {
  let id = getRandomInt();
  let blockContent = "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "             <p>Текст відображення:</p>"
      + "           <input id='newFileTypeText"+id+"' type='text' class=\"form-control input_info article_text\" >"
      + "             <br>"
      + "          <label for=\"file" + id
      + "\" class=\"drop-container\">\n"
      + "  <input type=\"file\" id=\"file" + id
      + "\" accept=\"*/*\" required>\n"
      + "</label>"
      + "       </div>";
  addNewBlock(blockContent, partId, sequenceNumberValue, "file", id);
}

function addLink(partId, sequenceNumberValue) {
  let id = getRandomInt();
  let blockContent = "<div class=\"row\" style=\"width: 130%;\">"
      + "      <div style=\"width: 77%; margin-left: 15px;\">\n"
      + "   <p>Текст посилання</p>"
      + "           <input id='newLinkTypeTitle"+id+"' type='text' class=\"form-control input_info article_text\">"
      + "<br>"
      + "   <p>Посилання</p>"
      + "           <input id='newLinkTypeLink"+id+"' type='text' class=\"form-control input_info article_text\">"
      + "       </div>";
  addNewBlock(blockContent, partId, sequenceNumberValue, "link", id);
}

function addNewBlock(blockContent, partId, sequenceNumberValue, type, id) {
  let allSequenceNumbers = Array.from(
      document.getElementsByName("sequenceNumber"));
  let codeAfter = "";
  let cElement;
  let size = allSequenceNumbers.length;
  sequenceNumberValue = parseInt(sequenceNumberValue);
  let isUsed = false;
  for (const element of allSequenceNumbers) {
    let elementValue = parseInt(element.value);
    let mainId = element.id.replace("sequenceNumber", "");
    if (elementValue > sequenceNumberValue || elementValue
        === sequenceNumberValue && isUsed) {
      setNewSequenceNumber(mainId, (elementValue + 1), (size + 1));
    } else if (elementValue === sequenceNumberValue) {
      isUsed = true;
      codeAfter += "<div id='articleBlock" + id + "'>\n"
          + "<input type='number' "
          + "      name='sequenceNumber'"
          + "      id='sequenceNumber" + id + "' "
          + "      value='" + (elementValue + 1) + "' hidden>\n"
          + "<input type='text' "
          + "      name='type'"
          + "      id='type" + id + "' "
          + "      value='" + type + "' hidden>\n"
          + "<input type='text' "
          + "      name='newOld'"
          + "      id='newOld" + id + "' "
          + "      value='new' hidden>";
      codeAfter += blockContent;
      codeAfter += getOptionBlock(id, (elementValue + 1), (size + 1),
          true);
      codeAfter += "</div>"
          + "</div>";
      codeAfter += getAddNewElementButtonBlock(id, (elementValue + 1));
      cElement = document.getElementById("prevNode" + partId)
      cElement.parentNode.insertBefore(
          convertStringToHTML(codeAfter).children[1],
          cElement.nextSibling);
      cElement.parentNode.insertBefore(
          convertStringToHTML(codeAfter).children[0],
          cElement.nextSibling);
      if (element.value > 0) {
        setNewSequenceNumber(mainId, elementValue, (size + 1));
      }

    } else if (elementValue < sequenceNumberValue && elementValue > 0) {
      setNewSequenceNumber(mainId, elementValue, (size + 1));
    }
  }
}