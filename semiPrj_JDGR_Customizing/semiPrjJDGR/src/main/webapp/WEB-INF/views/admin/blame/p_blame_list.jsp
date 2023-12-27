<%@page import="com.semi.jdgr.admin.blame.vo.AdminPostBlameVo"%>
<%@page import="com.semi.jdgr.page.vo.AdminBlamePageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
    	List<AdminPostBlameVo> blameVoList = (List<AdminPostBlameVo>) request.getAttribute("blameVoList");
    	AdminBlamePageVo pvo = (AdminBlamePageVo)request.getAttribute("pvo");
    	AdminPostBlameVo apbv = (AdminPostBlameVo) request.getAttribute("apbv");
    %>
    
    <%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
 
     <!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>포스트 신고 목록 조회</h2>
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
                        <label for="sel_01">포스트 제목</label>
                        <div class="form_box">
                            <input type="text" id="inp_02"  name="title">
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
                                <option value="1">제재 X</option>
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
                                <th scope="col">포스트 제목</th>
                                <th scope="col">신고 일자</th>
                                <th scope="col">신고 구분</th>
                                <th scope="col">제재 여부</th>
                                <th scope="col">처리 일자</th>
                            </tr>
                        </thead>
                        <tbody>
                       
                        <% for(AdminPostBlameVo vo : blameVoList){ %>
                            <tr>
                                <td><%= vo.getpBlaNo() %></td>
                                <td><%= vo.getpBlamerNo() %></td>
                                <td><%= vo.getpWriterNo() %></td>
                                <td><%= vo.getpBlaTit() %></td>
                                <td><%= vo.getpBlaDate() %></td>
                                <td><%= vo.getpBlaList() %></td>
                                <td><%= vo.getpSancYn() %></td>
                                <td><%= vo.getpAnsDate() %></td>
                            </tr>
                        <%} %>
                            
                        </tbody>
                    </table>
                    
                </div>

                <div class="paging_box mt30">
                    <ul>
                        <%if(pvo.getStartPage() != 1) {%>
                        <li class="prev_all"><a href="/jdgr/admin/blame/p_blame_list?pno=1" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="/jdgr/admin/blame/p_blame_list?pno=<%=pvo.getStartPage()-1 %>" title="이전페이지로 이동">이전</a></li>

						<%} %>
						
						<%for(int i = pvo.getStartPage(); i<=pvo.getEndPage(); i++) {%>
							<%if(i == pvo.getCurrentPage()) {%>
								<li class="on"><a href="/jdgr/admin/blame/p_blame_list?pno=<%=i%>"><%=i %></a></li>
							<%}else{ %>
								<li><a href="/jdgr/admin/blame/p_blame_list?pno=<%=i%>"><%=i %></a></li>
						<%} %>
							<% } %>
						<%if(pvo.getEndPage() != pvo.getMaxPage()) {%>
	                        <li class="next"><a href="/jdgr/admin/blame/p_blame_list?pno=<%=pvo.getEndPage()+1 %>" title="다음페이지로 이동">다음</a></li>
	                        <li class="next_all"><a href="/jdgr/admin/blame/p_blame_list?pno=<%=pvo.getMaxPage() %>" title="마지막페이지로 이동"></a></li>
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
        location.href = '/jdgr/admin/blame/p_blame_detail?no='+no+'&currPage=<%=pvo.getCurrentPage()%>'
    }
	
    
    //초기화 버튼
    function resetSearch() {
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
    
    function search(){
	    let aaTag = document.querySelector(".aa");
	    let divTag = document.querySelector(".search_box");
	    let formTag = document.createElement("form");
	    formTag.setAttribute('method','get');
	    formTag.setAttribute('action','/jdgr/admin/blame/p_blame_detail?pno=?');
	    formTag.appendChild(divTag);
	    aaTag.appendChild(formTag);
	    formTag.submit();
    }   
    
    
    function search() {
        // 검색 조건을 가져오기
        let aaTag = document.querySelector(".aa");
        aaTag.appenChild(divTag);
        var blamer = document.getElementById('inp_02_blamer').value;
        var writer = document.getElementById('inp_02_writer').value;
        var title = document.getElementById('inp_02_title').value;
        var blameDate = document.getElementById('inp_02_blameDate').value;
        var answerDate = document.getElementById('inp_02_answerDate').value;
        var delDate = document.getElementById('inp_02_delDate').value;
		 // (다른 검색 조건들도 필요에 따라 추가)

				        // 검색 조건을 객체로 만들기
				        var searchParams = {
				            blamer: blamer,
// 				            writer: writer
				            // (다른 검색 조건들도 필요에 따라 추가)
				        };

				        // 서버로 전송할 URL 설정 (서버의 실제 URL에 맞게 수정해야 함)
				        var url = '/jdgr/admin/blame/p_blame_list/search';

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

				        var requestBody = JSON.stringify(searchParams);
				        xhr.send(requestBody);
				    }

				    // 서버 응답을 기반으로 테이블을 채우는 함수
				    function populateTable(data) {
				        var table = document.getElementById('blameTable');
				        var tbody = document.createElement('tbody');

				        // 데이터를 반복하여 행을 추가
				        for (var i = 0; i < data.length; i++) {
				            var vo = data[i];
				            var row = document.createElement('tr');

				            // 각 열 데이터를 채워넣기
				            var columns = [
				                vo.pBlaNo,
				                vo.pBlamerNo,
				                vo.pWriterNo,
				                vo.pBlaTit,
				                vo.pBlaDate,
				                vo.pBlaList,
				                vo.pSancYn,
				                vo.pAnsDate,
				                vo.pDelYn
				            ];

				            for (var j = 0; j < columns.length; j++) {
				                var cell = document.createElement('td');
				                cell.textContent = columns[j];
				                row.appendChild(cell);
				            }

				            tbody.appendChild(row);
				        }

				        // 테이블의 기존 tbody 삭제 후 새로운 tbody 추가
				        table.removeChild(table.getElementsByTagName('tbody')[0]);
				        table.appendChild(tbody);
				    }


				</script>