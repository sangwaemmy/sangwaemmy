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
        <title>STOCK-product</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">

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
            <div class="row justify-content-center" style="margin-top: 100px;">

                <div class="col-sm-5 col-sm-12 offset-sm-0 col-md-6 border rounded p-3" style=" border: #3e7169 1px solid ; background-color: #a1e2d8;">
                    <h2 id="label" style="font-weight: bold; " class="p-2" > Products</h2>
                    <div class="container">
                        <form action="http://localhost:8080/Stock_management/product" method="post">
                            <div class="form-group row">
                                <label for="name" class="col-md-3" id="label">Name</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control " placeholder="Enter product name" id="txt_name"  >
                                </div>
                            </div>
                            <div class="form-group row form-check ">

                                <button type="button" class="btn float-left  bg-danger d-none" style="color: #fff;" id="btn_cancel_update" >Cancel</button>
                                <button type="submit" class="btn float-right mr-3 mt-4 bg-success"id="btn_save_prod"  style="background-color: #fff; color: black">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">

            <div class="row container-fluid">
                
                <c:if test="${start==null && name=='admin' && prod_by_name==null}">  <!--no pagination -->
                    <h6> <a href="${root}/report?action=all_prod" target="blank" class="btn btn-primary ">Print All</a> 
                        <a class="" href="${root}/product?action=Frm_pruduct"></a></h6>
                </c:if>
                <c:if test="${prod_by_name!=null}">  <!--search -->
                    <h6>Products by name<a href="${root}/report?action=prod_by_name&prod_name=${prod_name}" target="blank" class="btn btn-primary ml-3 ">Print</a> <a class="ml-3 btn btn-primary " href="${root}/product?action=Frm_pruduct">All products</a></h6>
                </c:if>
                <c:if test="${prod_by_name==null && start!=null}">  <!-- no search -->
                    <h6>Products <a href="${root}/report?action=prod&start=${start}" target="blank" class="btn btn-primary ml-3 mt-3 mt-3">Print</a> </h6>
                </c:if>

            </div>
            <div class="row container mb-1 ">
                <div class="col-lg-9 col-sm-12">

                    <table>
                        <tr> 
                            <% int pages = 0;
                                if (request.getAttribute("tot") != null) {

                                    String tot1 = String.valueOf(request.getAttribute("tot"));
                                    int tot = Integer.parseInt(tot1);
                                    pages = tot / 20;//
                                    pages = ((tot % 20) > 0) ? (pages += 1) : pages;
                                    for (int i = 1; i <= pages; i++) {
                            %><td style="padding: 4px; margin-left: 5px; "> 
                                <a style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/product?action=Frm_pruduct&start=<%=i%>"><%=i%></a> </td><%
                                        }
                                    }
                                %>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-3 col-ms-12 d-flex justify-content-end">
                    <form action="${root}/product" method="get">
                        <div class="form-group d-flex justify-content-end">
                            <input type="hidden" value="prod_by_name" name="action"/>
                            <input type="text" style="min-width: 300px" name="prod_name" value="${prod_name}"  required placeholder="Enter Product Name">
                            <a href="#" class=" btn-info  " ><button class="btn" type="submit" style="">Search</button></a>
                        </div>
                    </form>
                </div>
            </div>
           <table class="table table-bordered table-striped data_table table-hover " id="data_table">
                <tr>
                    <th>Product name</th>
                    <th>Product(kg)</th>
                    <th>Sale cost </th>
                    <th>Total(Rwf) </th>

                    <c:if test="${sessionScope.name=='admin'}">
                        <th>Delete </th>
                        <th>Update</th>
                        </c:if>
                </tr>

                <c:if test="${prod_by_name!=null}">  <!--search -->
                    <c:forEach items="${prod_by_name}" var="pruduct">
                        <tr>
                            <td>${pruduct.name}</td>
                            <td>${pruduct.quantity}</td>
                            <td>${pruduct.sale_cost}</td>
                            <td>${pruduct.total}</td>
                            <c:if test="${sessionScope.name=='admin'}">
                                <td><a style="color:black; font-weight: bolder;" href="#"  class="link_product"  data-bind= "${pruduct.product_id}">delete</a> </td>
                                <td><a style="color: black; font-weight: bolder;" href="#" class="link_update_product"  data-bind= "${pruduct.product_id}">edit</a> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>

                <c:if test="${products!=null}">
                    <c:forEach items="${products}" var="pruduct">
                        <tr>
                            <td>${pruduct.name}</td>
                            <td>${pruduct.quantity}</td>
                            <td>${pruduct.sale_cost}</td>
                            <td>${pruduct.total}</td>
                            <c:if test="${sessionScope.name=='admin'}">
                                <td><a style="color:black; font-weight: bolder;" href="#"  class="link_product"  data-bind="${pruduct.product_id}">delete</a> </td>
                                <td><a style="color:black; font-weight: bolder;" href="#" class="link_update_product"  data-bind="${pruduct.product_id}">edit</a> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>

            </table>
        </div>
        <div class="row container">
            <table>
                <tr> 
                    <%  if (pages != 0) {
                            for (int i = 1; i <= pages; i++) {
                    %><td style="padding: 4px; margin-left: 5px; float: left;"> 
                        <a style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/product?action=Frm_pruduct&start=<%=i%>"><%=i%></a> </td><%

                                }
                            }
                        %>
                </tr>
            </table>
        </div>
    </div>
</dv>
<%@include file="../forms/footer_menu.jsp" %>
<script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="${root}/js/bootstrap.bundle.min.js"></script>
<script src="${root}/js/myscripts.js" type="text/javascript"></script>
</body>
</html>
