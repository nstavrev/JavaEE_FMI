<%@ include file="header.jsp" %>
<body>

	<div id="wrapper">
		<%@ include file="navTopLinks.jsp"%>
		<div id="index-container" class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="issues" class="huge"></div>
									<div>Issues!</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<a class="clearfix" href="issues.jsp"> <span
								class="pull-left">View All</span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="projects" class="huge"></div>
									<div>Projects</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<a class="clearfix" href="projects.jsp"> <span
								class="pull-left">View All</span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
							</a>
						</div>
					</div>
				</div>
				<% if(request.isUserInRole("Administrator")) { %>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-user fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="users" class="huge"></div>
									<div>Users</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<a class="clearfix" href="users.jsp"> <span class="pull-left">View
									All</span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-user fa-5x"></i>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<a class="clearfix" href="register.jsp"> <span
								class="pull-left">Register new user</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
							</a>
						</div>
					</div>
				</div>
				<% } %>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Issues
						</div>
						<div class="panel-body">
							<div id="morris-donut-chart"></div>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->
	<script src="../js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="../js/plugins/morris/raphael.min.js"></script>
	<script src="../js/plugins/morris/morris.js"></script>
	<script src="../js/plugins/morris/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
		$.ajax({
			url : "../rest/issue/count",
			type : "GET",
			success : function(data){
				$("#issues").html(data);
			}
		});
		
		$.ajax({
			url : "../rest/project/count",
			type : "GET",
			success : function(data){
				$("#projects").html(data);
			}
		});
		
		<% if(request.isUserInRole("Administrator")) { %>
		$.ajax({
			url : "../rest/user/count",
			type : "GET",
			success : function(data){
				$("#users").html(data);
			}
		});
		<% }%>
		
	</script>
</body>

</html>
