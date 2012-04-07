function confirm_account_removal(){
    $.Zebra_Dialog("Are you sure to remove your account?", {
        'type':     "question",
        'title':    "Confirmation",
        'modal': true,
        'onClose':  function(caption) {
            if (caption == "Yes"){
                document.getElementById("accountRemovalForm").submit();
            }
        }
    });
}

function check_empty_keyword(){
    var keyword = document.getElementById("keyword").value;
    if (keyword == ""){
        zebra_styled_dialog("error","Information","Please enter a keyword for search.",true);
        return false;
    }else
    return true;
}

function check_empty_credentials(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    if (email == "" || password == ""){
        zebra_styled_dialog("error","Information","Please enter your login credentials!",true);
        return false;
    }else
    return true;
}

function zebra_styled_dialog(type,title,message,modal){
    $.Zebra_Dialog(message, {
    'type': type,
    'title': title,
    'modal': modal
});
}