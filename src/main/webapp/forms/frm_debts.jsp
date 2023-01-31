<%

    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

    session = request.getSession();
    if (null == session.getAttribute("userid") || "".equals(session.getAttribute("userid")) || session.getAttribute("userid") == "") {

        request.setAttribute("Error", "Session has ended.  Please login.");
        RequestDispatcher rd = request.getRequestDispatcher("/forms/frm_login.jsp");
        rd.forward(request, response);
    }
%>
<%@include file="directives.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>STOCK-DEBTS</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">
        <link href="${root}/css/mystyles.css" rel="stylesheet">

        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${root}/css/mystyles.css" rel="stylesheet">
        <style>
            h1, h2,h3, h4, h5, h6{
                color: #18302c;
                font-weight: bold;
            }
            a{
                color: #2d5650;
            }
        </style>
    </head>
    <body style="background-color: #a6f4e8;">
        <%@include file="header_menu.jsp" %>

        <div class="container-sm">
             
                <div class="container">
                    <div> <h2>Lists of Debts</h2></div>
                    <table class="table table-bordered table-stripped " style="background-color: #fff">
                        <thead style="background-color: #0fff;">
                        <td>Customer name</td>
                        <td>Product name</td>
                        <td>Amount of debts</td>
                        <td>Date debts</td>
                        <td>Total debts</td>
                        <td>Delete</td>
                        <td>Update</td>
                        </thead>

                        <c:forEach items="${Frm_debts}" var="debt">
                            <tr class="brd">
                                <td>${debt.name} ${debt.surname}</td>
                                <td>${debt.product} </td>
                                <td>${debt.amount}</td>
                                <td>${debt.date_debts}</td>
                                <td>${debt.total_debts}</td>
                                <td><a style="color:black; " href="#"  class="link_debts"  data-bind= "${debt.debts_id}">delete</a> </td>
                                <td><a style="color: black; " href="#" class="link_update_debts "data-bind= "${debt.debts_id}">edit</a> </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>     
            </div>
       
            <%@include file="../forms/footer_menu.jsp" %>
            <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
            <script src="${root}/js/bootstrap.bundle.min.js"></script>
            <script src="${root}/js/myscripts.js" type="text/javascript"></script>

            <script src="${root}/js/jquery-2.1.3.min.js" type="text/javascript"></script>
            <script src="${root}/js/ui_scripts/jquery-ui.min.js" type="text/javascript"></script>
            <script src="${root}/js/ui_scripts/jquery-ui.js" type="text/javascript"></script>

    </body>
</html>
