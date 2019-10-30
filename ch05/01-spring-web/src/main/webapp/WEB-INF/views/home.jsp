<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>spring-web</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />"/>
</head>
<body>
    <h1>Welcome to spring web</h1>
    <a href="<c:url value="/messages"/>"></a> |
    <a href="<c:url value="/register"/>">Register</a>
</body>
</html>
