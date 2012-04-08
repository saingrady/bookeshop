<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  ~ The MIT License
  ~
  ~   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
  ~
  ~   Permission is hereby granted, free of charge, to any person obtaining a copy
  ~   of this software and associated documentation files (the "Software"), to deal
  ~   in the Software without restriction, including without limitation the rights
  ~   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  ~   copies of the Software, and to permit persons to whom the Software is
  ~   furnished to do so, subject to the following conditions:
  ~
  ~   The above copyright notice and this permission notice shall be included in
  ~   all copies or substantial portions of the Software.
  ~
  ~   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  ~   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  ~   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  ~   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  ~   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  ~   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  ~   THE SOFTWARE.
  --%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <br/>
                <h1> My order N° <c:out value="${order.orderId}"/>  details </h1><br/>
                    <table class="styledtable">
                        <thead>
                        <tr>
                            <th><b>ISBN</b></th>
                            <th><b>Title</b></th>
                            <th><b>Author</b></th>
                            <th><b>Date</b></th>
                            <th><b>Price <img src="images/icons/euro.png" width="12" height="12" border="0"/></b></th>
                            <th><b>Quantity</b></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${items}">
                            <tr>
                                <td><c:out value="${entry.key.isbn}"/></td>
                                <td><c:out value="${entry.key.title}"/></td>
                                <td><c:out value="${entry.key.author.firstName}"/> <c:out value="${entry.key.author.lastName}"/></td>
                                <td><c:out value="${entry.key.formattedPublishDate}"/></td>
                                <td><c:out value="${entry.key.discountPrice}"/></td>
                                <td><c:out value="${entry.value}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p><b>Order total = <c:out value="${order.formattedTotal}"/></b><img src="images/icons/euro.png" width="12" height="12" border="0"/></p>
                    <p><a href="javascript:window.print()"> Print details</a></p>

                    <p><img src="images/icons/ok.png" width="32" height="32" border="0" align="absmiddle"/> Thank you for your order on our site. A confirmation email was sent to '<b><c:out value="${loggedCustomer.email}"/></b>'</p>
                    <p><img src="images/icons/delivery.png" width="32" height="32" border="0" align="absmiddle"/> Shipping address : '<b><c:out value="${loggedCustomer.address}"/></b>'</p>
                    <p><a href="catalogue.do"> Back to catalogue</a></p>
                    <div style="clear: both;">&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>            

