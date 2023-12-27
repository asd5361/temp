<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.post.vo.PostVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>

<% 
			List<PostVo> postVoList = (List<PostVo>)request.getAttribute("postVoList");
			PageVo pageVo = (PageVo)request.getAttribute("pageVo"); 
			String searchOption = (String)request.getAttribute("searchOption");
			String searchContent = (String)request.getAttribute("searchContent");
			int listCount = (int)request.getAttribute("listCount");
		%>
       
    
    

        <!-- main -->
        <main>
            <div class="inner">
            <h3> '<%= searchContent%>'에 대한 검색결과 입니다.</h3> 
            <br>
            <h4>총 <%=listCount %>건 검색되었습니다.</h4> 
            <% for(PostVo postVo : postVoList) {%>
                <div class="postbox">
                         <div class="postImg"><%= postVo.getPostImg() %></div>
                        <div class="postWrite"> 
                            <div class="postWriteNick">
<!--                                 <div class="postNickimg"><img src="../images/ico/ico_people.svg"></div> -->
                                <div class="postNickname"><%= postVo.getUserNick() %></div>
                            </div>    
                            <div class="postWriteLink"><%= postVo.getContent() %></div>
                            <div class="postWrite12">
                                <div class="postWrite1"><img src="/jdgr/resources/user/images/ico/ico_like.svg"><%= postVo.getHeartCnt() %></div>
                                <div class="postWrite2"><img src="/jdgr/resources/user/images/ico/ico_reply.svg"><%= postVo.getReplyCnt() %></div>
                            	<input type="hidden" value="<%= postVo.getPostNo()%>">
                            </div>         
                        </div>
                       
                        
                    </div>
                    <%} %>
             <div class="paging_box">
                    <ul>
                    <%if(pageVo.getCurrentPage() != 1) {%>
                        <li class="prev_all"><a href="/jdgr/post/search?pno=1&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/post/search?pno=<%=pageVo.getStartPage()-1 %>&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>" title="이전페이지로 이동"></a></li>
                        <%} %>
                        
                        <%for(int i = pageVo.getStartPage(); i<= pageVo.getEndPage(); i++) {%>
                           <%if(i == pageVo.getCurrentPage()) {%>   
                           <li class="on"><a href="/jdgr/post/search?pno=<%= i%>&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>"><%= i%></a></li>
                           <%}else{ %>  
                           <li><a href="/jdgr/post/search?pno=<%= i%>&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>"><%= i%></a></li>
                        <%} }%>
                        <li class="next"><a href="/jdgr/post/search?pno=<%=pageVo.getCurrentPage()+1 %>&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="/jdgr/post/search?pno=<%=pageVo.getMaxPage() %>&searchOption=<%=searchOption%>&searchContent=<%=searchContent%>" title="마지막페이지로 이동"></a></li>
                    </ul>
                </div>
	</div>
        </main>
        <!-- //main -->
    
        <!-- footer -->
<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
        
<script>
const searchDivArr= document.querySelectorAll("div.postbox");
for(let x = 0 ; x < searchDivArr.length; ++x){
	searchDivArr[x].addEventListener('click' , goPost);
}

function goPost(event) {
	const div = event.currentTarget;
	
	var clickedElement = event.target;

    // closest를 사용하여 가장 가까운 postbox 요소를 찾음
    var postbox = clickedElement.closest('.postbox');

    // postbox 내부에서 input 엘리먼트를 찾음
    var inputElement = postbox.querySelector('input[type="hidden"]');

    // input 엘리먼트의 값 가져오기
    var postNo = inputElement.value;
	console.log(postNo);

	const form = document.createElement("form");
	form.action = "/jdgr/post/detail";
	form.method = "GET";

	
	
	const input = document.createElement("input");
    input.type = "hidden";
    input.name = "pNo";
    input.value = postNo;

    form.appendChild(input);
    document.body.appendChild(form);
	form.submit();
}

</script>

        