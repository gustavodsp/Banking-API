$(document).ready(function() {

    var queryString = decodeURIComponent(window.location.search);
    var stringUrl = "http://localhost:49000/api/myBank/logged"+queryString;
    $.ajax({
        url: stringUrl
    }).then(function(data) {
       
       var listc= [];
       $.each(data.accounts, function (index, value) {
            listc.push(value);
            $('.account').append($('<option>', {
                value: index,
                text: listc[index].name
            }));
       });       
       $('.acc').append(listc[0].name).css("display","inline-block");
       $('.amount').append(listc[0].balance).css({"display": "inline-block",
                                                    "float": "right"});
              
    });
    
    $('#btnT').click(function(){
        window.location.href = "transfer.html" + queryString;
    });
    
    $('#btnL').click(function(){
        window.location.href = "lodgement.html" + queryString;
    });
    
    $('#btnW').click(function(){
        window.location.href = "withdrawal.html" + queryString;
    });
    
    $('#btnAdd').click(function(){
        window.location.href = "newacc.html" + queryString;
    });
    
    $('#balanceButton').click(function(e){
        e.preventDefault;
        window.location.href = "balance.html" + queryString;
    });
    
    $('#transButton').click(function(e){
        e.preventDefault;
        window.location.href = "transactions.html" + queryString + "&" + document.getElementById("transAcc").selectedIndex;
    });
    
});

