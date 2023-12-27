<%@page import="com.semi.jdgr.user.member.vo.MemberVo"%>
<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<MemberVo> UserManagementVoList = (List<MemberVo>)request.getAttribute("UserManagementVoList"); %>
<% PageVo pvo = (PageVo)request.getAttribute("pageVo"); %>
<% MemberVo searchVo = (MemberVo)request.getAttribute("searchVo"); %>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

			<!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>회원 목록 조회</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="aa"></div>
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">아이디</label>
                        <div class="form_box">
                            <input type="text" id="inp_02" name="id">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">이름</label>
                        <div class="form_box">
                            <input type="text" id="inp_02" name="name">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">닉네임</label>
                        <div class="form_box">
                            <input type="text" id="inp_02" name="nick">
                        </div>
                    </div>
                   
                </div>
                
                <!-- 버튼 -->
                <div class="btn_box_group right">
                    <div class="btn_box">
                        <button class="btn_gray" onclick="reset()">초기화</button>
                    </div>
                    <div class="btn_box">
                        <button class="btn_black" onclick="sendPost()">검색</button>
                    </div>
                </div>

                <!-- 테이블 -->
                <div class="tbl_box data mt40">
                    <table>
                        <caption>회원가입 테이블</caption>
                        <colgroup>
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">아이디</th>
                                <th scope="col">이름</th>
                                <th scope="col">닉네임</th>
                                <th scope="col">가입일</th>
                                <th scope="col">탈퇴여부</th>
                            </tr>
                        </thead>
                        <tbody>
<%for(MemberVo vo : UserManagementVoList) {%>
							<tr>
								<td><%= vo.getMemNo()%></td>
								<td><%= vo.getMemId()%></td>
								<td><%= vo.getMemName() %></td>
								<td><%= vo.getMemNick() %></td>
								<td><%= vo.getEnrollDate()%></td>
								<td><%= vo.getQuitYn() %></td>
							</tr>
<%} %>
                        </tbody>
                    </table>
                </div>

                <div class="paging_box mt30">
                    <ul>
<%if(pvo.getStartPage() != 1) {%>
                        <li class="prev_all"><a href="/jdgr/admin/userManagement/list?pno=1" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/admin/userManagement/list?pno=<%=pvo.getStartPage()-1 %>" title="이전페이지로 이동"></a></li>

<%} %>

<%for(int i = pvo.getStartPage(); i<=pvo.getEndPage(); i++) {%>
	<%if(i == pvo.getCurrentPage()) {%>
						<li class="on"><a href="/jdgr/admin/userManagement/list?pno=<%=i%>"><%=i %></a></li>
	<%}else{ %>
						<li><a href="/jdgr/admin/userManagement/list?pno=<%=i%>"><%=i %></a></li>
<%} } %>
<%if(pvo.getEndPage() != pvo.getMaxPage()) {%>
                        <li class="next"><a href="/jdgr/admin/userManagement/list?pno=<%=pvo.getEndPage()+1 %>" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="/jdgr/admin/userManagement/list?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
<%} %>                    
                    </ul>
                </div>

            </div>
            <!-- //container -->

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>

   <script>
        const trArr = document.querySelectorAll(".tbl_box> table> tbody> tr");
        for(let i = 0; i<trArr.length; i++){
            trArr[i].addEventListener('click',handleClick);
        }
        function handleClick(event){
            const tr = event.currentTarget;          // 이벤트가 발생 된 tr 요소를 선택함
            const no = tr.children[0].innerText;    //글번호를 가져옴
            location.href = '/jdgr/admin/userManagement/detail?no='+no+'&currPage=<%=pvo.getCurrentPage()%>'
        }

        // 초기화 버튼
        function reset(){
            const inputArr = document.querySelectorAll(".form_box input"); 

            for(let i=0; i<inputArr.length; i++){
                inputArr[i].value = null;
            }
            sendPost(); //검색결과도 같이 초기화 하기 위해 사용함
        }

        //검색 버튼
        function sendPost(){
            let aaTag = document.querySelector(".aa");
            let divTag = document.querySelector(".search_box");
            let formTag = document.createElement("form");
            formTag.setAttribute('method','get');
            formTag.setAttribute('action','/jdgr/admin/userManagement/search?pno=1');
            formTag.appendChild(divTag);
            aaTag.appendChild(formTag);
            formTag.submit();
        }        

        <%if(searchVo != null){%>
        //검색 후에도 검색어 남기기
		function setSearchArea(){
            const idIntput = document.querySelector(".search_box input[name=id]");
            const nameIntput = document.querySelector(".search_box input[name=name]");
            const nickIntput = document.querySelector(".search_box input[name=nick]");

            
            idIntput.value = '<%=searchVo.getMemId() %>';
            nameIntput.value = '<%=searchVo.getMemName()%>';
            nickIntput.value = '<%=searchVo.getMemNick()%>';
            
        }
        setSearchArea();

        function setPageArea(){
            const aTagArr = document.querySelectorAll(".paging_box ul li a");
            for(let i=0; i<aTagArr.length; ++i){
                aTagArr[i].href = aTagArr[i].href.replace('list','search');
                aTagArr[i].href += '&id=<%=searchVo.getMemId() %>&name=<%=searchVo.getMemName()%>&nick=<%=searchVo.getMemNick()%>';
            }
        }
        setPageArea();
<%}%>
    </script>    