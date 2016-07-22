window.onload = function(){
  firebase.auth().getRedirectResult().then(function(result) {
    if(firebase.auth().currentUser){
      window.location = "http://alphabo.tk:8000/alphabo/";
    }
    else{
    }
  });
};

$(document).ready(function() {
  $('.facebook-login-button').click(function(){
    window.location.replace("http://alphabo.tk:8000/alphabo/login/facebook");
    });
  $('.register-button').click(function(){
    window.location.replace("http://alphabo.tk:8000/alphabo/user/register");  
  });
});


