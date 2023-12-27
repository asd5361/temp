<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
    
 <!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>댓글 상세 조회</h2>
            </div>

            <!-- 가로 테이블 -->
            <div class="detail_box">
                <!-- 테이블 -->
                <div class="tbl_group">
            
                    <div class="tbl_box">
                        <table>
                            <caption>댓글 상세</caption>
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="35%"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><label for="">댓글 번호</label></th>
                                    <td></td>
                                    <th scope="row"><label for="">포스트 번호</label></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td></td>
                                    <th scope="row"><label for=""></label></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성 일자</label></th>
                                    <td>2023.11.11 23:00</td>
                                    <th scope="row"><label for="">수정 일자</label></th>
                                    <td>2023.11.21 23:30</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">공개 여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">공개</option>
                                                <option value="">비공개</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">댓글 전체 내용</label></th>
                                    <td colspan="3">대래대ㅐ래래대대대댓글</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">대댓글</label></th>
                                    <td colspan="3">어떻게 구성할까

                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">제재 처리</label></th>
                                    <td colspan="3">
                                         <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">제재X</option>
                                                <option value="">로그인 3일 정지</option>
                                                <option value="">로그인 7일 정지</option>
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
                        <a href="" class="btn_grayline">목록가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="" class="btn_grayline">저장</a>
                    </div>
                    <div class="btn_box">
                        <a href="" class="btn_blue">처리완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->
        
        
        <%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>