<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

		<!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>관리자 설정</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>관리자 설정 테이블</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                                
                                <tr>
                                    <th scope="row"><label for="inp_03">관리자명</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <input type="text" id="inp_03"placeholder="관리자명을 입력해주세요">
                                        </div>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <th scope="row"><label for="inp_03">관리자 비밀번호</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <input type="text" id="inp_03"placeholder="비밀번호를 입력해주세요">
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <th scope="row"><label for="inp_03">가입일자</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            2023.11.26 00:00
                                        </div>
                                    </td>
                                </tr>

                         
                                
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="btn_box_group right mt20">
                    <div class="btn_box">
                        <a href="" class="btn_grayline">뒤로가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="" class="btn_blue">완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>