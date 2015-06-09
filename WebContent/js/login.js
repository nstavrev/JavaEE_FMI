function login() {
	
	var data = {
		userName : $("#username").val(),
		password : $("#password").val()
	}
	
	$.ajax({
		url : getContextPath() + "/rest/auth/login",
		type : "POST",
		contentType: "application/json;charset=UTF-8",
		data : JSON.stringify(data),
		statusCode : {
			401 : function() {
				var alertHTML = '';
				alertHTML += '<div class="alert alert-danger alert-dismissable">';
				alertHTML += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>';
				alertHTML += 'Invalid username or password';
				alertHTML += '</div>';
				$("#alert").html(alertHTML);
			},
			200 : function() {
				window.location= getContextPath() + "/faces/index.xhtml";
			}
		}
	});
}