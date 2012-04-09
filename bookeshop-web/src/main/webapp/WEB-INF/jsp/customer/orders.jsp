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
                    <c:choose>
                    <c:when test="${not empty orders}">
                    <h1>My orders</h1><br/>
                    <display:table name="orders" uid="order" sort="list" defaultorder="descending" requestURI="/customer/orders.do" class="dttable">
                        <display:column title="Order ID" >
                            <a href="/customer/order-details.do?orderId=${order.orderId}">${order.orderId}</a>
                        </display:column>
                        <display:column property="formattedDate" title="Date" sortable="true"/>
                        <display:column title="Status" sortable="true">
                            <c:out value="${order.status.label}"/>
                        </display:column>
                    </display:table>
                    </c:when>
                    <c:otherwise>
                       <h1>You have no order yet on Book eShop</h1>
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
