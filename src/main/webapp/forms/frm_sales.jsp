
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
        <title>STOCK - Sales</title>
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
                    <h2 id="label" style="font-weight: bold;">Sales </h2>
                    <div class="container login_frame">
                        <form action="#">

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
                                <label for="name" class="col-md-3" id="label">Sold quantity</label>
                                <div class="col-md-9">
                                    <input type="number" class="form-control" placeholder="Enter quantity" id="txt_sold_qty" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-md-3" id="label">Current quantity</label>
                                <div class="col-md-9" >
                                    <input type="number" class="form-control " placeholder="Enter current quantity" id="txt_current_qty">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-md-3" id="label">Sale cost</label>
                                <div class="col-md-9" >
                                    <input type="number" class="form-control" placeholder="Enter Sale cost" id="txt_sale_cost"> 
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Customers</label><div class="col-md-9">
                                    <select class="form-control " id="txt_person">
                                        <option></option>
                                        <c:forEach items="${Customers}" var="cus">
                                            <option value="${cus.person_id}">${cus.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-md-3" id="label">Amount Paid</label>
                                <div class="col-md-9" >
                                    <input type="number" class="form-control" placeholder="Enter amount" id="txt_amt_paid"> 
                                </div>
                            </div>
                            <div class="form-group ">
                            </div>
                            <button type="button" class="btn float-left  bg-danger d-none" style="color: #fff;" id="btn_cancel_update" >Cancel</button>
                            <button type="submit" class="btn float-right btn-success mt-4 mr-3" id="btn_save_sales" >Save</button>
                        </form>
                    </div>
                </div>
            </div>



            <!---->
            <c:if test="${start==null && name=='admin' && sales_by_date==null}">  <!--no pagination -->
                <h6> <a href="${root}/report?action=all_sales" target="blank" class="btn btn-primary mt-3">Print All</a> </h6>
            </c:if>
            <c:if test="${sales_by_date!=null}">  <!--search -->
                <h6 style="font-weight: bold;">sales by date<a href="${root}/report?action=sales_by_date&start=${start}&sale_date1=${date1}&sale_date2=${date2}" target="blank" class="btn btn-primary ml-3">Print</a>  <a class="ml-4 btn btn-primary" href="${root}/sales?action=get_sls_prd_ps"> Back to all sales</a></h6>
            </c:if>
            <c:if test="${sales_by_date==null && start!=null}">  <!-- no search -->
                <h6 style="font-weight: bold;">Sales<a href="${root}/report?action=get_sls_prd_ps&start=${start}" target="blank" class="btn btn-primary ml-3">Print</a> </h6>
            </c:if>
            <div class="row container mb-1 ">

                <div class="col-lg-6 col-ms-12">
                    <table>
                        <tr> 
                            <% String tot1 = String.valueOf(request.getAttribute("tot"));
                                int tot = Integer.parseInt(tot1);
                                int pages = tot / 20;
                                pages = ((tot % 20) > 0) ? (pages += 1) : pages;

                                String bydate = (request.getAttribute("bydate") != null) ? String.valueOf(request.getAttribute("bydate")) : "";
                                String still_page = (!"".equals(bydate)) ? "get_sales_prdct_prs" : "get_sls_prd_ps";
                                String dates_params = (!"".equals(bydate)) ? "txt_date1=" + request.getAttribute("date1") + "&txt_date2=" + request.getAttribute("date2") : "";

                                //?date1= &date2=
                                for (int i = 1; i <= pages; i++) {
                            %>
                            <td style="padding: 4px; margin-left: 5px; "> 
                                <a  style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/sales?action=<%=still_page%>&<%=dates_params%>&start=<%=i%>"><%=i%></a> </td>
                                <%
                                    }

                                %>
                        </tr>

                    </table>
                </div>

                <div class="col-lg-6 col-ms-12">
                    <div class="row container-fluid d-flex justify-content-end"> 

                        <form action="${root}/sales" method="get" class="col-12  container-fluid">
                            <div class="row">
                                <div class="col-lg-9">
                                    <div class="row">
                                        <div class="col-6"><input autocomplete="off" name="txt_date1" style="height: 35px;" 
                                                                  value="${date1}"type="text" class="date_input " placeholder="From"></div>
                                        <div class="col-6"><input autocomplete="off" name="txt_date2"style="height: 35px;" 
                                                                  value="${date2}"type="text" class="date_input"  placeholder="To"></div>
                                    </div>
                                </div>
                                <div class="col-lg-3 ">

                                    <input type="hidden" value="get_sales_prdct_prs" name="action"/>
                                    <button class="btn btn-info btn-block" type="submit" style="height: 35px;">Search</button>
                                </div>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
            
            <table class="table table-bordered table-striped data_table table-hover " id="data_table">
                <tr>
                    <th>Product name</th>
                    <th>Date</th>
                    <th>Sold Quantity(kg)</th>
                    <th>current quantity(kg)</th>
                    <th>Sale Cost</th>
                    <th>Amount paid</th>
                    <th>Total(Rwf)</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Telephone</th>
                        <c:if test="${sessionScope.name=='admin'}">
                        <th>Delete </th>
                        <th>Update</th>
                        </c:if>
                </tr>

                <!--data list by date search-->
                <c:if test="${sales_by_date!=null}">

                    <c:forEach items="${sales_by_date}" var="sales">

                        <tr>
                            <td>${sales.name}</td>
                            <td>${sales.date}</td>
                            <td>${sales.sold_qty}</td>
                            <td>${sales.sale_cost}</td>
                            <td>${sales.amt_paid}</td>
                            <td>${sales.total}</td>
                            <td>${sales.current_qty}</td>
                            <td>${sales.p_name}</td>
                            <td>${sales.surname}</td>
                            <td>${sales.phone_number} </td>

                            <c:if test="${sessionScope.name=='admin'}">
                                <td><a  href="#" style="color:black; font-weight: bolder;" class="link_sale"  data-bind="${sales.sale_id}">delete</h6></a></td>
                                <td><a href="#" style="color:black; font-weight: bolder;" class="link_update_sale"  data-bind="${sales.sale_id}">edit</a> </td>
                            </c:if>
                        </tr>

                    </c:forEach>
                </c:if>
                <!--data with no search-->
                <c:forEach items="${get_sls_prd_ps}" var="sales">
                    <tr>
                        <td>${sales.name}</td>
                        <td>${sales.date}</td>
                        <td>${sales.sold_qty}</td>
                        <td>${sales.current_qty}</td>      
                        <td>${sales.sale_cost}</td>
                        <td>${sales.amt_paid}</td>
                        <td>${sales.total}</td>
                        <td>${sales.p_name}</td>
                        <td>${sales.surname}</td>
                        <td>${sales.phone_number} </td>

                        <c:if test="${sessionScope.name=='admin'}">
                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_sale"  data-bind="${sales.sale_id}">delete</a> </td>
                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_update_sale"  data-bind="${sales.sale_id}">edit</a> </td>
                        </c:if>

                    </tr>

                </c:forEach>

            </table>
            <div class="row">
                <table style=" margin-left: 24px;">
                    <tr> 
                        <%for (int i = 1; i <= pages; i++) {

                        %><td style="padding: 4px; margin-left: 5px; "> 
                            <a style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/sales?action=<%=still_page%>&<%=dates_params%>&start=<%=i%>"><%=i%></a> </td><%
                                }

                            %>
                    </tr>   
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
