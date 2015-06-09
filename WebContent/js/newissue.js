		var issue = {
			status : {}
		}
		
		var statuses;
		
		$(document).ready(function(){
			$("#dueDate").datepicker();
		});
		
		$.ajax({
			url : "../rest/project/id/" + getParameterByName("id"),
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
					window.location = "project.xhtml?id=" + getParameterByName("id")
				}
			});
		}
	