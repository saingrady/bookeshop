/*
 * The MIT License
 *
 *   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

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
        zebra_styled_dialog("error","Error","Please enter a keyword for search.",true);
        return false;
    }else
    return true;
}

function check_empty_credentials(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    if (email == "" || password == ""){
        zebra_styled_dialog("error","Error","Please enter your login credentials!",true);
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