function removeUser(id) {
    		$.ajax({
    			url : "../rest/user/remove?id=" + id,
    			type : "DELETE",
    			success : function(data){
    				var table = $('#issues').DataTable();
    		        table.ajax.reload();
    			}
    		})
    	}
	    $(document).ready(function() {
	        $('#issues').dataTable({
	        	"ajax": '../rest/user/users',
	        	"aoColumns": [
	        	              {"mData" : "userName"},
	        	              {"mData" : "email"},
	        	              {"mData" : "fullName"},
	        	              {
	        	            	  "mRender" : function(data, a, row) {
	        	            		  return row.roles[0].name;
	        	            	  }
	        	              },
	        	              { "mRender" : function(data, a, row) {
	        	            	  console.log(row);
	        	            		  return "<button onclick=removeUser(" + row.id + ") class='btn btn-danger'>Remove</button>";
	        	              	} 
	        	              }
	        	        ]
	        });
	    });