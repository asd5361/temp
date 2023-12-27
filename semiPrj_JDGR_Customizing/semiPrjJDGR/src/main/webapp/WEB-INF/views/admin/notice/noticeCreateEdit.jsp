<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
<% String pno = (String)request.getAttribute("pno"); %>
	 <!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>공지사항 작성/수정</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>공지사항 상세 테이블</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><label for="">글번호</label></th>
                                    <td>001</td>
                                    <th scope="row"><label for="">조회수</label></th>
                                    <td>1001</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성일자</label></th>
                                    <td>2023.11.11 23:00</td>
                                    <th scope="row"><label for="">수정일자</label></th>
                                    <td>2023.11.11 23:30</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">공개여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box" name="del">
                                                <option value="Y">공개</option>
                                                <option value="N">비공개</option>
                                            </select>
                                        </div>
                                    </td>
                                    <th scope="row"><label for="">고정여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box" name="fixed">
                                                <option value="Y">고정</option>
                                                <option value="N">미고정</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td colspan="3"><%=loginAdminVo.getAdminName() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">제목</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <input type="text" id="inp_03" name="title" placeholder="제목 입력해주세요">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">내용</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <textarea placeholder="공지사항 내용을 입력해주세요" name="content"></textarea>
                                            <!-- <input type="text" id="inp_03"> -->
                                        </div>
                                    </td>
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="btn_box_group right mt20">
                    <div class="btn_box">
                        <a href="/jdgr/admin/notice/list?pno=<%=pno %>" class="btn_grayline">목록가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="javascript:sendPost();" class="btn_blue">작성완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>   

    <script>
        console.log('pno='+'<%=pno%>');

        function sendPost(){
        let tableTag = document.querySelector(".tbl_box table");
        let formTag = document.createElement("form");
        let divTag = document.querySelector(".tbl_box");
        formTag.setAttribute('method','post');
        formTag.setAttribute('action','/jdgr/admin/notice/write?pno=<%=pno%>');
        formTag.appendChild(tableTag);
        divTag.appendChild(formTag);
        formTag.submit();

            
        }
        
    </script>
