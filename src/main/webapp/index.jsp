<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <!--
    Author: Quinn Giebel
    Date: 12/11/20
-->
    <html lang="en">

    <head>
        <c:import url="head-tags.jsp">
            <c:param name="title" value="Gallery"/>
        </c:import>
    </head>

    <body>
        <c:import url="header-template.html" />

        <main>
            <c:forEach var="category" items="${categories}">
                <c:if test="${not empty category.pieces}">
                    <div id="<c:out value="${category.name}"/>">
                        <h2><c:out value="${category.title}"/></h2>
                        <hr>

                        <c:set var="openNewPair" value="true"/> <%-- Var to determine if a new imagePair div is needed --%>
                        <c:forEach var="piece" items="${category.pieces}">
                            <c:if test="${not piece.archived}">
                                <c:choose>
                                    <c:when test="${openNewPair}"> <%-- Opens a new imagePair div --%>
                                        <div class="imagePair">
                                            <img src="${piece.location}" alt="${piece.title}">

                                    </c:when>
                                    <c:otherwise> <%-- Closes imagePair div --%>
                                            <img src="${piece.location}" alt="${piece.title}">
                                        </div>

                                    </c:otherwise>
                                </c:choose>

                                <c:set var="openNewPair" value="${not openNewPair}"/>
                            </c:if>
                        </c:forEach>

                        <c:if test="${not openNewPair}"> <%-- Closes imagePair div if left dangling within loop --%>
                            </div>
                        </c:if>

                        <hr>
                    </div>
                </c:if>
            </c:forEach>
            <%--<div id="paintings">
                <h2>Paintings</h2>
                <hr>
                <div class="imagePair">
                    <img src="images/paintings/ship-on-the-water-doodle.jpg"
                        alt="It's a ship on some water. Looks like there's a water funnel in the background.">
                    <img src="images/paintings/california_hills_2020.png" alt="It's California when it was on fire.">
                </div>
                <hr>
            </div>

            <div id="props">
                <h2>Prop Design</h2>
                <hr>
                <div class="imagePair">
                    <img src="images/props/necronomicon-doodle.jpg"
                        alt="It's the necronomicon from the cult-classic film franchise, Evil Dead.">
                    <img src="images/props/lighter_doodle.jpg" alt="A lighter. It's pretty close to empty.">
                </div>

                <div class="imagePair">
                    <img src="images/props/taser-gauntlet.jpg" alt="A gauntlet taser with contacts on each knuckle.">
                    <img src="images/props/mag-glove.jpg"
                        alt="Similar to the taser gauntlet. This one has a pad of contacts on the palm.">
                </div>

                <div class="imagePair">
                    <img src="images/props/cryopod.jpg" alt="Quasi-futuristic cryopod.">

                </div>
                <hr>
            </div>

            <div id="other">
                <h2>Other Work</h2>
                <hr>
                <div class="imagePair">
                    <img src="images/studies/fish_eye_lens_practice.jpg"
                        alt="There's a guy looking at a forest, and it has a fish eye lens effect.">
                    <img src="images/studies/worshipers_in_cave.jpg"
                        alt="Just some dudes in a cave looking at an electric crystal.">
                </div>

                <div class="imagePair">
                    <img src="images/studies/portrait_with_weird_eyes.jpg" alt="A portrait with weird looking eyes.">
                    <img src="images/studies/picaso_lookin_faces-doodles.jpg" alt="Some faces.">
                </div>
                <hr>
            </div>--%>
        </main>

        <c:import url="footer-template.html" />

    </body>

    </html>