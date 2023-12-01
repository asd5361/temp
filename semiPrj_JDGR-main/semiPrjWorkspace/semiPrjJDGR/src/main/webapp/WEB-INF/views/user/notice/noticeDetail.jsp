<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
	
	<!-- main -->
    <main>
        <div class="con1 agn_c">
            <h1>고객센터</h1>
            <div class="btn_q mtp50">
                <ul class="tab_btns">
                    <li><button onclick="location.href='/jdgr/csboard';">고객센터</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/notice/list';">공지사항</button></li>
                    <li><button onclick="location.href='/jdgr/csboard/list';">1:1문의</button></li>
                </ul>
            </div>
            <div class="noti_box"></div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <div class="tbl_box noti_tbl mt50">
                <table>
                    <caption>공지사항 상세보기</caption>
                    <colgroup>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>공지사항 제목입니다.</th>
                        </tr>
                        <tr>
                            <th>
                                <div>
                                    <div>
                                        <img src="/jdgr/resources/user/images/ico/ico_people.svg"> 관리자
                                    </div>
                                    <div>
                                        <img src="/jdgr/resources/user/images/ico/ico_eye.svg">251 
                                        <img class="ml30" src="/jdgr/resources/user/images/ico/ico_cal.svg">2023-11-24
                                    </div>
                                </div>
                            </th>
                        </tr>
                        <tr>
                            <th>공지사항<br>내<br>용<br>입<br>니<br>다<br>ㅋ<br>ㅋㅋㅋ<br><br><br><br><br><br><br>ㅋㅋㅋ<br>ㅋㅋ</th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="noti_btn">
                <a href="" >목록</a>
            </div>
             <div class="tbl_box noti_ft">
                <table>
                    <colgroup>
                        <col width=""/>
                        <col width="90%"/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>이전글</th>
                            <td><a href="" >공지사항 제목입니다.</a></td>
                        </tr>
                        <tr>
                            <th>이전글</th>
                            <td><a href="" >공지사항 제목입니다.</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
