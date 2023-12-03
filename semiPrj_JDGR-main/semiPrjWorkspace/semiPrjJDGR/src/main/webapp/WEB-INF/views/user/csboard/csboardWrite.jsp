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
                    <li><button onclick="location.href='/jdgr/notice/list?pno=1';">공지사항</button></li>
                    <li class="on"><button onclick="location.href='/jdgr/csboard/list';">1:1문의</button></li>
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
                            <th>
                                <select><!-- class="sel_box"-->
                                    <option vaule="">일반문의</option>
                                    <option vaule="">기능문의</option>
                                    <option vaule="">신고문의</option>
                                </select>
                            </th>
                        </tr>
                        <tr>
                            <th><input type="text" name="" placeholder="제목을 입력해주세요"></th>
                        </tr>
                        <tr>
                            <th><textarea name="" placeholder="1:1문의 내용을 입력해주세요"></textarea></th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="noti_btn qboard_btn mb50">
                <a href="#" class="modal_open" data-target="#pop_complete">문의 접수</a>
                <a href="/jdgr/csboard/list" >취소</a>
            </div>
             
        </div>
    </main>
    <!-- //main -->
	
	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
	
	<!-- 레이어팝업 완료 모달 -->
<div id="pop_complete" class="modal_bg complete">
    <div class="modal_box">
        <div class="modal_header">
            <button class="modal_close">닫기</button>
        </div>
        <div class="modal_container">

            <div class="modal_content">
                <div class="img"></div>
                <div class="txt">
                    <strong>1:1 문의 완료</strong>
                </div>
            </div>

        </div>
        <div class="modal_footer">
            <button class="modal_close btn_black">확인</button>
        </div>
    </div>
</div>
