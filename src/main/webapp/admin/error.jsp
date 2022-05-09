<%--
  Created by IntelliJ IDEA.
  User: Quinn
  Date: 5/1/2022
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>We're sorry</h1>
    <p>Something's gone wrong.</p>
    <hr>
    <p><c:out value="${errorMsg}" /></p>
</body>
</html>
