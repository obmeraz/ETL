<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Viktoria
  Date: 4/27/2019
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:if test="${not empty message}">
    <h2 style="margin-top: 100px">${message}</h2>
    <c:set var="message" value="" scope="session"/>
</c:if>
</body>
</html>
