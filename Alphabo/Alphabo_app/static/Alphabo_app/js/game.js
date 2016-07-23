$(document).ready(function() {
  $('select').imagepicker();

  $('.btn-primary').click(function(){
    var number = 1 + Math.floor(Math.random() * 3);
    var select = $(".image-picker option:selected").val();
    if(select==1 && number==2){
      document.getElementById("game-result").textContent = "You Win!";
    }
    else if(select==1 && number==3){
      document.getElementById("game-result").textContent = "You Lose...";
    }
    else if(select==1 && number==1){
      document.getElementById("game-result").textContent = "Draw";
    }
    else if(select==2 && number==1){
      document.getElementById("game-result").textContent = "You Lose...";
    }
    else if(select==2 && number == 2){
      document.getElementById("game-result").textContent = "Draw";
    }   
    else if(select==2 && number == 3){
      document.getElementById("game-result").textContent = "You Win!";
    }
    else if(select==3 && number == 1){
      document.getElementById("game-result").textContent = "You Win!";
    }
    else if(select==3 && number == 2){
      document.getElementById("game-result").textContent = "You Lose...";
    }
    else if(select==3 && number == 3){
      document.getElemenyById("game-result").textContent = "Draw";
    } 
  });
});


