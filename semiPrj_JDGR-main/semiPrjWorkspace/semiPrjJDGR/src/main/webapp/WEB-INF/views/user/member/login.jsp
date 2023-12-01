<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../common/header.jsp"%>
<div class="wrap gray">

    <div class="membership_box">

        <!-- 타이틀 -->
        <div class="title">
            <h1>
                <img src="/jdgr/resources/user/images/logo.svg" alt="로고">
                <!-- <strong>회원가입</strong> -->
            </h1>
        </div>
		<form action="">
		<!-- 로그인/회원가입 영역 -->
        <div class="membership_area">
            <div class="form_box ico_id">
                <input type="text" name="memberId" placeholder="아이디를 입력해주세요.">
                <span class="txt_msg">아이디 중복확인이 완료되었습니다.</span>
            </div>
            <div class="form_box ico_pwd">
                <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요.">
                <span class="txt_msg">전화번호 형식이 맞지 않습니다.</span>
            </div>
            <!-- <div class="chk_box">
                <span>
                    <input type="checkbox" id="chk_01">
                    <label for="chk_01">아이디 기억하기</label>
                </span>
            </div> -->
        </div>

        <!-- 버튼 -->
        <div class="btn_area">
            <button type="submit">로그인</button>
        </div>
        <ul class="etc_btn">
            <li><a href="">회원가입</a></li>
            <li><a href="">아이디 찾기</a></li>
            <li><a href="">비밀번호 찾기</a></li>
        </ul>
		</form>
        

    </div>
<%@include file = "../common/footer.jsp"%>
