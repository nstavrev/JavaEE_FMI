$(function() {
	var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	$.ajax({
		url : contextPath + "/rest/issue/donutData",
		type : "GET",
		success : function(data){
			console.log(data);
			Morris.Donut({
		        element: 'morris-donut-chart',
		        data: data,
		        resize: true
		    });
		}
	});
	
});
