<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="page-header">
  <nav id="page-nav">
    <ul class="navbar navbar-expand bg-dark bg-gradient topbar mb-0">
      <c:choose>
        <c:when test="${param.active == 'admin'}">
          <li class="nav-item">
            <a class="nav-link text-light h2" href="/admin">Art</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light h4" href="/admin/users">Users</a>
          </li>
        </c:when>
        <c:when test="${param.active == 'users'}">
          <li class="nav-item">
            <a class="nav-link text-light h4" href="/admin">Art</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light h2" href="/admin/users">Users</a>
          </li>
        </c:when>
      </c:choose>
    </ul>
  </nav>
</header>