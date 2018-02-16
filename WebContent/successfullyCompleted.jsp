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
	<script type='text/javascript' src='js/inputFile.js'></script>
	<link rel='stylesheet' type='text/css' href='css/successfullyCompleted.css'>
	<script type='text/javascript' src='js/downloadWatermark.js'></script>
	

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
			<p>If the download does not start directly <a id="download") href='<%=request.getAttribute("image")%>' download>click here</a></p>
		</div>
		<br></br>
		<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">	
			<img src="<%= request.getAttribute("image") %>"/>
		</div>
	</div>
	<%
//		//	DOWNLOAD IMAGE
//		File file=new File((String) request.getAttribute("image"));
//	    response.setContentType("application/octet-stream");
//	    response.setHeader("Content-Disposition","attachment;filename="+file.getName());
//	    FileInputStream fileIn = new FileInputStream((String) request.getAttribute("image"));
//	    ServletOutputStream out = response.getOutputStream(); 
//	    byte[] outputByte = new byte[(int)file.length()];
//		//copy binary contect to output stream
//	    while(fileIn.read(outputByte, 0, (int)file.length()) != -1){
//	    response.getOutputStream().write(outputByte, 0, (int) file.length());
//		out.write(outputByte, 0, (int)file.length());
//	    }
//	    fileIn.close();
	%>

	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->

</body>
</html>