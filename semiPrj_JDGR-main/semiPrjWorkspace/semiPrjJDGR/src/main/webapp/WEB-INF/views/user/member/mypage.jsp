<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
    String str = loginMember.getMemEmail(); 
    String[] email = str.split("@");
    
	%>  
<%@include file = "../common/header.jsp"%>
<body>

    <!-- main -->
    <main>
        <div class="inner">
        
	        <form action="/jdgr/member/mypage" method="post">
	            <div class="mypage_div">
	                <!-- 타이틀 -->
	                <div class="title">
	                    <h1>
	                        마이페이지
	                    </h1>
	                </div>
	                <hr>
	                <table>
	                    <tbody>
	                        <tr>
	                            <td>이름</td>
	                            <td>
	                                <!-- 이름 입력 -->
	                                <div class="form_box ico_id">
	                                    <input type="text" name="name" value="<%=loginMember.getMemName()%>">
	                                </div>
	                            </td>
	                        </tr>
	                        
	                        <tr>
	                            <td>이메일</td>
	                            <td>
	                                <!-- 이메일 출력 -->
	                                <div class="form_box">
	                                    <input class="input_email" type="email" name="email1" disabled value="<%=email[0]%>">@
	                                    <input class="input_email" type="email" name="email2" disabled value="<%=email[1]%>">
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>비밀번호 변경</td>
	                            <td>
	                                <!-- 비번 변경 버튼 -->
	                                <div class="form_box">
	                                    <div class="btn_box">
	                                        <button class="btn_black" data-target="#pop_email">비밀번호 변경</button>
	                                    </div>
	                                    <span class="txt_msg">*사용자의 메일로 인증번호가 전송 되었습니다.</span>
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>인증번호 입력</td>
	                            <td>
	                                <!-- 인증번호 입력 -->
	                                <div class="form_box">
	                                    <div class="inp_btn">
	                                        <input  type="text" name="cert" >
	                                        <button >인증</button>
	                                    </div>
	                                    <span class="txt_msg">*인증완료</span>
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>비밀번호</td>
	                            <td>
	                                <!-- 이름 입력 -->
	                                <div class="form_box ico_id">
	                                    <input type="password" name="pwd1" >
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>비밀번호 확인</td>
	                            <td>
	                                <!-- 이름 입력 -->
	                                <div class="form_box ico_id">
	                                    <input type="password" name="pwd2" >
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>닉네임</td>
	                            <td>
	                                <!-- 이름 입력 -->
	                                <div class="form_box">
	                                    <div class="inp_btn">
	                                        <input  type="text" name="nick" value="<%=loginMember.getMemNick()%>">
	                                        <button >중복확인</button>
	                                    </div>
	                                    <span class="txt_msg">닉네임 중복확인이 완료되었습니다.</span>
	                                </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>전화번호</td>
	                            <td>
	                                <!-- 이름 입력 -->
	                                <div class="form_box ico_id">
	                                    <input type="tel" name="phone" value="<%=loginMember.getMemPhoneNum()%>">
	                                </div>
	                            </td>
	                        </tr>
	                        <!-- <tr>
	                            <td></td>
	                            <td>
	                                <div class="form_box">
	                                    <div class="btn_box">
	                                        <button class="btn_black dkdk" >변경사항 저장</button>
	                                    </div>
	                                    <div class="btn_box">
	                                        <button class="btn_black dkdk">탈퇴하기</button>
	                                    </div>
	                                </div>
	                            </td>
	                        </tr> -->
	                    </tbody>
	                </table>
	                <div class="form_box btn_center">
	                    <div class="btn_box">
	                        <button class="btn_black dkdk" type= "submit">변경사항 저장</button>
	                    </div>
	                    <div class="btn_box">
	                        <button class="btn_black dkdk">탈퇴하기</button>
	                    </div>
	                </div>
	            </div>
	        </form>
        
        
            
        </div>
        
        
    </main>
    <!-- //main -->

    <!-- footer -->
    
<%@include file = "../common/footer.jsp"%>