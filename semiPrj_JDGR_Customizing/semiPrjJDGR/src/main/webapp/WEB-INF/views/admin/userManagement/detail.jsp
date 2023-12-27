<%@page import="com.semi.jdgr.user.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String pno = (String)request.getAttribute("pno"); %>
<% MemberVo vo = (MemberVo)request.getAttribute("vo"); %>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

		<!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>회원목록 상세 조회</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>회원목록 상세 테이블</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><label for="">회원 번호</label></th>
                                    <td><%=vo.getMemNo()%></td><input type="hidden" name="memNo" value="<%=vo.getMemNo()%>">
                                    <th scope="row"><label for="">이름</label></th>
                                    <td><%=vo.getMemName()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">아이디</label></th>
                                    <td><%=vo.getMemId()%></td>
                                    <th scope="row"><label for="">비밀번호</label></th>
                                    <td><%=vo.getMemPwd()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">닉네임</label></th>
                                    <td><%=vo.getMemNick()%></td>
                                    <th scope="row"><label for="">이메일</label></th>
                                    <td><%=vo.getMemEmail()%></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">전화번호</label></th>
                                    <td><%=vo.getMemPhoneNum()%></td>
                                    <th scope="row"><label for="">탈퇴여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box Quit" name="quitYn">
                                                <option value="N">N</option>
                                                <option value="Y">Y</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">가입일자</label></th>
                                    <td><%=vo.getEnrollDate()%></td>
                                    <th scope="row"><label for="">탈퇴일자</label></th>
                                    <td><%=vo.getQuitData()%></td>
                                </tr>
<!--                                 <tr> -->
<!--                                     <th scope="row"><label for="" name="">로그인정지</label></th> -->
<!--                                     <td> -->
<!--                                         <div class="form_box"> -->
<!--                                             <select class="sel_box Longin" name="loginban"> -->
<!--                                                 <option value="">-</option> -->
<!--                                                 <option value="3">3일</option> -->
<!--                                                 <option value="7">7일</option> -->
<!--                                             </select> -->
<!--                                         </div> -->
<!--                                     </td> -->
<!--                                     <th scope="row"><label for="">제재여부</label></th> -->
<!--                                     <td> -->
<!--                                         <div class="form_box Ban" name="banYn"> -->
<!--                                             <select class="sel_box"> -->
<!--                                                 <option value="N">N</option> -->
<!--                                                 <option value="Y">Y</option> -->
<!--                                             </select> -->
<!--                                         </div> -->
<!--                                     </td> -->
<!--                                 </tr> -->
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="btn_box_group right mt20">
                    <div class="btn_box">
                        <a href="/jdgr/admin/userManagement/list?pno=1" class="btn_grayline">뒤로가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="javascript:sendPost();" class="btn_blue">수정완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>

<script>
    //DB값에 따라 SELECTED 변경
	const delArr = document.querySelectorAll(".Quit option");
    for(let i=0; i<delArr.length; ++i){
        if(delArr[i].value==='<%=vo.getQuitYn() %>'){
            delArr[i].selected = true;
        }
    }
  	//DB값에 따라 SELECTED 변경
//     const longinArr = document.querySelectorAll(".Longin option");
//     for(let i=0; i<longinArr.length; ++i){
<%--         if(longinArr[i].value==='<%=vo.getDelYn() %>'){ --%>
//             longinArr[i].selected = true;
//         }
//     }
  	//DB값에 따라 SELECTED 변경
//     const banArr = document.querySelectorAll(".Ban option");
//     for(let i=0; i<banArr.length; ++i){
<%--         if(banArr[i].value==='<%=vo.getDelYn() %>'){ --%>
//             banArr[i].selected = true;
//         }
//     }
    
    function sendPost(){
        let tableTag = document.querySelector(".tbl_box table");
        let formTag = document.createElement("form");
        let divTag = document.querySelector(".tbl_box");
        formTag.setAttribute('method','post');
        formTag.setAttribute('action','/jdgr/admin/userManagement/detail');
        formTag.appendChild(tableTag);
        divTag.appendChild(formTag);
        formTag.submit();
        }
    
</script>