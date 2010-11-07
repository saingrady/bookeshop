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
                <c:when test="${loggedCustomer == null}">
                    <h2>Client Account</h2>
                    <s:form action="login">
                        <s:textfield name="email" label="Email"/>
                        <s:password name="password" label="Password"/>
                        <s:submit value="Sign In" />
                    </s:form>
                    <p>Forgot your password? <a href="nyi">Get it here</a><br/>
                    New customer? <a href="create">Start here</a></p>
                </c:when>
                <c:otherwise>
                    <h2>Client Details</h2>
                    First name: <c:out value="${loggedCustomer.firstName}"/><br/>
                    Last name: <c:out value="${loggedCustomer.lastName}"/><br/>
                    Email: <c:out value="${loggedCustomer.email}"/><br/>
                    <p><a href="update">My account</a> | <a href="logout">Sign out</a></p>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <h2>Shopping Cart</h2>
            <ul>
                <c:choose>
                    <c:when test="${theCart == null || theCart.count == 0}">
                        <li>Your shopping cart is empty</li>
                    </c:when>
                    <c:otherwise>
                        <li>Items number = ${theCart.count}</li>
                        <a href="cart">cart details</a>
                    </c:otherwise>
                </c:choose>
            </ul>
        </li>
        <li>
            <h2>Download</h2>
            <ul>
                <li><a href="pdf-catalog">Download PDF catalog</a></li>
                <li><a href="nyi">Download eBooks</a></li>
            </ul>
        </li>        
    </ul>
</div>
<!-- end #sidebar2 -->