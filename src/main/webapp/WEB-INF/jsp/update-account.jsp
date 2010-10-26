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
                <h1> Your personal data : </h1><br/>
                    <form name="updateAccountForm" action="update-account" method="POST">
                        <table>
                            <tbody>
                            <tr>
                                <td>Fist name </td>
                                <td><input type="text" name="firstName" value="${loggedCustomer.firstName}"  size="20"/></td>
                            </tr>
                            <tr>
                                <td>Last name</td>
                                <td><input type="text" name="lastName" value="${loggedCustomer.lastName}" size="20"/></td>
                            </tr>
                            <tr>
                                <td>E-mail </td>
                                <td><input type="text" name="email" value="${loggedCustomer.email}" size="20"/></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><input type="text" name="address" value="${loggedCustomer.address}" size="20"/></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type="text" name="password" value="${loggedCustomer.password}" size="20"/></td>
                            </tr>
                            </tbody>
                        </table><br/>
                        <input type="submit" value="Update" name="updateButton"/> <input type="reset" value="Reset" /> <input type=button value="Cancel" onClick="history.go(-1)">
                        <div style="clear: both;">&nbsp;</div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>
