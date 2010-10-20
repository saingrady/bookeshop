<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                    <h1> Book details : ${book.title} </h1><br/>
                    <img src="images/books/${book.isbn}.gif" width="80" height="120" border="0" class="floatRight"/>
                    <p><h2>Isbn : <c:out value="${book.isbn}"/></h2></p>
                    <p><h2>Title : <c:out value="${book.title}"/></h2></p>
                    <p><h2>Author : <c:out value="${book.author}"/></h2></p>
                    <p><h2>Year : <c:out value="${book.year}"/></h2></p>
                    <p><h2>Price : <c:out value="${book.price}"/></h2></p>
                    <a href="update-cart?id=${book.isbn}&action=add&from=book"><img src="images/cart_add.png" width="32" height="32" border="0" class="floatRight"/></a>
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

