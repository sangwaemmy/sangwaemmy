
<%@include file="directives.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1">
        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/">
        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${root}/css/mystyles.css" rel="stylesheet">
        
        <style>
            .form-control{
                /*padding: 10px;*/
                
            }
            label{
                font-size: 14px;
            }
        </style>
    </head>
    <body style="background-color: #a6f4e8; background-image: url('../images/loginImage.jpg')">
        <%@include file="header_menu.jsp" %>
        <div class="container-sm">
            <div class="row justify-content-center " id="login" style="margin-top: 10%">

                <div class="col-sm-6  offset-sm-0 border rounded p-3 " style=" border: #3e7169 1px solid ; box-shadow: 0px 0px 5px #000; background-color: #a1e2d8;">
                    <h2 id="label" style="font-weight: bold;">Login</h2>
                    <div class="container login_frame">
                        <form action="${root}/login" method="post" >

                            <div class="form-group row mt-5">
                                <label for="name" class="col-md-3" id="label">Username</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="txt_username" autocomplete="off"  placeholder="Enter username" id="txt_username">
                                </div>
                            </div>
                            <div class="form-group row mt-3">
                                <label for="name" class="col-md-3" id="label">Password</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" name="txt_password" autocomplete="off"  placeholder="Enter password" id="txt_password">
                                </div>
                            </div>
                            <div class="form-group  mt-2">

                                <input type="submit" class="btn btn-success btn-lg float-right mr-3 mt-3 "  id="btn_login" value="Login">

                            </div>
                    </div> 
                </div>
            </div>
        </div>
        <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
        <script src="${root}/js/bootstrap.bundle.min.js"></script>
        <script src="${root}/js/myscripts.js" type="text/javascript"></script>

    </body>
</html>






