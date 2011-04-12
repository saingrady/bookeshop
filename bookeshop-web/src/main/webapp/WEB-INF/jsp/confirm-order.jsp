<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  ~ This file is part of the Book-eShop project.
  ~
  ~  Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
  ~
  ~  This program is free software; you can redistribute it and/or modify
  ~  it under the terms of the GNU General Public License as published by
  ~  the Free Software Foundation; either version 2 of the License, or
  ~  (at your option) any later version.
  ~
  ~  This program is distributed in the hope that it will be useful,
  ~  but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~  GNU General Public License for more details.
  ~
  ~  You should have received a copy of the GNU General Public License
  ~  along with this program; if not, write to the Free Software
  ~  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
  ~
  ~ Author :
  ~ 	Mahmoud Ben Hassine <md.benhassine@gmail.com>
  --%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <h1> Your order details : </h1><br/>
                    <table border="1">
                        <thead>
                        <tr>
                            <th><b>Isbn</b></th>
                            <th><b>Title</b></th>
                            <th><b>Author</b></th>
                            <th><b>Publication date</b></th>
                            <th><b>Price(<img src="images/euro.png" width="12" height="12" border="0"/>)</b></th>
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
                    <p><b>Your order total = <c:out value="${total}"/></b>(<img src="images/euro.png" width="12" height="12" border="0"/>)</p>
                    <p><img src="images/ok.png" width="32" height="32" border="0"/>Thank you for your order on our site. <a href="javascript:window.print()"> Print details</a></p>
                    <p><img src="images/mail.png" width="32" height="25" border="0"/> A confirmation email was sent to '<b><c:out value="${loggedCustomer.email}"/></b>'</p>

                    <p><img src="images/delivery.png" width="32" height="32" border="0"/> Your items will be delivered to '<b><c:out value="${loggedCustomer.address}"/></b>'</p>
                    <p><img src="images/customer_service.png" width="32" height="32" border="0"/> For after sale service, please contact us at <a href="mailto:customer@bookeshop.com">customer@bookeshop.com</a></p>
                    <p>We hope to see you soon on our site. <a href="catalogue.do"> Back to catalogue page</a></p>
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

