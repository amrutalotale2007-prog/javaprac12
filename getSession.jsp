<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Session Variable</title>
</head>
<body>

<h2>Session Variable Value</h2>

<%
    String username = (String) session.getAttribute("username");
%>

<p>Username: <b><%= username %></b></p>

</body>
</html>