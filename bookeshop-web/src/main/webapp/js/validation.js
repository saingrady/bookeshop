function confirm_account_removal(){
	var r = confirm("Are you sure to want to remove your account?");
	return r;
}

function check_empty_keyword(){
    keyword = document.getElementById("keyword").value;
    if (keyword == ""){
        alert("Please enter a keyword!")
        return false;
    }else
    return true;
}

function check_empty_credentials(){
    email = document.getElementById("email").value;
    password = document.getElementById("password").value;
    if (email == "" || password == ""){
        alert("Please enter login credentials!")
        return false;
    }else
    return true;
}