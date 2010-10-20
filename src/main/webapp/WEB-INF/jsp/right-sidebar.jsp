<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sidebar2">
    <ul>
        <li>
            <c:choose>
                <c:when test="${loggedCustomer == null}">
                    <h2>Client Account</h2>
                    <form id="loginForm" method="post" action="login">
                        <label for="email">Email:</label>
                        <input id="email" type="text" name="email"/>
                        <label for="password">Password:</label>
                        <input id="password" type="password" name="password"/>
                        <input type="submit" name="signin" value="Sign In" />
                        <p>Forgot your password? <a href="nyi">Get it here</a><br/>
                            New customer? <a href="create">Start here</a></p>
                    </form>
                </c:when>
                <c:otherwise>
                    <h2>Client Details</h2>
                    First name: <c:out value="${loggedCustomer.firstName}"/><br/>
                    Last name: <c:out value="${loggedCustomer.lastName}"/><br/>
                    Email: <c:out value="${loggedCustomer.email}"/><br/>
                    <p><a href="update">My account</a> | <a href="logout">Sign out</a></p>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <h2>Shopping Cart</h2>
            <ul>
                <c:choose>
                    <c:when test="${theCart == null || theCart.count == 0}">
                        <li>Your shopping cart is empty</li>
                    </c:when>
                    <c:otherwise>
                        <li>Items number = ${theCart.count}</li>
                        <a href="cart">cart details</a>
                    </c:otherwise>
                </c:choose>
            </ul>
        </li>
        <li>
            <h2>Download</h2>
            <ul>
                <li><a href="pdf-catalog">Download PDF catalog</a></li>
                <li><a href="nyi">Download eBooks</a></li>
            </ul>
        </li>        
    </ul>
</div>
<!-- end #sidebar2 -->