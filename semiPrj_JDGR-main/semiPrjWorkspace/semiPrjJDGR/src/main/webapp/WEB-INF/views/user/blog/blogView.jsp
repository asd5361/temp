<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>

<!-- main -->
<main>
    <div class="inner">

        <!-- blog_layout -->
        <div class="blog_layout">

            <%@ include file="/WEB-INF/views/user/blog/blogSide.jsp" %>

            <!-- 포스트 목록 및 내용 및 댓글 -->
            <div class="blog_right">

                <!-- 전체목록 -->
                <div class="b_post_list">
                    <a href="" class="tit">
                        <strong>전체보기</strong>
                        <span>4개의 글</span>
                    </a>
                    <table>
                        <colgroup>
                            <col width="*">
                            <col width="12%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">글 제목</th>
                                <th scope="col">조회수</th>
                                <th scope="col">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>유저가 작성한 글 제목이 여기에 표시됩니다.</td>
                                <td>33</td>
                                <td>2023-11-23</td>
                            </tr>
                            <tr>
                                <td>유저가 작성한 글 제목이 여기에 표시됩니다.</td>
                                <td>33</td>
                                <td>2023-11-23</td>
                            </tr>
                            <tr>
                                <td>유저가 작성한 글 제목이 여기에 표시됩니다.</td>
                                <td>33</td>
                                <td>2023-11-23</td>
                            </tr>
                            <tr>
                                <td>유저가 작성한 글 제목이 여기에 표시됩니다.</td>
                                <td>33</td>
                                <td>2023-11-23</td>
                            </tr>
                            <tr>
                                <td>유저가 작성한 글 제목이 여기에 표시됩니다.</td>
                                <td>33</td>
                                <td>2023-11-23</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="paging_box">
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
                </div>

                <!-- 포스트 상세보기 -->
                <div class="b_post_detail">
					<%@ include file="/WEB-INF/views/user/post/detail.jsp" %>
                </div>

                <!-- 
                    포스트 댓글
                    댓글버튼 :: li에 reply class 추가됨 reply_inp_box class에 display block
                    댓글취소 :: li에 reply class 제거됨 reply_inp_box class에 display none
                    수정하기 :: li에 edit class 추가됨 reply_edit class에 display block / 나머지 none
                    수정취소 :: li에 edit class 제거됨 reply_edit class에 display none / 나머지 block
                    삭제하기 :: li에 delete class 추가됨 blind div태그 추가됨
                -->
                <div class="b_post_reply">
                    <ul>
                        <li>
                            <div class="reply_pop">
                                <a href=""></a>
                                <div class="pop_content">
                                    <a href="" class="edit">수정하기</a>
                                    <a href="" class="delete">삭제하기</a>
                                </div>
                            </div>
                            <div class="user_nick">
                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                <strong>댓글 단 유저의 닉네임</strong>
                            </div>
                            <div class="reply_content">ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ뭔데</div>
                            <div class="reply_edit">
                                <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                <div>
                                    <button>취소</button>
                                    <button>수정</button>
                                </div>
                            </div>
                            <div class="reply_info">
                                <span class="date">2023.11.12 15:12</span>
                                <a href="">신고</a>
                            </div>
                            <div class="btn_area">
                                <button>댓글</button>
                            </div>
                            <div class="reply_inp_box">
                                <div class="user_nick">
                                    <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                    <strong>유저닉네임</strong>
                                </div>
                                <div class="reply_inp">
                                    <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                </div>
                                <div class="reply_footer">
                                    <div class="count">
                                        <span>0</span>
                                        <span>/ 3000</span>
                                    </div>
                                    <div class="reply_btn">
                                        <button>등록</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="rreply">
                            <div class="reply_pop">
                                <a href=""></a>
                                <div class="pop_content">
                                    <a href="" class="edit">수정하기</a>
                                    <a href="" class="delete">삭제하기</a>
                                </div>
                            </div>
                            <div class="user_nick">
                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                <strong>댓글 단 유저의 닉네임</strong>
                            </div>
                            <div class="reply_content">ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ뭔데</div>
                            <div class="reply_edit">
                                <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                <div>
                                    <button>취소</button>
                                    <button>수정</button>
                                </div>
                            </div>
                            <div class="reply_info">
                                <span class="date">2023.11.12 15:12</span>
                                <a href="">신고</a>
                            </div>
                            <div class="reply_inp_box">
                                <div class="user_nick">
                                    <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                    <strong>유저닉네임</strong>
                                </div>
                                <div class="reply_inp">
                                    <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                </div>
                                <div class="reply_footer">
                                    <div class="count">
                                        <span>0</span>
                                        <span>/ 3000</span>
                                    </div>
                                    <div class="reply_btn">
                                        <button>등록</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="rreply">
                            <div class="reply_pop">
                                <a href=""></a>
                                <div class="pop_content">
                                    <a href="" class="edit">수정하기</a>
                                    <a href="" class="delete">삭제하기</a>
                                </div>
                            </div>
                            <div class="user_nick">
                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                <strong>댓글 단 유저의 닉네임</strong>
                            </div>
                            <div class="reply_content">ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ뭔데</div>
                            <div class="reply_edit">
                                <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                <div>
                                    <button>취소</button>
                                    <button>수정</button>
                                </div>
                            </div>
                            <div class="reply_info">
                                <span class="date">2023.11.12 15:12</span>
                                <a href="">신고</a>
                            </div>
                            <div class="reply_inp_box">
                                <div class="user_nick">
                                    <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                    <strong>유저닉네임</strong>
                                </div>
                                <div class="reply_inp">
                                    <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                </div>
                                <div class="reply_footer">
                                    <div class="count">
                                        <span>0</span>
                                        <span>/ 3000</span>
                                    </div>
                                    <div class="reply_btn">
                                        <button>등록</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="reply_pop">
                                <a href=""></a>
                                <div class="pop_content">
                                    <a href="" class="edit">수정하기</a>
                                    <a href="" class="delete">삭제하기</a>
                                </div>
                            </div>
                            <div class="user_nick">
                                <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                <strong>댓글 단 유저의 닉네임</strong>
                            </div>
                            <div class="reply_content">ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ뭔데</div>
                            <div class="reply_edit">
                                <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                <div>
                                    <button>취소</button>
                                    <button>수정</button>
                                </div>
                            </div>
                            <div class="reply_info">
                                <span class="date">2023.11.12 15:12</span>
                                <a href="">신고</a>
                            </div>
                            <div class="btn_area">
                                <button>댓글</button>
                            </div>
                            <div class="reply_inp_box">
                                <div class="user_nick">
                                    <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                                    <strong>유저닉네임</strong>
                                </div>
                                <div class="reply_inp">
                                    <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                                </div>
                                <div class="reply_footer">
                                    <div class="count">
                                        <span>0</span>
                                        <span>/ 3000</span>
                                    </div>
                                    <div class="reply_btn">
                                        <button>등록</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="paging_box">
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
                    <div class="reply_inp_box">
                        <div class="user_nick">
                            <div class="img"><img src="/jdgr/resources/user/images/content/img_main01.png" alt=""></div>
                            <strong>유저닉네임</strong>
                        </div>
                        <div class="reply_inp">
                            <textarea placeholder="타인에게 불쾌감을 주는 욕설과 악플은 삭제될 수 있습니다."></textarea>
                        </div>
                        <div class="reply_footer">
                            <div class="count">
                                <span>0</span>
                                <span>/ 3000</span>
                            </div>
                            <div class="reply_btn">
                                <button>등록</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <!-- //blog_layout -->

    </div>
</main>
<!-- //main -->

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>