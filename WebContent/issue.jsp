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
    <script src="js/jquery-1.11.0.js"></script>
    
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>
	
	<script src="js/functions.js"></script>
	
	<script type="text/javascript">
		var issue = {
				issue : {
					status : {}
				}
		};
		
		$("#dueDate").datepicker()
		
		$.ajax({
			url : "rest/issue/statuses",
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
				issue.issue = data;
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
					});
				} else {
					loadIssueDataForUser(function(html){
						$("#issueData").html(html);
						$("#title").html(data.title);
						$("#description").html(data.description);
						$("#dueDate").html(data.dueDate);
						$("#assignee").html(data.assignee.userName);
					});
				}
				
			});

			if(role.name == "Administrator"){
				editIssue = function() {
					issue.issue.title = $("#title").val();
					issue.issue.description = $("#description").val();
					issue.issue.dueDate = new Date($("#dueDate").val());
					issue.issue.status.id = $("#statuses").val();
					$.ajax({
						url : "rest/admin/issue/edit",
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
					issue.issue.status.id = $("#statuses").val();
					$.ajax({
						url : "rest/issue/changeStatus",
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
				url : "rest/issue/comments/<% out.print(request.getParameter("id")); %>",
				type : "GET",
				success : function(data) {
					var html = "";
					data.forEach(function(comment){
						
						html += '<div class="row"><div class="col-lg-12">';
						html += '<div class="panel panel-default">';
						html += '<div class="panel-body">';
						html += '<p>' + comment.content  + '</p>';
						html += '</div>';
						html += '<div class="panel-footer">';
						html += '<strong> Author </strong> ' + comment.creator.userName;
						html += '<strong> Date </strong> ' + comment.creationDate;
						html += '</div>';
						html += '</div>';
						html += '</div></div>';
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
					$("#comments").html(html);
				}
			});	
		}
		
		getComments();
		
		function addComment() {
			var comment = {
					comment : {
						content : $("#comment").val()
					}
			}
			$.ajax({
				url : "rest/issue/addComment/" + issue.issue.id,
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
