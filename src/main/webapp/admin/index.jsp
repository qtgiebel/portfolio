 <%-- Created by IntelliJ IDEA. User: Quinn Date: 2/10/2022 --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>


<head>
    <script type="text/javascript">
        const imageCollection = ${images}; <%--List of image objects as JSON TODO:01: make this into an api call --%>
    </script>
    <c:import url="templates/default-head-tags.jsp">
        <c:param name="title" value="Art Admin" />
        <c:param name="page" value="admin" />
    </c:import>
</head>

<c:import url="templates/header-template.jsp">
    <c:param name="active" value="admin" />
</c:import>

<body>
    <section class="sidebar max-height" id="art-admin-view">
        <!-- flex right? -->
        <div class="row run-to-bottom">
            <header class="col-2 bg-dark bg-gradient " id="categories-admin-view">

                    <%--Loop over each category --%>
                <c:forEach var="category" items="${categories}">
                <div class="container py-1">
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row">
                                <span class="px-4 py-2 text-light category-name">${category.title}</span>
                            </header>
                            <main class="row row-cols-2">

                                    <%-- Loop over each piece in the category --%>
                                <c:forEach var="piece" items="${category.pieces}">
                                <div class="col py-2">
                                    <button class="focus img-thumbnail <c:if test="${piece.archived}">red-shift</c:if>">
                                        <img src="${piece.location}" alt="${piece.title}" data-catIndex="${category.title}"/>
                                    </button>
                                </div>
                                </c:forEach>

                                    <%-- Add new image button and dialog --%>
                                <div class="col py-2">
                                    <button class="img-thumbnail new-dialog-btn" data-dialogId="new-image-${category.id}-dialog">
                                        <img src="/portfolio/images/add-button.jpg" alt="Add new image"/>
                                    </button>

                                    <div id="new-image-${category.id}-dialog" class="dialog">
                                        <div class="container rounded-corners dialog-content">
                                            <span class="close-btn" data-dialogId="new-image-${category.id}-dialog">&times;</span>
                                            <form action="/portfolio/admin/addPiece" method="post" enctype="multipart/form-data">
                                                <div class="my-3">
                                                    <label class="form-label" for="title">Title:</label>
                                                    <input class="form-control" type="text" name="title" id="title" required>
                                                </div>
                                                <div class="my-3">
                                                    <input class="form-control" type="file" name="file" id="file" required/>
                                                </div>
                                                <input type="hidden" name="category" value="${category.id}"/>
                                                <button class="btn btn-dark" type="submit">Add Piece</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </main>
                        </div>
                    </section>
                </div>
                </c:forEach>

                    <%--Create new category button --%>
                <div class="container py-1">
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row">
                                <button class="btn btn-dark new-dialog-btn" data-dialogId="new-category-dialog">
                                    New Category
                                </button>
                                <div id="new-category-dialog" class="dialog">
                                    <div class="container rounded-corners dialog-content">
                                        <span class="close-btn" data-dialogId="new-category-dialog">&times;</span>
                                        <form method="post" action="/portfolio/admin/addCategory">
                                            <div class="my-3">
                                                <label class="form-label" for="category">Category:</label>
                                                <input class="form-control" type="text" name="category" id="category">
                                            </div>

                                            <button class="btn btn-dark" type="submit">Create Category</button>
                                        </form>
                                    </div>
                                </div>
                            </header>
                        </div>
                    </section>
                </div>

            </header>
            <main class="col-10 max-height" id="piece-admin-view">


                <div class="navbar bg-light">
                    <div class="container">
                        <div class="float-start">
                            <p class="h2 nav-link text-dark float-start col">
                                <span class="row small text-secondary" id="hero-category">
                                    Category
                                </span>
                                <span class="row h2" id="hero-title">
                                    Title
                                </span>
                            </p>

                        </div>
                        <div class="float-end">
                            <button class="btn btn-secondary">Archive</button>
                            <button class="btn btn-danger">Delete</button>
                        </div>
                    </div>
                </div>
                <div class="bg-secondary run-to-bottom px-4 py-5" id="hero-image-container">
                </div>
            </main>
        </div>
    </section>
</body>


</html>