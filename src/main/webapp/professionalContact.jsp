<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
    Author: Quinn Giebel
    Date: 12/11/20
-->
<html lang="en">

<head>
    <title>Contact Me</title>
    <c:import url="default-head-tags.jsp" />
    <link rel="stylesheet" href="css/professionalContacts.css">
</head>

<body>

    <c:import url="header-template.html" />

    <main>
        <h2>Contact Me</h2>
        <hr>
        <article>
            <h3>Commissions</h3>
            <p>Fill out this form to request a commission. Check the commissions page for prices.</p>
        </article>
        <form action="http://itins3.madisoncollege.edu/echo.php" id="commissionForm">

            <label for="getEmailCommission" class="fixedWidth">Email:</label>
            <input type="text" name="getEmailCommission" id="getEmailCommission" title="Enter a valid email address."
                pattern="^[\w\.\-]*@\w*\.\w*$" required>
            <br>
            <label for="getName" class="fixedWidth">Name:</label>
            <input type="text" name="getName" id="getName">

            <fieldset>
                <legend>Choose the size of the piece.</legend>

                <input type="radio" name="getPiece" id="bust" value="bust" checked>
                <label for="bust" class="fixedWidth firstInFieldset">Bust</label>
                <br>
                <input type="radio" name="getPiece" id="waistUp" value="waistUp">
                <label for="waistUp" class="fixedWidth">Waist-up</label>
                <br>
                <input type="radio" name="getPiece" id="fullBody" value="fullBody">
                <label for="fullBody" class="fixedWidth">Full Body</label>
            </fieldset>

            <fieldset>
                <legend>Add any extras.</legend>

                <input type="checkbox" name="addCharacter" id="addCharacter" value="true">
                <label for="addCharacter" class="firstInFieldset">Add an additional Character</label>
                <br>
                <input type="checkbox" name="addObject" id="addObject" value="true">
                <label for="addObject">Add an additional Object</label>
                <br>
                <input type="checkbox" name="addLighting" id="addLighting" value="true">
                <label for="addLighting">Add lighting and a background.</label>
            </fieldset>

            <label for="getDetailsCommission" class="textareaLabel">Describe what you want the piece to look
                like.</label>
            <textarea name="getDetailsCommission" id="getDetailsCommission" cols="30" rows="10" required></textarea>

            <div class="button-wrapper">
                <input type="submit" value="Submit">
                <input type="reset" value="Clear">
            </div>
        </form>

        <article>
            <h3>Business Inquiries</h3>
            <p>Fill out this form with other professional inquiries. Please, allow up to 24 hours to receive a reply.
            </p>
        </article>
        <form action="http://itins3.madisoncollege.edu/echo.php" id="otherContactForm">
            <label for="getEmailOther" class="fixedWidth">Email:</label>
            <input type="text" name="getEmailOther" id="getEmailOther" title="Enter a valid email address."
                pattern="^[\w\.\-]*@\w*\.\w*$">
            <br>

            <label for="getInquiryType">Reason for contact:</label>
            <select name="getInquiryType" id="getInquiryType">
                <option value="opportunity">Business Opportunity</option>
                <option value="critique">Request Critique</option>
                <option value="collab">Collaboration</option>
                <option value="other">Other</option>
            </select>

            <br>
            <label for="getDetailsOther" class="textareaLabel">Inquiry Details:</label>
            <textarea name="getDetailsOther" id="getDetailsOther" cols="30" rows="10"></textarea>

            <div class="button-wrapper">
                <input type="submit" value="Submit">
                <input type="reset" value="Clear">
            </div>
        </form>

    </main>

    <c:import url="footer-template.html" />

</body>

</html>