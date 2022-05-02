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
    <c:out value="${errorMsg}" />
    <c:out value="${stackTrace}" />
</body>
</html>
