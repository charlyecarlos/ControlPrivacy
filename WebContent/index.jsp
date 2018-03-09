<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'
'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<%@page import="domain.Canvas_files"%>
<%@page import="services.ServiceStatistic_file"%>
<%@page import="util.Fecha"%>
<%@page import="domain.Statistics_index"%>
<%@page import="services.ServiceStatistics_index"%>
<%@page import="domain.User"%>
<%@page import="java.util.List"%>
<%@page errorPage="internalError.jsp"%> 

<html xmlns='http://www.w3.org/1999/xhtml' lang='en' xml:lang='en'>
<head>
	<meta name='author' content='Carlos Campos' />
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name='copyright' content='Carlos Campos' />
	<meta name='description' content='index' />
	
	<%@ include file="snippet/util/bootstrap.jsp"%>
	
	<link rel="stylesheet" href="css/footer-distributed-with-address-and-phones.css"></link>
	<!-- Custom styles for this template -->
	<link href="css/index.css" rel="stylesheet"></link>
	
	<link href="https://fonts.googleapis.com/css?family=Niconne|Special+Elite|Lobster" rel="stylesheet"></link>

	<!--titulo-->
	<title>Control Privacy</title>
</head>
<body>
	<!-- MENU -->
	<%@ include file="snippet/header/header.jsp"%>
	<!-- END MENU -->

	<header>
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active gradient-purple">
					<div class="carousel-caption">
						<h1>Register to upload up to 20 files at once.</h1>
						<p>If you register, you will have the ability to upload up to
							20 images at the same time.</p>
					</div>
				</div>
				<div class="item gradient-purple">
					<div class="carousel-caption">
						<h1>Carousel in a container</h1>
						<p>This is a demo for the Bootstrap Carousel Guide.</p>
					</div>
				</div>
				<div class="item gradient-purple">
					<div class="carousel-caption">
						<h1>Carousel in a container</h1>
						<p>This is a demo for the Bootstrap Carousel Guide.</p>
					</div>
				</div>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</header>

	<section class="gradient-white">
		<article class="col-lg-10 col-lg-offset-1">
			<h2 class="col-lg-12 text-center"><i class="fa fa-bar-chart"></i> Statistics</h2>
	        <div class="card col-lg-6">
	            <div class="card-body">
	            	<canvas id="myPieChart" width="100%" height="100%"></canvas>
	            	<script src="register/vendor/chart.js/Chart.js"></script>
					<script>
						new Chart(document.getElementById("myPieChart"), {
						    type: 'pie',
						    data: {
						      labels: [
								<%
								ServiceStatistics_index  sStatistics_index=new ServiceStatistics_index();
								List<Canvas_files> statistics_files=sStatistics_index.readTypeFiles();
								
								boolean first=true;
								boolean exit=false;
								int i=0;
					        	for(;i<statistics_files.size()&&!exit;i++){
					        		if(i!=6){
						        		if(first)
						        			first=false;
						        		else{%> 
						        			,
						        		<%}
						        		%>
						        		<%='"'+statistics_files.get(i).getType_file()+'"'%>
					        	<%	}else
						        		exit=true;
					        	}
					        	if(i>=6){%>
					        		,"Other"
					        	<%}%>
						    	],
						      datasets: [{
						        label: "Type",
						        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
						        data: [
						        <%
						        i=0;
								int sum=0;
								first=true;
						        for(Canvas_files cf:statistics_files){
							        if(i!=6){
							        	if(first)
							        		first=false;
							        	else{%> 
							        		,
							        	<%}
							        	i++;%>
							        	<%=cf.getCont()%>
							        <%}else
					        			sum+=cf.getCont();
					        	}
						        if(i==6){%>
						        	<%=","+sum%>
						        <%}%>
						        ]
						      }]
						    },
						    options: {
						      title: {
						        display: true,
						        text: 'Type of files scanned.'
						      }
						    }
						});
					</script>
	            </div>
	            <br></br>
	            <div class="card-footer small text-center text-muted">Updated Today</div>
	        </div>
	        <div class="card col-lg-6">
	          	<div class="card-body">
	          		<canvas id="line-chart" width="100%" height="100%"></canvas>
	            	<script src="register/vendor/chart.js/Chart.js"></script>
					<script>
						<%
						List<Statistics_index> statistics=sStatistics_index.readStatisticsUser();
						
						%>
						new Chart(document.getElementById("line-chart"), {
						  type: 'line',
						  data: {
						    labels: [
						    	<%first=true;
					        	for(Statistics_index si:statistics){
					        		if(first)
					        			first=false;
					        		else{%> 
					        			,
					        		<%}%>
					        		<%='"'+Fecha.nameOfMonth(si.getMonth())+" "+si.getYear()+'"'%>
					        	<%}%>
				        	],
						    datasets: [{
						        data: [
						        	<%first=true;
						        	sum=0;
						        	for(Statistics_index si:statistics){
						        		if(first)
						        			first=false;
						        		else{%> 
						        			,
						        		<%}%>
						        		<%=sum+=si.getCont()%>
						        	<%}%>
						        ],
						        label: "Users",
						        borderColor: "#3e95cd",
						        fill: false
						      }, {
						        data: [
						        <%statistics=sStatistics_index.readStatistics("ViewImage");
						        first=true;
					        	sum=0;
					        	for(Statistics_index si:statistics){
					        		if(first)
					        			first=false;
					        		else{%> 
					        			,
					        		<%}%>
					        		<%=sum+=si.getCont()%>
					        	<%}%>],
						        label: "Images upload",
						        borderColor: "#8e5ea2",
						        fill: false
						      }, {
							    data: [
							    <%statistics=sStatistics_index.readStatistics("Watermark");
								first=true;
						       	sum=0;
						     	for(Statistics_index si:statistics){
						        	if(first)
						        		first=false;
						        	else{%> 
						        		,
						        	<%}%>
						        	<%=sum+=si.getCont()%>
						        <%}%>
						        ],
							    label: "Watermark",
							    borderColor: "#FFFF33",
							    fill: false
							  }, {
								data: [
								<%statistics=sStatistics_index.readStatistics("FileAnalyse");
								first=true;
								sum=0;
								for(Statistics_index si:statistics){
									if(first)
								    	first=false;
								   	else{%> 
							    		,
							    	<%}%>
					        	<%=sum+=si.getCont()%>
						        <%}%>
						        ],
								label: "File Analyse",
								borderColor: "#FF99FF",
								fill: false
							  }
						    ]
						  },
						  options: {
						    title: {
						      display: true,
						      text: 'Users and Images upload'
						    }
						  }
						});
					</script>
	            </div>
	            <br></br>
            	<div class="card-footer small text-center text-muted">Updated Today</div>
        	</div>
        </article>
	</section>
	<section class="gradient-blue">
		<article class="col-lg-10 col-lg-offset-1"></article>
	</section>

	<link rel="stylesheet" href="css/demo.css">
	
	<footer class="footer-distributed">
		<div class="footer-left">
			<h3>Company<span>logo</span></h3>
			<p class="footer-links">
				<a href="#">Home</a> · <a href="#">About us</a> · <a href="#">Faq</a> · <a href="#">Contact</a>
			</p>
			<p class="footer-company-name">Control Privacy &copy; 2018</p>
		</div>
		<div class="footer-center">
			<div>
				<i class="fa fa-map-marker"></i>
				<p>
					<span>21 Revolution Street</span> Paris, France
				</p>
			</div>
			<div>
				<i class="fa fa-phone"></i>
				<p>+34 666 66 66 66</p>
			</div>
			<div>
				<i class="fa fa-envelope"></i>
				<p>
					<a href="mailto:support@controlprivacy.net">support@controlprivacy.net</a>
				</p>
			</div>
		</div>
		<div class="footer-right">
			<p class="footer-company-about">
				<span>About the company</span>This page is based on control of privacy 
				and copyright of your files. The data control is the most important for us.
			</p>
			<div class="footer-icons">
				<a href="#"><i class="fa fa-facebook"></i></a> 
				<a href="#"><i class="fa fa-twitter"></i></a>
				<a href="https://es.linkedin.com/in/carlos-campos-gil-2b7624129"><i class="fa fa-linkedin"></i></a>
				<a href="https://github.com/charlyecarlos"><i class="fa fa-github"></i></a>
			</div>
		</div>
	</footer>

	<%if (session.isNew()) {%>
		<!-- COOKIE -->
		<%@ include file="snippet/cookie/cookie.jsp"%>
		<!-- END COOKIE -->
	<%}%>
</body>
</html>