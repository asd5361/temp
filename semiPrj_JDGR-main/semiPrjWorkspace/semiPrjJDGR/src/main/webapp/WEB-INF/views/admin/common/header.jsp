<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String adminClassName = (String) request.getAttribute("blogClassName");
	if(adminClassName == null){
		adminClassName = "";
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터 상세</title>

    <!-- css -->
    <link rel="stylesheet" href="/jdgr/resources/admin/css/allCss.css">

    <!-- js -->
    <script src="/jdgr/resources/admin/js/jquery-3.6.0.js"></script>

</head>
<body>

<div class="wrap">

    <!-- aside -->
    <aside>

        <!-- 로고 -->
        <h1><a href=""><span><span>관리자</span> 페이지</span></a></h1>

        <!-- 메뉴 -->
        <nav>
            <ul>
                <li><a href="">사용자 관리</a></li>
                <li><a href="">고객센터</a></li>
                <li><a href="">신고/제재</a></li>
                <li><a href="">공지사항</a></li>
                <li><a href="">블로그 관리</a></li>
                <li><a href="">포스트 관리</a></li>
                <li><a href="">관리자 설정</a></li>
            </ul>
        </nav>

    </aside>
    <!-- //aside -->

    <!-- main -->
    <main>

        <!-- header -->
        <header>
            <div class="welcomMsg">
                <div class="img"><img src="/jdgr/resources/admin/images/ico/ico_info.png" alt="사용자아이콘"></div>
                <span>관리자</span>님 환영합니다.
            </div>
            <a href="">로그아웃</a>
        </header>
        <!-- //header -->