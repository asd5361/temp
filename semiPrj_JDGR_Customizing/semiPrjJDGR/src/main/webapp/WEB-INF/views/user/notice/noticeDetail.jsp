<%@page import="com.semi.jdgr.notice.vo.NoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% NoticeVo noticeVo = (NoticeVo)request.getAttribute("noticeVo"); %>
<% String pno = (String)request.getAttribute("currPage"); %>

	<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
	
	<!-- main -->
    <main>
        <div class="con1 agn_c">
            <h1>고객센터</h1>
            <div class="btn_q mtp50">
                <ul class="tab_btns">
                    <li><button onclick="location.href='/jdgr/csboard';">고객센터</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/notice/list?pno=1';">공지사항</button></li>
                    <li><button onclick="location.href='/jdgr/csboard/list?pno=1';">1:1문의</button></li>
                </ul>
            </div>
            <div class="noti_box"></div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <div class="tbl_box noti_tbl mt50">
                <table>
                    <caption>공지사항 상세보기</caption>
                    <colgroup>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th><%=noticeVo.getTitle() %></th>
                        </tr>
                        <tr>
                            <th>
                                <div>
                                    <div>
                                        <img src="/jdgr/resources/user/images/ico/ico_people.svg"> 관리자
                                    </div>
                                    <div>
                                        <img src="/jdgr/resources/user/images/ico/ico_eye.svg"> <%=noticeVo.getInquiry() %>
                                        <img class="ml30" src="/jdgr/resources/user/images/ico/ico_cal.svg"> <%=noticeVo.getEnrollDate() %>
                                    </div>
                                </div>
                            </th>
                        </tr>
                        <tr>
                            <th>
                            	<%= noticeVo.getContent() %>
                            </th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="noti_btn">
                <a href="/jdgr/notice/list?pno=<%= pno%>" >목록</a>
            </div>
             <div class="tbl_box noti_ft">
                <table>
                    <colgroup>
                        <col width=""/>
                        <col width="90%"/>
                    </colgroup>
<!--                     <tbody> -->
<!--                         <tr> -->
<!--                             <th>이전글</th> -->
<!--                             <td><a href="" >공지사항 제목입니다.</a></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th>다음글</th> -->
<%-- <%int x = Integer.parseInt(noticeVo.getNoticeNo())+1; %> <!-- 앞 뒤 컬럼 조회 후 max 나 min 확인해서 없으면 숨기기 --> --%>
<%--                             <td><a href="/jdgr/notice/detail?no=<%=x%>&currPage=<%=pno%>" >공지사항 제목입니다.</a></td> --%>
<!--                         </tr> -->
<!--                     </tbody> -->
                </table>
            </div>
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
