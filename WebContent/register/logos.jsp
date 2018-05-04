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
