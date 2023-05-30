function setNewSequenceNumber(partId, sequenceNumber, size) {
  document.getElementById("sequenceNumber" + partId).setAttribute('value',
      sequenceNumber);

  let upButton = document.getElementById("upButton" + partId);
  upButton.setAttribute('onclick',
      'upArticlePart(' + partId + ', ' + sequenceNumber + ', ' + size + ')');
  if (sequenceNumber === 1) {
    upButton.className = 'upDisableButton'
    upButton.disabled = true;
  } else {
    upButton.className = 'upActiveButton'
    upButton.disabled = false;
  }

  let downButton = document.getElementById("downButton" + partId);
  downButton.setAttribute('onclick',
      'downArticlePart(' + partId + ', ' + sequenceNumber + ', ' + size + ')');
  if (sequenceNumber === size - 1) {
    downButton.className = 'downDisableButton'
    downButton.disabled = true;
  } else {
    downButton.className = 'downActiveButton'
    downButton.disabled = false;
  }

  document.getElementById("addText" + partId).setAttribute('onclick',
      'addText(' + partId + ',' + sequenceNumber + ')');
  document.getElementById("addImage" + partId).setAttribute('onclick',
      'addImage(' + partId + ',' + sequenceNumber + ')');
  document.getElementById("addCode" + partId).setAttribute('onclick',
      'addCode(' + partId + ',' + sequenceNumber + ')');
  document.getElementById("addFile" + partId).setAttribute('onclick',
      'addFile(' + partId + ',' + sequenceNumber + ')');
  document.getElementById("addLink" + partId).setAttribute('onclick',
      'addLink(' + partId + ',' + sequenceNumber + ')');
}