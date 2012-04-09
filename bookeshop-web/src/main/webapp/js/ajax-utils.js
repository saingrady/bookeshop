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
	var address = "/cart/addAjax.do?" + data;
	xhr.open("GET", address, true);
	xhr.send(null);
}

function updateCartStatus(request) {
    if ((request.readyState == 4) &&
        (request.status == 200)) {
        var jsonResponse = eval( "(" + request.responseText + ")");
        if(jsonResponse.status == "ok"){
            var cartStatus = "<li>Items number = " + jsonResponse.cartSize + "</li><a href='/cart/details.do\'>cart details</a>";
            document.getElementById("cartStatus").innerHTML = cartStatus;
            var message = "Book '" + jsonResponse.book + "' successfully added your cart.<br/>" +
                "Your cart contains " + jsonResponse.cartSize + " item(s)<br/>" +
                "<a href='/cart/details.do\'>cart details</a>";
            zebra_styled_dialog("confirmation","Confirmation",message,true);
        }else
            zebra_styled_dialog("error","Error",jsonResponse.error,true);
    }else {
        var error = "HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText;
        styledDialog("Error",error,true);
    }
}
