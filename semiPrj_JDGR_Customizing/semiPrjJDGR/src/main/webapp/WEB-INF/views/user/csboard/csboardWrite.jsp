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
                    <li class="on"><button onclick="location.href='/jdgr/csboard/list?pno=1';">1:1문의</button></li>
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
                                <select name="category"><!-- class="sel_box"-->
                                    <option value="NM">일반문의</option>
                                    <option value="SM">기능문의</option>
                                    <option value="PM">신고문의</option>
                                </select>
                            </th>
                        </tr>
                        <tr>
                            <th><input type="text" name="title" placeholder="제목을 입력해주세요"></th>
                        </tr>
                        <tr>
                            <th><textarea name="content" placeholder="1:1문의 내용을 입력해주세요"></textarea></th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="noti_btn qboard_btn mb50">
                <a href="javascript:sendPost();" class="modal_open" data-target="#pop_complete">문의 접수</a>
                <a href="/jdgr/csboard/list" >취소</a>
            </div>
             
        </div>
    </main>
    <!-- //main -->


	<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>

    <script>

        function sendPost(){
        let tableTag = document.querySelector(".tbl_box table");
        let formTag = document.createElement("form");
        let divTag = document.querySelector(".tbl_box");
        formTag.setAttribute('method','post');
        formTag.setAttribute('action','/jdgr/csboard/write');
        formTag.appendChild(tableTag);
        divTag.appendChild(formTag);
        formTag.submit();

            
        }
        
    </script>

