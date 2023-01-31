<%@page import="java.io.PrintWriter"%>
<%@include file="directives.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility // HTTP 1.0.
    session.getAttribute("userid");
    if (session.getAttribute("userid") == null || session.getAttribute("userid") == "" || "".equals(session.getAttribute("userid"))) {
        response.sendRedirect("frm_login.jsp");
    }
    HttpSession sesson2 = request.getSession();
    String userid = String.valueOf(sesson2.getAttribute("userid"));

    if ("".equals(userid) || userid == null) {
        response.sendRedirect("frm_login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">
        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${root}/css/mystyles.css" rel="stylesheet">
    </head>
    <body  style="background-color: #99ffff;">
        <%@include file="header_menu.jsp" %>
        <div class="ml-5 mt-3" style="text-transform: capitalize;"> <h1>${name} dashboard</h1></div>
        <div class="container">
            <center>

                <div class="text-muted mb-4">Welcome to the Stock management System. </div>
            </center>
            
            
        </div>
        <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
        <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->
        <!--<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>-->
        <script src="${root}/js/bootstrap.bundle.min.js"></script>
        <script src="${root}/js/myscripts.js" type="text/javascript"></script>
    </body>
</html>
