<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="POST" action="/login" class="form-signin">
    <h2 class="form-heading">Log in</h2>

    <div class="form-group ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input name="username" type="text" class="form-control" placeholder="Username"
               autofocus="true"/>
        <input name="password" type="password" class="form-control" placeholder="Password"/>
        <span>${error}</span>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
    </div>

</form>
<form method="GET" action="/registration">
        <button type="submit">Registration</button>
</form>
</body>
</html>
