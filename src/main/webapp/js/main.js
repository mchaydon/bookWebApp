$(function() {
    var baseUrl = "AuthorController";
    var authorSelected = 0;
    
    $("input:radio[name=authorSelected]").click(function (){
        authorSelected = $(this).val();  
        alert(authorSelected);
    });
    
    $("#authorDelete").click(function () {
        $.ajax({
            type: 'POST',
            url: baseUrl + "?submit=delete&authorSelected=" + authorSelected
        })
                .done(function () {
                    $("#" + authorSelected).remove();
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                });
    });
});