<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = request.getParameter("name");

    session.setAttribute("username", name);

    response.sendRedirect("getSession.jsp");
%>