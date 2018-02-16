<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='Paginas web de principiantes' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'>
	<script type='text/javascript' src='js/inputFile.js'></script>
	

	<link href="https://fonts.googleapis.com/css?family=Ultra" rel="stylesheet"> 
	<!-- TITLE -->
	<title>View Image</title>
</head>
<body>

	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- FIN MENU -->

	<div class="container">
		<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
			<h1>View Image</h1>
			<br>
			<form method="post" action="#" enctype="multipart/form-data">
				<div class="form-group">
					<div class="input-group input-file" name="imageFile">
			    		<input type="text" class="form-control" placeholder='Choose a file...' />
						<span class="input-group-btn">
			        		<button class="btn btn-default btn-choose" type="button">Choose</button>
			    		</span>
					</div>
					<br>
					<select name="viewImage" class="form-control">
						<%for(int i=2;i<26;i+=2){%>
							<option value="<%=i%>"><%=i%> horas</option>
						<%}%>
					</select>					
				</div>
					<!-- COMPONENT END -->
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Upload</button>
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
	<!-- FIN FOOTER -->
			
	<%if(session.isNew()){%>
		<!-- COOKIE -->
		<%@ include file="snippet/cookie/cookie.jsp" %>
		<!-- END COOKIE -->
	<%}%>
</body>
</html>