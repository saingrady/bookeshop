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

<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="left-sidebar.jsp" flush="true"/>
<div id="content">
    <div class="post">
        <div class="post-bgtop">
            <div class="post-bgbtm">
                <div align="center">
                <br/>
                <h1> About Book e-Shop </h1><br/>
                <table cellspacing="15px">
                    <tbody>
                    <tr>
                        <td>License</td>
                        <td><a href="http://www.gnu.org/licenses/gpl-2.0.html">GPL v2</a></td>
                    </tr>
                    <tr>
                        <td>Developed by</td>
                        <td><a href="mailto:md.benhassine@gmail.com">Mahmoud Ben Hasine</a></td>
                    </tr>
                    <tr>
                        <td>Developed with</td>
                        <td><a href="http://www.jetbrains.com/idea/"><img alt="The best Java IDE" src="images/icons/idea.png" border="0"/></a></td>
                    </tr>
                    <tr>
                        <td>Built by</td>
                        <td><a href="http://maven.apache.org/"><img alt="Built by Maven" src="images/icons/maven.png" border="0"/></a></td>
                    </tr>
                    <tr>
                        <td><a href="http://www.springsource.org/about">Spring</a></td>
                        <td>3.0.7</td>
                    </tr>
                    <tr>
                        <td><a href="http://struts.apache.org/">Struts</a></td>
                        <td>2.3.1</td>
                    </tr>
                    <tr>
                        <td><a href="http://www.hibernate.org/">Hibernate</a></td>
                        <td>3.6.8</td>
                    </tr>
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

