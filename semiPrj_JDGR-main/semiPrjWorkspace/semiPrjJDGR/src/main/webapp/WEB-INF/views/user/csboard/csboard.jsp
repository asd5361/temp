<%@page import="com.semi.jdgr.user.csboard.vo.CsboardVo"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.semi.jdgr.notice.vo.NoticeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<NoticeVo> noticeVoList = (List<NoticeVo>)request.getAttribute("noticeVoList");   %>
<% List<CsboardVo> csboardVoList = (List<CsboardVo>)request.getAttribute("csboardVoList");   %>
	
	
	<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
	
	<!-- main -->
    <main>
        <div class="con1 agn_c">
            <h1>고객센터</h1>
            <div class="btn_q mtp50">
                <ul class="tab_btns">
                    <li class="on"><button onclick="location.href='/jdgr/csboard';">고객센터</button></li>
                    <li><button onclick="location.href='/jdgr/notice/list?pno=1';">공지사항</button></li>
                    <li><button onclick="location.href='/jdgr/csboard/list';">1:1문의</button></li>
                </ul>
            </div>
            <div class="q_box">
                <h1 class="mtp50">궁금한 점을 검색해보세요.</h1>
                <div class="search_box mt40">
                    <input type="text" name="">
                    <button>검색</button>
                </div>
            </div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <dl class="item-content">
                    <div class="q_tit mt50">
                        <h1>공지사항</h1>
                        <a href="/jdgr/notice/list?pno=1">
                            <h5>더보기<img class="icon_next ml10" src="/jdgr/resources/user/images/ico/ico_next.svg"></h5>
                        </a>

                    </div>
                    <!-- 가로테이블 -->
                    <div class="tbl_box q_data mt40">
                        <table>
                            <caption>테이블</caption>
                            <colgroup>
                                <col width="5%">
                                <col width="20%">
                                <col width="15%">
                                <col width="10%">
                            </colgroup>
                            <thead>                                                      
                                <tr >
                                    <th scope="col">No</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성일자</th>
                                    <th scope="col">조회수</th>
                                </tr>
                            </thead>
                            <tbody>
<%for(NoticeVo vo : noticeVoList){%>
								<tr>
                                    <td><%= vo.getNoticeNo()%></td>
                                    <td><%= vo.getTitle()%></td>
                                    <td><%= vo.getEnrollDate()%></td>
                                    <td><%= vo.getInquiry()%></td>
                                </tr>
<%} %>  
                            </tbody>
                        </table>
                    </div>

                    <div class="q_tit mt50">
                        <h1>1:1문의</h1>
                        <a href="/jdgr/csboard/list">
                            <h5>더보기<img class="icon_next ml10" src="/jdgr/resources/user/images/ico/ico_next.svg"></h5>
                        </a>

                    </div>
                    <!-- 가로테이블 -->
                    <div class="tbl_box q_data mt40">
                        <table>
                            <caption>테이블</caption>
                            <colgroup>
                                <col width="5%">
                                <col width="20%">
                                <col width="15%">
                                <col width="10%">
                            </colgroup>
                            <thead>
                                <tr >
                                    <th scope="col">No</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성일자</th>
                                    <th scope="col">문의글 구분</th>
                                </tr>
                            </thead>
                            <tbody>
<%for(CsboardVo vo : csboardVoList){%>
								<tr>
                                    <td><%= vo.getqNo()%></td>
                                    <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"><%= vo.getqTit()%></td>
                                    <td><%= vo.getqWriteDate()%></td>
                                    <td><%= vo.getQuestionCategoryName()%></td>
                                </tr>
<%} %>  

                            </tbody>
                        </table>
                    </div>

        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
