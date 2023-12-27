<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<%
	int subtractedSize = 3 - loginMemberBlogVoList.size(); 
%>

<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <div class="left">
                <dl>
                    <dt>블로그 관리</dt>
                    <dd>
                        <ul>
                            <li class="on"><a href="/userSet/blog">블로그 정보</a></li>
                        </ul>
                    </dd>
                </dl>
                <dl>
                    <dt>계정 관리</dt>
                    <dd>
                        <ul>
                            <li><a href="/jdgr/member/mypage">개인정보 수정</a></li>
                        </ul>
                    </dd>
                </dl>
            </div>

            <div class="right">
                <h2>블로그 정보</h2>

                <div class="content">

                    <div class="account_setting">
                        <dl class="blog_using">
                            <dt>운영중인 블로그</dt>
                            <dd>
                                <form action="/jdgr/userSet/blog" method="post">
                                    <ul class="blog_list">
                                    	<% if(loginMemberBlogVoList != null){ %>
                                    		<% for(BlogVo blogVo : loginMemberBlogVoList){ %>
                                    			<li>
		                                            <input type="radio" id="<%= blogVo.getBlogUrl() %>" name="blog" value="<%= blogVo.getBlogUrl() %>" <% if(blogVo.getRepYn().equals("Y")){ %>checked<% } %>>
		                                            <label for="<%= blogVo.getBlogUrl() %>">
		                                                <div class="img"><img src="/<%= blogVo.getBlogImg() %>" alt=""></div>
		                                                <div class="cont">
		                                                    <div class="tit"><%= blogVo.getBlogTitle() %></div>
		                                                    <a href="/jdgr/blog/view/<%= blogVo.getBlogUrl() %>" target="_blank" title="새 창으로 이동">/jdgr/blog/view/<%= blogVo.getBlogUrl() %></a>
		                                                </div>
		                                                <div class="req">대표</div>
		                                            </label>
		                                        </li>
	                                        <% } %>
                                    	<% } %>
                                    </ul>
                                    <div class="btn_area">
                                        <button>변경사항 저장</button>
                                    </div>
                                </form>
                            </dd>
                        </dl>
                        <dl class="blog_create">
                            <dt>운영·개설</dt>
                            <dd>
                            	<% if(loginMemberBlogVoList.size() < 3){ %>
                            		<div class="gg"><span><%= subtractedSize %></span>개의 블로그를 더 운영할 수 있습니다.</div>
	                                <div class="btn_area">
	                                    <button type="submit" onclick="newBlogValidationCheck();">새 블로그 만들기</button>
	                                </div>
                            	<% } else { %>
                            		
                            		<div class="gg">더이상 블로그를 운영하실 수 없습니다.</div>
                            	<% } %>
                            </dd>
                        </dl>
                    </div>

                </div>

            </div>

        </div>

    </div>
</main>
<!-- //main -->

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>

<script>
    function newBlogValidationCheck(){
        const warningPopup = document.querySelector('.modal_bg.warning');
        const warningTitle = document.querySelector('.modal_bg.warning .modal_content strong');
        const warningContent = document.querySelector('.modal_bg.warning .modal_content span');

        // 블로그를 3개 이상 만들시
        const blogCnt = document.querySelector('.blog_create .gg span');
        if(blogCnt.innerHTML > 3){
            warningPopup.style.display = 'flex';
            warningTitle.innerHTML = '블로그는 3개까지만 생성 가능합니다.';
            warningContent.innerHTML = ' ';

            return false;
        }

        // url요청
        location.href='/jdgr/blog/create'
    }
</script>