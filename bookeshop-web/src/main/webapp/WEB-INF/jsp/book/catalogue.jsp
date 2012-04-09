<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
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
                    <h1>Discount books!</h1><br/>
                    <display:table name="books" uid="book" sort="list" defaultorder="descending" requestURI="/book/catalogue.do" class="dttable">
                        <display:column title="Preview" >
                            <a href="/book/details.do?bookId=${book.isbn}&height=200&width=300" title="Book details" class="thickbox">
                                <img src="/images/books/${book.isbn}.gif" width="80" height="100" border="0"/>
                            </a>
                        </display:column>
                        <display:column property="title" title="Title" sortable="true"/>
                        <display:column title="Author" sortable="true">
                            <c:out value="${book.author.firstName}"/> <c:out value="${book.author.lastName}"/>
                        </display:column>
                        <display:column property="formattedPublishDate" title="Date" sortable="true"/>
                        <display:column title='Price <img src="/images/icons/euro.png" width="12" height="12" border="0"/>' sortable="true">
                            <c:out value="${book.price}"/>
                            <c:if test="${book.offer != 0}">
                                <img src="/images/icons/offer_${book.offer}.png" width="32" height="32" border="0" align="absmiddle"/>
                            </c:if>
                        </display:column>
                        <display:column property="stockStatus" title="Stock"/>
                        <display:column title="Cart" >
                            <c:choose>
                                <c:when test="${book.stockStatus == 'Out of stock'}">
                                    <img src="/images/icons/cancel.png" width="32" height="32" border="0"/>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:addItemToCart(${book.isbn})"><img src="/images/icons/cart_add.png" width="32" height="32" border="0"/></a>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                    </display:table>
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
