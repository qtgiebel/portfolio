<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en-US">

    <head>
        <c:import url="default-head-tags.html" />
        <title>Eleganter - New Amazing HTML5 Template</title>
    </head>

    <body class="size-1140">

        <c:import url="header-template.html" />

        <!-- MAIN -->
        <main role="main">
            <!-- Main Header -->
            <header>
                <div class="carousel-default owl-carousel carousel-main carousel-nav-white background-dark text-center">
                    <div class="item">
                        <div class="s-12">
                            <img src="images/header-2.jpg" alt="">
                            <div class="carousel-content">
                                <div class="content-center-vertical line">
                                    <div class="margin-top-bottom-80">
                                        <!-- Title -->
                                        <h1
                                            class="text-white margin-bottom-30 text-size-60 text-m-size-30 text-line-height-1">
                                            Quinn Giebel<br> Personal Projects</h1>
                                        <div class="s-12 m-10 l-8 center">
                                            <p class="text-white text-size-14 margin-bottom-40">These are some of my
                                                projects.</p>
                                        </div>
                                        <div class="line"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Section 1 -->
            <section id="unit-links" class="section-small-padding background-white text-center">
                <div class="line">
                    <div class="margin ">
                        <div class="s-12 m-12 l-4 margin-m-bottom">
                            <div class="padding-2x block-bordered">
                                <h2 class="text-thin">Art Portfolio</h2>
                                <p class="margin-bottom-30">A simple portfolio site that built with HTML and CSS. All of
                                    the art shown is my own as well.</p>
                                <a class="button button-dark-stroke text-size-12" href="portfolio/portfolio.html">GO</a>
                            </div>
                        </div>
                        <div class="s-12 m-12 l-4 margin-m-bottom">
                            <div class="padding-2x block-bordered">
                                <h2 class="text-thin">Simple Employee Manager</h2>
                                <p class="margin-bottom-30">Some simple employee management tools built with Java and
                                    utilizing MVC architecture.</p>
                                <a class="button button-dark-stroke text-size-12" href="employeeTools.jsp">GO</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <c:import url="footer-template.html" />

            <script type="text/javascript" src="js/responsee.js"></script>
            <script type="text/javascript" src="owl-carousel/owl.carousel.js"></script>
            <script type="text/javascript" src="js/template-scripts.js"></script>
    </body>

    </html>