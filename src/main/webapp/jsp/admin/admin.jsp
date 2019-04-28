<%--
  Created by IntelliJ IDEA.
  User: Viktoria
  Date: 4/27/2019
  Time: 12:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<c:import url="../header.jsp"/>
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">

<div class="container-fluid">
    <img class="mb-4 rounded-circle" src="${pageContext.request.contextPath}/img/Free_Sample_By_Wix.jpg" alt=""
         width="140" height="140">
</div>

<div class="adduser">
    <h4>Register new user:</h4>
    <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_user">
        <div class="form-group mb-2">
            <label for="StaticFirstname" class="sr-only">Firstname</label>
            <input type="text" name="firstname" class="form-control" id="StaticFirstname" placeholder="Firstname">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="staticEmail2" class="sr-only">Lastname</label>
            <label for="StaticLastname"></label>
            <input name="lastname" type="text" class="form-control" id="StaticLastname"
                   placeholder="Lastname">
        </div>
        <div class="form-group mb-2">
            <label for="staticEmail2" class="sr-only">Email</label>
            <input name="email" type="text" class="form-control" id="staticEmail2" placeholder="email@example.com">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword2" class="sr-only">Password</label>
            <input type="password" name="password" class="form-control" id="inputPassword2" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-outline-success mb-2">Submit</button>
    </form>
</div>

<div>
    <h3 class="tableusers">All Users:</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Email</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="theCount">
            <tr>
                <th scope="row">${theCount.count}</th>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <c:if test="${sessionScope.user.id ne user.id}">
                    <td>

                        <c:if test="${user.role eq 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/controller?command=remove_admin&id=${user.id}"
                               class="btn btn-outline-warning">Remove Admin</a>
                        </c:if>
                        <c:if test="${user.role ne 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/controller?command=make_admin&id=${user.id}"
                               class="btn btn-outline-warning">Make Admin</a>
                        </c:if>
                    </td>
                    <td>
                            <a href="${pageContext.request.contextPath}/controller?command=delete_user&id=${user.id}"
                               class="btn btn-outline-danger">Delete</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr class="featurette-divider">

<!-- /END THE FEATURETTES -->


<!-- FOOTER -->
<footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>© 2019 BSUIR </p>
</footer>
</body>
</html>
