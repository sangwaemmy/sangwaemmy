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
        <title>STOCK-Users</title>
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
            <div class="row justify-content-center mt-4">

                <div class="col-sm-5 col-sm-12 offset-sm-0 col-md-12 border rounded p-3"  style=" border: #3e7169 1px solid ; background-color: #a1e2d8;" >
                    <h2 id="label" style="font-weight: bold;"> Users</h2>
                    <div class="container">
                        <form action="#" >

                            <div class="form-group row">
                                <label for="name" class="col-md-3" id="label">Name</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" placeholder="Enter name" id="txt_name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-md-3" id="label">Surname</label><div class="col-md-9" >
                                    <input type="text" class="form-control" placeholder="Enter surname" id="txt_surname"> </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Category</label><div class="col-md-9">
                                    <select class="form-control" id="txt_category">
                                        <option></option> 
                                        <c:forEach items="${Categories}" var="cat">
                                            <option value="${cat.acc_category_id}">${cat.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Gender</label><div class="col-md-9">
                                    <select class="form-control" id="txt_gender">
                                        <option></option>
                                        <option>Male</option>
                                        <option>Female</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Telephone</label><div class="col-md-9">
                                    <input type="text" class="form-control"  id="txt_phone_number"> </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Username</label><div class="col-md-9">
                                    <input type="email" class="form-control" placeholder="Enter username" id="txt_username"> </div>
                            </div>
                            <div class="form-group row">
                                <label for="pwd" class="col-md-3" id="label">Password</label><div class="col-md-9">
                                    <input type="password" class="form-control" placeholder="Enter password" id="txt_password"> </div>
                               
                            </div> 
                           <div class="form-group">
                                <button type="button" class="btn float-left  bg-danger d-none" style="color: #fff;" id="btn_cancel_update" >Cancel</button>
                                <button type="submit" class="btn float-right btn-success mr-3 mt-3" id="btn_save_users" >Save</button>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
            
            <c:if test="${users_lst!=null }">  <!-- no search -->
                <h6 style="font-weight: bold;">Users<a href="${root}/report?action=get_per_cat_acc" target="blank" class="btn btn-primary ml-3">Print</a> </h6>
            </c:if>

            <div class="row mt-4">
                <h2>Lists of Users</h2>
                
                <table class="table table-bordered table-striped data_table table-hover " id="data_table">
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Gender</th>
                        <th>Telephone</th>
                        <th>Categories</th>
                        <th>Delete</th>
                        <th>Update</th>


                    </tr>
                    <c:forEach items="${get_per_cat_acc}" var="person">
                        <tr>
                            <td>${person.name}</td>
                            <td>${person.surname}</td>
                            <td>${person.gender}</td>
                            <td>${person.phone_number}</td>
                            <td>${person.acc_name}</td>

                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_user"  data-bind="${person.person_id}">delete</a></td>
                            <td><a href="#" style="color:black; font-weight: bolder;" class="link_update_users"  data-bind="${person.person_id}">edit</a></td>

                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
    <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
    <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->
    <!--<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>-->
    <script src="${root}/js/bootstrap.bundle.min.js"></script>
    <script src="${root}/js/myscripts.js" type="text/javascript"></script>
    <%@include file="../forms/footer_menu.jsp" %>
</body>
</html>
