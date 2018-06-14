<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.TimeImage"%>
<%@page import="services.ServiceTimeImage"%>
<%@page import="java.util.List" %>
<%@page errorPage="internalError.jsp"%>
<html xmlns='http://www.w3.org/1999/xhtml' lang='en' xml:lang='en'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

	<link rel='stylesheet' type='text/css' href='css/staticPrincipalPages.css'></link>
	<script type='text/javascript' src='js/inputFile.js'></script>
	
	<link href="https://fonts.googleapis.com/css?family=Ultra" rel="stylesheet"></link>
	<!-- TITLE -->
	<title>View Image</title>
</head>
<body>

	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp" %>
	<!-- END MENU -->

	<div class="container">
		<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
			<h1>View Image</h1>
			<br></br>
			<form method="post" action="ViewImage" enctype="multipart/form-data">
				<div class="form-group">
					<div class="input-group input-image" name="imageFile">
			    		<input type="text" class="form-control" placeholder='Choose a file...' />
						<span class="input-group-btn">
			        		<button class="btn btn-default btn-choose" type="button">Choose</button>
			    		</span>
					</div>
					<br></br>
					<select name="viewImage" class="form-control">
						<%
						ServiceTimeImage stimeimage=new ServiceTimeImage();
						List<TimeImage> timeimages=stimeimage.recoverAllTimeImage();
						for(TimeImage timeimage:timeimages){
						%>
							<option value="<%=timeimage.getMinutes()%>"><%=timeimage.getDescription()%> to expire</option>
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
		<%if(session.getAttribute("error")!=null){%>
			<div class="alert alert-danger col-lg-6 col-lg-offset-3 text-center">
				<p><%=session.getAttribute("error")%></p>
				<%session.removeAttribute("error"); %>
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