$(function() {
	
	$.ajax({
		url : "rest/issue/donutData",
		type : "GET",
		success : function(data){
			Morris.Donut({
		        element: 'morris-donut-chart',
		        data: data,
		        resize: true
		    });
		}
	});
	
});
