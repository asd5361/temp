<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

<%
	BlogVo blogVo = (BlogVo) request.getAttribute("blogVo");
	String currPage = (String) request.getAttribute("currPage");
	if(currPage == null){
		currPage = "1";
	}
%>

<!-- container -->
<div class="container">


    <!-- 제목 -->
    <div class="tit_box">
        <h2>블로그 상세 조회</h2>
    </div>

    <!-- 가로 테이블 -->
    <div class="detail_box mt30">
        <!-- 테이블 -->
        <div class="tbl_group">
    
            <div class="tbl_box">
                <table>
                    <caption>블로그 상세 테이블</caption>
                    <colgroup>
                        <col width="15%">
                        <col width="35%">
                        <col width="15%">
                        <col width="35%">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row"><label for="">회원 아이디</label></th>
                            <td><%= blogVo.getMemId() %></td>
                            <th scope="row"><label for="">회원 닉네임</label></th>
                            <td><%= blogVo.getMemNick() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">블로그 프로필 이미지</label></th>
                            <td><img src="/<%= blogVo.getBlogImg() %>" alt=""></td>
                            <th scope="row"><label for="">블로그 번호</label></th>
                            <td><%= blogVo.getBlogNo() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">블로그 URL</label></th>
                            <td>/jdgr/blog/view/<%= blogVo.getBlogUrl() %></td>
                            <th scope="row"><label for="">블로그 타이틀</label></th>
                            <td><%= blogVo.getBlogTitle() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">레이아웃</label></th>
                            <td><%= blogVo.getLayout() %></td>
                            <th scope="row"><label for="">구독 블로그 노출 여부</label></th>
                            <td><%= blogVo.getFollowBlogYn() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">스킨</label></th>
                            <td><%= blogVo.getSkin() %></td>
                            <th scope="row"><label for="">블로그 공개</label></th>
                            <td><%= blogVo.getOpenYn() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">방문자수</label></th>
                            <td><%= blogVo.getVisitCnt() %></td>
                            <th scope="row"><label for="">시계 노출 여부</label></th>
                            <td><%= blogVo.getClockYn() %></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">방문자수 노출</label></th>
                            <td><%= blogVo.getVisitorsCntYn() %></td>
                            <th scope="row"><label for="">지도 노출 여부</label></th>
                            <td><%= blogVo.getMapYn() %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
        </div>

        <div class="btn_box_group right mt20">
            <div class="btn_box">
                <a href="/jdgr/admin/blog/list?pno=<%= currPage %>" class="btn_grayline">목록가기</a>
            </div>
<!--             <div class="btn_box"> -->
<!--                 <a href="" class="btn_grayline">카테고리 그룹 조회</a> -->
<!--             </div> -->
<!--             <div class="btn_box"> -->
<!--                 <a href="" class="btn_grayline">방문자 확인</a> -->
<!--             </div> -->
<!--             <div class="btn_box"> -->
<!--                 <a href="" class="btn_blue">수정하기</a> -->
<!--             </div> -->
        </div>

    </div>
</div>
<!-- //container -->


<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>