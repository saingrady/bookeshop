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

<%--Processing wait page : does not include header.jsp to add HTML meta refresh --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="refresh" content="3;url=<s:url includeParams="all"/>"/>
    <link rel="stylesheet" href="css/style.css"  type="text/css" media="screen" />
    <title>Book e-Shop</title>
</head>

<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="#">Welcome to Book e-Shop</a></h1>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
			<li><a href="#">Home</a><img src="images/home.png" width="32" height="32" border="0"/></li>
			<li><a href="#">About</a><img src="images/info.png" width="32" height="32" border="0"/></li>
			<li><a href="#">Contact</a><img src="images/customer_service.png" width="32" height="32" border="0"/></li>
		</ul>
	</div>
	<!-- end #menu -->
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">

<jsp:include page="left-sidebar.jsp" flush="true"/>
        
<div id="content">
    <div align="center">
        <br/>
        <h2>${processingMessage}</h2>
        <img src="images/processing.gif" alt="processing ...">
    </div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>