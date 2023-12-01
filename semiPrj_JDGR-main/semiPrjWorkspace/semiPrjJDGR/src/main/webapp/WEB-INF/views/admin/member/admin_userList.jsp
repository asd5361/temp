<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/admin_header.jsp" %>

			<!-- container -->
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>회원 목록 조회</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">아이디</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">이름</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">닉네임</label>
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
                                <th scope="col">번호</th>
                                <th scope="col">아이디</th>
                                <th scope="col">이름</th>
                                <th scope="col">닉네임</th>
                                <th scope="col">가입일</th>
                                <th scope="col">탈퇴여부</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
                                <td>2023.11.26 00:00</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>id</td>
                                <td>이름</td>
                                <td>닉네임</td>
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
                        <li><a href="">10</a></li>
                        <li><a href="">100</a></li>
                        <li><a href="">1000</a></li>
                        <li><a href="">9</a></li>
                        <li><a href="">10</a></li>
                        <li class="next"><a href="" title="다음페이지로 이동"></a></li>
                        <li class="next_all"><a href="" title="마지막페이지로 이동"></a></li>
                    </ul>
                </div>

            </div>
            <!-- //container -->

<%@ include file="/WEB-INF/views/common/admin_footer.jsp" %>
    