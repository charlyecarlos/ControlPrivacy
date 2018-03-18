<!DOCTYPE html>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.Image"%>
<%@page import="services.ServiceImage"%>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="">
      <%@ include file="../snippet/login/bootstrap.jsp" %>
      
      <!-- Page level plugin CSS-->
      <link href="register/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
      
      <script type="text/javascript" src="js/user.js"></script>     
      <%
         User user=null;
          if(session.getAttribute("user")==null)
            response.sendRedirect("login.html");
          else{
            user=(User) session.getAttribute("user");%>
      <title>Control Privacy - <%=user.getName() %></title>
   </head>
   <body class="fixed-nav sticky-footer bg-dark" id="page-top">
      <%@ include file="../snippet/header/headerUser.jsp" %>
      <div class="content-wrapper">
         <div class="container-fluid">
            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
               <li class="breadcrumb-item">
                  <a href="#">Dashboard</a>
               </li>
               <li class="breadcrumb-item active">My Dashboard</li>
            </ol>
            
            <!-- watermark-multiple -->
            <%@ include file="../snippet/userComponent/watermarkMultiple.jsp" %>
            
            <!-- Example DataTables Card-->
            <div class="card mb-3">
               <div class="card-header">
                  <i class="fa fa-table"></i> Data ViewImage
               </div>
               <div class="card-body">
                  <div class="table-responsive">
                     <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                           <tr>
                              <th>Url Image</th>
                              <th>Date Creation</th>
                              <th>Expiration Date</th>
                              <th>Visits</th>
                           </tr>
                        </thead>
                        <tbody>
                           <%
                              ServiceImage simage=new ServiceImage();
                              List<Image> images=new ArrayList<Image>();
                              images=simage.recoverImageForUser(user);
                              for(Image image:images){%>
                           <tr>
                              <td>http://localhost:8080/ControlPrivacy/image.html?img=<%=image.getUrl_redirect()%></td>
                              <td><%=image.getDate_creation()%></td>
                              <td><%=image.getExpiration_date()%></td>
                              <td><%=image.getVisits()%></td>
                           </tr>
                           <%}%>
                        </tbody>
                     </table>
                  </div>
               </div>
               <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>
         </div>
         <!-- /.container-fluid-->
         <!-- /.content-wrapper-->
         <footer class="sticky-footer">
            <div class="container">
               <div class="text-center">
                  <small>Copyright © Control Privacy 2018</small>
               </div>
            </div>
         </footer>
         <!-- Scroll to Top Button-->
         <a class="scroll-to-top rounded" href="#page-top">
         <i class="fa fa-angle-up"></i>
         </a>
         <!-- Bootstrap core JavaScript-->
         <script src="register/vendor/jquery/jquery.min.js"></script>
         <script src="register/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
         <!-- Core plugin JavaScript-->
         <script src="register/vendor/jquery-easing/jquery.easing.min.js"></script>
         <!-- Page level plugin JavaScript-->
         <script src="register/vendor/chart.js/Chart.min.js"></script>
         <script src="register/vendor/datatables/jquery.dataTables.js"></script>
         <script src="register/vendor/datatables/dataTables.bootstrap4.js"></script>
         <!-- Custom scripts for all pages-->
         <script src="register/js/sb-admin.min.js"></script>
         <!-- Custom scripts for this page-->
         <script src="register/js/sb-admin-datatables.min.js"></script>
         <script src="register/js/sb-admin-charts.min.js"></script>
      </div>
      <%}%>
   </body>
</html>
