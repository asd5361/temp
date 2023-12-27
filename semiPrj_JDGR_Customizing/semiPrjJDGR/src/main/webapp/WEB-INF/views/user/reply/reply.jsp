<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
			
// 		//ajax 를 이용하여 댓글 목록 조회
// 		function getReplyList(refNo){
// 			fetch("/app99/board/reply/list?no=1")
// 			.then( ( resp )=>{ return resp.json() } )
// 			.then( ( data )=>{ console.log(data); } )
// 			.catch( ()=>{ alert("댓글 불러오기 실패..."); });
// 		}
		
// 		//댓글 목록들을 화면에 보여주기
// 		function setReplyArea(){
// 			const divTag = document.querySelector("#replyArea");
// 			const replyVoList = getReplyList(1);	// 변수를 이용해서 바꿔줄 예정 ...
// 			divTag.innerHTML = replyVoList;
// 		}
		
	//ajax 를 이용하여 댓글 목록 조회
		function getReplyList(refNo){
			fetch("/app99/board/reply/list?no=1")
			.then( ( resp )=>{ return resp.json() } )	
			.then( ( data )=>{ console.log(data); } )
			.catch( ()=>{ alert("댓글 불러오기 실패..."); });
		}
		
		getReplyList(1);  //여기 전달하는 숫자는 변수로 처리해야하나, 일단 그냥 1로 ㄱㄱ
	</script>
</body>
</html>