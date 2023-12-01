<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

	<!-- container -->
        <div class="container">

            <!-- 내용 -->
            

            <!-- 제목 -->
            <div class="tit_box">
                <h2>공지사항 상세 조회</h2>
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
                                            <select class="sel_box">
                                                <option value="">공개</option>
                                                <option value="">비공개</option>
                                            </select>
                                        </div>
                                    </td>
                                    <th scope="row"><label for="">고정여부</label></th>
                                    <td>
                                        <div class="form_box">
                                            <select class="sel_box">
                                                <option value="">고정</option>
                                                <option value="">미고정</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td colspan="3">관리자</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">제목</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <input type="text" id="inp_03">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">내용</label></th>
                                    <td colspan="3">
                                        <div class="form_box">
                                            <textarea >

                                            </textarea>
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
                        <a href="" class="btn_grayline">목록가기</a>
                    </div>
                    <div class="btn_box">
                        <a href="" class="btn_blue">수정하기</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>