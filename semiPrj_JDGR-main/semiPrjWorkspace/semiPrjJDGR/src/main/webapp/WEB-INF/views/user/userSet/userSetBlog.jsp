<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/user/common/header.jsp" %>

<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <div class="left">
                <dl>
                    <dt>블로그 관리</dt>
                    <dd>
                        <ul>
                            <li><a href="">블로그 정보</a></li>
                        </ul>
                    </dd>
                </dl>
                <dl>
                    <dt>계정 관리</dt>
                    <dd>
                        <ul>
                            <li><a href="">개인정보 수정</a></li>
                        </ul>
                    </dd>
                </dl>
            </div>

            <div class="right">
                <h2>블로그 정보</h2>

                <div class="content">

                    <div class="account_setting">
                        <dl class="blog_using">
                            <dt>운영중인 블로그</dt>
                            <dd>
                                <form action="">
                                    <ul class="blog_list">
                                        <li>
                                            <input type="radio" id="blog1" name="blog">
                                            <label for="blog1">
                                                <div class="img"><img src="../images/content/img_main01.png" alt=""></div>
                                                <div class="cont">
                                                    <div class="tit">블로그제목</div>
                                                    <a href="">jdgr/blog/유저닉네임 링크</a>
                                                </div>
                                                <div class="req">대표</div>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" id="blog2" name="blog">
                                            <label for="blog2">
                                                <div class="img"><img src="../images/content/img_main01.png" alt=""></div>
                                                <div class="cont">
                                                    <div class="tit">블로그제목</div>
                                                    <a href="">jdgr/blog/유저닉네임 링크</a>
                                                </div>
                                                <div class="req">대표</div>
                                            </label>
                                        </li>
                                    </ul>
                                    <div class="btn_area">
                                        <button>변경사항 저장</button>
                                    </div>
                                </form>
                            </dd>
                        </dl>
                        <dl class="blog_create">
                            <dt>운영·개설</dt>
                            <dd>
                                <div class="gg"><span>2개</span>의 블로그를 더 운영할 수 있습니다.</div>
                                <div class="btn_area">
                                    <button>새 블로그 만들기</button>
                                </div>
                            </dd>
                        </dl>
                    </div>

                </div>

            </div>

        </div>

    </div>
</main>
<!-- //main -->

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>