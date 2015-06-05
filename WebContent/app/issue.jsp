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
                    <h2 id="issueTitle" class="page-header"></h2>
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
                                		<div id="issueData">
                                        <!-- <div class="form-group">
                                            <label>Title</label>
                                            <input id="title" class="form-control" placeholder="Enter title">
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
                                        </div> -->
                                       </div>
                                        <div class="form-group">
                                        	<label>Status</label>
                                        	<select id="statuses" class="form-control">
                                        		
                                        	</select>
                                        </div>
                                        <button type="button" onclick="editIssue()" class="btn btn-default">Edit</button>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-lg-12">
                                	<div id="alert" class="form-group">
	                                	
                                	</div>
                                	<div class="form-group">
                                		<h4>Add Comment</h4> 
                                	</div>
                                	<div class="form-group">
										<textarea id="comment" rows="5" class="form-control" placeholder="Type your comment here"></textarea>
                                	</div>
                                	<div class="form-group"> 
                                		<button onclick="addComment()" class="btn btn-primary pull-right">Save</button>
                                	</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                	<div class="form-group">
                                		<h4>Comments</h4> 
                                	</div>
                                </div>
                            </div>
                            <div id="comments">
	                            
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
	
	<script src="../js/user.js"></script>
	
	<script src="../js/issue.js"></script>
	
	<script type="text/javascript">
	
		var issue = {
			status : {}
		}
		
		
		$.ajax({
			url : "../rest/issue/statuses",
			type : "GET",
			success : function(data) {
				data.forEach(function(status){
					$("#statuses").append($('<option>', {
					    value: status.id,
					    text: status.name
					}));
				});
			}
		});
		
		
		var editIssue = undefined;
		
		getUserRole(function(role){
			getIssueById('<% out.print(request.getParameter("id")); %>', function(data){
				issue = data;
				if(role.name == "Administrator"){
					loadIssueDataForAdmin(function(html){
						$("#issueData").html(html);
						$("#projectName").html(data.project.name);
						$("#issueTitle").html("Issue " + data.id);
						$("#title").val(data.title);
						$("#description").val(data.description);
						$("#dueDate").val(data.dueDate);
						$("#assignee").val(data.assignee.userName);
						$("#statuses").val(data.status.id);
						$("#dueDate").datepicker(); 
					});
				} else {
					loadIssueDataForUser(function(html){
						$("#issueData").html(html);
						$("#title").html(data.title);
						$("#description").html(data.description);
						$("#dueDate").html(data.dueDate);
						$("#assignee").html(data.assignee.userName);
						$("#statuses").val(data.status.id);
					});
				}
				
			});

			if(role.name == "Administrator"){
				editIssue = function() {
					issue.title = $("#title").val();
					issue.description = $("#description").val();
					issue.dueDate = $("#dueDate").val();
					issue.status.id = $("#statuses").val();
					$.ajax({
						url : "../rest/issue/edit",
						type : "POST",
						contentType: "application/json;charset=UTF-8",
						data : JSON.stringify(issue),
						success : function(data){
							window.location.replace("issues.jsp");
						}
					});
				}
			} else {
				editIssue = function(){
					issue.status.id = $("#statuses").val();
					$.ajax({
						url : "../rest/issue/changeStatus",
						type : "POST",
						contentType: "application/json;charset=UTF-8",
						data : JSON.stringify(issue),
						success : function(data){
							window.location.replace("issues.jsp");
						}
					});
				}
			}
		});
		
		function getComments() {
			$.ajax({
				url : "../rest/issue/comments/<% out.print(request.getParameter("id")); %>",
				type : "GET",
				success : function(data) {
					$("#comments").html("");
					data.forEach(function(comment){
						var html = "";
						html += '<div class="row"><div class="col-lg-12">';
						html += '<div class="panel panel-default">';
						html += '<div class="panel-body">';
						html += '<p id=' + comment.id + '>' + '</p>';
						html += '</div>';
						html += '<div class="panel-footer">';
						html += '<strong> Author </strong> ' + comment.creator.userName;
						html += '<strong> Date </strong> ' + comment.creationDate;
						html += '</div>';
						html += '</div>';
						html += '</div></div>';
						$("#comments").append(html);
						$("#" + comment.id).text(comment.content);
						/*
							<div class="row">
		                            	<div class="col-lg-12">
		                            		<div class="panel panel-default">
						                        <div class="panel-body">
						                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue.</p>
						                        </div>
						                        <div class="panel-footer">
						                            Panel Footer
						                        </div>
						                    </div>
		                            	</div>
		                            </div>
						*/
					});
					
				}
			});	
		}
		
		getComments();
		
		function addComment() {
			var comment = {
				content : $("#comment").val()
			};
			
			$.ajax({
				url : "../rest/issue/addComment/" + issue.id,
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(comment),
				success : function(data) {
					getComments();
					var alertHTML = "";
					alertHTML += '<div class="alert alert-success alert-dismissable">';
					alertHTML += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>';
					alertHTML += 'Your comment was saved successfully';
					alertHTML += '</div>';
					/*
						<div class="alert alert-success alert-dismissable">
			                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
											Your comment was saved successfully
		                            	</div>
					*/
					$("#alert").html(alertHTML);
					$("#comment").val("");
				}
			});
		}
		
	</script>
	
</body>

</html>
