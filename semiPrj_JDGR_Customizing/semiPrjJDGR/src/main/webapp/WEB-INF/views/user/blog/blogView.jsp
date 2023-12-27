<%-- <%@page import="com.semi.jdgr.user.blame.vo.PostBlameVo"%> --%>
<%@page import="com.semi.jdgr.user.reply.vo.ReplyVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<% 
	List<ReplyVo> replyVoList = (List<ReplyVo>) request.getAttribute("replyVoList");
// 	PostBlameVo pbo = (PostBlameVo)request.getAttribute("pbo");
// 	PostVo pbo = (PostVo)request.getAttribute("pbo");
// 	List<PostBlameVo> list = (List<PostBlameVo>)request.getAttribute("list");
%> 
<script>
// 	// 댓글창
// 	let ReplyList = false;

// 	function toggleReplyList() {
//     const replyList = document.getElementById('replyList');
//     ReplyList = !ReplyList;

//     if (ReplyList) {
//     	replyList.style.display = 'block';
//     } else {
//     	replyList.style.display = 'none';
//     }
// }

//     let likeClick = true;
//     let plusClick = true;
//     let repClick = true;
//     let repListClick = true;
    
//     // 이미지 변경	
//     function clickEvent(mode) {
//         switch (mode) {
        
//             case 'like' :
//                 const likeBtn = document.getElementById('like_btn');
//                 likeClick ? likeBtn.className = "btn_k like" : likeBtn.className = "btn_k un_like";
//                 likeClick = !likeClick;
//                 heart();
//             break;
                
//             case 'follow' :
//                 const plusBtn = document.getElementById('plus_btn');
//                 plusClick ? plusBtn.className = "btn_k plus" : plusBtn.className = "btn_k un_plus";
//                 plusClick = !plusClick;
//                 follow()
//             break;
                
//             case 'reply' :
//                 const repBtn = document.getElementById('rep_btn');
//                 repClick ? repBtn.className = "btn_k rep" : repBtn.className = "btn_k un_rep";
//                 repClick = !repClick;
//                 toggleReplyList()
//             break;
                
//         }
        
//     }
    
//     신고
//     function blame() {
//     	const form = document.createElement("form");
//         form.action = "/jdgr/user/blame/p_blamepop?pNo=?";
// //         form.action = "/jdgr/user/blame/p_blamepop?pNo="
//         form.method = "get";
        
//         document.body.appendChild(form);
        
//         form.submit();
//     }



//     // 신고
//     function blame() {
//     	const form = document.createElement("form");
// //         form.action = "/jdgr/user/blame/p_blamepop?pNo=7";

// 	   // 동적으로 pNo 값을 가져와서 설정
<%-- <%--     	const pNoValue = "<%= pbo.getpWriterNo() %>"; --%> --%>
<%-- 			const pNoValue = "<%= pbo.getUserNo() %>"; --%>
//     	form.action = "/jdgr/user/blame/p_blamepop?" + pNoValue;

//         form.method = "get";
        
//         document.body.appendChild(form);
        
//         form.submit();
//     }



    
//     // 공감
//     function heart() {
//     	const form = document.createElement("form");
//         form.action = "/jdgr/post/heart";
//         form.method = "GET";
        
//         document.body.appendChild(form);
        
//         form.submit();
//     }
    
//     // 구독
//     function follow() {
//     	const form = document.createElement("form");
//         form.action = "/jdgr/post/follow";
//         form.method = "GET";
        
//         document.body.appendChild(form);
        
//         form.submit();
//     }
    

    
  
</script>

<!-- main -->
<main>
    <div class="inner">

        <!-- blog_layout -->
        <div class="blog_layout">

            <%@ include file="/WEB-INF/views/user/blog/blogSide.jsp" %>

            <!-- 포스트 목록 및 내용 및 댓글 -->
            <div class="blog_right">

                <!-- 전체목록 -->
                <div class="b_post_list">
                    <a href="" class="tit">
                        <strong>전체보기</strong>
                        <span>4개의 글</span>
                    </a>
                    <table>
                        <colgroup>
                            <col width="*">
                            <col width="12%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">글 제목</th>
                                <th scope="col">조회수</th>
                                <th scope="col">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="paging_box">
                    </div>
                </div>

                <!-- 포스트 상세보기 -->
                <div class="b_post_detail">
					<%@ include file="/WEB-INF/views/user/post/detail.jsp" %>
                </div>

                <!-- 
                    포스트 댓글
                    댓글버튼 :: li에 reply class 추가됨 reply_inp_box class에 display block
                    댓글취소 :: li에 reply class 제거됨 reply_inp_box class에 display none
                    수정하기 :: li에 edit class 추가됨 reply_edit class에 display block / 나머지 none
                    수정취소 :: li에 edit class 제거됨 reply_edit class에 display none / 나머지 block
                    삭제하기 :: li에 delete class 추가됨 blind div태그 추가됨
                -->
                <div id="replyList" class="b_post_reply">
                    <ul>
		                <% for(ReplyVo vo : replyVoList){ %>
			                <% if(vo.getParentsNo() == null){ %>
		                        <li>
		                        	<% if(loginMemberVo != null) { %>
		                        		<% if(loginMemberVo.getMemNo().equals(vo.getReplyMem())){ %>
				                            <div class="reply_pop">
				                                <a href=""></a>
				                                <div class="pop_content">
				                                    <a href="" class="edit">수정하기</a>
				                                    <a href="/jdgr/reply/delete?replyNo=<%= vo.getReplyNo() %>&postNo=<%= vo.getPostNo() %>" class="delete">삭제하기</a>
				                                </div>
				                            </div>
		                        		<% } %>
		                        	<% } %>
		                            <div class="user_nick">
		                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
		                                <strong><%= vo.getReplyMemNick() %></strong>
		                            </div>
		                            <div class="reply_content"><%= vo.getCon() %></div>
		                            <div class="reply_edit">
                                        <form action="/jdgr/reply/edit" method="get">
                                            <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다." name="con"><%= vo.getCon() %></textarea>
                                            <div>
                                                <button type="button">취소</button>
                                                <button>수정</button>
                                            </div>
                                            <input type="hidden" name="replyNo" value="<%= vo.getReplyNo() %>">
                                            <input type="hidden" name="postNo" value="<%= vo.getPostNo() %>">
                                        </form>
		                            </div>
		                            <div class="reply_info">
		                                <span class="date"><%= vo.getWriteDate() %></span>
		                                <a href="">신고</a>
		                            </div>
		                            <% if(loginMemberVo != null){%>
		                            <div class="btn_area">
		                                <button>댓글</button>
		                            </div>
		                            	
		                            <div class="reply_inp_box">
                                        <form action="/jdgr/reply/write" method="get">
                                            <div class="user_nick">
                                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                                <strong><%= loginMemberVo.getMemNick() %></strong>
                                            </div>
                                            <div class="reply_inp">
                                                <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다." name="con"></textarea>
                                            </div>
                                            <div class="reply_footer">
                                                <div class="count">
                                                    <span>0</span>
                                                    <span>/ 3000</span>
                                                </div>
                                                <div class="reply_btn">
                                                    <button>등록</button>
                                                </div>
                                            </div>
                                            <input type="hidden" name="replyMem" value="<%= loginMemberVo.getMemNo() %>">
                                            <input type="hidden" name="replyNo" value="<%= vo.getReplyNo() %>">
                                            <input type="hidden" name="postNo" value="<%= vo.getPostNo() %>">
                                        </form>
		                            </div>
		                            <% } %>
		                        </li>
			                <% } else { %>
			                	<li class="rreply">
		                            <% if(loginMemberVo != null) { %>
		                        		<% if(loginMemberVo.getMemNo().equals(vo.getReplyMem())){ %>
				                            <div class="reply_pop">
				                                <a href=""></a>
				                                <div class="pop_content">
				                                    <a href="" class="edit">수정하기</a>
				                                    <a href="/jdgr/reply/delete?replyNo=<%= vo.getReplyNo() %>&postNo=<%= vo.getPostNo() %>" class="delete">삭제하기</a>
				                                </div>
				                            </div>
		                        		<% } %>
		                        	<% } %>
		                            <div class="user_nick">
		                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
		                                <strong><%= vo.getReplyMemNick() %></strong>
		                            </div>
		                            <div class="reply_content"><%= vo.getCon() %></div>
		                            <div class="reply_edit">
                                        <form action="/jdgr/reply/edit" method="get">
                                            <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다." name="con"><%= vo.getCon() %></textarea>
                                            <div>
                                                <button type="button">취소</button>
                                                <button>수정</button>
                                            </div>
                                            <input type="hidden" name="replyNo" value="<%= vo.getReplyNo() %>">
                                            <input type="hidden" name="postNo" value="<%= vo.getPostNo() %>">
                                        </form>
		                            </div>
		                            <div class="reply_info">
		                                <span class="date"><%= vo.getWriteDate() %></span>
		                                <a href="">신고</a>
		                            </div>
		                        </li>
			                <% } %>
		                <% } %>
                    </ul>
                    <% if(loginMemberVo != null){ %>
                        <form action="/jdgr/reply/write" method="get">
                            <div class="reply_inp_box">
                                <div class="user_nick">
                                    <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                    <strong>
                                        <%= loginMemberVo.getMemNick() %>
                                    </strong>
                                </div>
                                <div class="reply_inp">
                                    <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다." name="con"></textarea>
                                </div>
                                <div class="reply_footer">
                                    <div class="count">
                                        <span>0</span>
                                        <span>/ 3000</span>
                                    </div>
                                    <div class="reply_btn">
                                        <button>등록</button>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="replyMem" value="<%= loginMemberVo.getMemNo() %>">
                            <input type="hidden" name="postNo" value="<%= postDetailVo.getPostNo() %>">
                        </form>
                    <% } %>
                </div>

            </div>

        </div>
        <!-- //blog_layout -->

    </div>
    
   
</main>
<!-- //main -->




<script>
    // 파라미터 가져오기
    const currentUrl = window.location.href;
    const url = new URL(currentUrl);
    const urlParams = url.searchParams;
    const blogUrl = urlParams.get("url");
    const groupNo = urlParams.get("GroupNo");
    const pNo = urlParams.get("pNo");
    
    let pnumCnt = urlParams.get("pnum");

    
    function getPostList(pnum, blogurl, groupno, pno){
    	
        fetch('/jdgr/post/list?pnum=' + pnum + "&categoryNo=" + groupno + "&url=" + blogurl + "&pNo=" + pno)
//         fetch('/jdgr/post/list?pNo=' + pNo)
        .then(resp => resp.json())
        .then(data => {
            // 서버 응답 처리
            const pvo = data.pvo;
            const postVoList = data.postVoList;

            const tbody = document.querySelector('.b_post_list table tbody');

            // 기존 tr 제거
            tbody.innerHTML = '';

            //
            for (let i = 0; i < postVoList.length; i++) {
                const post = postVoList[i];
                const tr = document.createElement('tr');

                // td 추가
                const titleCell = document.createElement('td');
                titleCell.textContent = post.postTitle;
                tr.appendChild(titleCell);

                const inquiryCell = document.createElement('td');
                inquiryCell.textContent = post.inquiry;
                tr.appendChild(inquiryCell);

                const dateCell = document.createElement('td');
                dateCell.textContent = post.enrollDate;
                tr.appendChild(dateCell);

                // 클릭 이벤트 추가
                tr.onclick = function() {
                    location.href = '/jdgr/post/detail?pNo=' + post.postNo;
                };

                // tbody에 행 추가
                tbody.appendChild(tr);
            }
            
            const pagingBox = document.querySelector('.paging_box');
            const pagingUl = document.createElement('ul');

            // 이전 페이지 링크
            if (pvo.startPage < pvo.currentPage) {
                const prevLi = document.createElement('li');
                const prevLiBtn = document.createElement('a');
                prevLiBtn.href = '/jdgr/post/detail?pnum=' + (pvo.currentPage - 1) + '&&url=' + data.PblogUrl + '&&categoryNo=' + data.PgroupNo;
                prevLi.classList.add('prev');
                prevLi.appendChild(prevLiBtn);
                pagingUl.appendChild(prevLi);
            }

            // 중간 페이지 링크
            for (let i = pvo.startPage; i < pvo.endPage + 1; i++) {
                const pagingLi = document.createElement('li');
                const pagingLiBtn = document.createElement('a');
                if (pvo.currentPage === i) {
                    pagingLi.classList.add('on');
                    pagingLiBtn.href = '#';  // 현재 페이지는 링크를 비활성화
                } else {
                    pagingLiBtn.href = '/jdgr/post/detail?pnum=' + i + '&&url=' + data.PblogUrl + '&&categoryNo=' + data.PgroupNo;
                }
                pagingLiBtn.textContent = i;
                pagingLi.appendChild(pagingLiBtn);
                pagingUl.appendChild(pagingLi);
            }

            // 다음 페이지 링크
            if (pvo.endPage > pvo.currentPage) {
                const nextLi = document.createElement('li');
                const nextLiBtn = document.createElement('a');
                nextLiBtn.href = '/jdgr/post/detail?pnum=' + (pvo.currentPage + 1) + '&&url=' + data.PblogUrl + '&&categoryNo=' + data.PgroupNo;
                nextLi.classList.add('next');
                nextLi.appendChild(nextLiBtn);
                pagingUl.appendChild(nextLi);
            }

            pagingBox.appendChild(pagingUl);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
    
    if(pnumCnt === null){
        pnumCnt = 1;
    }
    getPostList(pnumCnt, blogUrl, groupNo, pNo);

    // function getReplyList(refNo){
    //     fetch("/app99/board/reply/list?refNo=" + refNo)
    //     .then( (resp) => { return resp.json() } )
    //     .then( (data) => { console.log(data) } )
    //     .catch(() => { alert("댓글불러오기 실패") });
    // }
    // getReplyList(1);
</script>
<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>