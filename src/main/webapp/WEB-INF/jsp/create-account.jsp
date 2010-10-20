<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <h1>Fill in the form with your personal data </h1><br/>
                    <form name="createAccountForm" action="create-account" method="POST">
                        <table>
                            <tbody>
                            <tr>
                                <td>Fist name </td>
                                <td><input type="text" name="firstName" value=""  size="20"/></td>
                            </tr>
                            <tr>
                                <td>Last name</td>
                                <td><input type="text" name="lastName" value="" size="20"/></td>
                            </tr>
                            <tr>
                                <td>E-mail </td>
                                <td><input type="text" name="email" value="" size="20"/></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><input type="text" name="address" value="" size="20"/></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type="text" name="password" value="" size="20"/></td>
                            </tr>
                            </tbody>
                        </table><br/>
                        <input type="submit" value="Create" name="createButton"/> <input type="reset" value="Reset" />
                        <div style="clear: both;">&nbsp;</div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="right-sidebar.jsp" flush="true"/>
<jsp:include page="footer.jsp" flush="true"/>
