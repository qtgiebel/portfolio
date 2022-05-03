<%-- Created by IntelliJ IDEA. User: Quinn Date: 2/10/2022 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>


<head>
    <script type="text/javascript">
        const userCollection = ${users}; <%--List of image objects as JSON TODO:01: make this into an api call--%>
    </script>
    <c:import url="templates/default-head-tags.jsp">
        <c:param name="title" value="Admin Users" />
        <c:param name="page" value="users" />
    </c:import>

</head>

<c:import url="templates/header-template.jsp">
    <c:param name="active" value="users" />
</c:import>

<body>
    <section class="sidebar max-height" id="art-admin-view">
        <!-- flex right? -->
        <div class="row run-to-bottom">
            <header class="col-2 bg-dark bg-gradient " id="categories-admin-view">
                <!--
                        get all users
                        for each user 
                            print as entry in sidebar
                    -->
                <div class="container py-1">
                    <section class="container bg-secondary rounded-corners my-1 mt-2">
                        <div class="col">
                            <header class="row"><span class="px-4 py-2 text-light category-name"
                                    data-category-index="0">Users</span>
                            </header>
<%--                            TODO:04 loop through each user and print email as button to send to main panel--%>
                            <main id="user-list" class="bg-light container">

                            </main>
                        </div>
                    </section>
                    
                    <button class="btn btn-dark" id="new-user-dialog-btn">New User</button>
                    <div id="new-user-dialog" class="dialog">
                        <div class="container rounded-corners dialog-content">
                            <span id="close-btn">&times;</span>
                            <form action="/admin/add-user">
                                <div class="my-3">
                                    <label class="form-label" for="email">Email:</label>
                                    <input class="form-control" type="text" name="email" id="email">
                                </div>
                                <div class="mb-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="admin" id="dialog-admin">
                                        <label class="form-check-label" for="dialog-admin">Admin?</label>
                                    </div>
                                </div>
                                <button class="btn btn-dark" type="submit">Create User</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Create new user button -->
            </header>
            <main class="col-10 max-height" id="user-admin-view">
                
                <div class="navbar bg-light">
                    <div class="container">
                        <div class="float-start">
                            <p class="h2 nav-link text-dark float-start col">
                                <span class="row h2" id="hero-title">
                                    User Information
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="bg-secondary run-to-bottom px-4 py-5">
                    <div class="container bg-light rounded-corners">
                        <form action="/portfolio/admin/update-user">
                            <div class="container">
                                <div class="mb-3">
                                    <p id="hero-user" class="form-label pt-6">qtgiebel@gmail.com</p>
                                </div>
                                <fieldset class="col pb-3">
                                    <legend>Permissions</legend>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="permission" id="view" value="view" checked>
                                        <label class="form-check-label" for="view">View</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="permission" id="admin" value="admin">
                                        <label class="form-check-label" for="admin">Admin</label>
                                    </div>
                                </fieldset>
                                <input type="submit" value="Save Changes" class="btn btn-danger mb-3">
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </section>
</body>

</html>