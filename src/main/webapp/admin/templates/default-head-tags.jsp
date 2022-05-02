<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>${param.title}</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="/portfolio/css/admin/main.css">
<c:choose>
    <c:when test="${param.page == 'admin'}">
        <link rel="stylesheet" href="/portfolio/js/admin.js">
    </c:when>
    <c:when test="${param.page == 'users'}">
    </c:when>
</c:choose>
