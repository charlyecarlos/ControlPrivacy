<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='' />

	<!-- BOOTSTRAP -->
	<%@ include file="snippet/util/bootstrap.jsp" %>
	<!-- END BOOTSTRAP -->

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'>
	<script type='text/javascript' src='js/inputFile.js'></script>
	

	<link href="https://fonts.googleapis.com/css?family=Ultra" rel="stylesheet"> 
	
	<!-- TITLE -->
	<title>Clean Meta</title>
</head>
<body>
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->

	<div class="container">
		<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
			<h1>Create watermark</h1>
			<br>
			<form method="post" action="CreateSimpleWatermark" enctype="multipart/form-data">
				<div class="form-group">
					<div class="input-group input-image" name="imageFile">
			    		<input type="text" class="form-control" placeholder='Choose a file...' />
						<span class="input-group-btn">
			        		<button class="btn btn-default btn-choose" type="button">Choose</button>
			    		</span>
					</div>
					<br>					
					<input type="text" name="textWatermark" class="form-control" placeholder='Name of the watermark...' />		
				</div>
					<!-- COMPONENT END -->
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Create</button>
					<button type="reset" class="btn btn-danger">Reset</button>
				</div>
			</form>	
		</div>
		<%if(request.getAttribute("error")!=null){%>
			<div class="alert alert-danger col-lg-6 col-lg-offset-3 text-center">
				<p><%=request.getAttribute("error")%></p>
			</div>
		<%}%>
	</div>
	

	<!-- FOOTER -->
	<%@ include file="snippet/footer/footer.jsp" %>
	<!-- END FOOTER -->
	
	<%if(session.isNew()){%>
	<!-- COOKIE -->
	<%@ include file="snippet/cookie/cookie.jsp" %>
	<!-- END COOKIE -->
	<%}%>
</body>
</html>