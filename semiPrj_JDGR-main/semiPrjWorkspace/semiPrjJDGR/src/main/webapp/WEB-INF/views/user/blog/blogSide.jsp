<%@page import="java.util.List"%>
<%@page import="com.semi.jdgr.blog.vo.GroupVo"%>
<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	// 	블로그 카테고리 정보 vo
	List<GroupVo> groupVoList = (List<GroupVo>) request.getAttribute("groupVoList");

%>

<!-- 블로그 주인 정보 및 위젯 -->
<div class="blog_left">

    <!-- 유저 정보 -->
    <div class="blog_info">
        <div class="img">
        	<img src="/<%= loginMemberBlogVo.getBlogImg() %>" alt="">
        	이미지 url 있으면 경로쓰고 없으면 기본이미지 ()
        </div>
        <div class="info">
            <strong><%= loginMemberVo.getMemNick() %></strong>
            <span><%= loginMemberVo.getMemId() %></span>
        </div>
        <div class="btn_area">
            <a href="/jdgr/blog/write" class="write">글쓰기</a>
            <a href="/jdgr/blogSet" class="set">블로그·관리</a>
        </div>
    </div>

    <!-- 카테고리 -->
    <div class="blog_category">
        <a href="" class="tit">
            <strong>카테고리</strong>
        </a>
        <ul>
        	<li class="category"><a href="/jdgr/hjy/post/view?url=<%= loginMemberBlogVo.getBlogUrl() %>"><strong>전체보기</strong><span>(12)</span></a></li>
        	<% if(groupVoList != null){ %>
        		<% for(GroupVo groupVo : groupVoList){ %>
        			<li class="category"><a href="/jdgr/hjy/post/view?url=<%= loginMemberBlogVo.getBlogUrl() %>"><strong><%= groupVo.getGroupName() %></strong><span>(3)</span></a></li>
        		<% } %>
        	<% } %>
            <li class="line"></li>
        </ul>
    </div>

    <!-- 방문자 수 -->
    <% if(loginMemberBlogVo.getVisitorsCntYn().equals("Y")){ %>
    <div class="widget blog_count">
        <h4 class="tit">방문자 수</h4>
        <dl class="today">
            <dt>TODAY</dt>
            <dd>6,314</dd>
        </dl>
        <dl class="total">
            <dt>TOTAL</dt>
            <dd>33,268,691</dd>
        </dl>
    </div>
    <% } %>
    

    <!-- 최근 댓글 -->
    <% if(loginMemberBlogVo.getrCommentsYn().equals("Y")){ %>
    <div class="widget blog_newreply">
        <h4 class="tit">최근댓글</h4>
        <ul>
            <li><a href="">속초 저녁식사 술안주 맛집 소개합니다속초 저녁식사 술안주 맛집 소개합니다</a></li>
            <li><a href="">속초 저녁식사 술안주 맛집 소개합니다속초 저녁식사 술안주 맛집 소개합니다</a></li>
            <li><a href="">속초 저녁식사 술안주 맛집 소개합니다속초 저녁식사 술안주 맛집 소개합니다</a></li>
            <li><a href="">속초 저녁식사 술안주 맛집 소개합니다속초 저녁식사 술안주 맛집 소개합니다</a></li>
            <li><a href="">속초 저녁식사 술안주 맛집 소개합니다속초 저녁식사 술안주 맛집 소개합니다</a></li>
        </ul>
    </div>
    <% } %>

    <!-- 시계 -->
    <% if(loginMemberBlogVo.getClockYn().equals("Y")){ %>
    <div class="widget blog_clock">
        <h4 class="tit">시계</h4>
        <span class="time">
            <strong class="ampm">오전</strong>
            <span class="clock">12:31</span>
        </span>
    </div>
    <% } %>

    <!-- 구독 블로그 -->
    <% if(loginMemberBlogVo.getFollowBlogYn().equals("Y")){ %>
    <div class="widget blog_subscribeblog">
        <a href="" class="tit">
            <strong>구독 블로그</strong>
        </a>
        <ul>
            <li><a href="">누구누구님의 블로그</a></li>
            <li><a href="">디스러브</a></li>
            <li><a href="">1등 취업정보 블로그입니다</a></li>
            <li><a href="">누구누구님의 블로그</a></li>
            <li><a href="">디스러브</a></li>
            <li><a href="">1등 취업정보 블로그입니다</a></li>
        </ul>
    </div>
    <% } %>

    <!-- 지도 -->
    <% if(loginMemberBlogVo.getMapYn().equals("Y")){ %>
    <div class="widget blog_map">
        <h4 class="tit">지도</h4>
        <div>
            <div id="daumRoughmapContainer1700745953734" class="root_daum_roughmap root_daum_roughmap_landing"></div>
            <script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
            <script charset="UTF-8">
                new daum.roughmap.Lander({
                    "timestamp" : "1700745953734",
                    "key" : "2gxwj",
                    "mapWidth" : "200",
                    "mapHeight" : "200"
                }).render();
            </script>
        </div>
    </div>
    <% } %>

</div>