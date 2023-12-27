<%@page import="com.semi.jdgr.csboard.vo.CsboardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% CsboardVo csboardVo = (CsboardVo)request.getAttribute("csboardVo"); %>
<% String currPage = (String)request.getAttribute("currPage"); %>

	<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
	
	<!-- main -->
    <main>
        <div class="con1 agn_c">
            <h1>고객센터</h1>
            <div class="btn_q mtp50">
                <ul class="tab_btns">
                    <li><button onclick="location.href='/jdgr/csboard';">고객센터</button></li>
                    <li><button onclick="location.href='/jdgr/notice/list?pno=1';">공지사항</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/csboard/list?pno=1';">1:1문의</button></li>
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
                        <col width=""/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th><%=csboardVo.getqTit() %></th>
                        </tr>
                        <tr>
                            <th>
                                <div>
                                    <div>
<% String x = csboardVo.getAdminName();  if(x == null){  x = "미지정"; }%>
                                        <img src="/jdgr/resources/user/images/ico/ico_people.svg"><%=x %>
                                    </div>
                                    <div>
                                        <%= csboardVo.getQuestionCategoryName() %>
                                        <img class="ml30" src="/jdgr/resources/user/images/ico/ico_cal.svg"><%=csboardVo.getqWriteDate() %>
                                    </div>
                                </div>
                            </th>
                        </tr>
                        <tr>
                            <th>
                            	<%=csboardVo.getqCon() %>
                            </th>
                        </tr>
<%if(csboardVo.getAnsewr() != null){%>
                        <tr>
                            <th>
                            	<%= csboardVo.getAnsewr() %>
                            </th>
                        </tr>
<%} %>
                    </tbody>
                </table>
            </div>
            <div class="noti_btn">
                <a href="/jdgr/csboard/list?pno=<%=currPage%>" >목록</a>
               <!--  <a href="" >답변 작성</a>  -->
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
<!--                             <th>이전글</th> -->
<!--                             <td><a href="" >공지사항 제목입니다.</a></td> -->
<!--                         </tr> -->
<!--                     </tbody> -->
                </table>
            </div>
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>

