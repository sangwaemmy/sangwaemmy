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
        <title>STOCK - Paydebts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">

        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${root}/css/mystyles.css" rel="stylesheet">
        <%@include file="ui_cripts_link.jsp"%>

    </head>
    <body style="background-color: #a6f4e8;">
        <%@include file="header_menu.jsp" %>
        <div class="container-sm">
            <div class="row justify-content-center mt-4">

                <div class="col-sm-6 col-sm-12 offset-sm-0 col-md-12 border rounded   p-3" style=" border: #3e7169 1px solid ; background-color: #a1e2d8;">
                    <h2 id="label" style="font-weight: bold;">Pay debts </h2>
                    <div class="container login_frame">
                        <form action="#">
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Customers</label><div class="col-md-9">
                                    <select class="form-control " id="txt_person">
                                        <option></option>
                                        <c:forEach items="${customers}" var="cus">
                                            <option value="${cus.person_id}">${cus.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Product</label>
                                <div class="col-md-9">
                                    <select class="form-control cbo_sale cb_sale_cost" id="txt_product">
                                        <option></option> 
                                        <c:forEach items="${get_all_products}" var="prod">
                                            <option value="${prod.product_id}">${prod.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="name" class="col-md-3" id="label">Amount paid</label>
                                <div class="col-md-9">
                                    <input type="number" class="form-control" placeholder="Enter Amount" id="txt_amt_paid" >
                                </div>
                            </div>

                            <div class="form-group ">
                            </div>
                            <button type="button" class="btn float-left  bg-danger d-none" style="color: #fff;" id="btn_cancel_update" >Cancel</button>
                            <button type="submit" class="btn float-right btn-success mt-4 mr-3" id="btn_save_paydebts" >Save</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container-sm">

                <div class="container">
                    <div> <h2>Lists of Pay debts</h2></div>
                    <table class="table table-bordered table-stripped " style="background-color: #fff">
                        <thead style="background-color: #0fff;">
                        <td>Customer name</td>
                        <td>Product name</td>
                        <td>Amount paid</td>
                        <td>Amount Remaining</td>
                        <td>Date paid</td>
                        <td>Delete</td>
                        <td>Update</td>
                        </thead>

                        <c:forEach items="${frm_paydebts}" var="paydebt">
                            <tr class="brd">
                                <td>${paydebt.person}</td>
                                <td>${paydebt.product} </td>
                                <td>${paydebt.amt_paid}</td>
                                <td>${paydebt.amt_remaining}</td>
                                <td>${paydebt.date_paid}</td>
                                <td><a style="color:black; " href="#"  class="link_paydebts"  data-bind= "${paydebt.paydebts_id}">delete</a> </td>
                                <td><a style="color: black; " href="#" class="link_update_paydebts "data-bind= "${paydebt.paydebts_id}">edit</a> </td>
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