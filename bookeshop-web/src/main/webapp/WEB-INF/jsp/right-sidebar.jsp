<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<div id="sidebar2">
    <ul>
        <li>
            <c:choose>
                <c:when test="${loggedCustomer == null || loggedCustomer.id == 0}">
                    <h2>Client account</h2>
                    <s:form action="/customer/login.do" onsubmit="return check_empty_credentials()">
                        <s:textfield name="loginEmail" label="Email" id="email" size="13"/>
                        <s:password name="loginPassword" label="Password" id="password" size="13"/>
                        <s:submit value="Sign In" cssClass="buttonStyle"/>
                    </s:form>
                    New customer? <a href="/customer/create.do">Start here</a></p>
                </c:when>
                <c:otherwise>
                    <h2>Client details</h2>
                    Identifier : <c:out value="${loggedCustomer.id}"/><br/>
                    First name: <c:out value="${loggedCustomer.firstName}"/><br/>
                    Last name: <c:out value="${loggedCustomer.lastName}"/><br/>
                    Email: <c:out value="${loggedCustomer.email}"/><br/>
                    <p><a href="/customer/account.do">My account</a> | <a href="/customer/orders.do">My orders</a> |<a href="/customer/logout.do">Sign out</a></p>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <h2>Shopping cart</h2>
            <ul>
                <div id="cartStatus">
                <c:choose>
                    <c:when test="${theCart == null || theCart.count == 0}">
                        <li>Your shopping cart is empty</li>
                    </c:when>
                    <c:otherwise>
                        <li>Items number = ${theCart.count}</li>
                        <a href="/cart/details.do">cart details</a>
                    </c:otherwise>
                </c:choose>
                </div>
            </ul>
        </li>
        <li>
            <h2>Quick search</h2>
            <s:form id="searchForm" action="/book/search.do" onsubmit="return check_empty_keyword()">
                <s:textfield id="keyword" name="keyword" label="Title/Author" size="12"/>
                <s:submit value="Go!" cssClass="buttonStyle"/>
            </s:form>
        </li>
    </ul>
</div>
<!-- end #sidebar2 -->