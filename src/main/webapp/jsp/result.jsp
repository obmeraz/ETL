<%--
  Created by IntelliJ IDEA.
  User: Viktoria
  Date: 4/27/2019
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<p><a class="btn btn-secondary" href="${pageContext.request.contextPath}/jsp/main.jsp" role="button"> Назад </a></p>
<c:forEach items="${logs}" var="item">
    ${item}<br>
</c:forEach>
</body>
</html>
