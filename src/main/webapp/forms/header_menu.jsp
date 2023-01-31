 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    a{
        color: #cef0eb;
    }
</style>
<nav class="navbar navbar-expand-sm navbar-dark p-3" style="background-color: #1c6156; 
     font-family: arial;
     font-weight: bold; 
     color: #fff;">
    <a class="navbar-brand" style="font-style: idiatalic;" href="${root}/">IBAKWE</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample03">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope.userid!=null}">  <!--  if logged in-->
                <!--by category name-->
                <li class="nav-item">
                    <a class="nav-link" href="${root}/forms/admin_dashboard.jsp">Home</a>
                </li>
                <c:if test="${name=='admin'}">

                    <li class="nav-item">
                        <a class="nav-link" href="${root}/product?action=Frm_pruduct">Products</a>
                    </li>
                </c:if>
                <c:if test="${name=='admin'}">

<!--                    <li class="nav-item">
                        <a class="nav-link" href="${root}/purchase?action=get_pur_prd_prs">Purchases</a>

                    </li>-->
                </c:if>

<!--                <li class="nav-item">
                    <a class="nav-link" href="${root}/sales?action=get_sls_prd_ps">Sales</a>
                </li>-->













                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Transactions
                    </a>
                    <div class="dropdown-menu">
                        <c:if test="${name=='admin'}">
                            <a class="dropdown-item" href="${root}/purchase?action=get_pur_prd_prs">Purchases</a>
                        </c:if>
                        <a class="dropdown-item" href="${root}/sales?action=get_sls_prd_ps">Sales</a>

                    </div>
                </li>









                <c:if test="${name=='admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/damages?action=get_dam_prdct_prs">Damages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/Users?action=get_per_cat_acc">Users</a>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Debts
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${root}/debts?action=Frm_debts">Debts</a>
                            <a class="dropdown-item" href="${root}/paydebts?action=Frm_paydebts">Debts payment</a>
                            <a class="dropdown-item" href="${root}/expenses?action=Frm_expenses">Expenses</a>
                        </div>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" href="${root}/paydebts?action=daily_report">Report</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${root}/settings?action=settings">Settings</a>
                    </li>
                </c:if>

                <li class="nav-item">
                    <a class="nav-link" href="${root}/confirmlogout.jsp">Logout</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.userid==null}">
                <li class="nav-item">
                    <a class="nav-link" href="${root}/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${root}/forms/frm_login.jsp">Login</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-md-0">
            <input class="form-control" type="text" placeholder="Search">
            <input type="hidden" id="loggedin_user_cat" value="${sessionScope.name}" />
        </form>
    </div>
</nav>
