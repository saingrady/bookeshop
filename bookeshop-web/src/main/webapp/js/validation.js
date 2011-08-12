function confirm_account_removal(){
    $.Zebra_Dialog(message, {
        'type':     "question",
        'title':    title,
        'modal': true,
        'onClose':  function(caption) {
            if (caption == "Yes"){
               document.location.href = "remove.do";
            }
        }
    });
}

function check_empty_keyword(){
    keyword = document.getElementById("keyword").value;
    if (keyword == ""){
        zebra_styled_dialog("error","Information","Please enter a keyword for search.",true);
        return false;
    }else
    return true;
}

function check_empty_credentials(){
    email = document.getElementById("email").value;
    password = document.getElementById("password").value;
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