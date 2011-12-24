<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  ~ This file is part of the Book-eShop project.
  ~
  ~    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
  ~
  ~    This program is free software; you can redistribute it and/or modify
  ~    it under the terms of the GNU General Public License as published by
  ~    the Free Software Foundation; either version 2 of the License, or
  ~    (at your option) any later version.
  ~
  ~    This program is distributed in the hope that it will be useful,
  ~    but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~    GNU General Public License for more details.
  ~
  ~    You should have received a copy of the GNU General Public License
  ~    along with this program; if not, write to the Free Software
  ~    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
  ~
  ~    Author :
  ~   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
  --%>

<div id="sidebar2">
    <ul>
        <li>
            <c:choose>
                <c:when test="${loggedCustomer == null || loggedCustomer.id == 0}">
                    <h2><s:text name="web.sidebar.right.client.login.title"/></h2>
                    <s:form action="login" onsubmit="return check_empty_credentials()">
                        <s:textfield key="web.sidebar.right.client.login.email" name="loginEmail" id="email" size="13"/>
                        <s:password key="web.sidebar.right.client.login.password" name="loginPassword" id="password" size="13"/>
                        <s:submit key="web.sidebar.right.client.login.signin" cssClass="buttonStyle"/>
                    </s:form>
                    <s:text name="web.sidebar.right.client.new"/> <a href="create.do"><s:text name="web.sidebar.right.client.start"/></a></p>
                </c:when>
                <c:otherwise>
                    <h2><s:text name="web.sidebar.right.client.account"/></h2>
                    <s:text name="web.sidebar.right.client.details.id"/>: <c:out value="${loggedCustomer.id}"/><br/>
                    <s:text name="web.sidebar.right.client.details.firstName"/>: <c:out value="${loggedCustomer.firstName}"/><br/>
                    <s:text name="web.sidebar.right.client.details.lastName"/>: <c:out value="${loggedCustomer.lastName}"/><br/>
                    <s:text name="web.sidebar.right.client.details.email"/>: <c:out value="${loggedCustomer.email}"/><br/>
                    <p><a href="update.do"><s:text name="web.sidebar.right.client.account"/></a> | <a href="orders.do"><s:text name="web.sidebar.right.client.orders"/></a> | <a href="logout.do"><s:text name="web.sidebar.right.client.login.signout"/></a></p>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <h2><s:text name="web.sidebar.right.cart.title"/></h2>
            <ul>
                <c:choose>
                    <c:when test="${theCart == null || theCart.count == 0}">
                        <li><s:text name="web.sidebar.right.cart.empty"/></li>
                    </c:when>
                    <c:otherwise>
                        <li><s:text name="web.sidebar.right.cart.items"/> ${theCart.count}</li>
                        <a href="cartDetails.do"><s:text name="web.sidebar.right.cart.details"/></a>
                    </c:otherwise>
                </c:choose>
            </ul>
        </li>
        <li>
            <h2><s:text name="web.sidebar.right.search.title"/></h2>
            <s:form action="lookup" onsubmit="return check_empty_keyword()">
                <s:textfield id="keyword" name="keyword" key="web.sidebar.right.search.label" size="12"/>
                <s:submit key="web.sidebar.right.search.submit" cssClass="buttonStyle"/>
            </s:form>
        </li>
    </ul>
</div>
<!-- end #sidebar2 -->