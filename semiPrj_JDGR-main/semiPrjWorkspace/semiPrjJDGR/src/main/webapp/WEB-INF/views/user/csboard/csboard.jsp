<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
	
	<!-- main -->
    <main>
        <div class="con1 agn_c">
            <h1>고객센터</h1>
            <div class="btn_q mtp50">
                <ul class="tab_btns">
                    <li class="on"><button onclick="location.href='/jdgr/csboard';">고객센터</button></li>
                    <li><button onclick="location.href='/jdgr/notice/list';">공지사항</button></li>
                    <li><button onclick="location.href='/jdgr/csboard/list';">1:1문의</button></li>
                </ul>
            </div>
            <div class="q_box">
                <h1 class="mtp50">궁금한 점을 검색해보세요.</h1>
                <div class="search_box mt40">
                    <input type="text" name="">
                    <button>검색</button>
                </div>
            </div>
        </div>
        <div class="inner">
            <!-- 여기에 내용작성하시면 됩니다. 고객센터 제외 -->
            <dl class="item-content">
                    <div class="q_tit mt50">
                        <h1>공지사항</h1>
                        <a href="/jdgr/notice/list">
                            <h5>더보기<img class="icon_next ml10" src="/jdgr/resources/user/images/ico/ico_next.svg"></h5>
                        </a>

                    </div>
                    <!-- 가로테이블 -->
                    <div class="tbl_box q_data mt40">
                        <table>
                            <caption>테이블</caption>
                            <colgroup>
                                <col width="5%">
                                <col width="20%">
                                <col width="15%">
                                <col width="10%">
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
                                <tr>
                                    <td>5</td>
                                    <td>공지사항 제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>공지사항 제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>공지사항 제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>공지사항 제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>공지사항 제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="q_tit mt50">
                        <h1>1:1문의</h1>
                        <a href="/jdgr/csboard/list">
                            <h5>더보기<img class="icon_next ml10" src="/jdgr/resources/user/images/ico/ico_next.svg"></h5>
                        </a>

                    </div>
                    <!-- 가로테이블 -->
                    <div class="tbl_box q_data mt40">
                        <table>
                            <caption>테이블</caption>
                            <colgroup>
                                <col width="5%">
                                <col width="20%">
                                <col width="15%">
                                <col width="10%">
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
                                <tr>
                                    <td>5</td>
                                    <td><img class="q_icon" src="../images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td><img class="q_icon" src="../images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr><tr>
                                    <td>5</td>
                                    <td><img class="q_icon" src="../images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr><tr>
                                    <td>5</td>
                                    <td><img class="q_icon" src="../images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr><tr>
                                    <td>5</td>
                                    <td><img class="q_icon" src="../images/ico/ico_secret.svg"> 1:1 문의제목입니다.</td>
                                    <td>2023-11-15</td>
                                    <td>336</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
