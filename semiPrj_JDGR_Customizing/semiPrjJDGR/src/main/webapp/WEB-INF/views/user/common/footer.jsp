<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 모달 팝업
	Map<String, String> popText = (Map<String, String>)session.getAttribute("popText");
	// popText가 null이 아니면 completeId키 값이 들어가는데 completeId키값이 없으면 기본값으로 null을 세팅
	String completeId = popText != null ? popText.getOrDefault("completeId", null) : null;
	String completeTitle = popText != null ? popText.getOrDefault("completeTitle", null) : null;
	String completeContent = popText != null ? popText.getOrDefault("completeContent", null) : null;
	String warningId = popText != null ? popText.getOrDefault("warningId", null) : null;
	String warningTitle = popText != null ? popText.getOrDefault("warningTitle", null) : null;
	String warningContent = popText != null ? popText.getOrDefault("warningContent", null) : null;

%>
    
	<!-- footer -->
    <footer>Copyright © KH Group3 PowerBloger. All Rights Reserved.</footer>
    <!-- //footer -->
    
</div>
<!-- 레이어팝업 모달 -->
<div id="pop_<%= completeId %>" class="modal_bg">
    <div class="modal_box">
        <div class="modal_header">
            <h2>제목</h2>
            <button class="modal_close">닫기</button>
        </div>
        <div class="modal_container">
            
        </div>
        <div class="modal_footer">
            <button class="modal_close btn_black">확인</button>
        </div>
    </div>
</div>

<!-- 레이어팝업 경고 모달 -->
<div id="pop_warning" class="modal_bg warning" style="<%= warningId %>">
    <div class="modal_box">
        <div class="modal_header">
            <button class="modal_close">닫기</button>
        </div>
        <div class="modal_container">
            
            <div class="modal_content">
                <div class="img"></div>
                <div class="txt">
                    <strong><%= warningTitle %></strong>
                    <span><%= warningContent %></span>
                </div>
            </div>
            
        </div>
        <div class="modal_footer">
            <button class="modal_close btn_black">확인</button>
        </div>
    </div>
</div>

<!-- 레이어팝업 완료 모달 -->
<div id="pop_complete" class="modal_bg complete" style="<%= completeId %>">
    <div class="modal_box">
        <div class="modal_header">
            <button class="modal_close">닫기</button>
        </div>
        <div class="modal_container">

            <div class="modal_content">
                <div class="img"></div>
                <div class="txt">
                    <strong><%= completeTitle %></strong>
                    <span><%= completeContent %></span>
                </div>
            </div>

        </div>
        <div class="modal_footer">
            <button class="modal_close btn_black">확인</button>
        </div>
    </div>
</div>
<%
	// 세션제거
	session.removeAttribute("popText");
%>
</body>
</html>