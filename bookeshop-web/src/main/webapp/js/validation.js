function confirm_account_removal(){
	var r = confirm("Are you sure to want to remove your account?");
	return r;
}

function check_empty_keyword(){
    keyword = document.getElementById("keyword").value;
    if (keyword == ""){
        styledDialog("Information","Please enter a keyword for search.",true);
        return false;
    }else
    return true;
}

function check_empty_credentials(){
    email = document.getElementById("email").value;
    password = document.getElementById("password").value;
    if (email == "" || password == ""){
        styledDialog("Information","Please enter your login credentials!",true);
        return false;
    }else
    return true;
}