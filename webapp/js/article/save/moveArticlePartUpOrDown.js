function upArticlePart(partId, sequenceNumber, size) {
  if (sequenceNumber < 1) {
    return;
  }
  let articleBlock = document.getElementById("articleBlock" + partId);
  let addNewElementBlock = document.getElementById("prevNode" + partId);
  let prevPartId = articleBlock.previousElementSibling.id.replace("prevNode",
      "");
  setNewSequenceNumber(partId, (parseInt(sequenceNumber) - 1), parseInt(size));
  setNewSequenceNumber(prevPartId, parseInt(sequenceNumber), parseInt(size));

  let prevArticleBlock = document.getElementById("articleBlock" + prevPartId);

  document.getElementById("prevNode" + partId).remove();
  document.getElementById("articleBlock" + partId).remove();

  prevArticleBlock.parentNode.insertBefore(addNewElementBlock,
      prevArticleBlock);
  prevArticleBlock.parentNode.insertBefore(articleBlock,
      prevArticleBlock.previousSibling);
}

function downArticlePart(partId, sequenceNumber, size) {
  if (sequenceNumber > size - 1) {
    return;
  }
  let articleBlock = document.getElementById("articleBlock" + partId);
  let addNewElementBlock = document.getElementById("prevNode" + partId);
  let nextPartId = addNewElementBlock.nextElementSibling.id.replace(
      "articleBlock",
      "");
  setNewSequenceNumber(partId, (parseInt(sequenceNumber) + 1), parseInt(size));
  setNewSequenceNumber(nextPartId, parseInt(sequenceNumber), parseInt(size));

  let nextPrevNode = document.getElementById("prevNode" + nextPartId);

  document.getElementById("prevNode" + partId).remove();
  document.getElementById("articleBlock" + partId).remove();
  nextPrevNode.parentNode.insertBefore(addNewElementBlock,
      nextPrevNode.nextSibling);
  nextPrevNode.parentNode.insertBefore(articleBlock,
      nextPrevNode.nextSibling);
}
