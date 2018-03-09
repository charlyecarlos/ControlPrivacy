<%@page import="sun.rmi.server.Dispatcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='Paginas web de principiantes' />

	<!-- BOOTSTRAP -->
	<%@ include file="snippet/util/bootstrap.jsp" %>
	<!-- END BOOTSTRAP -->

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'>
	<script type='text/javascript' src='js/copyImage.js'></script>

	<link href="https://fonts.googleapis.com/css?family=Ultra" rel="stylesheet"> 
	
	<!-- TITLE -->
	<title>Clean Meta</title>
</head>
<body onload="download()">
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->
	<iframe id="imageDownload" style="display:none;" src="<%=request.getAttribute("image")%>"></iframe>
	<div class="container">
		<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
			<h1>Successfully Completed</h1>
			<br>
			<p> This is the link to view the image:</p>
			<input class="form-control" id="urlImage" type="text" value="http://localhost:8080/ControlPrivacy/image.html?img=<%=request.getAttribute("image")%>" readonly/>
		</div>
	</div>
	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->

</body>
</html>