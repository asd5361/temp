<%@page import="com.semi.jdgr.csboard.vo.CsboardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String pno = (String)request.getAttribute("pno"); %>
<% CsboardVo vo = (CsboardVo)request.getAttribute("vo"); %>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

	<!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>고객센터 상세 조회</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>고객센터 상세 테이블</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><label for="">문의 번호</label></th>
                                    <td><%=vo.getqNo() %></td>
                                    <th scope="row"><label for="">문의 분류</label></th>
                                    <td><%=vo.getQuestionCategoryName() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td><%=vo.getMemNick()%></td>
                                    <th scope="row"><label for="">담당 관리자</label></th>
                                    <td><%=vo.getAdminName() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성 일자</label></th>
                                    <td><%=vo.getqWriteDate() %></td>
                                    <th scope="row"><label for="">답변 일자</label></th>
                                    <td><%=vo.getAnsewrDate() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">공개 여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box" name="del">
                                                <option value="Y">공개</option>
                                                <option value="N">비공개</option>
                                            </select>
                                        </div>
                                    </td>
                                    <th scope="row"><label for="">답변 수정 일자</label></th>
                                    <td><%=vo.getUpdateDate()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">제목</label></th>
                                    <td colspan="3"><%=vo.getqTit() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">내용</label></th>
                                    <td colspan="3"><%=vo.getqCon() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">답변 내용</label></th>
                                    <td colspan="3">
                                         <div class="form_box">
                                            <textarea name="ansewr"placeholder="답변 내용을 입력해주세요"><%=vo.getAnsewr() %></textarea>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="btn_box_group right mt20">
                    <div class="btn_box">
                        <a href="/jdgr/admin/csboard/list?pno=<%=pno %>" class="btn_grayline">목록가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="javascript:sendPost();" class="btn_blue">답변완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->
        
<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>
<script>
    //DB값에 따라 SELECTED 변경
	const optionArr = document.querySelectorAll(".sel_box option");
    for(let i=0; i<optionArr.length; ++i){
        if(optionArr[i].value==='<%=vo.getDelYn() %>'){
        	optionArr[i].selected = true;
        }
    }
    
    function sendPost(){
        let tableTag = document.querySelector(".tbl_box table");
        let formTag = document.createElement("form");
        let divTag = document.querySelector(".tbl_box");
        formTag.setAttribute('method','post');
        formTag.setAttribute('action','/jdgr/admin/csboard/ansewr?pno=<%=pno %>&no=<%=vo.getqNo()%>&ansDate=<%=vo.getAnsewrDate()%>');
        formTag.appendChild(tableTag);
        divTag.appendChild(formTag);
        formTag.submit();
        }
    
</script>