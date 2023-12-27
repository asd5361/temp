<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<div class="wrap gray">

	<div class="membership_box">

		<!-- 타이틀 -->
		<div class="title">
			<h1>
				<img src="/jdgr/resources/user/images/logo.svg" alt="로고">

				<!-- <strong>회원가입</strong> -->
			</h1>
		</div>

		<div>
			<h1>비밀번호 찾기</h1>
		</div>


		<!-- 아이디 찾기 영역 -->
		<div class="membership_area">

			<div class="form_box ico_id">
				<input type="text" name="" placeholder="이름"> <span
					class="txt_msg">아이디 중복확인이 완료되었습니다.</span>
			</div>
			<div class="form_box ico_id">
				<input type="password" name="" placeholder="아이디"> <span
					class="txt_msg">전화번호 형식이 맞지 않습니다.</span>
			</div>

		</div>


		<!-- 버튼 -->
		<div class="btn_area">
			<div class="btn_box btn_group">
				<button class="btn_black modal_open" data-target="#pop_email">이메일
					인증하기</button>
			</div>
			<ul class="etc_btn">
				<li><a href="/jdgr/member/join">회원가입</a></li>
				<li><a href="/jdgr/member/findid">아이디 찾기</a></li>
			</ul>
		</div>

	</div>

	<footer>Copyright © KH Group3 PowerBloger. All Rights
		Reserved.</footer>

	<!-- 이메일 팝업 -->
	<div id="pop_email" class="modal_bg">
		<div class="modal_box">
			<div class="modal_header">
				<h2>이메일 인증</h2>
				<button class="modal_close">닫기</button>
			</div>
			<div class="modal_container">
				<!-- 이메일 입력 -->
				<div class="form_box">
					이메일 <input class="input_email" type="email" name="email1">@
					<input id="input_addr" class="input_email" type="email"
						name="email2"> <select id="selet_addr" class="selet_addr"
						onchange="onChangeFruits(event)">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
					</select>
				</div>
				<!-- 이메일 전송 버튼 -->
				<div class="btn_box btn_group">
					<button class="btn_black modal_open" data-target="#pop_email"
						onclick="sendEmail()">인증번호 전송</button>
				</div>

				<!-- 인증번호 입력 -->
				<div class="form_box">
					<div class="form_box inp_btn ">
						인증번호 <input type="text" name="inputCheckNum" class="input_num">
						<button onclick="pwdCertification()">인증하기</button>
					</div>
				</div>
			</div>
			<input type="hidden" id="checkNum"> <input type="hidden"
				id="nowEmail" name="nowEmail">

			<div class="modal_footer">
				<button class="modal_close btn_black">확인</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script src="/jdgr/resources/user/js/sendEmail.js"></script>