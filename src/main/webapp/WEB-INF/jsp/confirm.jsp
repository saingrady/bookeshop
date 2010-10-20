<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <h1> Your order details : </h1><br/>
                    <table border="1">
                        <thead>
                        <tr>
                            <th><b>Isbn</b></th>
                            <th><b>Title</b></th>
                            <th><b>Author</b></th>
                            <th><b>Year</b></th>
                            <th><b>Price(<img src="images/euro.png" width="12" height="12" border="0"/>)</b></th>
                            <th><b>Quantity</b></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${items}">
                            <tr>
                                <td><c:out value="${entry.key.isbn}"/></td>
                                <td><c:out value="${entry.key.title}"/></td>
                                <td><c:out value="${entry.key.author}"/></td>
                                <td><c:out value="${entry.key.year}"/></td>
                                <td><c:out value="${entry.key.price}"/></td>
                                <td><c:out value="${entry.value}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p><b>Your order total = <c:out value="${total}"/></b>(<img src="images/euro.png" width="12" height="12" border="0"/>)</p>
                    <p><img src="images/ok.png" width="32" height="32" border="0"/>Thank you for your order on our site. <a href="javascript:window.print()"> Print details</a></p>
                    <p><img src="images/mail.png" width="32" height="25" border="0"/> A confirmation email was sent to '<b><c:out value="${loggedCustomer.email}"/></b>'</p>

                    <c:if test="${gift != null}">
                    <p><img src="images/present.png" width="32" height="32" border="0"/> Your items will be packaged as a gift.</p>
                    </c:if>

                    <p><img src="images/delivery.png" width="32" height="32" border="0"/> Your items will be delivered to '<b><c:out value="${loggedCustomer.address}"/></b>'</p>
                    <p><img src="images/customer_service.png" width="32" height="32" border="0"/> For after sale service, please contact us at <a href="mailto:customer@bookeshop.com">customer@bookeshop.com</a></p>
                    <p>We hope to see you soon on our site. <a href="catalog"> Back to catalog page</a></p>
                    <p>We will be grateful if you respond to our one minute survey and help us improve our site. <a href="nyi"> Go to survey</a></p>
                    <div style="clear: both;">&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>            

