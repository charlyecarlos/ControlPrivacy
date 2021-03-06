<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Control Privacy - Login</title>
  
  <%@ include file="../snippet/login/bootstrap.jsp" %>

  <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash" rel="stylesheet"> 
</head>

<body class="bg-light">
  <div class="container text-center">
    <div class="shadow card card-login mx-auto mt-5">
      <h1 class="card-header login">Login</h1>
      <div class="card-body">
        <form method="post" action="Validation">
        	<%if(session.getAttribute("error")!=null){%> 		
				<div class="alert alert-danger col-lg-12 text-center">
					<p style="margin-bottom:0px;"><%=session.getAttribute("error")%></p>
				</div>
        	<% session.removeAttribute("error");
        	}else if(request.getParameter("session")!=null){
        		session.invalidate();
        		session=request.getSession();
        	}%>
          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input class="form-control" name="email" type="email" aria-describedby="emailHelp" placeholder="Enter email">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input class="form-control" name="password" type="password" placeholder="Password">
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember Password</label>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="register.html">Register an Account</a>
          <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
  <br>
  <a href="index.html" class="returnToIndex"><span class="glyphicon glyphicon-arrow-left"></span>return to index</a>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
