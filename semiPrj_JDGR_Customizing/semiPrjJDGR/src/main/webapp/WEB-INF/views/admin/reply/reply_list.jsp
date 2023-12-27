<%@page import="java.util.Map"%>
<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.admin.reply.vo.AdminReplyVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<AdminReplyVo> replyVoList = (List<AdminReplyVo>) request.getAttribute("replyVoList");
    	PageVo pvo = (PageVo)request.getAttribute("pvo");
    	Map<String, String> searchMap = (Map<String, String>)request.getAttribute("searchMap");
    %>
    
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="/jdgr/resources/admin/css/allCss.css">


    <%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
 <!-- container -->
 
 
            <div class="container">
                <!-- 제목 -->
                <div class="tit_box">
                    <h2>댓글 조회</h2>
                </div>
                
                <!-- 검색박스 예시 -->
                <div class="search_box">
                    <div class="search_item">
                        <label for="sel_01">댓글 번호</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">포스트 번호</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">작성자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">내용</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">작성일자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">수정일자</label>
                        <div class="form_box">
                            <input type="text" id="inp_02">
                        </div>
                    </div>
                    <div class="search_item">
                        <label for="sel_01">공개여부</label>
                        <div class="form_box">
                            <select class="sel_box">
                                <option value="">공개</option>
                                <option value="">비공개</option>
                            </select>
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
                            <col width="">
                            <col width="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">댓글 번호</th>
                                <th scope="col">포스트 번호</th>
                                <th scope="col">작성자</th>
                                <th scope="col">내용</th>
                                <th scope="col">작성일자</th>
                                <th scope="col">수정일자</th>
                                <th scope="col">공개여부</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= vo.getPostNo() %></td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
                            </tr>
                            <tr>
                                <td>001</td>
                                <td>1402934</td>
                                <td>김상병</td>
                                <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                                <td>2023.11.11 18:00</td>
                                <td>2023.11.27 18:00</td>
                                <td>공개</td>
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
            
            <%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>