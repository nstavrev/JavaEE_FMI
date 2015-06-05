<%@ include file="header.jsp" %>
<body>

	<div id="wrapper">
		
		<!-- Navigation -->
		<%@ include file="navigation.jsp" %>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Registration</h3>
						</div>
						<div class="panel-body">
							<fieldset>
								<div class="form-group">
									<input id="username" class="form-control" placeholder="Username"
										type="text" autofocus>
								</div>
								<div class="form-group">
									<input id="password" class="form-control"
										placeholder="Password" name="password" type="password">
								</div>
								<div class="form-group">
									<input id="email" class="form-control"
										placeholder="Email" type="text">
								</div>
								<div class="form-group">
									<input id="fullName" class="form-control"
										placeholder="Full Name" type="text">
								</div>
								<div class="form-group">
									<label>Role</label>
									<select id="roles" class="form-control">
									</select>
								</div>
								<button onclick="register()"
									class="btn btn-lg btn-primary btn-block">Register</button>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->
	<script src="../js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../js/sb-admin-2.js"></script>

	<script type="text/javascript">
		$.ajax({
			url : "../rest/user/roles",
			type : "GET", 
			success : function(data) {
				data.forEach(function(role){
					$("#roles").append($('<option>', {
					    value: role.id,
					    text: role.name
					}));
				});
			}
		});
		function register() {
			var user = {
				userName : $("#username").val(),
				password : $("#password").val(),
				email : $("#email").val(),
				fullName : $("#fullName").val(),
				roles : [{
					id : $("#roles").val(),
					name : $('#roles option[value="' + $("#roles").val() + '"]').html()
				}]
			}
			
			$.ajax({
				url : "../rest/user/register",
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(user),
				success : function(){
					window.location.replace("users.jsp");
				}
			});
		}
	</script>

</body>

</html>

