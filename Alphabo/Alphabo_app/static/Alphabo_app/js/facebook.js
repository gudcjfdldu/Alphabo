    
    /**
     * Function called when clicking the Login/Logout button.
     */
    // [START buttoncallback]
  function toggleSignIn() {
    if (!firebase.auth().currentUser) {
      // [START createprovider]
      var provider = new firebase.auth.FacebookAuthProvider();
      // [END createprovider]
      // [START addscopes]
      provider.addScope('user_likes');
      // [END addscopes]
      // [START signin]
      firebase.auth().signInWithRedirect(provider);
      // [END signin]
    } else {
      // [START signout]
      firebase.auth().signOut();
      // [END signout]
    }
    // [START_EXCLUDE]
    document.getElementById('quickstart-sign-in').disabled = true;
    // [END_EXCLUDE]
  }
 // [END buttoncallback]

    /**
     * initApp handles setting up the Firebase context and registering
     * callbacks for the auth status.
     *
     * The core initialization is in firebase.App - this is the glue class
     * which stores configuration. We provide an app name here to allow
     * distinguishing multiple app instances.
     *
     * This method also registers a listener with firebase.auth().onAuthStateChanged.
     * This listener is called when the user is signed in or out, and that
     * is where we update the UI.
     *
     * When signed in, we also authenticate to the Firebase Realtime Database.
     */
  function initApp() {
      // Result from Redirect auth flow.
      // [START getidptoken]
    firebase.auth().getRedirectResult().then(function(result) {
      if (result.credential) {
          // This gives you a Facebook Access Token. You can use it to access the Facebook API.
        var token = result.credential.accessToken;
          // [START_EXCLUDE]
        document.getElementById('quickstart-oauthtoken').textContent = token;
      } else {
        document.getElementById('quickstart-oauthtoken').textContent = 'null';
          // [END_EXCLUDE]
      }
        // The signed-in user info.
      var user = result.user;
      
    }).catch(function(error) {
        // Handle Errors here.
      var errorCode = error.code;
      var errorMessage = error.message;
        // The email of the user's account used.
      var email = error.email;
        // The firebase.auth.AuthCredential type that was used.
      var credential = error.credential;
        // [START_EXCLUDE]
      if (errorCode === 'auth/account-exists-with-different-credential') {
        alert('You have already signed up with a different auth provider for that email.');
          // If you are using multiple auth providers on your app you should handle linking
          // the user's accounts here.
      } else {
        console.error(error);
      }
        // [END_EXCLUDE]
    });
      // [END getidptoken]

      // Listening for auth state changes.
      // [START authstatelistener]
    firebase.auth().onAuthStateChanged(function(facebook_user) {
      if (facebook_user) {
          // User is signed in.
        var displayName = facebook_user.displayName;
        var email = facebook_user.email;
        var emailVerified = facebook_user.emailVerified;
        var photoURL = facebook_user.photoURL;
        var isAnonymous = facebook_user.isAnonymous;
        var uid = facebook_user.uid;
        var refreshToken = facebook_user.refreshToken;
        var providerData = facebook_user.providerData; 


          // [START_EXCLUDE]
        document.getElementById('quickstart-sign-in-status').textContent = 'Signed in';
        document.getElementById('quickstart-sign-in').textContent = 'Log out';
        document.getElementById('quickstart-account-details').textContent = JSON.stringify({
          displayName: displayName,
          email: email,
          emailVerified: emailVerified,
          photoURL: photoURL,
          isAnonymous: isAnonymous,
          uid: uid,
          refreshToken: refreshToken,
          providerData: providerData
        }, null, '  ');
        //location.href = "http://alphabo.tk:8000/alphabo/";
       // [END_EXCLUDE]
      } else {
        // User is signed out.
        // [START_EXCLUDE]
        document.getElementById('quickstart-sign-in-status').textContent = 'Signed out';
        document.getElementById('quickstart-sign-in').textContent = 'Log in with Facebook';
        document.getElementById('quickstart-account-details').textContent = 'null';
        document.getElementById('quickstart-oauthtoken').textContent = 'null';
        // [END_EXCLUDE]
      }
      // [START_EXCLUDE]
      document.getElementById('quickstart-sign-in').disabled = false;
      // [END_EXCLUDE]
    });
    // [END authstatelistener]
     document.getElementById('quickstart-sign-in').addEventListener('click', toggleSignIn, false);
  }
   window.onload = function() {
    initApp();
  };

$(document).ready(function() {
  var csrftoken = $.cookie('csrftoken');
  
  $.ajaxSetup({
    beforeSend: function(xhr){
      xhr.setRequestHeader("X-CSRFToken", csrftoken)
    }
  });

  $.ajax({
    type: "POST",
    url: "/user/register/",
    processData: false,
    contentType: false,
    data: 
  });
});



