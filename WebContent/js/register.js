		$.ajax({
			url : "../rest/user/roles",
			type : "GET", 
			success : function(data) {
				data.forEach(function(role){
					$("#roles").append($('<option>', {
					    value: role.id,
					    text: role.name
					}));
				});
			}
		});
		function register() {
			var user = {
				userName : $("#username").val(),
				password : $("#password").val(),
				email : $("#email").val(),
				fullName : $("#fullName").val(),
				roles : [{
					id : $("#roles").val(),
					name : $('#roles option[value="' + $("#roles").val() + '"]').html()
				}]
			}
			
			$.ajax({
				url : "../rest/user/register",
				type : "POST",
				contentType: "application/json;charset=UTF-8",
				data : JSON.stringify(user),
				success : function(){
					window.location.replace("users.xhtml");
				}
			});
		}