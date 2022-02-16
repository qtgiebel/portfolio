<%-- Created by IntelliJ IDEA. User: Quinn Date: 2/10/2022 --%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html>

    <head>
      <title>Title</title>
    </head>

    <body>
      <p>Did the thing.</p>
      <p><a href="/portfolio/admin/list">List all art pieces.</a> </p>

      <hr>

      <form action="/portfolio/admin/add-piece" method="get">
        <h2>Add a Piece</h2>

        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required>

        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required>

        <button type="submit">Confirm</button>
      </form>

      <hr>

      <form action="/admin/archive-piece" method="get">
        <h2>Archive a Piece</h2>

        <label for="titleArch">Piece</label>
        <select name="titleArch" id="titleArch" required>
          <c:forEach var="piece" items="${pieces}">
            <c:if test="${piece.archived ne true}">
              <option value="${piece.id}">${piece.title}</option>
            </c:if>
          </c:forEach>
        </select>

        <button type="submit">Confirm</button>
      </form>

      <form action="/admin/delete-piece" method="get">
        <h2>Delete a Piece</h2>

        <label for="titleRem">Piece</label>
        <select name="titleRem" id="titleRem" required>
          <c:forEach var="piece" items="${pieces}">
            <option <c:if test="${piece.archived}">style="background-color: black; color: white"</c:if>
                    value="${piece.id}">
                ${piece.title}
            </option>
          </c:forEach>
        </select>

        <button type="submit">Confirm</button>
      </form>
    </body>

    <c:if test="${fail}">
      <p>Something went wrong. Try again, please.</p>
    </c:if>

    </html>