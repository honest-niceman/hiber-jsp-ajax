<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<%
    String message = exception.getMessage();
    String exceptionClass = exception.getClass().toString();

    String advice = "";
    if (exceptionClass.equalsIgnoreCase("CLASS JAVA.LANG.NUMBERFORMATEXCEPTION")) {
        String typed = message.substring(message.indexOf("\""));
        advice = "Where you typed " + typed + ", should be only digits. Please, try again.";
    } else if (request.getAttribute("artist-not-deleted") != null) {
        advice = "Sorry, but artist is not deleted. It seems that artist has at least one reference to the album. Delete all related albums and then try again.";
    } else if (request.getAttribute("album-not-deleted") != null) {
        advice = "Sorry, but album is not deleted. It seems that album has at least one reference to the song. Delete all related songs and then try again.";
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="page-center">
    <p class="p">
        <%
            if (advice.equals("")) {%>
        <%=message%>
        <%} else {%>
        <%=advice%>
        <%}%>
    </p>
    <button class="center-btn" onclick="goBack()">Go Back</button>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</div>
</body>
</html>