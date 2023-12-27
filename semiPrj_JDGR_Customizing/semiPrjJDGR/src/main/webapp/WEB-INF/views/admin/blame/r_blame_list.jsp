<%@page import="com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo"%>
<%@page import="com.semi.jdgr.page.vo.AdminBlamePageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
    	List<AdminReplyBlameVo> blameVoList = (List<AdminReplyBlameVo>) request.getAttribute("blameVoList");
    	AdminBlamePageVo pvo = (AdminBlamePageVo)request.getAttribute("pvo");
    	AdminReplyBlameVo arbv = (AdminReplyBlameVo) request.getAttribute("arbv");
    %>
    
    <%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
 
     <!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>댓글 신고 목록 조회</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="aa"></div>
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">신고자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02" name="blamer">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">작성자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02" name="writer">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">댓글 내용</label>
                        <div class="form_box">
                            <input type="text" id="inp_02"  name="content">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">신고 일자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02"   name="blameDate">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">신고 구분</label>
                        <div class="form_box">
                            <select class="sel_box" name = "blameList">
                                <option value="1">스팸홍보 / 도배글</option>
                                <option value="2">음란물</option>
                                <option value="3">불법 정보를 포함</option>
                                <option value="4">청소년 유해</option>
                                <option value="5">욕설/생명경시/혐오/차별표현</option>
                                <option value="6">개인정보 노출 위험</option>
                                <option value="7">불쾌한 표현</option>
                            </select>
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">제재 여부</label>
                        <div class="form_box">
                            <select class="sel_box" name="sancYn">
                                <option value="1">제재 x</option>
                                <option value="2">로그인 3일 정지</option>
                                <option value="3">로그인 7일 정지</option>
                            </select>
                        </div>
                    </div>
                </div>
                
                <!-- 버튼 -->
                <div class="btn_box_group right">
                    <div class="btn_box">
                        <button class="btn_gray" onclick="resetSearch()">초기화</button>
                    </div>
                    <div class="btn_box">
                        <button class="btn_black" onclick="search()">검색</button>
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
	                            <col width="">
	                            <col width="">
	                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">신고자</th>
                                <th scope="col">작성자</th>
                                <th scope="col">댓글 내용</th>
                                <th scope="col">신고 일자</th>
                                <th scope="col">신고 구분</th>
                                <th scope="col">제재 여부</th>
                                <th scope="col">처리 일자</th>
<!--                                 <th scope="col">처리 일자</th> -->
                            </tr>
                        </thead>
                        <tbody>
                        <% for(AdminReplyBlameVo vo : blameVoList){ %>
                            <tr>
                                <td><%= vo.getrBlaNo() %></td>
                                <td><%= vo.getrBlamerNo() %></td>
                                <td><%= vo.getrWriterNo() %></td>
                                <td><%= vo.getrBlaCon() %></td>
                                <td><%= vo.getrBlaDate() %></td>
                                <td><%= vo.getrBlaList() %></td>
                                <td><%= vo.getrSancYn() %></td>
                                <td><%= vo.getrAnsDate() %></td>
<%--                                 <td><%= vo.getrDelYn() %></td> --%>
                            </tr>
                        <%} %>
                            
                        </tbody>
                    </table>
                    
                </div>

                <div class="paging_box mt30">
                    <ul>
                        <%if(pvo.getStartPage() != 1) {%>
                        <li class="prev_all"><a href="/jdgr/admin/blame/r_blame_list?pno=1" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/admin/blame/r_blame_list?pno=<%=pvo.getStartPage()-1 %>" title="이전페이지로 이동"></a></li>

						<%} %>

						<%for(int i = pvo.getStartPage(); i<=pvo.getEndPage(); i++) {%>
							<%if(i == pvo.getCurrentPage()) {%>
								<li class="on"><a href="/jdgr/admin/blame/r_blame_list?pno=<%=i%>"><%=i %></a></li>
							<%}else{ %>
								<li><a href="/jdgr/admin/blame/r_blame_list?pno=<%=i%>"><%=i %></a></li>
						<%} } %>
						<%if(pvo.getEndPage() != pvo.getMaxPage()) {%>
	                        <li class="next"><a href="/jdgr/admin/blame/r_blame_list?pno=<%=pvo.getEndPage()+1 %>" title="다음페이지로 이동"></a></li>
	                        <li class="next_all"><a href="/jdgr/admin/blame/r_blame_list?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
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
	        location.href = '/jdgr/admin/blame/r_blame_detail?no='+no+'&currPage=<%=pvo.getCurrentPage()%>'
	    }
    
	    
	    //초기화 버튼
        function resetSearch(){
            const inputArr = document.querySelectorAll(".form_box input");
            const blameListOptionArr = document.querySelectorAll("select[name=blameList] option");
            const sancYnOptionArr = document.querySelectorAll("select[name=sancYn] option");      

            for(let i=0; i<inputArr.length; i++){
                inputArr[i].value = null;
            }

            blameListOptionArr[0].selected = true;
            sancYnOptionArr[0].selected = true;
            search(); //검색결과도 같이 초기화 하기 위해 사용함
        }
		
	    
	    //검색 버튼
// 	            function search(){
//             let aaTag = document.querySelector(".aa");
//             let divTag = document.querySelector(".search_box");
//             let formTag = document.createElement("form");
//             formTag.setAttribute('method','get');
//             formTag.setAttribute('action','/jdgr/admin/blame/r_blame_list?pno=?');
//             formTag.appendChild(divTag);
//             aaTag.appendChild(formTag);
//             formTag.submit();
//         }       
	    
	    function search() {
        // 검색 조건을 가져오기
//         let aaTag = document.querySelector(".aa");
//         aaTag.appendChild(divTag);
        var blamer = document.getElementById('inp_02_blamer').value;
        var writer = document.getElementById('inp_02_writer').value;
        var content = document.getElementById('inp_02_content').value;
        var blameDate = document.getElementById('inp_02_blameDate').value;
        var blameList = document.querySelector('select[name="blameList"]').value;
        var sancYn = document.querySelector('select[name="sancYn"]').value;

	    

        // 검색 조건을 객체로 만들기
        var searchParams = {
            blamer: blamer,
            writer: writer,
            content: content,
            blameDate: blameDate,
            blameList: blameList,
            sancYn: sancYn
            // (다른 검색 조건들도 필요에 따라 추가)
        };

        // 서버로 전송할 URL 설정 (서버의 실제 URL에 맞게 수정해야 함)
        var url = '/jdgr/admin/blame/r_blame_list/search';

        // Ajax 요청
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onload = function () {
            if (xhr.status === 200) {
                // 응답을 JSON으로 파싱
                var response = JSON.parse(xhr.responseText);

                // 테이블을 채우는 함수 호출
                populateTable(response);
            } else {
                console.error('서버 에러:', xhr.status);
            }
        };
        
        
        // 검색 매개변수를 JSON으로 변환하여 요청 전송
	        var requestBody = JSON.stringify(searchParams);
	        xhr.send(requestBody);
	    }

	    // 서버 응답을 기반으로 테이블을 채우는 함수
	    function populateTable(data) {
	        var tableBody = document.querySelector('.tbl_box tbody');
	        tableBody.innerHTML = ''; // 기존 행 삭제

	        // 데이터를 반복하여 행을 추가
	        for (var i = 0; i < data.length; i++) {
	            var vo = data[i];
	            var row = document.createElement('tr');

	            // 각 열 데이터를 채워넣기
	            var columns = [
	                vo.rBlaNo,
	                vo.rBlamerNo,
	                vo.rWriterNo,
	                vo.rBlaCon,
	                vo.rBlaDate,
	                vo.rBlaList,
	                vo.rSancYn,
	                vo.rAnsDate,
	                vo.rDelYn
	            ];

	            for (var j = 0; j < columns.length; j++) {
	                var cell = document.createElement('td');
	                cell.textContent = columns[j];
	                row.appendChild(cell);
	            }

	            tbody.appendChild(row);
	        }

	    }


	</script>
	
	
	
	
	
	
	
	
	
	
	
<!-- 	//         var answerDate = document.getElementById('inp_02_answerDate').value; -->
<!-- //         var delDate = document.getElementById('inp_02_delDate').value; -->
<!-- //         // (다른 검색 조건들도 필요에 따라 추가) -->
	    
	    
	    
<%-- <%-- 	            <%if(arbv != null){%> --%> --%>
<!-- //         //검색 후에도 검색어 남기기 -->
<!-- // 		function setSearchArea(){ -->
<!-- //             const blamerIntput = document.querySelector(".search_box input[name=blamer]"); -->
<!-- //             const writerIntput = document.querySelector(".search_box input[name=writer]"); -->
<!-- //             const contentIntput = document.querySelector(".search_box input[name=content]"); -->
<!-- //             const blameDateIntput = document.querySelector(".search_box input[name=blameDate]"); -->
<!-- //             const blameListOptionArr = document.querySelectorAll("select[name=blameList] option"); -->
<!-- //             const sancYnOptionArr = document.querySelectorAll("select[name=sancYn] option");       -->

            
<%-- <%--             blamerIntput.value = '<%=arbv.getrBlamerNo()%>'; --%> --%>
<%-- <%--             writerIntput.value = '<%=arbv.getrWriterNo()%>'; --%> --%>
<%-- <%--             contentIntput.value = '<%=arbv.getrBlaCon()%>'; --%> --%>
<%-- <%--             blameDateIntput.value = '<%=arbv.getrBlaDate()%>'; --%> --%>
            
<!-- //             for(let i = 0; i< blameListOptionArr.length; ++i){ -->
<%-- <%--                 if(blameListOptionArr[i].value == '<%=arbv.getBlameList()%>'){ --%> --%>
<!-- //                 	blameListOptionArr[i].selected = true; -->
<!-- //                 }  -->
<!-- //             } -->
<!-- //             for(let i = 0; i< sancYnOptionArr.length; ++i){ -->
<%-- <%--                 if(sancYnOptionArr[i].value == '<%=arbv.getSancYn()%>'){ --%> --%>
<!-- //                 	sancYnOptionArr[i].selected = true; -->
<!-- //                 }  -->
<!-- //             } -->
<!-- //         } -->
	    
	    
<!-- // 		  setSearchArea(); -->
	    