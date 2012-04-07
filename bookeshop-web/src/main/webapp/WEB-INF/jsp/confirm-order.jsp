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

