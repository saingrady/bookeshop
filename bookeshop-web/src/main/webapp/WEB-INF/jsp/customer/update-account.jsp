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

<jsp:include page="../header.jsp" flush="true"/>
<jsp:include page="../left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <br/>
                <h1> My account </h1>
                    <div style="clear: both;">&nbsp;</div>
                    <s:form action="/customer/update-account.do">
                        <div align="center" style="width:20%;"><s:actionerror/></div>
                        <s:textfield label="Identifier" name="id"  size="20" disabled="true"/>
                        <s:textfield label="Fist name"  name="firstName"  size="20"/>
                        <s:textfield label="Last name" name="lastName" size="20"/>
                        <s:textfield label="E-mail" name="email" size="20"/>
                        <s:textfield label="Address" name="address"  size="20"/>
                        <s:password label="Password" name="password" size="20"/>
                        <s:password label="Confirm password" name="passwordConfirm" size="20"/>
                        <tr><td colspan="2" align="center">&nbsp;</td></tr>
                        <tr>
                            <td colspan="2" align="center">
                                <s:submit value="Update" cssClass="buttonStyle" theme="simple"/> <s:reset value="Reset" cssClass="buttonStyle" theme="simple"/>
                            </td>
                        </tr>
                    </s:form>
                    <s:form action="/customer/remove.do" id="accountRemovalForm">
                        <input type="button" onclick="javascript:confirm_account_removal()" class="buttonStyle" value="Remove my account"/>
                    </s:form>
                    <div style="clear: both;">&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;">&nbsp;</div>
</div>
<!-- end #content -->
<jsp:include page="../right-sidebar.jsp" flush="true"/>
<jsp:include page="../footer.jsp" flush="true"/>
