<%-- Created by IntelliJ IDEA. User: Quinn Date: 2/11/2022 Time: 2:57 PM To change this template use File | Settings |
  File Templates. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
      <c:forEach var="piece" items="pieces">
        <tr>
          <td>
            <c:out value="${piece.altText}" />
          </td>
          <td>
            <img src="${fn:escapeXml(piece.location)}" alt="${fn:escapeXml(piece.altText)}">
          </td>
          <td>
            <c:out value="${piece.category}" />
          </td>
        </tr>
      </c:forEach>

    </table>
  </body>

</html>