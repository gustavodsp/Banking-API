$(document).ready(function() {

    var queryString = decodeURIComponent(window.location.search);
    var stringUrl = "http://localhost:49000/api/myBank/logged"+queryString;
    $.ajax({
        url: stringUrl
    }).then(function(data) {

       $.each(data.accounts, function (index, value) {
            $('#balanceTable').append('<tr><th scope="row"><i class="far fa-credit-card"></i>&ensp;'+value.name+'</th><td><i class="fas fa-euro-sign"></i>&ensp;'+value.balance+'</td></tr>');
       });       
       
    });
    
    $('#btnBack').click(function(e){
        e.preventDefault;
        window.location.href = "account.html" + queryString;
    });
    
});


