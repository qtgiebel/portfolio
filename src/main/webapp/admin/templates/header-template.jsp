<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="page-header">
  <nav id="page-nav">
    <ul class="navbar navbar-expand bg-dark bg-gradient topbar mb-0">
      <c:choose>
        <c:when test="${param.active == 'admin'}">
          <li class="nav-item">
            <a class="nav-link text-light h2" href="/portfolio/admin">Art</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light h4" href="/portfolio/admin/users">Users</a>
          </li>
        </c:when>
        <c:when test="${param.active == 'users'}">
          <li class="nav-item">
            <a class="nav-link text-light h4" href="/portfolio/admin">Art</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light h2" href="/portfolio/admin/users">Users</a>
          </li>
        </c:when>
      </c:choose>
      <span class="navbar-text text-light">
        <c:out value="${verifiedUser.email}" /> <a href="/portfolio/signOut">Sign Out</a>
      </span>
    </ul>

  </nav>
</header>


<%--<nav class="navbar navbar-expand bg-dark bg-gradient topbar">--%>
<%--  <div class="contianer-fluid">--%>
<%--    <ul class="navbar-nav me-auto mb-2 mb-lg-0">--%>
<%--      <c:choose>--%>
<%--        <c:when test="${param.active == 'admin'}">--%>
<%--          <li class="nav-item">--%>
<%--            <a class="nav-link text-light active" href="/portfolio/admin">Art</a>--%>
<%--          </li>--%>
<%--          <li class="nav-item">--%>
<%--            <a class="nav-link text-light" href="/portfolio/admin/users">Users</a>--%>
<%--          </li>--%>
<%--        </c:when>--%>
<%--        <c:when test="${param.active == 'users'}">--%>
<%--          <li class="nav-item">--%>
<%--            <a class="nav-link text-light" href="/portfolio/admin">Art</a>--%>
<%--          </li>--%>
<%--          <li class="nav-item">--%>
<%--            <a class="nav-link text-light active" href="/portfolio/admin/users">Users</a>--%>
<%--          </li>--%>
<%--        </c:when>--%>
<%--      </c:choose>--%>
<%--    </ul>--%>
<%--  </div>--%>
<%--</nav>--%>