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

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">
        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${root}/css/mystyles.css" rel="stylesheet">
        <%@include file="ui_cripts_link.jsp"%>

        <title>Daly report</title>
        <style>
            #tb_head{
                background-color: #1d8337;
                color: #fff;
                font-size: 20px;
            }
            #figurs_row{
                font-size: 30px;
                font-weight: bolder;
                color: #03a900;
            }
        </style>
    </head>
    <body>
        <%@include file="header_menu.jsp" %>


        <div class="row container mb-5 ">
            <div class="col-6"></div>
            <div class="col-lg-6 col-ms-12 mt-5 border ">
                <div class="row container-fluid d-flex"> 
                   
                    <form action="${root}/paydebts?action=daily_report" method="get" class="col-12  container-fluid">
                        <div class="row">
                            <div class="col-lg-9   ">
                                <div class="row">
                                    <div class="col-6"> 
                                        <input autocomplete="off" name="txt_date1" style="height: 35px;" 
                                               value="${date1}" type="text" class="date_input " placeholder="From">
                                    </div>
                                    <div class="col-6"><input autocomplete="off" name="txt_date2"style="height: 35px;" 
                                                              value="${date2}"type="text" class="date_input"  placeholder="To"></div>
                                </div>
                            </div>
                            <div class="col-lg-3">

                                <input type="hidden" value="daily_report" name="action"/>
                                <button class="btn btn-info btn-block" type="submit" style="height: 35px;">Search</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>


        </div>
        <div class="container justify-content-center mb-5">
            <div class="row"> <h2>Financial Daily Summarized Report</h2></div>
            <div class="row  border border-dark">
                <div class="col-"></div>
                <table class="table table-bordered table-striped ">
                    <thead  id="tb_head">
                    <th>Expected sales</th>
                    <th>Debts Payments</th>
                    <th>Debts</th>
                    <th>Total Expenses</th>
                    <th>Cash to collect</th>

                    </thead>
                    <%
                        int sales = Integer.parseInt(String.valueOf(request.getAttribute("sales")));
                    %>
                    <tr id="figurs_row">
                        <td>  <%=sales%> </td>
                        <td> ${payments}</td>
                        <td> ${debts}</td>
                        <td> ${expenses}  </td>
                        <td> ${take_to_bank}</td>
                    </tr>
                </table>
            </div>
        </div>
                    <span class="row mt-5"/>
        <%@include file="../forms/footer_menu.jsp" %>
        <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
        <script src="${root}/js/bootstrap.bundle.min.js"></script>
        <script src="${root}/js/myscripts.js" type="text/javascript"></script>
        <script src="${root}/js/jquery-2.1.3.min.js" type="text/javascript"></script>
        <script src="${root}/js/ui_scripts/jquery-ui.min.js" type="text/javascript"></script>
        <script src="${root}/js/ui_scripts/jquery-ui.js" type="text/javascript"></script>
    </body>
</html>
