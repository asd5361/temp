<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<% 
	// 블로그 관리정보 vo
	BlogVo userSetblogVo = (BlogVo) request.getAttribute("blogUserData");

	// 블로그 구독 리스트
	List<BlogVo> followUserBlogVoList = (List<BlogVo>) request.getAttribute("followUserBlogVoList");
%>
<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <%@ include file="/WEB-INF/views/user/blogSet/side.jsp" %>

            <div class="right">
                <h2>구독한 블로그</h2>

                <div class="content">

                    <div class="gd_tbl">
                        <table>
                            <colgroup>
                                <col width="50px">
                                <col width="">
                                <col width="10%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th colspan="col">
                                        <div class="chk_box solo chk_All">
                                            <span>
                                                <input type="checkbox" id="chk_All">
                                                <label for="chk_All">&nbsp;</label>
                                            </span>
                                        </div>
                                    </th>
                                    <th colspan="col">구독블로그</th>
                                    <th colspan="col">구독삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<% for(BlogVo vo : followUserBlogVoList){ %>
                                <tr>
                                    <td>
                                        <div class="chk_box solo">
                                            <span>
                                                <input type="checkbox" id="chk_0<%= vo.getBlogNo() %>">
                                                <label for="chk_0<%= vo.getBlogNo() %>">&nbsp;</label>
                                            </span>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="info">
                                            <a href="/jdgr/blog/view/<%= vo.getBlogUrl() %>" target="_blank">
                                                <strong><%= vo.getMemNick() %></strong>
                                                <span><%= vo.getBlogTitle() %></span>
                                            </a>
                                        </div>
                                        <input type="hidden" name="memNo" value="<%= vo.getMemNo() %>">
                                        <input type="hidden" name="FollowMem" value="<%= vo.getBlogNo() %>">
                                    </td>
                                    <td>
                                        <div class="gd_btn">
                                            <button>구독삭제</button>
                                        </div>
                                    </td>
                                </tr>
                            	<% } %>
                            </tbody>
                        </table>
                        <div class="gd_chk_btn">
                            <a href="" class="allchk_Btn">전체선택</a>
                            <a href="" class="selectDelete_Btn">선택삭제</a>
                        </div>
                    </div>

<!--                     <div class="paging_box mt30"> -->
<!--                         <ul> -->
<!--                             <li class="prev_all"><a href="" title="최신페이지로 이동"></a></li> -->
<!--                             <li class="prev"><a href="" title="이전페이지로 이동"></a></li> -->
<!--                             <li class="on"><a href="">1</a></li> -->
<!--                             <li><a href="">2</a></li> -->
<!--                             <li><a href="">3</a></li> -->
<!--                             <li><a href="">4</a></li> -->
<!--                             <li><a href="">5</a></li> -->
<!--                             <li><a href="">6</a></li> -->
<!--                             <li><a href="">7</a></li> -->
<!--                             <li><a href="">8</a></li> -->
<!--                             <li><a href="">9</a></li> -->
<!--                             <li><a href="">10</a></li> -->
<!--                             <li class="next"><a href="" title="다음페이지로 이동"></a></li> -->
<!--                             <li class="next_all"><a href="" title="마지막페이지로 이동"></a></li> -->
<!--                         </ul> -->
<!--                     </div> -->

                </div>

            </div>

        </div>

    </div>
</main>
<!-- //main -->
<script>

// 전체선택
const allchkBtn = document.querySelector('.allchk_Btn'); // 전체선택 버튼
const allchkBtnInp = document.querySelector('.chk_All input'); // 전체선택 input
const checkboxes = document.querySelectorAll('tbody .chk_box input[type="checkbox"]'); // 체크박스들

// 전체 선택/해제 체크박스에 이벤트 리스너 추가
allchkBtn.addEventListener('click', (e) => {
    e.preventDefault();
    checkboxes.forEach(checkbox => {
        checkbox.checked = true;
        allchkBtnInp.checked = true;
    });
    const currentText = allchkBtn.innerHTML; // allchkBtn으로 변경
    const nextText = currentText === '전체선택' ? '전체해제' : '전체선택';
    allchkBtn.innerHTML = nextText; // allchkBtn으로 변경

    if(currentText === '전체해제'){
        checkboxes.forEach(checkbox => {
            checkbox.checked = false;
            allchkBtnInp.checked = false;
        });
    }
});

allchkBtnInp.addEventListener('change', (e) => {
    if (e.target.checked) {
        checkboxes.forEach(checkbox => {
            checkbox.checked = true;
        });
    } else {
        checkboxes.forEach(checkbox => {
            checkbox.checked = false;
        });
    }
});

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', () => {
        const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);
        allchkBtnInp.checked = allChecked;
    });
});


//여러 행 삭제 버튼 클릭 이벤트 처리
document.querySelector('.selectDelete_Btn').addEventListener('click', (e) => {
	e.preventDefault();
    // 선택된 항목 정보를 저장할 배열
    const selectedItems = [];

    // 각 체크박스의 클릭 이벤트 처리
    document.querySelectorAll('tbody input[type="checkbox"]').forEach((checkbox, index) => {
        if (checkbox.checked) {
            const memNo = document.querySelectorAll('input[name="memNo"]')[index].value;
            const FollowMem = document.querySelectorAll('input[name="FollowMem"]')[index].value;
            selectedItems.push({ memNo, FollowMem });
        }
    });

    // AJAX를 사용하여 선택된 항목들을 서버로 전송
    fetch('/jdgr/blogSet/subscribe/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedItems)
    })
    .then(response => response.json())
    .then(data => {
        // 서버 응답 처리
        console.log(data);
        // 선택된 항목들 초기화
        selectedItems.length = 0;
        // 선택된 항목들 삭제 후 페이지 리로드 또는 UI 갱신 등 추가 처리
        location.reload(); // 예시로 페이지 리로드
    })
    .catch(error => {
        console.error('Error:', error);
    });
});

// 구독삭제 버튼
document.querySelector('.gd_btn button').addEventListener('click', (e) => {
	e.preventDefault();
    // 선택된 항목 정보를 저장할 배열
    const selectedItems = [];

    // 해당 체크박스 데이터에 추가
    const memNo = e.target.parentNode.parentNode.parentNode.querySelector('input[name="memNo"]').value;
    const FollowMem = e.target.parentNode.parentNode.parentNode.querySelector('input[name="FollowMem"]').value;
    selectedItems.push({ memNo, FollowMem });
    console.log(selectedItems);

    // AJAX를 사용하여 선택된 항목들을 서버로 전송
    fetch('/jdgr/blogSet/subscribe/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedItems)
    })
    .then(response => response.json())
    .then(data => {
        // 서버 응답 처리
        console.log(data);
        // 선택된 항목들 초기화
        selectedItems.length = 0;
        // 선택된 항목들 삭제 후 페이지 리로드 또는 UI 갱신 등 추가 처리
        location.reload(); // 예시로 페이지 리로드
    })
    .catch(error => {
        console.error('Error:', error);
    });
});

</script>
<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>