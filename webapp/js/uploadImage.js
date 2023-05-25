var loadFile = function(event, outputId) {
  var reader = new FileReader();
  reader.onload = function(){
    var output = document.getElementById(outputId);
    output.src = reader.result;
  };
  reader.readAsDataURL(event.target.files[0]);
};