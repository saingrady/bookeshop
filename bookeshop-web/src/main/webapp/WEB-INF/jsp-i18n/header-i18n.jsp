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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/style.css"  type="text/css" media="screen" />
    <script type="text/javascript" src="js/validation.js"></script>
    <title>Book e-Shop</title>
</head>

<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="index.do"><s:text name="web.header.welcome"/></a></h1>
            <h4 align="right">
			    <a href="locale.do?request_locale=en"> <img src="images/icons/uk_flag.png" width="24" height="24" border="0"/> English </a>
                <a href="locale.do?request_locale=fr"> <img src="images/icons/fr_flag.png" width="24" height="24" border="0"/> Fran&ccedil;ais </a>
                <p>Currency : <img src="images/icons/euro.png" width="12" height="12" border="0"/> <img src="images/icons/pound.png" width="12" height="12" border="0"/></p>
                <p>Language : <a href="locale.do?request_locale=en"> <img src="images/icons/uk_flag.png" width="24" height="24" border="0" align="absmiddle"/></a> <a href="locale.do?request_locale=fr"> <img src="images/icons/fr_flag.png" width="24" height="24" border="0" align="absmiddle"/></a></p>
            </h4>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
			<li><a href="index.do"><s:text name="web.header.home"/></a><img src="images/icons/home.png" width="32" height="32" border="0"/></li>
			<li><a href="contact.do"><s:text name="web.header.contact"/></a><img src="images/icons/customer_service.png" width="32" height="32" border="0"/></li>
			<li><a href="about.do"><s:text name="web.header.about"/></a><img src="images/icons/info.png" width="32" height="32" border="0"/></li>
		</ul>
	</div>
	<!-- end #menu -->
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">