<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
                <br/>
                <h1>Create my account </h1><br/>
                <h3>(All fields are required)</h3><br/>
                    <s:form action="create-account">
                        <s:actionerror/>
                        <s:textfield label="Fist name"  name="firstName"  size="20"/>
                        <s:textfield label="Last name" name="lastName" size="20"/>
                        <s:textfield label="E-mail" name="email" size="20"/>
                        <s:textfield label="Address" name="address"  size="20"/>
                        <s:password label="Password" name="password" size="20"/>
                        <s:password label="Confirm password" name="passwordConfirm" size="20"/>
                        <tr>
                            <td colspan="2" align="center">
                                <s:submit value="Register" cssClass="buttonStyle" theme="simple"/> <s:reset value="Reset" cssClass="buttonStyle" theme="simple"/>
                            </td>
                        </tr>
                        <s:div style="clear: both;">&nbsp;</s:div>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>
