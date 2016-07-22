$(document).ready(function() {
  $('select').imagepicker();

  $('.btn-primary').click(function(){
    var number = 1 + Math.floor(Math.random() * 3);
    var select = $(".image-picker option:selected").val();
     
    if(number > select){
      document.getElementById("game-result").textContent = "You Lose...";
    }
    else if(number < select){
      document.getElementById("game-result").textContent = "You Win!";
    }
    else{
      document.getElementById("game-result").textContent = "Draw";
    }
      
  });
});


