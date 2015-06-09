			var project = {};
			var projectId;
			
			function loadProjects(){
				$.ajax({
					url : "../rest/project/all",
					type : "GET",
					success : function(data){ 
						getUserRole(function(role){
							var html = "";
							data.forEach(function(project){
								var settingButton = '<button onclick="getProjectInfo(' + project.id + ')" style="margin-right : 20px;" class="btn btn-primary pull-right">Settings</button>';
								var removeButton = '<button onclick="removeProject(' + project.id + ')" style="margin-right : 20px;" class="btn btn-danger pull-right">Remove</button>';
								var issuesButton = '<a href="project.xhtml?id=' + project.id +'" class="btn btn-default pull-right">View Issues</a>';
								var newIssueButton = '<a href="newissue.xhtml?id=' + project.id + '" style="margin-right : 20px;"class="btn btn-info pull-right">Create Issue</a>';
								if(role.name == "Administrator"){
									html += 
										'<div class="row"><div class="col-lg-6">' + 
										project.name + 
										'</div><div class="col-lg-6" style="padding-bottom : 10px">' + 
										issuesButton + 
										'&nbsp' + 
										newIssueButton + 
										'&nbsp&nbsp' + 
										settingButton +
										'&nbsp&nbsp' +
										removeButton +
										'</div></div>';
								} else {
									html += 
										'<div class="row"><div class="col-lg-7">' + 
										project.name + 
										'</div><div class="col-lg-5" style="padding-bottom : 10px">' + 
										issuesButton + 
										'&nbsp' + 
										newIssueButton + 
										'</div></div>';
								}
								
							});
							$("#projects").html(html);
						});
						
					}
				});	
			}
			loadProjects();
			
			$.ajax({
				url : "../rest/user/all",
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
				url : "../rest/project/id/" + id,
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
				url : "../rest/project/remove?id=" + id,
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
				url : "../rest/project/removeProjectMember/" + project.id,
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
				url : "../rest/project/newProjectMember/" + project.id,
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(member),
				success : function(data){
					getProjectInfo(projectId);
				}
			}); 
		}