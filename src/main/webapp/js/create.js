$(document).ready(function() {

    $('#main').on('submit', function(e) {
        
        e.preventDefault();
        var inputFName = document.getElementById("name").value;
        var inputLName = document.getElementById("surename").value;
        var inputAddress = document.getElementById("address").value;
        var inputEmail = document.getElementById("email").value;
        var inputPassword = document.getElementById("confirm-password").value;
        var optionBranch = document.getElementById("branch");
        var inputBranch = optionBranch.options[optionBranch.selectedIndex].value;
        
        
        var checkUrl = 'http://localhost:49000/api/myBank/check?email='+inputEmail;
        $.ajax({
            url: checkUrl
        }).then(function(data) {
            if(data=="true"){                
                alert("Email is already used in another account. Please log in or use another email");
            }
            else{
                
                var stringUrl = 'http://localhost:49000/api/myBank/newCustomer?branch='+inputBranch;
                var myJson = '{\"name\":\"'+inputFName+' '+inputLName+'\",\"address\":\"'+inputAddress+'\",\"email\":\"'+inputEmail+'\",\"password\":\"'+inputPassword+'\",\"branch\":\"'+inputBranch+'\"}';
                $.ajax({
                    type: 'POST',
                    url: stringUrl,
                    data: myJson,
                    contentType: 'application/json',
                    dataType: 'json'
                }).then(function(data) {    

                    var queryString = "?email=" + data.email;
                    window.location.href = "account.html" + queryString;
                    alert("Welcome to CORONA BANK!");

                });
            }
       
        });

    });

});