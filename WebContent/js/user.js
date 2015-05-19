function getUserRole(callback) {
	$.ajax({
		url : "rest/user/role",
		type : "GET",
		dataType : "json",
		success : function(data) {
			callback(data);
		}
	});
}