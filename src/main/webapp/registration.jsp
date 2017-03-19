<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>registration</title>
</head>
<body>
<spring:form method="POST" commandName="user" action="/addUser">
    <table>
        <tr>
            <td> <spring:label path="fio">Name and Surname:</spring:label></td>
            <td> <spring:input path="fio"/></td>
            <td><springForm:errors path="fio" cssClass="error"/></td>
        </tr>
        <tr>
            <td> <spring:label path="username">Login:</spring:label></td>
            <td> <spring:input path="username"/></td>
            <td><springForm:errors path="username" cssClass="error"/></td>
        </tr>
        <tr>
            <td> <spring:label path="password">Password:</spring:label></td>
            <td> <spring:password path="password"/></td>
            <td> <springForm:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="confirmPassword">Password:</spring:label></td>
            <td> <spring:password path="confirmPassword"/></td>
            <td> <springForm:errors path="confirmPassword" cssClass="error"/></td>
        </tr>
    </table>
    <spring:button type="submit">Save</spring:button>
    <spring:button type="reset">Clear</spring:button>
</spring:form>
<form method="GET" action="/login">
    <button type="submit">Start page</button>
</form>
</body>
</html>
