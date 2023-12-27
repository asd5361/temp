<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.semi.jdgr.post.vo.PostVo"%>
<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.user.blame.vo.PostBlameVo"%>

    <%@ include file="/WEB-INF/views/user/common/header.jsp" %>
     <%
//      	PostBlameVo pbo = (PostBlameVo)request.getAttribute("pbo");
     PostBlameVo pbo = (PostBlameVo)request.getAttribute("pbo");
		List<PostBlameVo> list = (List<PostBlameVo>)request.getAttribute("list");
	%>  
    

    
    
	<link rel="stylesheet" href="/jdgr/resources/user/css/blamepop.css">
	<button class="modal_open" data-target="#pop_email">신고하기</button>
	
	
	
	<!-- 레이어팝업 모달 -->
	<div id="pop_email" class="modal_bg">
	    <div class="modal_box">
	        <div class="modal_header">
	            <div class="rep_pos_pop"><h2>포스트 신고하기</h2></div>
	            <button class="modal_close">닫기</button>
	        </div>
	        <hr>
<%--                 <% --%>
<!-- // 				    // 임의의 사용자 이름 설정 -->
<!-- // 				    String fakeUserName = "1"; -->
<!-- // 					String fakeContent = "댓글내용"; -->
<!-- // 				    // 나머지 코드에서 vo.getReplyMem() 대신에 가상의 사용자 이름 사용 -->
<%-- 				%> --%>
<%--          <h5>작성자 |<%= fakeUserName %></h5>	  --%>
<!--         	<br> -->
<%--         <h5>내   용| <%=fakeContent %></h5> --%>


        <div class="writer_content">
        	<h5>작성자 | <%=pbo.getpWriterNo() %></h5>
<%-- 				<h5>작성자 | <%=pbo.getUserNo() %></h5> --%>

        	
			<h5>포스트 제목 | <%=pbo.getpTit() %></h5>
        </div>
        <hr>
        <form action ="/jdgr/user/blame/p_blamepop" method="post">
<!--         	<form action ="/jdgr/jdgr/post/detail" method="get"> -->
      	  <div class="select_reason">사유선택</div>
			<div class="modal_container">
            	<div class="reason_detail">
                	<ul>
	                	<% int i = 0 ;
// 	                		if(pbo != null){
	                		
	                          	for( PostBlameVo num : list){ %>
	                        	<li class="list">
	                                <div class="check_area">
	                                    <input type="radio" name="pBlaList" id="<%= i %>" class="report_reason"  value="">
<%-- 	                                    <%= num.getpBlaList() %> --%>
	                                    <label class="reason_content"  for="<%=i %>">
	                                    	<%= num.getpBlamerNo() %>
	                                  
	                                   	</label>
	
	                                </div>
	                            </li>
	                        
	                        <%i++; %>
	                     <%	} %> 
<%-- 	                          	<%} %> --%>
	             
<%-- 	                       <% if(pvo == null) { %> --%>
<!-- 							alert("신고 사유를 선택하세요"); -->
<%-- 						<%	} %> --%>
	             
	             
<!--                 	for( PostBlameVo num : pvo){ %> -->
<!--                 	<li class="list"> -->
<!--                         <div class="check_area"> -->
<%--                             <input type="radio" name="pBlaList" id="<%= i %>" class="report_reason"  value="<%= num.getpBlaList() %>"> --%>
<%--                             <label class="reason_content"  for="<%=i %>"> --%>
<%--                             	<%= num.getpBlamerNo() %> --%>
                          
<!--                            	</label> -->

<!--                         </div> -->
<!--                     </li> -->
                
<%--                 <%i++; } %> --%>
                   
              	  </ul>
            </div>
        </div>
        <div class="detail_reason">
            <input type="text" class="detail_content" name = "pBlaDetail" placeholder=" 세부 내용 작성">
<!--              <input type="hidden" name="pWriterNo" value = "1">  -->
<!--              <input type="hidden" name="pBlaTit" value = "댓글내용">  -->
<!--              <input type="hidden" name="pNo" value = "1">          -->
<%--             <input type="hidden" name="pWriterNo" value=<%= vo.getUserNick() %>> --%>
<%--             <input type="hidden" name="pBlaTit" value=<%= vo.getPostTitle() %>>     --%>
<%--             <input type="hidden" name="pWriterNo" value=<%= fakeUserName %>> --%>
<%--             <input type="hidden" name="pBlaTit" value=<%= fakeContent %>> --%>





            <input type="hidden" name="pWriterNo" value=<%= pbo.getpWriterNo() %>>
            <input type="hidden" name="pTit" value=<%= pbo.getpTit() %>>


<%--             <input type="hidden" name="PostNo" value = <%= pbo.getPostNo() %>> --%>
			<input type="hidden" name="PostNo" value = 8>
             
        </div>
        <div class="modal_footer">
            <button type="submit" class="modal_close btn_black">신고하기</button>
        </div>
      </form>
    </div>
</div>
<script>

function blame() { 
		//p_blamepop.jsp에서 모달 열기 
	const modal = document.getElementById('pop_email');
		modal.style.display = 'block'; 
		} 

 function submitReport() { 
 // 선택된 라디오 버튼의 값 가져오기 
  const selectedReason = document.querySelector('input[name="select"]:checked').value;
  

  // 만약 라디오 버튼이 선택되지 않은 경우, 경고 메시지를 표시하고 함수 종료  
 if (!selectedReason) { 
  alert('신고 사유를 선택하세요.'); 
  return; 
 }  
  

 // 선택된 라디오 버튼의 값 
 const selectedReasonValue = selectedReason.value; 

 // 세부 내용 가져오기 
 const detailContent = document.querySelector('.detail_content').value;  

//   // 서버로 데이터 전송  
        
//  const formData = new FormData(); 
<%--         formData.append('pNo', <%= pvo.getpostNo() %>); --%>
//   formData.append('pBlaList', selectedReasonValue); 
//  formData.append('pBlaDetail', detailContent); 
        
//  const formData = new FormData(); 
//      formData.append('selectedReason', selectedReasonValue); 
//          formData.append('detailContent', detailContent); 

   // fetch 코드 추가 
  fetch("/jdgr/user/blame/p_blamepop", {  
      method: 'POST',  
          body: formData, 
    }) 
 .then(response 
  if (!response.ok) {  
     throw new Error('서버 응답이 올바르지 않습니다.'); 
  }  
 return response.json();  
     }) 
  .then(data => {  
       const result = data.msg;  
         const isOk = result === "ok";  
         if (isOk) {  
            alert("신고 완료"); 
        } else { 
           alert("신고 오류"); 
             // 원하는 추가 작업 수행  
          }  
      })  
     .catch(error => {  
           console.error('오류 발생:', error);  
           alert('서버 통신 중 오류가 발생했습니다.');  
     });  

       // 모달 닫기  
  closePopup(); 
   }  

 function closePopup() { 
   // 모달 닫기  
 var modal = document.getElementById('pop_email');  
     modal.style.display = 'none'; 
    }  
  .then( (resp) => {return resp.json() } )  
          .then( (data) => { 
  			const result = data.msg;  
  			const isOk = result == "ok"; 
  			if(isOk){  
  				alert("신고 완료");  
  				window.idOk = true;  
  			}else{  
  				alert("신고 오류");  
  				window.idOk = false;  
  			}  
          }  
          );  
      }  
              console.log('서버 응답:', data);  
              // 성공 또는 실패에 따른 처리  
              if (data.success) {  
                  // 성공 처리  
                  closePopup();  
              } else {  
                  // 실패 처리  
                  alert('서버 오류가 발생했습니다.');  
              }  
          })  
          .catch(error => {  
              console.error('오류 발생:', error);  
              alert('서버 통신 중 오류가 발생했습니다.');  
          });  
        
          // 모달 닫기  
          closePopup();  
      }  

      function closePopup() {  
          // 모달 닫기  
          var modal = document.getElementById('pop_email');  
          modal.style.display = 'none';  
      }  
   </script>   
