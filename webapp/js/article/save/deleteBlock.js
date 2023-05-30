function deleteBlock(partId) {
  let allSequenceNumbers = Array.from(
      document.getElementsByName("sequenceNumber"));
  let size = allSequenceNumbers.length;
  let sequenceNumber = parseInt(
      document.getElementById("sequenceNumber" + partId).value);

  for (const element of allSequenceNumbers) {
    let elementValue = parseInt(element.value);
    let elementPartId = element.id.replace("sequenceNumber", "")
    if (elementValue > sequenceNumber) {
      setNewSequenceNumber(elementPartId, elementValue - 1, (size - 1));
    } else if (elementValue > 0) {
      setNewSequenceNumber(elementPartId, elementValue, (size - 1));
    }
  }
  document.getElementById("articleBlock" + partId).remove();
  document.getElementById("prevNode" + partId).remove();
}