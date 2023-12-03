<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.semi.jdgr.user.member.vo.MemberVo"%>
<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 블로그 css class부여
	String blogClassName = (String) request.getAttribute("blogClassName");
	if(blogClassName == null){
		blogClassName = "";
	}
	
	// 로그인 멤버 정보 vo
	MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMember");
	
	BlogVo loginMemberBlogVo = null;
	List<BlogVo> loginMemberBlogVoList = new ArrayList<BlogVo>();
	if(loginMemberVo != null){
		// 로그인 유저 블로그 정보 vo
		
	}
	loginMemberBlogVo = (BlogVo) request.getAttribute("loginMemberBlogVo");
	loginMemberBlogVoList = (List<BlogVo>) request.getAttribute("loginMemberBlogVoList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JDGR - 좋아요 댓글 구독 알람설정♡</title>

    <!-- swiper -->
    <link rel="stylesheet" href="/jdgr/resources/user/css/swiper-bundle.css">
    <script src="/jdgr/resources/user/js/swiper-bundle.min.js"></script>

    <!-- css -->
    <link rel="stylesheet" href="/jdgr/resources/user/css/allCss.css">

    <!-- js -->
    <script src="/jdgr/resources/user/js/jquery-3.6.0.js"></script>
    <script src="/jdgr/resources/user/js/common.js" defer></script>


</head>
<body>
<% if(loginMemberBlogVo != null){ %>		
<div class="wrap <%= blogClassName %> layout<%= loginMemberBlogVo.getLayout() %> skin<%= loginMemberBlogVo.getSkin() %>">
<% } else { %>
<div class="wrap <%= blogClassName %>">
<% } %>


    <!-- header -->
    <header>
        <div class="inner">

            <h1><a href="/jdgr/home">JDGR</a></h1>

            <div class="search_box">
                <select>
                    <option value="">검색기준</option>
                    <option value="">검색기준</option>
                    <option value="">검색기준</option>
                </select>
                <input type="text">
                <button>검색</button>
            </div>

            
            <div class="util_box">
				<% if(loginMemberVo == null){ %>
					<!-- 로그인 전 -->
	                <div class="before_login">
	                    <a href="/jdgr/member/login">로그인</a>
	                </div>
				<% } else { %>
					<!-- 로그인 후 -->
	                <div class="after_login">
						
						<div class="blog_home">
							<a href="/jdgr/home">블로그 홈</a>
						</div>
						
	                    <!-- 클릭시 on class -->
	                    <div class="user_box">
	
	                        <a href="">
	                            <div class="img"><img src="/jdgr/resources/user/images/ico/ico_info.svg" alt="유저프로필사진"></div>
	                            <span class="user_nick"><%= loginMemberVo.getMemNick() %><span>님</span></span>
	                            <span class="arrow"></span>
	                        </a>
	
	                        <!-- 유저 팝업 -->
	                        <div class="pop_user_menu">
	                            <div class="info_box">
	                                <div class="info_user_name">
	                                    <strong><%= loginMemberVo.getMemNick() %></strong>
	                                    <span>님</span>
	                                </div>
	                                <div class="info_email"><%= loginMemberVo.getMemEmail() %></div>
	                                <a href="/jdgr/userSet/blog" class="ico_set">관리</a>
	                            </div>
	                            <div class="user_blog">
	                                <div class="tit_box">
	                                    <strong>운영중인 블로그</strong>
	                                    <a href="/jdgr/userSet/newblog" class="ico_add">추가하기</a>
	                                </div>
	                                <ul>
	                                	<% if(loginMemberBlogVoList != null){ %>
	                                		<% for(BlogVo blogVo : loginMemberBlogVoList){ %>
	                                			<li>
			                                        <a href="/jdgr/blog/view/<%= blogVo.getBlogUrl() %>" class="tit"><%= blogVo.getBlogTitle() %></a>
			                                        <a href="" class="ico_write"></a>
			                                        <a href="/jdgr/userSet/blog" class="ico_set">관리</a>
			                                    </li>
                                			<% } %>
	                                		
	                                	<% } else { %>
	                                		운영중인 블로그가 없습니다.
	                                	<% } %>
	                                </ul>
	                            </div>
	                            <div class="btn_group">
	                                <a href="">고객센터</a>
	                                <a href="">로그아웃</a>
	                            </div>
	                        </div>
	                        <!-- //유저 팝업 -->
	
	                    </div>
	
	                    <div class="alarm_box active">
	                        <!-- 
	                            active class를 추가하면 알림표시
	                            on class 추가하면 팝업 표시
	                        -->
	                        <a href="" class="ico_alarm"></a>
	
	                        <!-- 알람 팝업 -->
	                        <div class="pop_alarm">
	                            <ul>
	                                <li>
	                                    <span class="ico_reply"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 글제목1에 댓글을 달았습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_reply"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 글제목1에 댓글을 달았습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_subscribe"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 내 블로그를 구독했습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_like"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 내 포스트에 공감을 했습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_reply"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 글제목1에 댓글을 달았습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_reply"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 글제목1에 댓글을 달았습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_subscribe"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 내 블로그를 구독했습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                                <li>
	                                    <span class="ico_like"></span>
	                                    <div class="txt">
	                                        <strong>유저닉네임</strong>
	                                        님이 내 포스트에 공감을 했습니다.
	                                    </div>
	                                    <a href="" class="delete">삭제</a>
	                                </li>
	                            </ul>
	                        </div>
	                        <!-- //알람 팝업 -->
	                    </div>
	
	                </div>
				<% } %>
                
            </div>

        </div>
    </header>
    <!-- //header -->
    
    <% if(!blogClassName.equals("") && !blogClassName.equals("blog_set")){ %>
    	<% if(loginMemberBlogVo != null){ %>		
		    <!-- blog_header -->
		    <div class="blog_header">
		        <h1><a href="/jdgr/blog/view"><%= loginMemberBlogVo.getBlogTitle() %></a></h1>
		    </div>
		    <!-- //blog_header -->
	    <% } else { %>
		    <!-- blog_header -->
		    <div class="blog_header">
		        <h1><a href="">-</a></h1>
		    </div>
		    <!-- //blog_header -->
	    <% } %>
    <% } %>
    