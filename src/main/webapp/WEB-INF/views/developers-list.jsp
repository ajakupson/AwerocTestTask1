<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: de1mos
  Date: 5.08.16
  Time: 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List Of Developers</title>

        <spring:url value="/resources/css/common.css" var="commonCss" />
        <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss" />
        <spring:url value="/resources/css/developers-list.css" var="developersListCss" />
        <spring:url value="/resources/js/plugins/require.js" var="requireJs" />

        <link href="${commonCss}" rel="stylesheet" type="text/css"/>
        <link href="${fontAwesomeCss}" rel="stylesheet" type="text/css"/>
        <link href="${developersListCss}" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <header class="page-header">
            <div class="logo-wrapper">
                <img class="header-logo" src="<c:url value="resources/img/logo.png"/>"/>
            </div>
        </header>
        <section class="page-content">
            <nav id="main-nav">
                <div class="section-heading">Main Navigation</div>
                <ul>
                    <li><a href="#"><i class="fa fa-users"></i>Developers</a></li>
                    <li><a href="#"><i class="fa fa-newspaper-o"></i>Updates</a></li>
                    <li><a href="#"><i class="fa fa-cog"></i>Options</a></li>
                    <li><a href="#"><i class="fa fa-cogs"></i>Advanced</a></li>
                </ul>
            </nav>
        </section>
        <%--<footer class="page-footer"></footer>--%>
        <%--<script data-main="${indexJs}" src="${requireJs}"></script>--%>
    </body>
</html>
