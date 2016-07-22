$(document).ready(function() {
  $('.facebook-logout').click(function(){
    firebase.auth().signOut().then(function() {
      window.location.replace("http://alphabo.tk:8000/alphabo/login");
    }, function(error) {

    });
  });
});

function getUserInformation(){
  firebase.auth().getRedirectResult().then(function(result) {
    if(firebase.auth().currentUser){
      var facebook_user = firebase.auth().currentUser.displayName;  
      document.getElementById('facebook_user').textContent = facebook_user;
    }
    else{
      window.location.replace("http://alphabo.tk:8000/alphabo/login");
    }
  });
}


function initIndex(){
  if(document.getElementById('django_user')){

  }

  else {
    console.log("bye");
    window.location.replace("http://alphabo.tk:8000/alphabo/login");
  }
}

window.onload = function(){
  firebase.auth().getRedirectResult().then(function(result) {
    if(firebase.auth().currentUser){
      getUserInformation();
    }
    else{
      initIndex();
    }
  });
};
