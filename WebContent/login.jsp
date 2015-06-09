<%@ include file="app/header.jsp"%>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">
                	<img class="img-responsive" src="https://www.sapbi.com/wp-content/themes/sapbi/images/logo-SAP.png" />
                </a>
			</div>
			<!-- /.navbar-header -->
		</nav>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Sign In </h3>
						</div>
						<div class="panel-body">
							<form role="form">
								<fieldset>
									<div id="alert" class="form-group"></div>
									<div class="form-group">
										<input id="username" name="username" class="form-control"
											placeholder="Username" type="text" autofocus>
									</div>
									<div class="form-group">
										<input id="password" class="form-control"
											placeholder="Password" name="password" type="password">
									</div>
									<button type="button" onclick="login()"
										class="btn btn-lg btn-success btn-block">Login</button>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>

	<script type="text/javascript">
		function login() {
			var data = {
					userName : $("#username").val(),
					password : $("#password").val()
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/rest/auth/login",
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(data),
				statusCode : {
					401 : function() {
						var alertHTML = '';
						alertHTML += '<div class="alert alert-danger alert-dismissable">';
						alertHTML += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>';
						alertHTML += 'Invalid username or password';
						alertHTML += '</div>';
						$("#alert").html(alertHTML);
					},
					200 : function() {
						window.location= "${pageContext.request.contextPath}/app";
					},
					complete : function(response) {
						console.log(response.statusText);
					}
				}
			});
		}
	</script>

</body>

</html>

