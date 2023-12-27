<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.notice.vo.NoticeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<NoticeVo> noticeVoList = (List<NoticeVo>)request.getAttribute("noticeVoList");   %>
<% PageVo pvo = (PageVo)request.getAttribute("pageVo"); %>
<% String searchValue = (String)request.getAttribute("searchValue"); %>

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
            <div class="q_box">
                <h1 class="mtp50">공지사항 검색하기</h1>
                <form action="/jdgr/notice/list/search?pno=1" method="get">
                <div class="search_box mt40">
                    <input type="text" name="searchValue">
                    <button>검색</button>
                </div>
                </form>
            </div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <dl class="item-content">
                    <!-- 가로테이블 -->
                    <div class="tbl_box q_data mt40">
                        <table>
                            <caption>테이블</caption>
                            <colgroup>
                                <col width="2%">
                                <col width="30%">
                                <col width="5%">
                                <col width="5%">
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
                                    <!-- <td><img src="/jdgr/resources/user/images/ico/ico_fix.svg" alt="고정표시"></td> -->
<%for(NoticeVo vo : noticeVoList){%>
								<tr>
									<%if(vo.getFixedYn().equals("Y")){ %>
										<td class="fixedTdTag"><img src="/jdgr/resources/user/images/ico/ico_fix.svg" alt="고정표시" ><%= vo.getNoticeNo()%></td> 
									<%}else{%>
									<td><%= vo.getNoticeNo()%></td>
									<%} %>
                                    <td><%= vo.getTitle()%></td>
                                    <td><%= vo.getEnrollDate()%></td>
                                    <td><%= vo.getInquiry()%></td>
                                </tr>	
<%} %>  
                            </tbody>
                        </table>
                    </div>
                    <div class="paging_box mt40">
                    <ul>
<% if(pvo.getStartPage() != 1){ %>
						<li class="prev_all"><a href="/jdgr/notice/list?pno=1" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/notice/list?pno=<%= pvo.getStartPage()-1 %>" title="이전페이지로 이동"></a></li>
<%} %>

<%for(int i=pvo.getStartPage(); i<=pvo.getEndPage(); i++) {%>
	<%if(i== pvo.getCurrentPage()) {%>
						<li class="on"><a href=""><%=i %></a></li>
	<%}else{ %>
	 					<li><a href="/jdgr/notice/list?pno=<%=i%>"><%=i %></a></li>
<%} }%>
<% if( pvo.getEndPage() != pvo.getMaxPage() ){ %>
                        <li class="next"><a href="/jdgr/notice/list?pno=<%= pvo.getEndPage()+1 %>" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="/jdgr/notice/list?pno=<%= pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
<%} %>
                        
                    </ul>
                </div>
        </div>
    </main>
    <!-- //main -->
	
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>

<script>
    const trArr = document.querySelectorAll(".tbl_box> table> tbody> tr");
    for(let i=0; i<trArr.length; i++){
        trArr[i].addEventListener('click',handleClick);
    }
    function handleClick(event){
        const tr = event.currentTarget;
        const no = tr.children[0].innerText;
        location.href = '/jdgr/notice/detail?no='+no+'&currPage=<%=pvo.getCurrentPage()%>';
    }
<% if(searchValue != null){ %>
    function setSearchArea(){
        const searchValueTag = document.querySelector(".q_box form div input[name=searchValue]")
        searchValueTag.value = '<%=searchValue%>';
    }
    setSearchArea();


	function setPageArea(){
	    const aTagArr = document.querySelectorAll(".paging_box ul li a");
	    for(let i = 0; i< aTagArr.length; i++){
	        aTagArr[i].href = aTagArr[i].href.replace('list','list/search');
	        aTagArr[i].href +='&searchValue=<%=searchValue%>';
	    }
	}
	setPageArea();
<%}%>


</script>
	