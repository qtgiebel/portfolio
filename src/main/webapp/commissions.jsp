<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
    Author: Quinn Giebel
    Date: 12/11/20
-->
<html lang="en">

<head>
    <title>Commissions</title>
    <c:import url="default-head-tags.jsp" />
    <link rel="stylesheet" href="css/commissions.css">
</head>

<body>

    <c:import url="header-template.html" />

    <main>
        <article>
            <h2>I Do Commissions</h2>
            <hr>
            <p>
                If you would like to commission a piece, <a href="professionalContact.jsp">click here</a>.
                <br>
                <br>
                <em>Unfortunately, commissions are currently closed.</em>
            </p>
            <hr>
        </article>

        <h2>Rates</h2>

        <div class="tablePair">
            <hr>
            <div id="baseWrapper">
                <h3>Base</h3>

                <table id="baseCommissions">
                    <tr>
                        <th>Piece</th>
                        <th>Price</th>
                    </tr>
                    <tr>
                        <td>Bust:</td>
                        <td class="price">$25</td>
                    </tr>
                    <tr>
                        <td>Waist-up:</td>
                        <td class="price">$35</td>
                    </tr>
                    <tr>
                        <td>Full Body:</td>
                        <td class="price">$45</td>
                    </tr>
                </table>
            </div>

            <div id="extrasWrapper">
                <h3>Extras</h3>

                <table id="commissionExtras">
                    <tr>
                        <td>
                            Additional Character
                            <br>
                            <em class="priceMayVary">Price may vary.</em>
                        </td>
                        <td class="price">+$20</td>
                    </tr>
                    <tr>
                        <td>
                            Additional Object
                            <br>
                            <em class="priceMayVary">Price may vary.</em>
                        </td>
                        <td class="price">+$15</td>
                    </tr>
                    <tr>
                        <td>Background/Lighting</td>
                        <td class="price">+$10</td>
                    </tr>
                </table>
            </div>
        </div>
    </main>

    <c:import url="footer-template.html" />

</body>

</html>