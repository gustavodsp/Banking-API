$(document).ready(function() {
    
    var queryString = decodeURIComponent(window.location.search);
    
    $('#newacc').on('submit', function(e){
        
        e.preventDefault();
        var optionBranch = document.getElementById("branch");
        var inputBranch = optionBranch.options[optionBranch.selectedIndex].value;
        var optionType = document.getElementById("type");
        var inputType = optionType.options[optionType.selectedIndex].value;
        
        var stringUrl = "http://localhost:49000/api/myBank/newAccount/"+inputBranch+"/"+inputType+queryString;
        
        $.ajax({
            url: stringUrl
        }).then(function(data) {
                                   
           alert(data);
           window.location.href = "account.html" + queryString;
            
        });
        
    });
    
});


