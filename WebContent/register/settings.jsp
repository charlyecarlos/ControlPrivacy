<!DOCTYPE html>
<%@page import="recursos.Position"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.User"%>
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
         
         
<script src="register/vendor/jquery/jquery.min.js"></script>
<script type='text/javascript' src='js/inputFile.js'></script>

<!-- Area Chart Example-->
<div class="card mb-3 text-center">
	<div class="card-header">
		<span class="glyphicon glyphicon-level-up"></span>Change password
	</div>
	<div class="card-body col-lg-10 mx-auto" style="padding: 1rem;">

		<form method="post" action="CreateMultipleWatermark" enctype="multipart/form-data">
			<div class="form-group">
				<div class="form-group row">
					<label for="example-text-input" class="col-3 col-form-label">-Old password: </label>
				  	<div class="col-9">
				    	<input class="form-control" type="password" name="lastPassword" placeholder="old password">
				  	</div>
				</div>
				<div class="form-group row">
				  	<label for="example-search-input" class="col-3 col-form-label">-New password: </label>
				  	<div class="col-9">
				    	<input class="form-control" type="password" name="newPassword" placeholder="new password">
				  	</div>
				</div>
				<div class="form-group row">
				  	<label for="example-email-input" class="col-3 col-form-label">-Repeat new password: </label>
				  	<div class="col-9">
				  		<input class="form-control" type="password" name="repeatPassword" placeholder="repeat new password">
				  	</div>
				</div>
			</div>
			<!-- COMPONENT END -->
			<div class="form-group" style="margin-bottom: 0;">
				<button type="submit" class="btn btn-primary">Change password</button>
			</div>
		</form>
	</div>
	<%
		if (request.getAttribute("error") != null) {
	%>
	<div class="alert alert-danger col-lg-6 col-lg-offset-3 text-center">
		<p><%=request.getAttribute("error")%></p>
	</div>
	<%
		}
	%>
	<div class="card-footer small text-muted">End change password</div>
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
         <%@ include file="../snippet/util/bootstrapRegister.jsp" %>
      </div>
      <%}%>
   </body>
</html>
