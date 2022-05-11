<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>${param.title}</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link rel="stylesheet" href="admin/css/main.css">
<link rel="stylesheet" href="admin/css/dialog.css">

<script src="admin/js/dialog.js"></script>

<c:choose>
    <c:when test="${param.page == 'admin'}">
        <script src="admin/js/admin.js"></script>
    </c:when>
    <c:when test="${param.page == 'users'}">
        <script src="admin/js/users.js"></script>
    </c:when>
</c:choose>
