<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<% 
	// 블로그 관리정보 vo
	BlogVo userSetblogVo = (BlogVo) request.getAttribute("blogUserData");
%>
<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <%@ include file="/WEB-INF/views/user/blogSet/side.jsp" %>

            <div class="right">
                <h2>레이아웃-위젯 설정</h2>

                <div class="content">

                    <form action="/jdgr/blogSet/layout" method="post">
                        <div class="data_dl">
                            <dl>
                                <dt>레이아웃 선택</dt>
                                <dd>
                                    <div class="layout_select">
                                        <ul>
                                            <% for(int i = 1; i < 4; i++){ %>
                                                <% if(Integer.toString(i).equals(userSetblogVo.getLayout())){ %>
                                                <li>
                                                    <input type="radio" id="layout<%= i %>" name="layoutSelect" value="<%= i %>" checked>
                                                    <label for="layout<%= i %>"><span></span></label>
                                                </li>
                                                <% } else { %>
                                                <li>
                                                    <input type="radio" id="layout<%= i %>" name="layoutSelect" value="<%= i %>">
                                                    <label for="layout<%= i %>"><span></span></label>
                                                </li>
                                                <% } %>
                                            <% } %>
                                        </ul>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>최근댓글 보이기</dt>
                                <dd>
                                    <div class="show_chk">
                                        <% if(userSetblogVo.getrCommentsYn().equals("Y")){ %>
                                        <input type="checkbox" id="show1" name="comments" checked>
                                        <% } else { %>
                                        <input type="checkbox" id="show1" name="comments">
                                        <% } %>
                                        <label for="show1"></label>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>구독블로그 보이기</dt>
                                <dd>
                                    <div class="show_chk">
                                        <% if(userSetblogVo.getFollowBlogYn().equals("Y")){ %>
                                        <input type="checkbox" id="show2" name="followBlog" checked>
                                        <% } else { %>
                                        <input type="checkbox" id="show2" name="followBlog">
                                        <% } %>
                                        <label for="show2"></label>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>방문자수 보이기</dt>
                                <dd>
                                    <div class="show_chk">
                                        <% if(userSetblogVo.getVisitorsCntYn().equals("Y")){ %>
                                        <input type="checkbox" id="show3" name="visitorsCnt" checked>
                                        <% } else { %>
                                        <input type="checkbox" id="show3" name="visitorsCnt">
                                        <% } %>
                                        <label for="show3"></label>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>시계 보이기</dt>
                                <dd>
                                    <div class="show_chk">
                                        <% if(userSetblogVo.getClockYn().equals("Y")){ %>
                                        <input type="checkbox" id="show4" name="clock" checked>
                                        <% } else { %>
                                        <input type="checkbox" id="show4" name="clock">
                                        <% } %>
                                        <label for="show4"></label>
                                    </div>
                                </dd>
                            </dl>
                            <dl>
                                <dt>지도 보이기</dt>
                                <dd>
                                    <div class="show_chk">
                                        <% if(userSetblogVo.getMapYn().equals("Y")){ %>
                                        <input type="checkbox" id="show5" name="map" checked>
                                        <% } else { %>
                                        <input type="checkbox" id="show5" name="map">
                                        <% } %>
                                        <label for="show5"></label>
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

<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>