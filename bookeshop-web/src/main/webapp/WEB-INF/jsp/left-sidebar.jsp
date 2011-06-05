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

<div id="sidebar">
    <ul>
        <li>
            <h2>Categories</h2>
            <ul>
                <c:forEach var="category" items="${categories}">
                <li><a href="category.do?categoryId=${category.id}">${category.name}</a></li>
                </c:forEach>
            </ul>
        </li>
        <li>
            <h2>Download</h2>
            <ul>
                <li><a href="download-catalogue.do">Download PDF catalogue <img src="images/pdf_icon.png" width="32" height="32" border="0"/></a></li>
            <ul>
        </li>
    </ul>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #sidebar -->
