<%@page import="com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       

    <%
     String pno = (String)request.getAttribute("pno");
    	String currPage = (String)request.getAttribute("currPage");
     AdminReplyBlameVo vo = (AdminReplyBlameVo) request.getAttribute("vo");
 	%>
<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
       
<!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>신고 / 제재 상세 조회</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>신고 / 제재</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                            	<tr>
                                    <th scope="row"><label for="">신고번호</label></th>
                                    <td><%= vo.getrBlaNo()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">댓글 번호</label></th>
                                    <td><%= vo.getrNo() %></td>
                                    <th scope="row"><label for="">신고 구분</label></th>
                                    <td><%= vo.getrBlaList()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">신고자</label></th>
                                    <td><%= vo.getrBlamerNo() %></td>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td><%= vo.getrWriterNo() %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">댓글 내용</label></th>
                                    <td><%= vo.getrBlaCon() %></td>
                                    <th scope="row"><label for="">신고 일자</label></th>
                                    <td><%= vo.getrBlaDate()   %></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">제재 여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box" name="sancYn">
                                                <option value="">이건 어떻게 가져오지</option>
                                                <option value="3">로그인 정지 3일</option>
                                                <option value="7">로그인 정지 7일</option>
                                            </select>
                                            <input type="hidden" name="pno" value="<%=pno%>">
                                            <input type="hidden" name="currPage" value="<%=currPage%>">                                            
                                        </div>
                                    </td>
                                    <th scope="row"><label for="">답변 일자</label></th>
                                    <td><%= vo.getrAnsDate()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">처리일자</label></th>
                                    <td><%= vo.getrDelYn()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">세부 신고 사유</label></th>
                                    <td colspan="3"><%= vo.getrBlaDetail() %></td>
                                </tr>
<!--                                 <tr> -->
<!--                                     <th scope="row"><label for="inp_03">답변 내용</label></th> -->
<!--                                     <td colspan="3"> -->
<!--                                          <div class="form_box"> -->
<!--                                             <textarea placeholder="답변을 입력하세요"></textarea> -->
<!--                                         </div> -->
<!--                                     </td> -->
<!--                                 </tr> -->
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="btn_box_group right mt20">
                    <div class="btn_box">
                        <a href="/jdgr/admin/blame/r_blame_list?no=1" class="btn_grayline">목록가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="javascript:sendPost();" class="btn_grayline">저장</a>
                    </div>
                    <div class="btn_box">
                        <a href="" class="btn_blue">처리완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

   <%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>
   
   <script>
   function sendPost(){
       let tableTag = document.querySelector(".tbl_box table");
       let formTag = document.createElement("form");
       let divTag = document.querySelector(".tbl_box");
       formTag.setAttribute('method','get');
       formTag.setAttribute('action','/jdgr/admin/blame/r_blame_update');
       formTag.appendChild(tableTag);
       divTag.appendChild(formTag);
       formTag.submit();
   }
</script>
   
  