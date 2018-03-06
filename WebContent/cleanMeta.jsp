<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml' lang='es' xml:lang='es'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='' />

	<%@ include file="snippet/util/bootstrap.jsp" %>

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
			<h1>Analyse Metadata</h1>
			<br>
			<form method="post" action="#" enctype="multipart/form-data">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">Upload File</a></li>
					<li><a data-toggle="tab" href="#url">URL</a></li>
					<li><a data-toggle="tab" href="#search">Search</a></li>
				</ul>
				<br><br>
				<div class="tab-content col-lg-12">
					<div id="home" class="tab-pane fade in active">
						<!-- COMPONENT START -->
						<div class="form-group">
							<div class="input-group input-file">
								<input type="text" name="localFile" class="form-control" placeholder='Choose a file...' />			
								<span class="input-group-btn">
									<button class="btn btn-default btn-choose" type="button">Choose</button>
								</span>
							</div>
						</div>
							<!-- COMPONENT END -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Analyse</button>
							<button type="reset" class="btn btn-danger">Reset</button>
						</div>
					</div>
					<div id="url" class="tab-pane fade">
						<div class="col-lg-12">
							<div class="input-group">
								<input type="url" class="form-control" placeholder="Search for...">
								<span class="input-group-btn">
									<button class="btn btn-primary" type="submit">Analyse</button>
								  </span>
							</div>
						</div>
					</div>
					<div id="search" class="tab-pane fade">
						<div class="col-lg-12">
							<div class="input-group">
								<input type="url" class="form-control" placeholder="Search for...">
								<span class="input-group-btn">
									<button class="btn btn-primary" type="submit">Analyse</button>
								  </span>
							</div>
						</div>
					</div>
				</div>
			</form>	
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