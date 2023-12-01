<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                                    <td>011</td>
                                    <th scope="row"><label for="">문의 분류</label></th>
                                    <td>일반 문의</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성자</label></th>
                                    <td>김병장</td>
                                    <th scope="row"><label for="">담당 관리자</label></th>
                                    <td>관리자</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="">작성 일자</label></th>
                                    <td>2023.11.11 23:00</td>
                                    <th scope="row"><label for="">답변 일자</label></th>
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
                                    <th scope="row"><label for="">답변 수정 일자</label></th>
                                    <td>2023.11.22 23:30</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">제목</label></th>
                                    <td colspan="3">컬럼내용컬럼내용컬럼내용컬럼내용컬럼내용컬럼내용</td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">내용</label></th>
                                    <td colspan="3">컬<br>럼<br>컬<br>럼<br>컬<br>럼<br>컬<br>럼<br>컬<br>럼<br>컬<br>
                                        럼내용컬럼내용컬럼내용컬럼내용컬럼내용컬럼내용
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="inp_03">답변 내용</label></th>
                                    <td colspan="3">
                                         <div class="form_box">
                                            <textarea placeholder="답변 내용을 입력해주세요"></textarea>
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
                        <a href="" class="btn_blue">답변완료</a>
                    </div>
                </div>

            </div>
        </div>
        <!-- //container -->
        
<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>