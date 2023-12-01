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
                    <li><button onclick="location.href='/jdgr/notice/list';">공지사항</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/csboard/list';">1:1문의</button></li>
                </ul>
            </div>
            <div class="q_box">
                <h1 class="mtp50">1:1문의 검색하기</h1>
                <div class="search_box mt40">
                    <input type="text" name="">
                    <button>검색</button>
                </div>
            </div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <!-- 가로테이블 -->
            <div class="tbl_box q_data mt40">
                <table>
                    <caption>테이블</caption>
                    <colgroup>
                        <col width="2%">
                        <col width="30%">
                        <col width="5%">
                        <col width="5%">
                    </colgroup>
                    <thead>
                        <tr >
                            <th scope="col">No</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성일자</th>
                            <th scope="col">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="modal_open" data-target="#pop_warning">
                            <td >5</td>
                            <td ><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr><tr>
                            <td>5</td>
                            <td><img class="q_icon" src="/jdgr/resources/user/images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                            <td>2023-11-15</td>
                            <td>336</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="paging_box mt40">
            <ul>
                <li class="prev_all"><a href="" title="최신페이지로 이동"></a></li>
                <li class="prev"><a href="" title="이전페이지로 이동"></a></li>
                <li class="on"><a href="">1</a></li>
                <li><a href="">2</a></li>
                <li><a href="">3</a></li>
                <li><a href="">4</a></li>
                <li><a href="">5</a></li>
                <li><a href="">6</a></li>
                <li><a href="">7</a></li>
                <li><a href="">8</a></li>
                <li><a href="">9</a></li>
                <li><a href="">10</a></li>
                <li class="next"><a href="" title="다음페이지로 이동"></a></li>
                <li class="next_all"><a href="" title="마지막페이지로 이동"></a></li>
            </ul>
        </div>
        <div class="noti_btn qboard_btn mb20">
            <a href="/jdgr/csboard/write" data-target="#pop_complete">문의 하기</a>
        </div>
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>