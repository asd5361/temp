<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/admin/common/header.jsp" %>
                
            <!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>포스트 관리 목록</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">작성자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">제목</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>

                   
                </div>
                
                <!-- 버튼 -->
                <div class="btn_box_group right">
                    <div class="btn_box">
                        <button class="btn_gray">초기화</button>
                    </div>
                    <div class="btn_box">
                        <button class="btn_black">검색</button>
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
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">작성자</th>
                                <th scope="col">블로그 번호</th>
                                <th scope="col">포스트 번호</th>
                                <th scope="col">카테고리명</th>
                                <th scope="col">제목</th>
                                <th scope="col">조회수</th>
                                <th scope="col">공감수</th>
                                <th scope="col">댓글수</th>
                                <th scope="col">등록일자</th>
                                <th scope="col">수정일자</th>
                                <th scope="col">공개여부</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>심원용</td>
                                <td>1</td>
                                <td>1</td>
                                <td>음식</td>
                                <td>마라탕은 맛있다.</td>
                                <td>3</td>
                                <td>5</td>
                                <td>5</td>
                                <td>2023.11.26 00:00</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="paging_box mt30">
                    <ul>
                        <li class="prev_all"><a href="" title="최신페이지로 이동"></a></li>
                        <li class="prev"><a href="" title="이전페이지로 이동"></a></li>
                        <li class="on"><a href="">1</a></li>
                        <li><a href="">2</a></li>
                        <li><a href="">3</a></li>
                        <li><a href="">4</a></li>
                        <li><a href="">5</a></li>
                        <li class="next"><a href="" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="" title="마지막페이지로 이동"></a></li>
                    </ul>
                </div>

            </div>
            <!-- //container -->
<%@include file = "/WEB-INF/views/admin/common/footer.jsp" %>
            