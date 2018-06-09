<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml' lang='en' xml:lang='en'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'></link>
	<script type='text/javascript' src='js/inputFile.js'></script>
	

	<link href="https://fonts.googleapis.com/css?family=Ultra|Berkshire+Swash" rel="stylesheet"></link>
	<!-- TITLE -->
	<title>Clean Meta</title>
</head>
<body>

	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->

	<div class="container">
		<div class="col-xs-12">
			<h1 class="login">Policy privacy</h1>
			<br/>
			<p>-We only collect data about your user to use your account and the minimum data 
				for the normal use of the application, the data will be erased every 15 days 
				(not yet implemented) and the account will not be deleted logically but really.
				<br/><br/>
				-If you continue browsing, you accept the privacy policy.
			</p>
		</div>
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