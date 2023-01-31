
<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

    session = request.getSession();

    String userName = String.valueOf(session.getAttribute("userid"));
    if (null == userName || "null".equals(userName) || "".equals(userName) || userName == "") {

        request.setAttribute("Error", "Session has ended.  Please login.");
        RequestDispatcher rd = request.getRequestDispatcher("forms/frm_login.jsp");
        rd.forward(request, response);
    } else {
//        response.getWriter().print(userName);
    }


%>
<%--<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1"/>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/"/>

        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet"/>
        <title>Logout</title>
    </head>
    <body class="bg-info  " style="background-color: #abb2dd; ">
        <div class="row  d-flex   justify-content-center ">
            <div class="col-sm-6 col-md-6 col-lg-6 text-center  text-white mt-5 "  >
                <h3>Are you sure you want to logout?</h3>
            </div>
        </div>

        <%  if (null == userName || "".equals(userName)) {

                request.setAttribute("Error", "Session has ended.  Please login.");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                response.sendRedirect("forms/frm_login.jsp");
            }

        %>
        <div class="row d-flex justify-content-center mt-3">
            <h2> <a href="logout.jsp"><button type="submit" class="btn " style="padding: 10px; min-width: 100px; background-color: #ff9999">Logout</button></a>  </h2>
        </div>
        <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
        <script src="${root}/js/bootstrap.bundle.min.js"></script>
        <script src="${root}/js/myscripts.js" type="text/javascript"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
