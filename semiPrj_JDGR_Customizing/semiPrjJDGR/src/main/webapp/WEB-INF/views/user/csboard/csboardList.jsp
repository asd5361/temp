<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.semi.jdgr.csboard.vo.CsboardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<CsboardVo> csboardVoList = (List<CsboardVo>)request.getAttribute("csboardVoList"); %>
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
                    <li><button onclick="location.href='/jdgr/notice/list?pno=1';">공지사항</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/csboard/list?pno=1';">1:1문의</button></li>
                </ul>
            </div>
            <div class="q_box">
                <h1 class="mtp50">1:1문의 검색하기</h1>
                <form action="/jdgr/csboard/list/search?pno=1" method="get">
                <div class="search_box mt40">
                    <input type="text" name="searchValue">
                    <button>검색</button>
                </div>
                </form>
            </div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
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
                            <th scope="col">문의글 구분</th>
                        </tr>
                    </thead>
                    <tbody>
<%for(CsboardVo vo : csboardVoList){ %>
						<tr> 
	                        <td><%= vo.getqNo()%></td>
	                        <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> <%=vo.getqTit() %></td>
	                        <td><%=vo.getqWriteDate() %></td>
	                        <td><%=vo.getQuestionCategoryName() %></td>
	                    </tr>
<%} %>
                        <!--비밀글 로그인 계정과 맞지 않으면 보여주게 만들기-->
                        <!-- <tr class="modal_open" data-target="#pop_warning"> 
                            <td >5</td>
                            <td ><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr>-->
                    </tbody>
                </table>
            </div>
            <div class="paging_box mt40">
            <ul>
<%if(pvo.getStartPage() != 1) {%>
                <li class="prev_all"><a href="/jdgr/csboard/list?pno=1" title="최신페이지로 이동"></a></li>
                <li class="prev"><a href="/jdgr/csboard/list?pno=<%=pvo.getStartPage()-1%>" title="이전페이지로 이동"></a></li>
<%} %>  
<%for(int i = pvo.getStartPage(); i<= pvo.getEndPage(); i++) {%>
	<%if(i == pvo.getCurrentPage()) {%>
				<li class="on"><a href="/jdgr/csboard/list?pno=<%= i%>"><%=i %></a></li>
	<%}else{ %>
				<li><a href="/jdgr/csboard/list?pno=<%= i%>"><%= i %></a></li>
	<%} %>
<%} %>
<% if(pvo.getEndPage() != pvo.getMaxPage()){ %>
                <li class="next"><a href="/jdgr/csboard/list?pno=<%=pvo.getEndPage()+1%>" title="다음페이지로 이동"></a></li>
                <li class="next_all"><a href="/jdgr/csboard/list?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
<%} %>
            </ul>
        </div>
        <div class="noti_btn qboard_btn mb20">
<% if(loginMemberVo != null){ %>
            <a href="/jdgr/csboard/write?pno=<%=pvo.getCurrentPage() %>" data-target="#pop_complete">문의 하기</a>
<% }%>
        </div>
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
	
    <script>
        
        //cs 팝업 분리
        function cs_modal(){
            document.querySelector("#pop_warning").style.display ='flex';
            body.style.overflow = 'hidden';
            document.querySelector("#pop_warning").querySelector('.modal_close').focus();
        }


        const trArr = document.querySelectorAll(".tbl_box> table> tbody> tr");
        for(let i = 0; i<trArr.length; i++){
            trArr[i].addEventListener('click',handleClick);
        }
        function handleClick(event){
            const tr = event.currentTarget;          // 이벤트가 발생 된 tr 요소를 선택함
            const no = tr.children[0].innerText;    //글번호를 가져옴
            //내 글 아니면 팝업 띄우기 위해 class 추가
            tr.classList.add('modal_open');
            tr.setAttribute('data-target', '#pop_warning');
<% if(loginMemberVo != null){ %>
            //글의 작성자 번호 담을 변수 선언
			let csMemNo ='';
    <%for(CsboardVo vo : csboardVoList){%>
            //tr에서 얻은 글번호와 전체 글 목록의 글번호가 일치하면 글의 작성자 번호 변수의 대입.
            if('<%=vo.getqNo()%>'=== no ){
                csMemNo = '<%=vo.getMemNo()%>';
            }
    <%} %>
            //로그인 유저 번호 와 글의 작성자 번호가 일치하면 상세보기로 이동.
            const memNo = '<%= loginMemberVo.getMemNo() %>';
			if(memNo === csMemNo){
            	location.href = '/jdgr/csboard/detail?no='+no+'&currPage=<%=pvo.getCurrentPage()%>';				
			}else{
                cs_modal();
            }
<%}else{%>
            cs_modal();
<%}%>
        }
	


<%if(searchValue != null){%>
        function setSearchArea(){
            const searchValueTag = document.querySelector(".q_box form div input[name=searchValue]")
            searchValueTag.value = '<%=searchValue%>';
        }
        setSearchArea();

        function setPageArea(){
            const aTagArr = document.querySelectorAll(".paging_box ul li a");
            for(let i=0; i< aTagArr.length; i++){
                aTagArr[i].href = aTagArr[i].href.replace('list','list/search');
                aTagArr[i].href += '&searchValue=<%=searchValue%>';
            }
        }
        setPageArea();
<%}%>
    </script>