<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
                    <c:choose>
                        <c:when test="${not empty items}">
                            <h1> My cart details </h1><br/>
                            <table class="styledtable">
                                <thead>
                                <tr>
                                    <th><b>Preview</b></th>
                                    <th><b>Isbn</b></th>
                                    <th><b>Title</b></th>
                                    <th><b>Author</b></th>
                                    <th><b>Publication date</b></th>
                                    <th><b>Price (euro)</b></th>
                                    <th><b>Quantity</b></th>
                                    <th><b>update</b></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${items}">
                                    <tr>
                                        <td>
                                            <a href="bookDetails.do?bookId=${entry.key.isbn}">
                                                <img src="images/books/${entry.key.isbn}.gif" width="80" height="120" border="0"/>
                                            </a>
                                        </td>
                                        <td><c:out value="${entry.key.isbn}"/></td>
                                        <td><c:out value="${entry.key.title}"/></td>
                                        <td><c:out value="${entry.key.author.firstName}"/> <c:out value="${entry.key.author.lastName}"/></td>
                                        <td><c:out value="${entry.key.formattedPublishDate}"/></td>
                                        <td><c:out value="${entry.key.price}"/>
                                        <c:if test="${entry.key.offer != 0}">
                                                <img src="images/icons/offer_${entry.key.offer}.png" width="32" height="32" border="0"/> = <c:out value="${entry.key.discountPrice}"/>
                                        </c:if>
                                        </td>
                                        <td><c:out value="${entry.value}"/></td>
                                        <td><a href="addItem.do?bookId=${entry.key.isbn}"/><img src="images/icons/cart_add.png" width="32" height="32" border="0"/><a href="removeItem.do?bookId=${entry.key.isbn}"/><img src="images/icons/cart_remove.png" width="32" height="32" border="0"/></td>

                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table><br/>

                            <p><b>Cart Total = <c:out value="${formattedTotal}"/></b> <img src="images/icons/euro.png" width="12" height="12" border="0"/></p>
                            <b>Clear cart </b><a href="clearCart.do"><img src="images/icons/cart.png" width="32" height="32" border="0"/></a>

                            <s:form action="checkout">
                                <s:submit value="proceed to checkout"/>
                            </s:form>
                        </c:when>
                        <c:otherwise>
                            <h1>Your shopping cart is empty</h1>
                        </c:otherwise>
                    </c:choose>
                    <div style="clear: both;">&nbsp;</div>
                </div>
                <div style="clear: both;">&nbsp;</div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>               

