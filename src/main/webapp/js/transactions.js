$(document).ready(function() {

    var queryString = decodeURIComponent(window.location.search);
    var params = queryString.split('&');
    var queryEmail = params[0];
    var stringUrl = "http://localhost:49000/api/myBank/logged"+queryEmail;
    $.ajax({
        url: stringUrl
    }).then(function(data) {
       
       var targetAcc;
       
       $.each(data.accounts, function (index, value) {
           
            if(index==params[1]){
                targetAcc=value;
            }
            
       });
       
       $.each(targetAcc.transactions, function (index, value) {
           
            $('#trans').append('<tr><th scope="row">'+value.description+'</th><td>'+value.date+'</td><td>'+value.postBalance+'</td></tr>');
            
       });
              
    });
    
    $('#btnT').click(function(){
        window.location.href = "transfer.html" + queryEmail;
    });
    
    $('#btnL').click(function(){
        window.location.href = "lodgement.html" + queryEmail;
    });
    
    $('#btnW').click(function(){
        window.location.href = "withdrawal.html" + queryEmail;
    });
    
    $('#btnAdd').click(function(){
        window.location.href = "newacc.html" + queryEmail;
    });
    
    $('#btnBack').click(function(e){
        e.preventDefault;
        window.location.href = "account.html" + queryEmail;
    });

});


