<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.csboard.vo.CsboardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<CsboardVo> csboardVoList = (List<CsboardVo>)request.getAttribute("csboardVoList"); %>
<% PageVo pvo = (PageVo)request.getAttribute("pageVo"); %>
<% CsboardVo searchVo = (CsboardVo)request.getAttribute("searchVo"); %>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

	<!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>고객센터 목록 조회</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="aa"></div>
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">제목</label>
                        <div class="form_box">
                            <input type="text" name="title" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">내용</label>
                        <div class="form_box">
                            <input type="text" name="content" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">작성자</label>
                        <div class="form_box">
                            <input type="text" name="writer" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">답변 내용</label>
                        <div class="form_box">
                            <input type="text" name="ansContent" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">문의 분류</label>
                        <div class="form_box">
                            <select class="sel_box" name="csSel">
                                <option value="">구분없음</option>
                                <option value="NM">일반문의</option>
                                <option value="SM">기능문의</option>
                                <option value="PM">신고문의</option>
                            </select>
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">답변여부</label>
                        <div class="form_box">
                            <select class="sel_box" name="ansSel">
                                <option value="">구분없음</option>
                                <option value="Y">답변완료</option>
                                <option value="N">미답변</option>
                            </select>
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
                        <caption>1:1문의 목록 테이블</caption>
                        <colgroup>
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
<!--                             <col width=""> -->
                            <col width="">
                            <col width="">
                            <col width="">
                            <col width="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
<!--                                 <th scope="col">내용</th> -->
                                <th scope="col">작성일자</th>
                                <th scope="col">답변여부</th>
                                <th scope="col">답변일자</th>
                                <th scope="col">삭제여부</th>
                                <th scope="col">문의분류</th>
                            </tr>
                        </thead>
                        <tbody>
<%for(CsboardVo vo : csboardVoList){%>
                            <tr>
								<td><%=vo.getqNo() %></td>
								<td><%=vo.getqTit() %></td>
								<td><%=vo.getMemNick() %></td>
<%-- 								<td><%=vo.getqConFirstLine()%></td> --%>
								<td><%=vo.getqWriteDate() %></td>
								<td><%=vo.getAnsewrDate() %></td>
								<td><%=vo.getAnsewrDate() %></td>
								<td><%=vo.getDelYn() %></td>
								<td><%=vo.getQuestionCategoryName() %></td>
                            </tr>
<%} %>
                        </tbody>
                    </table>
                </div>

                <div class="paging_box mt30">
                    <ul>
<%if(pvo.getStartPage() != 1) {%>
                        <li class="prev_all"><a href="/jdgr/admin/csboard/list?pno=1" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/admin/csboard/list?pno=<%=pvo.getStartPage()-1 %>" title="이전페이지로 이동"></a></li>
<%} %>     
               
<%for(int i = pvo.getStartPage(); i<= pvo.getEndPage(); i++) {%> 
	<%if(i == pvo.getCurrentPage()) {%>
						<li class="on"><a href="/jdgr/admin/csboard/list?pno=<%= i%>"><%= i%></a></li>
	<%}else{ %>	
						<li><a href="/jdgr/admin/csboard/list?pno=<%= i%>"><%= i%></a></li>
<%} }%>

<%if(pvo.getEndPage() != pvo.getMaxPage()) {%>
                        <li class="next"><a href="/jdgr/admin/csboard/list?pno=<%=pvo.getEndPage()+1 %>" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="/jdgr/admin/csboard/list?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
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
            location.href = '/jdgr/admin/csboard/detail?no='+no+'&currPage=<%=pvo.getCurrentPage() %>';
        }
        // 초기화 버튼
        function reset(){
            const inputArr = document.querySelectorAll(".form_box input");
            const csSelOptionArr = document.querySelectorAll("select[name=csSel] option");
            const ansSelOptionArr = document.querySelectorAll("select[name=ansSel] option");      

            for(let i=0; i<inputArr.length; i++){
                inputArr[i].value = null;
            }

            csSelOptionArr[0].selected = true;
            ansSelOptionArr[0].selected = true;
            sendPost(); //검색결과도 같이 초기화 하기 위해 사용함
        }
        //검색 버튼
        function sendPost(){
            let aaTag = document.querySelector(".aa");
            let divTag = document.querySelector(".search_box");
            let formTag = document.createElement("form");
            formTag.setAttribute('method','get');
            formTag.setAttribute('action','/jdgr/admin/csboard/search?pno=1');
            formTag.appendChild(divTag);
            aaTag.appendChild(formTag);
            formTag.submit();
        }
<%if(searchVo != null){%>
        //검색 후에도 검색어 남기기
		function setSearchArea(){
            const titleIntput = document.querySelector(".search_box input[name=title]");
            const contentIntput = document.querySelector(".search_box input[name=content]");
            const writerIntput = document.querySelector(".search_box input[name=writer]");
            const ansContentIntput = document.querySelector(".search_box input[name=ansContent]");
            const csSelOptionArr = document.querySelectorAll("select[name=csSel] option");
            const ansSelOptionArr = document.querySelectorAll("select[name=ansSel] option");

            
            titleIntput.value = '<%=searchVo.getqTit()%>';
            contentIntput.value = '<%=searchVo.getqCon()%>';
            writerIntput.value = '<%=searchVo.getMemNick()%>';
            ansContentIntput.value = '<%=searchVo.getAnsewr()%>';
            for(let i = 0; i< csSelOptionArr.length; ++i){
                if(csSelOptionArr[i].value == '<%=searchVo.getQuestionCategory()%>'){
                    csSelOptionArr[i].selected = true;
                } 
            }
            for(let i = 0; i< ansSelOptionArr.length; ++i){
                if(ansSelOptionArr[i].value == '<%=searchVo.getAnsewrDate()%>'){
                    ansSelOptionArr[i].selected = true;
                } 
            }
        }
        setSearchArea();

        function setPageArea(){
            const aTagArr = document.querySelectorAll(".paging_box ul li a");
            for(let i=0; i<aTagArr.length; ++i){
                aTagArr[i].href = aTagArr[i].href.replace('list','search');
                aTagArr[i].href += '&title=<%=searchVo.getqTit()%>&content=<%=searchVo.getqCon()%>&writer=<%=searchVo.getMemNick()%>&ansContent=<%=searchVo.getAnsewr()%>&csSel=<%=searchVo.getQuestionCategory()%>&ansSel=<%=searchVo.getAnsewrDate()%>';
            }
        }
        setPageArea();
<%}%>

    </script>