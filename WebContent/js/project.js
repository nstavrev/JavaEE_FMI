$(document).ready(function() {
	        $('#dataTables-example').dataTable({
	        	"ajax": '../rest/issue/all/' + getParameterByName("id"),
	        	"aoColumns": [
	        	              {"mData" : "title"},
	        	              {"mData" : "assignee.userName"},
	        	              {"mData" : "reporter.userName"},
	        	              {"mData" : "dueDate"},
	        	              {"mData" : "status.name"},
	        	              { "mRender" : function(data, a, row) {
	        	            	  console.log(row);
	        	            		  return "<a href='issue.xhtml?id=" + row.id + "' class='btn btn-default'>Review</a>";
	        	              	} 
	        	              }
	        	        ]
	        });
	    });
	    
	    $.ajax({
			url : "../rest/project/id/" + getParameterByName("id"),
			type : "GET",
			success : function(data){
				console.log(data);
				if(data){
					project = data;
					$("#title").html(data.name ? data.name : "This project has no name");
				} else {
					$("#title").html("Sorry, but such project does not exist");
				}
			
			}
		});