/**
 *  Join Form에서 검증
 */

 $("#userName").blur(idDuplicateCheck)
 
 function idDuplicateCheck() {
	 $.ajax({
		 type : 'GET',
		 url : './idDuplicateCheck',
		 data : {
			 userName : $('#userName').val()
		 },
		 
		success : function(result) {
			console.log(result)
			if(result) {
				console.log("사용가능한 ID")
			}else {
				console.log("중복 ID")	
			}
		},
		error :function() {
			console.log("error")
		}
		
	 })
 }
 
 $("#passwordCheck").blur(pwCheck) 
	 function pwCheck() {
		if($("#passwordCheck").val() == $("#password").val()) {
			console.log("비밀번호가 일치합니다")
		}else {
			console.log("비밀번호가 일치하지 않습니다")
		}
		
	 }
 