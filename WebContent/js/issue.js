
function getIssueById(id, callback){
	$.ajax({
		url : "	../rest/issue/id/" + id,
		type : "GET",
		success : function(data){
			callback(data);
		}
	}); 
}

function loadIssueDataForAdmin(callback) {
	$.ajax({
		url : "issueDataAdmin.html",
		type : "GET",
		success : function(html){
			callback(html);
		}
	})
}

function loadIssueDataForUser(callback){
	$.ajax({
		url : "issueDataUser.html",
		type : "GET",
		success : function(html){
			callback(html);
		}
	})
}