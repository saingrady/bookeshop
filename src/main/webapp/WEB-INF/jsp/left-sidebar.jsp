<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sidebar">
    <ul>
        <li>
            <h2>Categories</h2>
            <ul>
                <c:forEach var="category" items="${categories}">
                <li><a href="category?categoryId=${category.id}">${category.name}</a></li>
                </c:forEach>
                <li><a href="catalog">All</a></li>
            </ul>
        </li>
        <li>
            <h2>Quick Search</h2>
            <form id="lookup" method="post" action="lookup">
                <label for="keyword">Book Title/Author:</label>
                <input id="keyword" type="text" name="keyword"/>
                <input id="go" type="submit" value="Go!" />
            </form>
        </li>
    </ul>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #sidebar -->
