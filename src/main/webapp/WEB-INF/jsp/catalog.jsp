<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                    <h1>Welcome ${loggedCustomer.firstName}</h1><br/>
                    <table border="1">
                        <thead>
                        <tr>
                            <th><b>Preview</b></th>
                            <th><b>Title</b></th>
                            <th><b>Author</b></th>
                            <th><b>Year</b></th>
                            <th><b>Price(<img src="images/euro.png" width="12" height="12" border="0"/>)</b></th>
                            <th><b>Cart</b></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td><a href="book?id=${book.isbn}"><img src="images/books/${book.isbn}.gif" width="80" height="120" border="0"/></a></td>
                                <td><c:out value="${book.title}"/></td>
                                <td><c:out value="${book.author}"/></td>
                                <td><c:out value="${book.year}"/></td>
                                <td><c:out value="${book.price}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${book.quantity == 0}">
                                            <img src="images/cancel.png" width="32" height="32" border="0"/>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="update-cart?id=${book.isbn}&action=add&from=catalog"><img src="images/cart_add.png" width="32" height="32" border="0"/></a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div style="clear: both;">&nbsp;</div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>
