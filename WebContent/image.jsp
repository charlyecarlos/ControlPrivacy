<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<%@page import="services.ServiceImage" %>
<%@page import="domain.Image" %>
<%@page import="util.Fecha" %>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='Paginas web de principiantes' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'>
	
	<link rel="stylesheet" type="text/css" href="css/viewImage.css">

	<!--titulo-->
	<title>Image</title>
</head>
<body>
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->
	
	<div class="container">
		<div class="col-lg-2">
		</div>
		<div class="col-lg-8">
			<%	String img=(String) request.getParameter("img");	// NO TERMINADO
			 	if(img!=null){
					ServiceImage simage=new ServiceImage();
					Image image= simage.recoverImage(new Image(img));
					boolean expiration=false;
					if(image.getExpiration_date().getTime()<Fecha.fechaActual().getTime())
						expiration=true;
					if(image.getUrl_image()!=null && !expiration){%>
						<a href="<%=image.getUrl_image()%>" title="click here to see the full sized image">
							<img src="<%=image.getUrl_image()%>" alt="" />
						</a>
			<%		}else{%>
						<h1>The image has expired or does not exist.</h1>
			<%		}
				}else
					response.sendRedirect("http://localhost:8080/ControlPrivacy/viewImage.jsp");
			%>
					
		</div>
		<div class="col-lg-2">
		</div>
	</div>
	
	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->
</body>
</html>