<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <spring:url value="/resources/js/includes/developersList.js" var="developersListJs" />

        <link href="${commonCss}" rel="stylesheet" type="text/css"/>
        <link href="${fontAwesomeCss}" rel="stylesheet" type="text/css"/>
        <link href="${developersListCss}" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
            <%--var developersData = ${developersData};--%>
        </script>

    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <section class="page-body">
            <jsp:include page="menu.jsp"/>
            <section class="page-content">
                <h1>Developers</h1>
                <div class="sub-section" id="developers-list">
                    <h1>Developers List</h1>
                    <div class="developers">
                        <j:forEach  var="developer" items="${developersData}">
                            <div class="developer-row">
                                <div class="photo">
                                    <img src="/aweroc/resources/img/noavatar.png">
                                </div>
                                <div class="name">
                                    <j:out value="${developer.fullName}"/>
                                </div>
                                <div class="weighted-average">
                                    <h4>Weighted Average:</h4>
                                    <span class="number">
                                        <j:out value="${developer.weightedAverageOfBurnedStoryPoints}"/>
                                    </span>
                                </div>
                                <h2 class="clear">Sprints & Tasks <i class="fa fa-plus-square-o expand-collapse" aria-hidden="true"></i></h2>
                                <div class="tasks">
                                    <j:forEach  var="sprint" items="${developer.developerSprints}">
                                        <h3>Sprint week <j:out value="${sprint.sprintWeekNumber}"/></h3>
                                        <j:forEach  var="task" items="${sprint.sprintTasks}">
                                            <div class="task">
                                                <div class="story-points"><j:out value="${task.burnedPoints}"/> story point(s)</div>
                                                <div class="task-name"><j:out value="${task.taskName}"/></div>
                                                <div class="status">Done</div><p class="clear"></p>
                                            </div>
                                        </j:forEach>
                                    </j:forEach>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </j:forEach>
                    </div>
                </div>
            </section>
        </section>
        <script data-main="${developersListJs}" src="${requireJs}"></script>
    </body>
</html>
