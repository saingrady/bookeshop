/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
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