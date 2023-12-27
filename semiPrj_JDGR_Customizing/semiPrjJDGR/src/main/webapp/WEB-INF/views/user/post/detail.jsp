<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@page import="com.semi.jdgr.user.member.vo.MemberVo"%>
<%@page import="com.semi.jdgr.user.follow.vo.FollowVo"%>
<%@page import="com.semi.jdgr.heart.vo.HeartVo"%>
<%@page import="java.util.List"%>
<%@page import="com.semi.jdgr.post.vo.PostVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	// 포스트 상세보기 정부 출력
    	PostVo postDetailVo = (PostVo) session.getAttribute("postDetailVo");
    	PostVo heartCnt = (PostVo) request.getAttribute("heartCnt");
    	PostVo replyCnt = (PostVo) request.getAttribute("replyCnt");
    	
    	// 공감and구독 중복체크
    	Integer add = (Integer) request.getAttribute("add");
    	Integer del = (Integer) request.getAttribute("del");
    	
    	// 공감and구독 vo 가져와서 js 처리
//     	List<HeartVo> heartVoList = (List<HeartVo>) session.getAttribute("heartVoList");
//     	List<FollowVo> followVoList = (List<FollowVo>) session.getAttribute("followVoList");
    	
    %>

    <div class="container01">
        <div class="header01">
            <div><%= postDetailVo.getCategoryName() %></div>
            
            <div><h1><%= postDetailVo.getPostTitle() %></h1></div>

            <div class="head01">
                <div class="left01">
                    <span><img class="user" src=<%= postDetailVo.getPostImg() %> alt="유저프로필사진"></span>
                    <span class="nick"><%= postDetailVo.getUserNick() %></span>
                    <span><%= postDetailVo.getEnrollDate() %></span>
                </div> 
                
                <div class="right01">
                	<% if(loginMemberVo != null){%>
			           <% if(blogUrlVo.getMemNo().equals(loginMemberVo.getMemNo())){ %>
			           <div class="set">
	                        <div class="reply_pop">
	                            <a href=""></a>
	                            <div class="pop_content">
	                                <a href="/jdgr/post/edit?postNo=<%= postDetailVo.getPostNo() %>&&url=<%= blogUrlVo.getBlogUrl() %>" class="edit">수정하기</a>
	                                <a href="/jdgr/post/delete?postNo=<%= postDetailVo.getPostNo() %>&&url=<%= blogUrlVo.getBlogUrl() %>" class="delete">삭제하기</a>
	                            </div>
	                        </div>
	                    </div>
			           <% } %>
			        <% } %>
                    
                </div>
            </div>
            <br>
        </div>
        
            <div class="text01"><%= postDetailVo.getContent() %></div>
        
            <div class="footer01">
                <div class="foot01">
                    <div class="left02">
                        <button id="like_btn" class="btn_k un_like" onclick="clickEvent('like')">공감<%= heartCnt.getPostNo() %></button>
                        <div class="btn_area">
                        <button id="rep_btn" class="btn_k un_rep" onclick="clickEvent('reply')">댓글<%= replyCnt.getPostNo() %></button>
                        </div>
                    </div>
                    <div class="right02">
                        <button id="plus_btn" class="btn_k un_plus" onclick="{clickEvent('follow')}">구독하기</button>
                        <button class="btn_k" onclick="blame()"><img src="/jdgr/resources/user/images/ico/ico_declaration.svg" alt="신고">신고하기</button>
                    </div>    
                </div>
            </div>
    </div>

<script>

// ajax 공감
function heart(){
    
    fetch('/jdgr/post/heart', {
       method : 'get',
    })
    .then(resp => { 
    	if (!resp.ok) {
        	throw new Error('Network response was not ok');
    	}
    	return resp.json(); 
    })
  
    .then( data => {
    	console.log('공감 성공:', data);
    })
    .catch(error => {
        console.error('catch블럭 실행:', error);
    });
 }

	// ajax 구독
function follow() {
    fetch('/jdgr/post/follow', {
        method: 'GET'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Network response was not ok');
        }
        return resp.json();
    })
    .then(data => {
        console.log('구독 성공:', data);
        // 받은 데이터를 처리하여 필요한 동작 수행
    })
    .catch(error => {
        console.error('catch블럭 실행:', error);
    });
}
	
// 신고
function blame() {
	const form = document.createElement("form");
<%--     form.action = "/jdgr/user/blame/p_blamepop?pNo=<%= postDetailVo.getPostNo() %>"; --%>
    form.action = "/jdgr/user/blame/p_blamepop?pNo=7"
    form.method = "get";
    
    document.body.appendChild(form);
    
    form.submit();
}
	
// 댓글창
let ReplyList = false;

function toggleReplyList() {
const replyList = document.getElementById('replyList');
ReplyList = !ReplyList;

if (ReplyList) {
	replyList.style.display = 'none';
} else {
	replyList.style.display = 'block';
}
}

let likeClick = true;
let plusClick = true;
let repClick = true;
let repListClick = true;

// 이미지 변경	
function clickEvent(mode) {
    switch (mode) {
    
        case 'like' :
            const likeBtn = document.getElementById('like_btn');
            likeClick ? likeBtn.className = "btn_k like" : likeBtn.className = "btn_k un_like";
            likeClick = !likeClick;
            heart();
        break;
            
        case 'follow' :
            const plusBtn = document.getElementById('plus_btn');
            plusClick ? plusBtn.className = "btn_k plus" : plusBtn.className = "btn_k un_plus";
            plusClick = !plusClick;
            follow()
        break;
            
        case 'reply' :
            const repBtn = document.getElementById('rep_btn');
            repClick ? repBtn.className = "btn_k rep" : repBtn.className = "btn_k un_rep";
            repClick = !repClick;
            toggleReplyList()
        break;
            
    }
    
}

</script>


