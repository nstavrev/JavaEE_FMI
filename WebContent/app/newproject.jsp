<%@ include file="header.jsp" %>
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
    <script src="../js/jquery-1.11.0.js"></script>
    
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
	                 
		var project = {}
		
		function createNewProject() {
			console.log(project)
			project.name = $("#projectName").val();
			$.ajax({
				url : "../rest/project/new",
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
