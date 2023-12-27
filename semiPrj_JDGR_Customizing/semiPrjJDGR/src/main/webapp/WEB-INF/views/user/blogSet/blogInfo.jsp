<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<%

	// 블로그 정보 vo
	BlogVo userBlogVo = (BlogVo) request.getAttribute("blogUserData");

%>
<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <%@ include file="/WEB-INF/views/user/blogSet/side.jsp" %>

            <div class="right">
                <h2>블로그 정보</h2>

                <div class="content">

                    <form action="/jdgr/blogSet/blogInfo" method="post" enctype="multipart/form-data">
                        <div class="data_dl">
                            <dl>
                                <dt>블로그명</dt>
                                <dd>
                                    <div class="inp_box">
                                        <% if(userBlogVo != null){ %>
                                        <input type="text" value="${blogUserData.blogTitle}" name="blogTitle">
                                        <% } %>
                                        <span>한글, 영문, 숫자 혼용가능 (한글기준 25자 이내)</span>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>블로그 프로필 이미지</dt>
                                <dd>
                                    <div class="profile_box">
                                        <div class="img">
                                            <% if(userBlogVo != null){ %>
                                            <img src="${blogUserData.blogImg}" alt="">
                                            <% } %>
                                        </div>
                                        <div class="profile_btn">
                                            <input type="file" id="blogImg" name="blogImg">
                                            <label for="blogImg">등록</label>
                                            <span class="txt">프로필 이미지는 가로 200px, 세로 200px로 생성됩니다.</span>
                                        </div>
                                    </div>
                                </dd>
                            </dl>
                        </div>
    
                        <div class="blog_set_btn">
                            <button>저장</button>
                        </div>
                        <input type="hidden" name="blogUrl" value="${blogUserData.blogUrl}">
                    </form>

                </div>

            </div>

        </div>

    </div>
</main>
<!-- //main -->
<script>
    const blogImgInp = document.querySelector('.blog_set_layout .right .data_dl dl dd .profile_box .profile_btn input');
    const blogImgDiv = document.querySelector('.blog_set_layout .right .data_dl dl dd .profile_box .img');

    blogImgInp.addEventListener('change', function () {
        // 파일이 선택되었는지 확인
        if (blogImgInp.files.length > 0) {
            const file = blogImgInp.files[0];

            // FileReader 객체를 사용하여 파일 읽기
            const reader = new FileReader();
            reader.onload = function (e) {
                // 이미지를 보여주는 div에 이미지 추가
                const imgElement = document.createElement('img');
                imgElement.src = e.target.result;
                blogImgDiv.innerHTML = ''; // 이전에 추가된 이미지 삭제
                blogImgDiv.appendChild(imgElement);
            };

            // 파일을 읽기
            reader.readAsDataURL(file);
        }
    });
</script>
<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>