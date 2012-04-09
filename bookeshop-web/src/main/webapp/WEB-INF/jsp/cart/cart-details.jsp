<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

<jsp:include page="../header.jsp" flush="true"/>
<jsp:include page="../left-sidebar.jsp" flush="true"/>
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
                                    <th><b>Date</b></th>
                                    <th><b>Price <img src="/images/icons/euro.png" width="12" height="12" border="0"/></b></th>
                                    <th><b>Quantity</b></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${items}">
                                    <tr>
                                        <td>
                                            <a href="/book/details.do?bookId=${entry.key.isbn}&height=200&width=300" title="Book details" class="thickbox">
                                                <img src="/images/books/${entry.key.isbn}.gif" width="80" height="100" border="0"/>
                                            </a>
                                        </td>
                                        <td><c:out value="${entry.key.isbn}"/></td>
                                        <td><c:out value="${entry.key.title}"/></td>
                                        <td><c:out value="${entry.key.author.firstName}"/> <c:out value="${entry.key.author.lastName}"/></td>
                                        <td><c:out value="${entry.key.formattedPublishDate}"/></td>
                                        <td><c:out value="${entry.key.price}"/>
                                        <c:if test="${entry.key.offer != 0}">
                                                <img src="/images/icons/offer_${entry.key.offer}.png" width="32" height="32" border="0" align="absmiddle"/>
                                        </c:if>
                                        </td>
                                        <td><c:out value="${entry.value}"/><br/><br/>
                                        <a href="/cart/add.do?bookId=${entry.key.isbn}"/><img src="/images/icons/cart_add.png" width="32" height="32" border="0"/><a href="/cart/remove.do?bookId=${entry.key.isbn}"/><img src="/images/icons/cart_remove.png" width="32" height="32" border="0"/>
                                        </td>

                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table><br/>

                            <p><b>Cart Total = <c:out value="${formattedTotal}"/></b> <img src="/images/icons/euro.png" width="12" height="12" border="0"/></p>
                            <table>
                                <tr>
                                    <td>
                                        <s:form action="/cart/clear.do">
                                            <s:submit value="clear cart" cssClass="buttonStyle"/>
                                        </s:form>
                                    </td>
                                    <td>
                                        <s:form action="/cart/checkout.do">
                                            <s:submit value="checkout" cssClass="buttonStyle"/>
                                        </s:form>
                                    </td>
                                </tr>
                            </table>
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
<jsp:include page="../right-sidebar.jsp" flush="true"/>
<jsp:include page="../footer.jsp" flush="true"/>

