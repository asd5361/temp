<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>


<main>
	<div class="inner">
		<!-- blog_layout -->
		<div class="blog_layout">
		
		    <!-- 블로그 주인 정보 및 위젯 -->
		    <%@ include file="/WEB-INF/views/user/blog/blogSide.jsp" %>
		
		    <!-- 포스트 목록 및 내용 및 댓글 -->
		    <div class="blog_right">
		
		        <!-- 블로그 메인 -->
		        <div class="blog_main">
		
		            <% if(blogUrlVo.getBlogMain() == null){ %>
		            	<!-- 메인이 없을시 -->
		            	<div class="no_main">
			                <strong>설정된 블로그 메인이 없습니다.</strong>
			                <span>블로그 메인을 꾸며보세요!</span>
			
			                <a href="">메인 꾸미기</a>
			            </div>
	            	<% } else { %>
		            	<!-- 있을시 -->
			            <div class="blog_main_content">
			                <%= blogUrlVo.getBlogMain() %>
			            </div>
	            	<% } %>
		            
		        </div>
		
		    </div>
		
		</div>
		<!-- //blog_layout -->
	</div>
</main>

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>