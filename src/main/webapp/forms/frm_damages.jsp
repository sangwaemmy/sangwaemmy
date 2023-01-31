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
        <title>STOCK-Damages</title>

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
            <div class="row justify-content-center mt-4">

                <div class="col-sm-5 col-sm-12 offset-sm-0 col-md-12 border rounded p-3" style=" border: #3e7169 1px solid ; background-color: #a1e2d8;">
                    <h2 id="label" style="font-weight: bold;"> Damages</h2>
                    <div class="container login_frame">
                        <form action="#" >

                            <div class="form-group row">
                                <label for="pwd" class="col-md-3 needs-validation" id="label">Product</label>
                                <div class="col-md-9">
                                    <select class="form-control needs-validation" id="txt_product" >
                                        <option></option> 
                                        <c:forEach items="${get_all_products}" var="prod" >
                                            <option value="${prod.product_id}">${prod.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row ">
                                <label for="name" class="col-md-3" id="label">Damage quantity</label>
                                <div class="col-md-9">
                                    <input type="text" value="${byname}" class="form-control" placeholder="Enter quantity" id="txt_damage_qty">
                                </div>
                            </div>

                            <div class="form-group ">

                                <button type="button" class="btn float-left  bg-danger d-none" style="color: #fff;" id="btn_cancel_update" >Cancel</button>
                                <button type="submit" class="btn float-right btn-success mr-3 "id="btn_save_dam" >Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="row container-fluid">
                <c:if test="${start==null && name=='admin' && byname==null}">  <!--no pagination -->
                    <h6> <a href="${root}/report?action=all_damage" target="blank" class="btn btn-primary mt-3">Print All</a> </h6>
                </c:if>
                <c:if test="${byname!=null}">  <!--search -->
                    <h6 style="font-weight: bold;">Damage by name<a href="${root}/report?action=damage_by_name&start=${start}&damage_name=${pr_name}" target="blank" class="btn btn-primary ml-3">Print</a> <a class="ml-3 btn btn-primary" href="${root}/damages?action=get_dam_prdct_prs">Back to all damages</a></h6>
                </c:if>
                <c:if test="${byname==null && start!=null}">  <!-- no search -->
                    <h6 style="font-weight: bold;">Damages<a href="${root}/report?action=get_dam_prd_prs&start=${start}" target="blank" class="btn btn-primary ml-3">Print</a> </h6>
                </c:if>
            </div>
            <div class="row container mb-1 ">
                <div class="col-lg-9 col-sm-12">
                    <table>
                        <tr> 
                            <%  String tot1 = String.valueOf(request.getAttribute("tot"));
                                int tot = Integer.parseInt(tot1);
                                int pages = tot / 20;//
                                pages = ((tot % 20) > 0) ? (pages += 1) : pages;
                                for (int i = 1; i <= pages; i++) {

                                    String byname = (request.getAttribute("byname") != null) ? String.valueOf(request.getAttribute("byname")) : "";
                                    String still_page = (!"".equals(byname)) ? "get_dam_prd_prs_p_nm" : "get_dam_prdct_prs";
                                    String name_params = (!"".equals(byname)) ? "pr_name=" + request.getAttribute("pr_name") : ""    ;

                            %><td style="padding: 4px; margin-left: 5px; "> 
                                <a s style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/damages?action=<%=still_page%>&<%=name_params%>&start=<%=i%>"><%=i%></a></td><%
                                    }

                                %>
                        </tr>

                    </table>
                </div>
                <div class="col-lg-3 col-ms-12 d-flex justify-content-end">
                    <form action="${root}/damages" method="get">
                        <div class="form-group d-flex justify-content-end">
                            <input type="hidden" value="damage_by_name" name="action"/>
                            <input type="text" style="min-width: 300px" name="pr_name" value="${pr_name}"  required placeholder="Enter damage product">
                            <a href="#" class=" btn-info" ><button class="btn" type="submit" style="">Search</button></a>
                        </div>
                    </form>
                </div>
            </div>
            <table class="table table-bordered table-striped data_table table-hover " id="data_table">
                <tr>
                    <th>Product name</th>
                    <th>Date</th>
                    <th>Damage quantity</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                <c:if test="${damage_by_name!=null}">

                    <c:forEach items="${damage_by_name}" var="damage">
                        <tr>
                            <td>${damage.name}</td>
                            <td>${damage.date}</td>
                            <td>${damage.damage_qty}</td>
                            
                            <c:if test="${sessionScope.name=='admin'}">
                                <td><a href="#" style="color:black; font-weight: bolder;" class="link_damage"  data-bind="${damage.damage_id}">delete</a> </td>
                                <td><a href="#" style="color:black; font-weight: bolder;" class="link_update_damage"  data-bind="${damage.damage_id}">edit</a> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:forEach items="${get_dam_prdct_prs}" var="damage">
                    <tr>
                        <td>${damage.name}</td>
                        <td>${damage.date}</td>
                        <td>${damage.damage_qty}</td> 
                        
                        <c:if test="${sessionScope.name=='admin'}">
                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_damage"  data-bind="${damage.damage_id}">delete</a> </td>
                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_update_damage"  data-bind="${damage.damage_id}">edit</a> </td>
                        </c:if>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <div class="row">
            <table>
                <tr> 
                    <%for (int i = 1; i <= pages; i++) {
                            String byname = (request.getAttribute("byname") != null) ? String.valueOf(request.getAttribute("byname")) : "";
                            String still_page = (!"".equals(byname)) ? "get_dam_prd_prs_p_nm" : "get_dam_prdct_prs";
                            String name_params = (!"".equals(byname)) ? "txt_name=" + request.getAttribute("name") : "";

                    %><td style="padding: 4px; margin-left:20px; "> 
                        <a  style="background-color: #fff; color: #18302c; font-weight: bolder;" href="${root}/damages?action=<%=still_page%>&<%=name_params%>&start=<%=i%>"><%=i%></a> </td><%
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
