$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        
        e.preventDefault();
        var inputEmail = document.getElementById("materialLoginFormEmail").value;
        var inputPassword = document.getElementById("materialLoginFormPassword").value;
        var stringUrl = "http://localhost:49000/api/myBank/login/"+inputEmail+"/"+inputPassword;
        
        $.ajax({
            url: stringUrl
        }).then(function(data) {    
            
            var queryString = "?email=" + data.email;
            window.location.href = "account.html" + queryString;
         
        }).fail(function(){
       
            alert("Email or password invalid.");
         
        });

    });
});
