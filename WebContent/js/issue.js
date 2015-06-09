
function getIssueById(id, callback){
	$.ajax({
		url : "	../rest/issue/id/" + id,
		type : "GET",
		success : function(data){
			callback(data);
		}
	}); 
}

function loadIssueDataForAdmin(callback) {
	$.ajax({
		url : "issueDataAdmin.html",
		type : "GET",
		success : function(html){
			callback(html);
		}
	})
}

function loadIssueDataForUser(callback){
	$.ajax({
		url : "issueDataUser.html",
		type : "GET",
		success : function(html){
			callback(html);
		}
	})
}

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
		getIssueById(getParameterByName("id"), function(data){
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
						window.location.replace("issues.xhtml");
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
						window.location.replace("issues.xhtml");
					}
				});
			}
		}
	});
	
	function getComments() {
		$.ajax({
			url : "../rest/issue/comments/" + getParameterByName("id"),
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
	