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
                            <main class="row row-cols-1">
                                <div class="col py-2">
                                    qtgiebel@gmail.com
                                </div>
                                <div class="col py-2">
                                    giebelq@madisoncollege.edu
                                </div>
                                <div class="col py-2">
                                    example@domain.com
                                </div>
                                <div class="col py-2">
                                    things@stuff.net
                                </div>
                            </main>
                        </div>
                    </section>
                    
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
                        <form>
                            <div class="container">
                                <p class="mt-6">
                                    qtgiebel@gmail.com
                                </p>
                                <fieldset class="col">
                                    <legend>Permissions</legend>
                                    <label for="view">View:</label> <input type="checkbox" name="view" id="view" checked disabled>
                                    <label for="admin">Admin:</label> <input type="checkbox" name="admin" id="admin"  disabled>
                                </fieldset>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </section>
</body>

</html>