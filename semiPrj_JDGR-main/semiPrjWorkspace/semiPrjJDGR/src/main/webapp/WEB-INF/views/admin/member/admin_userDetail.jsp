<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/admin_header.jsp" %>

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
                                    <td>015</td>
                                    <th scope="row"><label for="">이름</label></th>
                                    <td>이름</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">아이디</label></th>
                                    <td>id</td>
                                    <th scope="row"><label for="">비밀번호</label></th>
                                    <td>123</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">닉네임</label></th>
                                    <td>닉네임</td>
                                    <th scope="row"><label for="">이메일</label></th>
                                    <td>이메일</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">전화번호</label></th>
                                    <td>전화번호</td>
                                    <th scope="row"><label for="">탈퇴여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">N</option>
                                                <option value="">Y</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">가입일자</label></th>
                                    <td>2023.11.26 00:00</td>
                                    <th scope="row"><label for="">탈퇴일자</label></th>
                                    <td>2023.11.26 00:00</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="" name="">로그인정지</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">-</option>
                                                <option value="">3일</option>
                                                <option value="">5일</option>
                                            </select>
                                        </div>
                                    </td>
                                    <th scope="row"><label for="">제재여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">N</option>
                                                <option value="">Y</option>
                                            </select>
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
                        <a href="" class="btn_blue">수정완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

<%@ include file="/WEB-INF/views/common/admin_footer.jsp" %>