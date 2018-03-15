<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<%@page import="domain.Metadata"%>
<%@page import="java.util.List"%>
<%@page import="services.ServiceImage" %>
<%@page import="domain.Image" %>
<%@page import="util.Fecha" %>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
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
	<title>Image</title>
</head>
<body>
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->
	
	<div class="container">
		<div class="col-lg-12">
			<%List<Metadata> metadata=(List<Metadata>) request.getAttribute("Metadata");
			if(metadata!=null){%>
				<h1>File metadata</h1>
				<br></br>
				<table class="table table-hover col-lg-12 text-left">
					<tbody>
				 	<%	boolean pair=false;
				 		for(Metadata meta:metadata){
				 			if(!pair){%>
				 			<tr>
				 			<%}%>
								<td class="col-lg-2"><b><%= meta.getName() %>:</b></td>
								<td class="col-lg-4"><span class="<%=meta.canRemove()?"glyphicon glyphicon-ok text-success":"glyphicon glyphicon-remove text-danger"%>"></span> <i style="color:<%= meta.isSensitive()?"red":"black" %>"> <%= meta.getData() %></i></td>
							<%if(pair){%>
				 			</tr>
				 			<%pair=false;
				 			}else
				 				pair=true;%>
					<%	}%>
					</tbody>
				</table>
				<div class="text-right"><span class="glyphicon glyphicon-ok text-success"></span> = Can remove. &nbsp<span class="glyphicon glyphicon-remove text-danger"></span> = Can´t remove. &nbsp<i style='color:red'>Data sensitive.</i></div>
				<br>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Delete</button>
				</div>
			<%}else
				response.sendRedirect("http://localhost:8080/ControlPrivacy/cleanMeta.html");%>					
		</div>
	</div> 
	
	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->
</body>
</html>