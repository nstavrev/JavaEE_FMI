<%@ include file="header.jsp" %>

<body>

    <div id="wrapper">

        <!-- Navigation -->
		<%@ include file="navigation.jsp" %>
		
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 id="title" class="page-header">
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <!-- /.row -->
            <div class="row">
	            	<div class="col-lg-12">
	                	<a href="register.jsp" class="btn btn-lg btn-warning pull-right">Register new user</a>
	                </div>
            </div>
            <br/>
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							<h4>Users</h4>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="issues">
                                    <thead>
                                        <tr>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Full Name</th>
                                            <th>Role</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery Version 1.11.0 -->
    <script src="../js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../js/plugins/metisMenu/metisMenu.min.js"></script>
	
	 <!-- DataTables JavaScript -->
    <script src="../js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
	
    <!-- Custom Theme JavaScript -->
    <script src="../js/sb-admin-2.js"></script>
    
    <script type="text/javascript">
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
	    
    </script>

</body>

</html>

