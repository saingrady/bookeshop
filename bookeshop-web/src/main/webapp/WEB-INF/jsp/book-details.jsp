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
                    <h1> Book details : ${book.title} </h1><br/>
                    <table border="1" align="center">
                        <tr>
                            <td>Preview</td>
                            <td align="center"><img src="images/books/${book.isbn}.gif" width="80" height="120" border="0"/></td>
                        </tr>
                        <tr>
                            <td>ISBN</td>
                            <td align="center"><c:out value="${book.isbn}"/></td>
                        </tr>
                        <tr>
                            <td>Title</td>
                            <td align="center"><c:out value="${book.title}"/></td>
                        </tr>
                        <tr>
                            <td>Author</td>
                            <td align="center"><c:out value="${book.author.firstName}"/> <c:out value="${book.author.lastName}"/></td>
                        </tr>
                        <tr>
                            <td>Publication date</td>
                            <td align="center"><c:out value="${book.formattedPublishDate}"/></td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td align="center"><c:out value="${book.price}"/> <img src="images/euro.png" width="12" height="12" border="0"/>
                              <c:if test="${book.offer != 0}">
                                  <img src="images/offer_${book.offer}.png" width="32" height="32" border="0"/> = <c:out value="${book.discountPrice}"/> <img src="images/euro.png" width="18" height="18" border="0"/>
                               </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Availability :</td>
                            <td align="center"><c:out value="${book.stockStatus}"/>
                                <c:if test="${book.stockStatus != 'Out of stock'}">
                                    <a href="addItem.do?bookId=${book.isbn}"><img src="images/cart_add.png" width="32" height="32" border="0"/></a>
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

