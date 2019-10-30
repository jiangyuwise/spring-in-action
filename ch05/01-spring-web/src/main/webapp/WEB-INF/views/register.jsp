<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
</head>
<body>

    <h1>register</h1>
    <form method="post">
        <label for="name">name</label> <input type="text" name="name" id="name" /><br/>
        <label for="password">password</label> <input type="text" name="password" id="password" /><br/>
        <input type="submit" value="register"/>
    </form>
</body>
</html>
