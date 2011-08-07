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

function getRequestObject() {
	if (window.XMLHttpRequest) {
		return(new XMLHttpRequest());
	} else if (window.ActiveXObject) {
		return(new ActiveXObject("Microsoft.XMLHTTP"));
	} else {
		return(null);
	}
}

function addItemToCart(bookId) {
	var xhr = getRequestObject();
	xhr.onreadystatechange = function() { updateCartStatus(xhr); };
	var data = "bookId=" + bookId;
	var address = "addItemWithAjax.do?" + data;
	xhr.open("GET", address, true);
	xhr.send(null);
}

function updateCartStatus(request) {
    if ((request.readyState == 4) &&
        (request.status == 200)) {
        var jsonResponse = eval( "(" + request.responseText + ")");
        if(jsonResponse.status == "ok"){
            var cartStatus = "<li>Items number = " + jsonResponse.cartSize + "</li><a href='cartDetails.do\'>cart details</a>";
            document.getElementById("cartStatus").innerHTML = cartStatus;
            var message = "Book '" + jsonResponse.book + "' successfully added your cart.";
            styledDialog("Confirmation",message,false);
        }else
            styledDialog("Error",jsonResponse.error,false);
    }else {
        var error = "HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText;
        styledDialog("Error",error,true);
    }
}

/*
 * JQueryUI
 */
function styledDialog(title,message,modal){
	var $dialog = $('<div></div>')
	.html(message)
	.dialog({
		autoOpen: false,
		title: title,
        modal : modal,
		buttons: {
				OK: function() {
                    $( this ).dialog( "close" );
                    return true;
				}
	}});

	$dialog.dialog('open');
}
