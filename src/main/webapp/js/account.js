$(document).ready(function() {

    var queryString = decodeURIComponent(window.location.search);
    var stringUrl = "http://localhost:49000/api/myBank/logged"+queryString;
    $.ajax({
        url: stringUrl
    }).then(function(data) {
       
       $('#nome').append(data.name);

       $.each(data.accounts, function (index, value) {
            $('#accTable').append('<tr><th scope="row">'+value.name+'</th><td>'+value.accNumber+'</td><td>'+value.branch+'</td></tr>');
       });       
       
    });
});