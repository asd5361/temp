
<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.post.vo.CategoryVo"%>
<%@page import="com.semi.jdgr.post.vo.PostVo"%>
<%@page import="com.semi.jdgr.user.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/user/common/header.jsp" %>

		<% 
			PostVo postListDetailVo = (PostVo) session.getAttribute("postListDetailVo"); 
			List<CategoryVo> categoryVoList = (List<CategoryVo>)request.getAttribute("categoryVoList");
			
			List<PostVo> postVoList = (List<PostVo>)request.getAttribute("postVoList");
			PageVo pvo = (PageVo)request.getAttribute("pvo");
			
			List<PostVo> bestVoList = (List<PostVo>)request.getAttribute("bestVoList");
		%>
     	
<!-- main -->
<main>
    <div class="inner">

        <!-- 인기블로그 글 BEST10 -->
        <div class="best10_box">
            <h3 class="tit">인기블로그 글 BEST10</h3>

            <!-- swiper -->
       		<div class="best10_content">
                <div class="swiper-container">
                    <ul class="swiper-wrapper">
                        <% for(PostVo vo : bestVoList){ %>
                        <li class="swiper-slide">
                            <a href="/jdgr/post/detail?pNo=<%= vo.getPostNo()%>">                           
                                <div class="img"><img src="<%= vo.getPostImg()%>"></div>
                                <div class="txt">
                                    <div class="info">
                                        <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt="유저이미지"></span>
                                        <strong><%= vo.getUserNick()%></strong>
                                    </div>
                                    <div class="content">
                                        <strong><%= vo.getContent()%></strong>
                                        <span><%= vo.getContent()%></span>
                                    </div>
                                    <div class="util">
                                        <span class="date"><%= vo.getEnrollDate()%></span>
                                        <span class="like"><%= vo.getHeartCnt()%></span>
                                    </div>
                                </div>                           
                            </a>
                        </li>
                        <%} %> 
                    </ul>
                </div>
                <button class="swiper_btn ico_prev"></button>
                <button class="swiper_btn ico_next"></button>
            </div>

        </div>

        <!-- main 컨텐츠 -->
        <div class="main_layout">
            <!-- left -->
            <div class="main_left">
                <!-- 구독한 블로그 포스트 (없으면 안나와야함) -->
                <h3>구독블로그 새글</h3>
				
				<% if(loginMemberVo == null){ %>
					<!-- 포스트가 없을시/로그아웃상태 일시 -->
	                <div class="post_none">
	                    로그아웃 상태입니다.<br/>
	                    로그인하여 구독 블로그의 새 글을 확인해보세요.
	                </div>
				<% } else { %>
					<!-- 포스트가 없을시/로그아웃상태 일시 -->
	                <div class="post_none">
	                    구독된 블로그가 없습니다.<br/>
	                    다양한 관심사의 블로그를 구독해보세요.
	                </div>
				<% } %>
                

                <!-- 카테고리 버튼 -->
                <ul class="tab_btns category">
                    <% for(CategoryVo categoryVo : categoryVoList) {%>                        
                        <li class="on">
                            <button><%=categoryVo.getCategoryName() %></button>
                            <input type="hidden" value="<%= categoryVo.getCategoryNo()%>">   
                        </li>
                    <%} %>
                    
                </ul>

                <!-- 카테고리별 블로그 포스트 -->
                <div id="target">
                
                    <% for(PostVo postVo : postVoList) {%>
                        <div class="postbox">
                                <div class="postImg"><img src="<%= postVo.getPostImg() %>"></div>
                                <div class="postWrite"> 
                                    <div class="postWriteNick">
        <!--                                 <div class="postNickimg"><img src="../images/ico/ico_people.svg"></div> -->
                                        <div class="postNickname"><%= postVo.getUserNick() %></div>
                                    </div>    
                                    <div class="postWriteLink"><%= postVo.getContent() %></div>
                                    <div class="postWrite12">
                                        <div class="postWrite1"><img src="/jdgr/resources/user/images/ico/ico_like.svg"><%= postVo.getHeartCnt() %></div>
                                        <div class="postWrite2"><img src="/jdgr/resources/user/images/ico/ico_reply.svg"><%= postVo.getReplyCnt() %></div>
                                    </div>         
                                </div>
                        </div>
                    <%} %>
                </div>
 				<div id="target2">
 				
                    <!-- 페이징 -->
                    <div class="paging_box mt30">
                        <ul>
                        <%if(pvo.getCurrentPage() != 1) {%>
                            <li class="prev_all"><a href="/jdgr/home?pno=1" title="최신페이지로 이동"></a></li>
                            <li class="prev"><a href="/jdgr/home?pno=<%=pvo.getStartPage()-1 %>" title="이전페이지로 이동"></a></li>
                            <%} %>
                            
                            <%for(int i = pvo.getStartPage(); i<= pvo.getEndPage(); i++) {%>
                            <%if(i == pvo.getCurrentPage()) {%>   
                            <li class="on"><a href="/jdgr/home?pno=<%= i%>"><%= i%></a></li>
                            <%}else{ %>  
                            <li><a href="/jdgr/home?pno=<%= i%>"><%= i%></a></li>
                            <%} }%>
                            <li class="next"><a href="/jdgr/home?pno=<%=pvo.getCurrentPage()+1 %>" title="다음페이지로 이동"></a></li>
                            <li class="next_all"><a href="/jdgr/home?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
                        </ul>
                    </div>
 				</div>
                
                
            </div>
           
            <!-- right -->
            <div class="main_right">
                <!-- 로그인창 or 유저정보 -->
                <div class="user_content">

					<% if(loginMemberVo == null){ %>
						<!-- 로그인 전 -->
	                    <div class="before_login">
	                        <span>로그인 후 이용하실 수 있습니다.</span>
	                        <a href="/jdgr/member/login" class="login">로그인</a>
	                        <a href="/jdgr/member/join" class="join">회원가입</a>
	                    </div>
					<% } else { %>
						<!-- 로그인 후 -->
	                    <div class="after_login">
	
	                        <div class="user_info">
	                            <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt="유저이미지"></div>
	                            <div class="txt">
	                                <strong><%= loginMemberVo.getMemNick() %></strong>
	                                <% if(loginMemberBlogVo != null && loginMemberBlogVo.getVisitCnt() != null){ %>
	                                <span>오늘 <em><%= loginMemberBlogVo.getVisitCnt() %></em>명 방문</span>
	                                <% } %>
	                            </div>
	                            <a href="/jdgr/member/logout" class="btn_logout">로그아웃</a>
	                        </div>
	
							<!-- 유저가 가지고있는 블로그가 있으면  -->
							<% if(loginMemberBlogVoList != null && !loginMemberBlogVoList.isEmpty()){ %>
								<div class="btn_util">
		                            <a href="/jdgr/blog/view/<%= loginMemberBlogVo.getBlogUrl() %>" class="my_blog">내 블로그</a>
		                            <a href="/jdgr/blog/write?url=<%= loginMemberBlogVo.getBlogUrl() %>" class="my_write">글쓰기</a>
		                        </div>
							<% } else { %>
							<!-- 유저가 가지고있는 블로그가 없으면  -->
								<div class="btn_util">
		                            <a href="/jdgr/blog/create" class="my_blog">블로그 만들기</a>
		                        </div>
							<% } %>
	
	                        <div class="tab_group">
	                            <ul class="tab_btns">
	                                <li class="on"><button rel="tab_content_01">내소식</button></li>
	                                <li><button rel="tab_content_02">구독 목록</button></li>
	                                <li><button rel="tab_content_03">블로그 목록</button></li>
	                            </ul>
	                            <div class="tab_content">
	                                <div class="tab_content_01 on">
	
	                                    <div class="pop_alarm_box">
	                                        <ul>
	                                        
	                                        	<% for(AlarmVo alarmVo : alarmVoList){ %>
	                                            	<% if(alarmVo.getPostNo() != null) {%>
	                                            <li>
	                                            	<% if(alarmVo.getAlarmType().contains("댓글")) {%>
	                                                	<span class="ico_reply" ></span>
	                                            	<%} else if(alarmVo.getAlarmType().contains("공감")){%>
	                                            		<span class="ico_like" ></span>
	                                            	<%}else if(alarmVo.getAlarmType().contains("포스팅")){%>
	                                            		<span style ="background-image: url(/jdgr/resources/user/images/ico/ico_write_b.svg) "></span>
	                                            	<%}%>
	                                                <div class="txt">
                                                       <strong><%=alarmVo.getUserNick() %></strong>
                                                         님이 "<%=alarmVo.getPostTitle() %>" <%=alarmVo.getAlarmType() %>
                                                         <input type="hidden" id="postNo2" name = "postNo2" value="<%=alarmVo.getPostNo()%>">
                                                         <input type="hidden" id="alarmNo" name="alarmNo" value="<%=alarmVo.getAlarmNo()%>">
                                                   </div>
	                                            
	                                                <a href="" class="delete">삭제</a>
	                                            </li>
	                                            	<%} else{%>
	                                            <li>
	                                                <span class="ico_subscribe"></span>
	                                            	
	                                                <div class="txt" onclick="goBlog()">
	                                                    <strong><%=alarmVo.getUserNick() %></strong>
	                                                    님이 <%=alarmVo.getAlarmType() %>
                                                         <input type="hidden" id="blogUrl2" name = "blogUrl2" value="<%=alarmVo.getBlogUrl()%>">
                                                         <input type="hidden" id="alarmNo" name="alarmNo" value="<%=alarmVo.getAlarmNo()%>">
	                                                </div>
	                                            	
	                                                <a href="" class="delete">삭제</a>
	                                            </li>
	                                            	<%}%>
	                                            	
	                                            <%} %>
	                                        </ul>
	                                    </div>
	
	                                </div>
	                                <div class="tab_content_02">
	
	                                    <div class="subscription_list">
	                                        <div class="search_box">
	                                            <input type="text">
	                                            <button>검색</button>
	                                        </div>
	                                        <ul>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                            <li>
	                                                <a href="">
	                                                    <span class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></span>
	                                                    <strong>구독한닉네임</strong>
	                                                </a>
	                                            </li>
	                                        </ul>
	                                        <div class="btn_area">
	                                            <button class="prev"></button>
	                                            <button class="next"></button>
	                                        </div>
	                                    </div>   
	                                    
	                                </div>
	                                <div class="tab_content_03">
	                                    
	                                    <div class="user_blog">
	                                        <div class="tit_box">
	                                            <strong>운영중인 블로그</strong>
	                                            <a href="/jdgr/userSet/blog" class="ico_add">추가하기</a>
	                                        </div>
	                                        <ul>
	                                        	<% if(loginMemberBlogVoList != null){ %>
	                                        		<% for(BlogVo blogVo : loginMemberBlogVoList){ %>
	                                        			<li>
			                                                <a href="/jdgr/blog/view/<%= blogVo.getBlogUrl() %>" class="tit"><%= blogVo.getBlogTitle() %></a>
			                                                <a href="/jdgr/blog/write?url=<%= blogVo.getBlogUrl() %>" class="ico_write"></a>
			                                                <a href="/jdgr/blogSet/blogInfo?url=<%= blogVo.getBlogUrl() %>" class="ico_set">관리</a>
			                                            </li>
	                                        		<% } %>
	                                        	<% } %>
	                                        </ul>
	                                    </div>
	
	                                </div>
	                            </div>
	                        </div>
	
	                    </div>
					<% } %>

                </div>
            </div>
        </div>

    </div>
</main>
<!-- //main -->

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>

<script>
const divArr2 = document.querySelectorAll("div.txt");
for(let i = 0 ; i < divArr2.length; ++i){
	divArr2[i].addEventListener('click' , goPost2);
}

function goPost2(event) {
    const li2 = event.currentTarget;
    
     const id2 = li2.children[1].id;
     const postNo2 = li2.children[1].value;
     console.log("아앙"+id2);
     console.log(postNo2);

     const form2 = document.createElement("form");
     form2.method = "GET";

     if (id2 === "blogUrl2") {
         form2.action = "/jdgr/blog/view/" + postNo2;
         //
	const alarmNo2 = li2.children[2].value;
              
              fetch("/jdgr/alarm/delete?alarmNo=" + alarmNo2 )
             .then( (resp) => { return resp.json() } )
             .then( (data) => { 
                const result = data.msg;
                const isOk = result === "ok";
                if(isOk){
                   consloe.log("알람 지우기 성공");
                }else{
                   consloe.log("알람 지우기 실패");
                }
             } );
         //
         console.log("블로그");
     }else if (id2 === "postNo2") {
     	
         const input2 = document.createElement("input");
         input2.type = "hidden";
         input2.name = "pNo"; // id 값에 따라 name 설정
         input2.value = postNo2;
         form2.appendChild(input2);
         console.log("포스트");
			//
			const alarmNo2 = li2.children[2].value;
	            console.log("포스트 삭제 " +alarmNo2);
	            fetch("/jdgr/alarm/delete?alarmNo=" + alarmNo2 )
	             .then( (resp) => { return resp.json() } )
	             .then( (data) => { 
	                const result = data.msg;
	                const isOk = result === "ok";
	                if(isOk){
	                   consloe.log("알람 지우기 성공");
	                }else{
	                   consloe.log("알람 지우기 실패");
	                }
	             } );
			//
         form2.action = "/jdgr/post/detail";
     }
     
//      const input2 = document.createElement("input");
//       input2.type = "hidden";
//       input2.name = "pNo";
//       input2.value = postNo2;
      
//      form2.appendChild(input2);
     document.body.appendChild(form2);
     form2.submit();
}


//       const alarmNo = li2.children[2].value;
//      fetch("/jdgr/alarm/delete?alarmNo=" + alarmNo )
//     .then( (resp) => { return resp.json() } )
//     .then( (data) => { 
//        const result = data.msg;
//        const isOk = result === "ok";
//        if(isOk){
//           consloe.log("알람 지우기 성공");
//        }else{
//           consloe.log("알람 지우기 실패");
//        }
//     } );
///
const divArray = document.querySelectorAll(".tab_btns.category li");
    for(let j = 0 ; j < divArray.length; ++j){
       divArray[j].addEventListener('click' , selectCategory);
    }

    
    function selectCategory(event) {
    	var postBoxes = document.querySelectorAll('.postbox'); // postbox 클래스의 모든 요소 선택
        postBoxes.forEach(function(postBox) {
            postBox.remove(); // 각 postbox 요소를 삭제
        });
    	
          const cateLi = event.currentTarget;
           
           const categoryNo = cateLi.children[1].value;
           console.log(categoryNo);

           fetch("/jdgr/post/category?categoryNo="+categoryNo)
           .then((resp) => {return resp.json()})
           .then((data)=>{
            console.log(data);
            
//          const map = data;
// 			HashMap<String, Object> map = new HashMap<String, Object>();
            const cateVoList = data.postVoList;
			const pageVo = data.pvo;
			
            
			
            const targetTag = document.querySelector("#target");
            cateVoList.forEach((vo) => {
                   const postBox = document.createElement("div");
                   postBox.classList.add("postbox");

                   const postImg = document.createElement("div");
                   postImg.classList.add("postImg");
                   
                   
                   const img = document.createElement("img");
                   img.src = vo.postImg;
                   
                   
                   postImg.appendChild(img);
                   postBox.appendChild(postImg);

                   const postWrite = document.createElement("div");
                   postWrite.classList.add("postWrite");

                   const postWriteNick = document.createElement("div");
                   postWriteNick.classList.add("postWriteNick");


                   const postNickname = document.createElement("div");
                   postNickname.classList.add("postNickname");
                   postNickname.textContent = vo.userNick;
                   postWriteNick.appendChild(postNickname);
                   postWrite.appendChild(postWriteNick);

                   const postWriteLink = document.createElement("div");
                   postWriteLink.classList.add("postWriteLink");
                   postWriteLink.textContent = vo.content;
                   postWrite.appendChild(postWriteLink);

                   const postWrite12 = document.createElement("div");
                   postWrite12.classList.add("postWrite12");

                   const postWrite1 = document.createElement("div");
                   postWrite1.classList.add("postWrite1");

                   const likeImg = document.createElement("img");
                   likeImg.src = "/jdgr/resources/user/images/ico/ico_like.svg";

                   // 텍스트 내용을 담을 span 요소를 생성합니다.
                   const textSpan = document.createElement("span");
                   textSpan.textContent = vo.heartCnt;

                   // 이미지와 텍스트 요소를 postWrite1에 추가합니다.
                   postWrite1.appendChild(likeImg);
                   postWrite1.appendChild(textSpan);

                   postWrite12.appendChild(postWrite1);

                   const postWrite2 = document.createElement("div");
                   postWrite2.classList.add("postWrite2");
                   
                   const replyImg = document.createElement("img");
                   replyImg.src = "/jdgr/resources/user/images/ico/ico_reply.svg";
                   
                   const textSpan1 = document.createElement("span");
                   textSpan1.textContent = vo.replyCnt;
                   
                   postWrite2.appendChild(replyImg);
                   postWrite2.appendChild(textSpan1);
                   
                   postWrite12.appendChild(postWrite2);

                   postWrite.appendChild(postWrite12);
                   postBox.appendChild(postWrite);

                   targetTag.appendChild(postBox); // 실제로 추가할 부분
                   
               });

         	
            createPageLink(pageVo);
           });
   }

    <!-- 카테고리 페이징 -->
 	// 모든 .paging_box 요소를 선택하여 삭제하는 함수
function createPageLink(pageVo) {
 		console.log("페이지 브이오11 :::");
	console.log(pageVo);
	var pagingBoxes = document.querySelectorAll('.paging_box'); // paging_box 클래스의 모든 요소 선택
    pagingBoxes.forEach(function(pagingBox) {
        pagingBox.remove(); // 각 paging_box 요소를 삭제
    });
    const pagingBox = document.createElement('div');
    pagingBox.classList.add('paging_box');

    const ul = document.createElement('ul');
    pagingBox.appendChild(ul);

    const currentPage = pageVo.currentPage;
    const startPage = pageVo.startPage;
    const endPage = pageVo.endPage;
    const maxPage = pageVo.maxPage;

    if (currentPage !== 1) {
        createPageLinkElement(ul, '/jdgr/home?pno=1', '최신페이지로 이동', 'prev_all');
        createPageLinkElement(ul, '/jdgr/home?pno=' + (startPage - 1), '이전페이지로 이동', 'prev');
    }

    for (let i = startPage; i <= endPage; i++) {
        if (i === currentPage) {
            createPageLinkElement(ul, '/jdgr/home?pno=' + i, i, 'on');
        } else {
            createPageLinkElement(ul, '/jdgr/home?pno=' + i, i);
        }
    }

    if (currentPage < maxPage) {
        createPageLinkElement(ul, '/jdgr/home?pno=' + (currentPage + 1), '다음페이지로 이동', 'next');
        createPageLinkElement(ul, '/jdgr/home?pno=' + maxPage, '마지막페이지로 이동', 'next_all');
    }

    const target2 = document.querySelector('#target2');
    
    target2.appendChild(pagingBox);
}

function createPageLinkElement(parent, href, text, className = '') {
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.setAttribute('href', href);
    a.setAttribute('title', text);
    a.textContent = text;
    li.appendChild(a);
    if (className !== '') {
        li.classList.add(className);
    }
    parent.appendChild(li);
}

    
    
    
    
    
const swiper = new Swiper('.swiper-container', {
    direction: 'horizontal', // 수평 슬라이드
    slidesPerView: 4,
    spaceBetween: 30,
    loop: true, // 무한 루프
    navigation: {
        nextEl: '.ico_next',
        prevEl: '.ico_prev',
    },
});

</script>