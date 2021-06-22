$(document).ready(function() {
    
    var queryString = decodeURIComponent(window.location.search);
    
    $('#transfer').on('submit', function(e){
        
        e.preventDefault();
        var inputIndex = document.getElementById("opcao").selectedIndex;
        var inputBranch = document.getElementById("bBranch").value;
        var inputAcc = document.getElementById("bAcc").value;
        var inputAmount = document.getElementById("amount").value;
        
        var stringUrl = "http://localhost:49000/api/myBank/logged"+queryString;
        
        $.ajax({
            url: stringUrl
        }).then(function(data) {
            
            var branch;
            var acc;
            $.each(data.accounts, function (index, value) {
                if(index==inputIndex){
                    branch = value.branch;
                    acc = value.accNumber;
                }
            });       
                       
            var amount = parseInt(inputAmount);
            
            var stringUrl2 = "http://localhost:49000/api/myBank/transfer/"+branch+"/"+acc+"/"+inputBranch+"/"+inputAcc+"/"+amount;
            
            $.ajax({
                url: stringUrl2
            }).then(function(data) {
                
                alert(data);
                window.location.href = "account.html" + queryString;
                
            });
            
        });
        
    });
    
});


