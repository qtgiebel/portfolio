<%-- Created by IntelliJ IDEA. User: Quinn Date: 2/11/2022 Time: 2:57 PM To change this template use File | Settings |
  File Templates. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Art Pieces</title>
  </head>

  <body>

    <table>
      <tr>
        <th>Title</th>
        <th>Piece</th>
        <th>Category</th>
      </tr>


      <c:forEach var="piece" items="${pieces}">
        <tr>
          <td>
            ${piece.title}
          </td>
          <td>
            <img style="max-width: 100px" src="${piece.location}" alt="${piece.title}">
          </td>
          <td>
            <c:out value="${piece.category}" />
          </td>
          <c:if test="${piece.archived}">
            <td>
              Archived
            </td>
          </c:if>
        </tr>
      </c:forEach>

    </table>
  </body>

</html>