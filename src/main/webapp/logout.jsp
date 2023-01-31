<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility // HTTP 1.0.

%>
<html>
    <head>
        <title>Logout</title>
    </head>
    <body class="bg-info">
        <h1>Sucessfully logout</h1>
        <a href="index.jsp">Login</a>

        <%   if (session.getAttribute("userid") != null ) {
                    session.removeAttribute("userid");
                    //presence of category name                     
                    session.removeAttribute("name");
                    request.getSession(false);
                    session.setAttribute("userid",null);
                    // with no category name
                    session.setAttribute("name",null);
                    session.invalidate();
                    response.sendRedirect("forms/frm_login.jsp");
            }
        %>
    </body>
</html>
