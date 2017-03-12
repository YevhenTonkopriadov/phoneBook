<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Find Some Records </title>
</head>
<body>

<table border="1">
    <tr>
        <th>Lastname</th>
        <th>Name</th>
        <th>Surtname</th>
        <th>Telefon(mobile)</th>
        <th>Telefon(home)</th>
        <th>Adress</th>
        <th>E-mail</th>
    </tr>
    <c:forEach var="record" items="${records}">
        <tr>
            <td>${record.lastname}</td>
            <td> ${record.name}</td>
            <td>${record.surname}</td>
            <td>${record.phone}</td>
            <td>${record.phoneHome}</td>
            <td> ${record.adress}</td>
            <td> ${record.email}</td>
        </tr>
    </c:forEach>
</table>
<br>
<spring:form method="POST" action="/logout">
    <button type="submit">Logout</button>
</spring:form>
<spring:form method="GET" action="/">
    <button type="submit">Main</button>
</spring:form>
</body>
</html>
