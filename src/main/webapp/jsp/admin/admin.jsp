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
    <c:if test="${not empty errorLogs}">
        <div class="alert alert-danger alert-dismissible text-left" role="alert">
            <h4 class="alert-heading">Attention!</h4>
            <hr/>
            <ul>
                <c:forEach items="${errorLogs}" var="message">
                    <li>${message}</li>
                </c:forEach>
            </ul>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <c:set var="errorLogs" value="" scope="session"/>
    </c:if>
    <h4>Register new user:</h4>
    <form name="add_user" class="form-inline" action="${pageContext.request.contextPath}/controller" id="add_user" method="post" novalidate>
        <input type="hidden" name="command" value="add_user">
        <div class="form-group mb-2">
            <label for="StaticFirstname" class="sr-only">Firstname</label>
            <input type="text" pattern="^[A-ZЁА-Я]([a-z]{1,20}|[а-яё]{1,20})$" maxlength="20" name="firstname"
                   class="form-control" id="StaticFirstname"  placeholder="Firstname" required>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="staticEmail2" class="sr-only">Lastname</label>
            <label for="StaticLastname"></label>
            <input name="lastname" pattern="^[A-ZЁА-Я]([a-z]{1,20}|[а-яё]{1,20})$" maxlength="20" type="text"
                   class="form-control" id="StaticLastname"
                   placeholder="Lastname" required>
        </div>
        <div class="form-group mb-2">
            <label for="staticEmail2" class="sr-only">Email</label>
            <input name="email"
                   pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,35}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,35}[a-zA-Z0-9])?)*$"
                   maxlength="35" type="text" class="form-control" id="staticEmail2" placeholder="email@example.com"
                   required>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword2" class="sr-only">Password</label>
            <input type="password"  minlength="6" maxlength="30" name="password" class="form-control"
                   id="inputPassword2" placeholder="Password" required>
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
