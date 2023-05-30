function editBlock(partId) {
  let prevBlock = "prevBlock" + partId;
  let editBlock = "editBlock" + partId;
  let editButtonOption = "editButtonOption" + partId;
  let cancelButtonOption = "cancelButtonOption" + partId;
  document.getElementById(prevBlock).hidden = true;
  document.getElementById(editBlock).hidden = false;
  document.getElementById(editButtonOption).hidden = true;
  document.getElementById(cancelButtonOption).hidden = false;
}

function cancelEditBlock(partId) {
  let prevBlock = "prevBlock" + partId;
  let editBlock = "editBlock" + partId;
  let editButtonOption = "editButtonOption" + partId;
  let cancelButtonOption = "cancelButtonOption" + partId;
  document.getElementById(prevBlock).hidden = false;
  document.getElementById(editBlock).hidden = true;
  document.getElementById(editButtonOption).hidden = false;
  document.getElementById(cancelButtonOption).hidden = true;
}