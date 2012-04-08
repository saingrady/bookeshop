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

<%--Processing wait page : does not include header.jsp to add HTML meta refresh --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
            <h1><a href="index.do">Book e-Shop <img src="images/icons/library.png" width="64" height="64" border="0" align="absmiddle"/></a></h1>
            <h3>
                Customer service &nbsp; <img src="images/icons/customer_service.png" width="32" height="32" border="0" align="absmiddle"/><br/>
                <img src="images/icons/phone.png" width="24" height="24" border="0" align="absmiddle"/> +33 1 23 45 67 89 <br/>
                <img src="images/icons/email_icon.png" width="24" height="24" border="0" align="absmiddle"/> <a href="mailto:customer@bookeshop.com">customer@bookeshop.com</a>
            </h3>
        </div>
    </div>
    <!-- end #header -->
    <div id="menu">
        <ul>
            <li><a href="index.do">Home</a><img src="images/icons/home.png" width="32" height="32" border="0"/></li>
            <li><a href="about.do">About</a><img src="images/icons/info.png" width="32" height="32" border="0"/></li>
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
        <img src="images/icons/processing.gif" alt="processing ...">
    </div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>