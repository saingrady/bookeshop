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
                    <h1> Book details : ${item.book.title} </h1><br/>
                    <img src="images/books/${item.book.isbn}.gif" width="80" height="120" border="0" class="floatRight"/>
                    <table border="0" align="center">
                        <tr>
                            <td>ISBN</td>
                            <td><c:out value="${item.book.isbn}"/></td>
                        </tr>
                        <tr>
                            <td>Title</td>
                            <td><c:out value="${item.book.title}"/></td>
                        </tr>
                        <tr>
                            <td>Author</td>
                            <td><c:out value="${item.book.author}"/></td>
                        </tr>
                        <tr>
                            <td>Year</td>
                            <td><c:out value="${item.book.year}"/></td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td><c:out value="${item.book.price}"/> <img src="images/euro.png" width="18" height="18" border="0"/>
                              <c:if test="${item.offer != 0}">
                                  <img src="images/offer_${item.offer}.png" width="32" height="32" border="0"/> = <c:out value="${item.discountPrice}"/> <img src="images/euro.png" width="18" height="18" border="0"/>
                               </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Availability :</td>
                            <td><c:out value="${item.stockStatus}"/>
                                <c:if test="${item.stockStatus != 'Out of stock'}">
                                    <a href="addItem.do?bookId=${item.book.isbn}"><img src="images/cart_add.png" width="32" height="32" border="0"/></a>
                                </c:if>
                            </td>
                        </tr>
                    </table>
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

