<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Jquery UI css -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<%@ include file="navigation.jsp" %>
		<div id="page-wrapper">
			<div class="row">
                <div class="col-lg-12">
                    <h1 id="title" class="page-header">Projects</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
	            	<div class="col-lg-12">
	                	<a href="newproject.jsp" class="btn btn-lg btn-warning pull-right">Create Project</a>
	                </div>
            </div>
            <br/>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label>Project Name</label>
									</div>
								</div>
							</div>
							<div id="projects"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Modal  -->
	<div class="modal fade" id="project" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="title"></h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input id="members" class="form-control" placeholder="Search members"/>
					</div>
					<div class="form-group">
						<h4> Members</h4>
					</div>
					<div id="projectMembers">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /Modal -->
	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->
	<script src="js/jquery-1.11.0.js"></script>

	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

	<script type="text/javascript">
			var project = {};
			var projectId;
			
			function loadProjects(){
				$.ajax({
					url : "rest/project/all",
					type : "GET",
					success : function(data){ 
						var html = "";
						data.forEach(function(project){
							html += '<div class="row"><div class="col-lg-7">' + project.name + '</div><div class="col-lg-5" style="padding-bottom : 10px"><button onclick="getProjectInfo(' + project.id + ')" class="btn btn-primary">Settings</button>&nbsp&nbsp<a href="project.jsp?id=' + project.id +'" class="btn btn-default">View Issues</a>&nbsp<a href="newissue.jsp?id=' + project.id + '" class="btn btn-info">Create Issue</a>&nbsp&nbsp<button onclick="removeProject(' + project.id + ')" class="btn btn-danger">Remove</button></div></div>';
						});
						$("#projects").html(html);
					}
				});	
			}
			loadProjects();
			
			$.ajax({
				url : "rest/user/all",
				type : "GET",
				success : function(data){
					var arr = [];
					data.forEach(function(user){
						arr.push({ label : user.userName, value : user.userName, object : user});
					});
					console.log(arr);
					$( "#members" ).autocomplete({
						source: arr,
						select : function(event, item) {
							addMember(item.item.object);
							refreshMembers();
						},
						appendTo : "#project"
					}).data("ui-autocomplete")._renderItem = function(ul, item){
				 		return $( "<li class='page-row'></li>" )
			            .data( "item.autocomplete", item.label )
			            .append( $( "<a></a>" )[ this.options.html ? "html" : "text" ]( item.label ) )
			            .appendTo( ul );
				 		
				 	}
				}
			});
		
		function getProjectInfo(id) {
			projectId = id;
			$.ajax({
				url : "rest/project/id/" + id,
				type : "GET",
				success : function(data){
					project = data;
					$("#title").html(data.name ? data.name : "This project has no name");
					refreshMembers();
					$("#project").modal();
				}
			});
		}
		
		function removeProject(id) {
			$.ajax({
				url : "rest/admin/project/remove?id=" + id,
				type : "DELETE",
				success : function(data){
					loadProjects();
				}
			});
		}
		
		function refreshMembers() {
			var members = project.members;
			var html = "";
			members.forEach(function(member, index){
				html += "<div class='form-group'><label>" + member.userName + "</label> <button onclick='removeMember(" + index + ")' class='btn btn-danger'>Remove</button></div>";
			});
			$("#projectMembers").html(html);
		}
		
		function removeMember(index){
			$.ajax({
				url : "rest/project/removeProjectMember/" + project.id,
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(project.members[index]),
				success : function(data){
					project.members.splice(index, 1);
					refreshMembers();
				}
			});
		}
		
		function addMember(member) {
			 $.ajax({
				url : "rest/project/newProjectMember/" + project.id,
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(member),
				success : function(data){
					getProjectInfo(projectId);
				}
			}); 
		}
		
		
	</script>
	
</body>

</html>
