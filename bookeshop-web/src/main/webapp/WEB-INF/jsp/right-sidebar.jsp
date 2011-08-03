<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<div id="sidebar2">
    <ul>
        <li>
            <c:choose>
                <c:when test="${loggedCustomer == null || loggedCustomer.id == 0}">
                    <h2>Client Account</h2>
                    <s:form action="login" onsubmit="return check_empty_credentials()">
                        <s:textfield name="loginEmail" label="Email" id="email" size="13"/>
                        <s:password name="loginPassword" label="Password" id="password" size="13"/>
                        <s:submit value="Sign In" cssClass="buttonStyle"/>
                    </s:form>
                    New customer? <a href="create.do">Start here</a></p>
                </c:when>
                <c:otherwise>
                    <h2>Client details</h2>
                    Identifier : <c:out value="${loggedCustomer.id}"/><br/>
                    First name: <c:out value="${loggedCustomer.firstName}"/><br/>
                    Last name: <c:out value="${loggedCustomer.lastName}"/><br/>
                    Email: <c:out value="${loggedCustomer.email}"/><br/>
                    <p><a href="update.do">My account</a> | <a href="orders.do">My orders</a> |<a href="logout.do">Sign out</a></p>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <h2>Shopping Cart</h2>
            <ul>
                <div id="cartStatus">
                <c:choose>
                    <c:when test="${theCart == null || theCart.count == 0}">
                        <li>Your shopping cart is empty</li>
                    </c:when>
                    <c:otherwise>
                        <li>Items number = ${theCart.count}</li>
                        <a href="cartDetails.do">cart details</a>
                    </c:otherwise>
                </c:choose>
                </div>
            </ul>
        </li>
        <li>
            <h2>Quick Search</h2>
            <s:form action="lookup" onsubmit="return check_empty_keyword()">
                <s:textfield id="keyword" name="keyword" label="Title/Author" size="12"/>
                <s:submit value="Go!" cssClass="buttonStyle"/>
            </s:form>
        </li>
    </ul>
</div>
<!-- end #sidebar2 -->