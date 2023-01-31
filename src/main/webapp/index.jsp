

<%@include  file="forms/directives.jsp" %>
<html>
    <head>
        <title>Index</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v4.1.1"/>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/"/>

        <!-- Bootstrap core CSS -->
        <link href="${root}/css/bootstrap.min.css" rel="stylesheet"/>
        <%@include file="forms/header_menu.jsp" %>
         
        <style>
            * {
                box-sizing: border-box;
            }

            body {
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }

            .header {
                text-align: center;
                padding: 32px;
            }

            .row {
                display: -ms-flexbox; /* IE 10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE 10 */
                flex-wrap: wrap;
                padding: 0 4px;
            }

            /* Create two equal columns that sits next to each other */
            .column {
                -ms-flex: 50%; /* IE 10 */
                flex: 50%;
                padding: 0 4px;
            }

            .column img {
                margin-top: 8px;
                vertical-align: middle;
            }

            /* Style the buttons */
            .btn {
                border: none;
                outline: none;
                padding: 10px 16px;
                background-color: #f1f1f1;
                cursor: pointer;
                font-size: 18px;
            }

            .btn:hover {
                background-color: #ddd;
            }

            .btn.active {
                background-color: #666;
                color: white;
            }
        </style>
    </head>
    <body style="background-image: url('images/DjumaShop.jpg'); background-repeat: no-repeat; ">

        <!-- Header -->
         
        
        <div style="min-height: 600px;">
            height
        </div>

        <!-- Photo Grid -->
     
                    <script>
                        // Get the elements with class="column"
                        var elements = document.getElementsByClassName("column");

                        // Declare a loop variable
                        var i;

                        // Full-width images
                        function one() {
                            for (i = 0; i < elements.length; i++) {
                                elements[i].style.msFlex = "100%";  // IE10
                                elements[i].style.flex = "100%";
                            }
                        }

                        // Two images side by side
                        function two() {
                            for (i = 0; i < elements.length; i++) {
                                elements[i].style.msFlex = "50%";  // IE10
                                elements[i].style.flex = "50%";
                            }
                        }

                        // Four images side by side
                        function four() {
                            for (i = 0; i < elements.length; i++) {
                                elements[i].style.msFlex = "25%";  // IE10
                                elements[i].style.flex = "25%";
                            }
                        }

                        // Add active class to the current button (highlight it)
                        var header = document.getElementById("myHeader");
                        var btns = header.getElementsByClassName("btn");
                        for (var i = 0; i < btns.length; i++) {
                            btns[i].addEventListener("click", function () {
                                var current = document.getElementsByClassName("active");
                                current[0].className = current[0].className.replace(" active", "");
                                this.className += " active";
                            });
                        }
                    </script>

                    </body>
                    <%@include file="forms/footer_menu.jsp" %>

                    <script src="${root}/js/jquery_3_5-1.min.js" type="text/javascript"></script>
                    <script src="${root}/js/bootstrap.bundle.min.js"></script>
                    <script src="${root}/js/myscripts.js" type="text/javascript"></script>
                    <script src="${root}/js/jquery-2.1.3.min.js" type="text/javascript"></script>
                    <script src="${root}/js/ui_scripts/jquery-ui.min.js" type="text/javascript"></script>

                    </body>
                    </html>