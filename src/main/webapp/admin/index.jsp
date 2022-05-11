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

                                    <%-- Delete category button is on hold for a later version --%>
                                    <%-- Currently being checked by if in portfolio/index.jsp --%>
                                <%--<div class="col-1">
                                    <span class="close-btn new-dialog-btn" data-dialogid="delete-category-${category.id}-dialog">&times;</span>
                                    <div id="delete-category-${category.id}-dialog" class="dialog">
                                        <div class="container rounded-corners dialog-content">
                                            <span class="close-btn" data-dialogid="delete-category-${category.id}-dialog">&times;</span>
                                            <form action="/portfolio/admin/deleteCategory" method="post">
                                                <div class="my-3">
                                                    <label class="form-label" for="confirm-delete-${category.id}">Are you sure you want to delete this category?</label>
                                                    <input class="form-control" type="checkbox" name="confirm-delete" id="confirm-delete-${category.id}" required>
                                                </div>
                                                <input type="hidden" name="category" value="${category.id}"/>
                                                <button class="btn btn-dark" type="submit"
                                                        id="confirm-delete-${category.id}-submit" disabled>
                                                    Delete Category
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>--%>

                            </header>
                            <main class="row row-cols-2">

                                    <%-- Loop over each piece in the category --%>
                                <c:forEach var="piece" items="${category.pieces}">
                                <div class="col py-2">
                                    <button class="focus img-thumbnail <c:if test="${piece.archived}">archived</c:if>">
                                        <img src="${piece.location}" alt="${piece.title}" data-category="${category.title}" data-pieceid="${piece.id}" class="rounded-corners"/>
                                    </button>
                                </div>
                                </c:forEach>

                                    <%-- Add new image button and dialog --%>
                                <div class="col py-2">
                                    <button class="img-thumbnail new-dialog-btn" data-dialogid="new-image-${category.id}-dialog">
                                        <img src="admin/images/add-button.jpg" alt="Add new image"/>
                                    </button>

                                    <div id="new-image-${category.id}-dialog" class="dialog">
                                        <div class="container rounded-corners dialog-content">
                                            <span class="close-btn" data-dialogid="new-image-${category.id}-dialog">&times;</span>
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
                                <button class="btn btn-dark new-dialog-btn" data-dialogid="new-category-dialog">
                                    New Category
                                </button>
                                <div id="new-category-dialog" class="dialog">
                                    <div class="container rounded-corners dialog-content">
                                        <span class="close-btn" data-dialogid="new-category-dialog">&times;</span>
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
                            <button id="archive-btn" class="btn btn-secondary new-dialog-btn" data-dialogid="archive-dialog" disabled>Archive</button>
                            <div id="archive-dialog" class="dialog">
                                <div class="container rounded-corners dialog-content">
                                    <span class="close-btn" data-dialogid="archive-dialog">&times;</span>
                                    <form id="archive-form" method="post" action="/portfolio/admin/archivePiece">
                                        <p>Confirm</p>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="confirm-archive" id="confirm-archive" required>
                                            <label class="form-check-label" for="confirm-archive">Are you sure you want to edit this piece?</label>
                                        </div>
                                        <div class="button-box col-12">
                                            <button id="archive-submit" class="btn btn-secondary" type="submit" disabled>Yes</button>
                                            <input class="btn btn-dark close-btn-fn" type="reset" value="No" data-dialogid="archive-dialog">
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <button id="delete-btn" class="btn btn-danger new-dialog-btn" data-dialogid="delete-dialog" disabled>Delete</button>
                            <div id="delete-dialog" class="dialog">
                                <div class="container rounded-corners dialog-content">
                                    <span class="close-btn" data-dialogid="delete-dialog">&times;</span>
                                    <form id="delete-form" action="/portfolio/admin/deletePiece" method="post">
                                        <p>Confirm</p>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="confirm-delete" id="confirm-delete" required/>
                                            <label class="form-check-label" for="confirm-delete">Are you sure you want to delete this piece? This action cannot be undone.</label>
                                        </div>
                                        <div class="button-box col-12">
                                            <button id="delete-submit" class="btn btn-danger" type="submit" disabled>Yes</button>
                                            <input class="btn btn-dark close-btn-fn" type="reset" value="No" data-dialogid="archive-dialog">
                                        </div>
                                    </form>
                                </div>
                            </div>
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