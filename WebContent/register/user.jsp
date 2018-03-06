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
      <title>Control Privacy - user</title>
      <%@ include file="../snippet/login/bootstrap.jsp" %>
      
      <!-- Page level plugin CSS-->
      <link href="register/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
      
      <script type="text/javascript" src="js/user.js"></script>
      <script type='text/javascript' src='js/inputFile.js'></script>
   </head>
   <body class="fixed-nav sticky-footer bg-dark" id="page-top">
      <%
         User user=null;
          if(session.getAttribute("user")==null)
            response.sendRedirect("login.html");
          else{
            user=(User) session.getAttribute("user");%>
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
            <!-- Area Chart Example-->
            <div class="card mb-3 text-center">
               <div class="card-header">
                  <span class="glyphicon glyphicon-level-up"></span>Add watermark with multiple files
               </div>
               <div class="card-body col-lg-10 mx-auto" style="padding: 1rem;">
                  <form method="post" action="CreateSimpleWatermark" enctype="multipart/form-data">
                     <div class="form-group">
                        <div class="input-group input-image" name="imageFile">
                           <input type="text" class="form-control" placeholder='Choose a file...' />
                           <span class="input-group-btn">
                           <button class="btn btn-default btn-choose" type="button">Choose</button>
                           </span>
                        </div>
                        <div class="text-left" style="margin: 10px;">
                        	<input type="radio" name="tab" value="hidden" onclick="hiddenText();" checked/> Logo
							<input type="radio" name="tab" value="show" onclick="showText();" /> Text
                        </div>
                        <div id="hide" class="hide" style="display: none;">
	                        <input type="text" name="textWatermark" class="form-control" placeholder='Name of the watermark...' />    
                        </div>
                     </div>
                     <!-- COMPONENT END -->
                     <div class="form-group" style="margin-bottom: 0;">
                        <button type="submit" class="btn btn-primary">Create</button>
                        <button type="reset" class="btn btn-danger">Reset</button>
                     </div>
                  </form>
               </div>
               <div class="card-footer small text-muted"> You must add a logo to be able to put it to the watermark </div>
            </div>
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
                              <td><%=image.getUrl_redirect()%></td>
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
