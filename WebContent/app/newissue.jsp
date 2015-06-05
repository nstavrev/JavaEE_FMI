<%@ include file="header.jsp" %>
<body>

    <div id="wrapper">

		<!-- Navigation -->
		<%@ include file="navigation.jsp" %>
        <div id="page-wrapper">
            <div class="row">
            	<div class="col-lg-12">
                    <h1 id="projectName" class="page-header"></h1>
                </div>
                <div class="col-lg-12">
                    <h2 class="page-header">New Issue</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                        	<form id="newIssueForm">
                            <div class="row">
                                <div class="col-lg-12">
                                        <div class="form-group">
                                            <label>Title</label>
                                            <input id="title" name="title" class="form-control" placeholder="Enter title">
                                        </div>
                                        <div class="form-group">
                                        	<label>Description</label>
                                        	<textarea id="description" rows="10" class="form-control" placeholder="Enter Description"></textarea>
                                        </div>
                                        <div class="form-group">
                                        	<label>Due Date</label>
                                        	<input id="dueDate" class="form-control" type="text" />
                                        </div>
                                        <div class="form-group">
                                        	<label>Assignee</label>
                                        	<input id="assignee" class="form-control" type="text"/>
                                        </div>
                                        <div class="form-group">
                                        	<label>Status</label>
                                        	<select id="statuses" class="form-control">
                                        		
                                        	</select>
                                        </div>
                                        <button type="button" onclick="createIssue()" class="btn btn-default">Save</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#wrapper -->

    <!-- jQuery Version 1.11.0 -->
    <script src="../js/jquery-1.11.0.js"></script>
    
	<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../js/plugins/metisMenu/metisMenu.min.js"></script>
	
	<!-- Jquery Validate -->
	<script src="../js/plugins/validate/jquery.validate.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="../js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
		var issue = {
			status : {}
		}
		
		var statuses;
		
		$(document).ready(function(){
			$("#dueDate").datepicker();
		});
		
		$.ajax({
			url : "../rest/project/id/<% out.print(request.getParameter("id")); %>",
			type : "GET",
			success : function(data){
				issue.project = data;
				$("#projectName").html(data.name)
			}
		});
		
		$.ajax({
			url : "../rest/issue/statuses",
			type : "GET",
			success : function(data) {
				statuses = data;
				statuses.forEach(function(status){
					$("#statuses").append($('<option>', {
					    value: status.id,
					    text: status.name
					}));
				});
			}
		});
		
		$.ajax({
			url : "../rest/user/all",
			type : "GET",
			success : function(data){
				var arr = [];
				data.forEach(function(user){
					arr.push({ label : user.userName, value : user.userName, object : user});
				});
				console.log(arr);
				$( "#assignee" ).autocomplete({
					source: arr,
					select : function(event, item) {
						issue.assignee = item.item.object;
					}
				}).data("ui-autocomplete")._renderItem = function(ul, item){
			 		return $( "<li class='page-row'></li>" )
		            .data( "item.autocomplete", item.label )
		            .append( $( "<a></a>" )[ this.options.html ? "html" : "text" ]( item.label ) )
		            .appendTo( ul );
			 	}
			}
		});
	
		function createIssue() {
			issue.title = $("#title").val();
			issue.description = $("#description").val();
			issue.dueDate = $("#dueDate").val();
			var statusId = $("#statuses").val();
			issue.status = {
				id : statusId,
				name : $("#statuses option[value='" + statusId + "']").text(),
				important : true
			}
			
			$.ajax({
				url : "../rest/issue/new",
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(issue),
				success : function(data){
					if(data && data.length > 0) {
						alert(data);	
					}
					window.location = "project.jsp?id=<% out.print(request.getParameter("id")); %>"
				}
			});
		}
	</script>
	
</body>

</html>
