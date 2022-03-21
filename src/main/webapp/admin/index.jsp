<!-- <%-- Created by IntelliJ IDEA. User: Quinn Date: 2/10/2022 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->

<html>


<head>
    <c:import url="templates/default-head-tags.jsp">
        <c:param name="title" value="Art Admin" />
    </c:import>

    <script src="js/index.js"></script>
</head>

<c:import url="templates/header-template.jsp">
    <c:param name="active" value="admin" />
</c:import>

<body>
    <section class="sidebar max-height" id="art-admin-view">
        <!-- flex right? -->
        <div class="row run-to-bottom">
            <header class="col-2 bg-dark bg-gradient " id="categories-admin-view">
                <!--
                        for each category // flex down?
                            output a section:
                                with header: category name
                                with main: 
                                for each piece in category 
                                    pieces wrapped in PANEL buttons 
                                    additional button to upload new piece
                                end for
                        end for
                    -->
                <c:forEach items="category" var="${categories}">
                <div class="container py-1">
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row"><span class="px-4 py-2 text-light category-name"
                                                      data-category-index="0">${category.name}</span>
                            </header>
                            <main class="row row-cols-2">
                    <c:forEach items="piece" var="${category.pieces}">

                    </c:forEach>
                            </main>
                        </div>
                    </section>
                </div>
                </c:forEach>
                <div class="container py-1">
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row"><span class="px-4 py-2 text-light category-name"
                                    data-category-index="0">Sketch</span>
                            </header>
                            <main class="row row-cols-2">
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="0" />
                                    </button>
                                </div>
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="0" />
                                    </button>
                                </div>
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="0" />
                                    </button>
                                </div>
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="0" />
                                    </button>
                                </div>
                            </main>
                        </div>
                    </section>
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row"><span class="px-4 py-2 text-light category-name"
                                    data-category-index="1">Painting</span></header>
                            <main class="row row-cols-2">
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="1" />
                                    </button>
                                </div>
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="1" />
                                    </button>
                                </div>
                                <div class="col py-2">
                                    <button class="focus img-thumbnail">
                                        <img src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
                                            alt="Necronomicon Doodle" data-image-category-index="1" />
                                    </button>
                                </div>
                            </main>
                        </div>
                    </section>
                </div>
                <!-- Create new category button -->
            </header>
            <main class="col-10 max-height" id="piece-admin-view">
                <!--
                JS: PANEL onclick
                    ouput section:
                        with header: category name
                        with main: large version of image from og button
                end JS
            -->
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
                <div class="bg-secondary run-to-bottom px-4 py-5" id="hero-image">
                    <!-- <img class="rounded mx-auto d-block" src="" alt="Necronomicon Doodle" /> -->
                </div>
            </main>
        </div>
    </section>
</body>

</html>