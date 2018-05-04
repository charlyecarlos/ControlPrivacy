<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<%@page import="java.util.List"%>
<%@page import="services.ServiceImage" %>
<%@page import="domain.Image" %>
<%@page import="util.Fecha" %>
<html xmlns='http://www.w3.org/1999/xhtml' lang='en' xml:lang='en'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'></link>
	<link rel="stylesheet" type="text/css" href="css/table.css"></link>

	<link href="https://fonts.googleapis.com/css?family=Ultra" rel="stylesheet"></link>
	
	<!--titulo-->
	<title>Delete Analyse</title>
</head>
<body>
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->
	
	<div class="container">
		<div class="col-lg-12">
			<%List<String> metadata=(List<String>)request.getAttribute("Metadata");
			if(metadata!=null){%>
				<h1>Delete metadata</h1>
				<br></br>
				<table class="table table-hover col-lg-12 text-left">
					<tbody>
				 	<%for(String meta:metadata){%>
			 				<tr>
								<td class="col-lg-2 col-md-4"><b><%=meta %></b></td>
			 				</tr>
					<%	}%>
					</tbody>
				</table>
			<%}else
				response.sendRedirect("http://localhost:8080/ControlPrivacy/cleanMeta.html");%>					
		</div>
	</div> 
	
	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->
</body>
</html>