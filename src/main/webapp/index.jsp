<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello ${pageContext.request.userPrincipal.name} </h1>
<spring:form method="POST" commandName="record" action="/save">
    <table>
        <spring:input path="id" type="hidden"></spring:input>
        <tr>
            <td><spring:label path="name">Name:</spring:label></td>
            <td><spring:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="lastname">Lastname:</spring:label></td>
            <td><spring:input path="lastname"/></td>
            <td><springForm:errors path="lastname" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="surname">Surname:</spring:label></td>
            <td><spring:input path="surname"/></td>
            <td><springForm:errors path="surname" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="phone">Phone:</spring:label></td>
            <td><spring:input path="phone"/></td>
            <td><springForm:errors path="phone" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="phoneHome">Home phone:</spring:label></td>
            <td><spring:input path="phoneHome"/></td>
            <td><springForm:errors path="phoneHome" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:label path="adress">Adress:</spring:label></td>
            <td><spring:input path="adress"/></td>
            <td></td>
        </tr>
        <tr>
            <td><spring:label path="email">Email:</spring:label></td>
            <td><spring:input path="email"/></td>
            <td><springForm:errors path="email" cssClass="error"/></td>
        </tr>
    </table>
    <div>
        <button type="submit">Save</button>
    </div>
</spring:form>
<table border="1">
    <tr>
        <th>Lastname</th>
        <th>Name</th>
        <th>Surtname</th>
        <th>Telefon(mobile)</th>
        <th>Telefon(home)</th>
        <th>Adress</th>
        <th>E-mail</th>
        <th>Edit</th>
        <th>Delate</th>
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
            <td><a href="<c:url value="/edit/${record.id}" />">Edit</a></td>
            <td><a href="<c:url value="/remove/${record.id}" />">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<spring:form method="POST" action="/logout">
        <button type="submit">Logout</button>
</spring:form>
</body>
</html>
