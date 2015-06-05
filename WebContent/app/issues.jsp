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
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
							<h4>Issues</h4>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="issues">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Assignee</th>
                                            <th>Reporter</th>
                                            <th>Due Date</th>
                                            <th>Status</th>
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
	    $(document).ready(function() {
	    		 $('#issues').dataTable({
	 	        	"ajax": '../rest/issue/all',
	 	        	"aoColumns": [
	 	        	              {"mData" : "title"},
	 	        	              {"mData" : "assignee.userName"},
	 	        	              {"mData" : "reporter.userName"},
	 	        	              {"mData" : "dueDate"},
	 	        	              {"mData" : "status.name"},
	 	        	              { "mRender" : function(data, a, row) {
	 	        	            		  return "<a href='issue.jsp?id=" + row.id + "' class='btn btn-primary'>Review</a>";
	 	        	              	} 
	 	        	              }
	 	        	        ]
	 	        });
	    	
	       
	    });
	    
    </script>

</body>

</html>

