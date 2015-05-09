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
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<!-- Jquery UI css -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
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
                    <h1 class="page-header">New Project</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                        
                                        <div class="form-group">
                                            <label>Project Name</label>
                                            <input id="projectName" class="form-control" placeholder="Enter project name">
                                        </div>
                                        <div class="form-group">
                                        	<input id="username" class="form-control" placeholder="Search member" />
                                        </div>
                                        
                                        <div id="members">
                                        </div>
                                        <button type="button" onclick="createNewProject()" class="btn btn-default">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
	                 
		var project = {
				members : []
		}
		
			$.ajax({
				url : "rest/user/all",
				type : "GET",
				success : function(data){
					var arr = [];
					data.forEach(function(user){
						arr.push({ label : user.userName, value : user.username, object : user});
					});
					
					$( "#username" ).autocomplete({
						source: arr,
						select : function(event, item) {
							project.members.push(item.item.object);
							refreshMembers();
						}
					}).data("ui-autocomplete")._renderItem = function(ul, item){
				 		
				 		return $( "<li class='page-row'></li>" )
			            .data( "item.autocomplete", item.label )
			            .append( $( "<a></a>" )[ this.options.html ? "html" : "text" ]( item.label ) )
			            .appendTo( ul );
				 		
				 	}
				}
			});
		
		function refreshMembers() {
			var members = project.members;
			var html = "";
			members.forEach(function(member, index){
				html += "<div class='form-group'><label>" + member.userName + "</label> <button onclick='removeMember(" + index + ")' class='btn btn-danger'>Remove</button></div>";
			});
			$("#members").html(html);
		}
		
		function removeMember(index){
			project.members.splice(index, 1);
			refreshMembers();
		}
		
		function createNewProject() {
			project.name = $("#projectName").val();
			$.ajax({
				url : "rest/project/new",
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(project),
				success : function(){
					window.location.replace("projects.jsp");
				}
			});
		}
	</script>
	
</body>

</html>
