<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<% 
	// 블로그 관리정보 vo
	BlogVo userSetblogVo = (BlogVo) request.getAttribute("blogUserData");

	// 배경색 
	String[] backgroundColors = {"#FFC1C1", "#333333", "#1F34EF", "#FFFFFF", "#FFEAC1", "#DEFFE5"};
	String[] fontColors = {"#333333", "#FFFFFF", "#FFFFFF", "#333333", "#333333", "#333333"};
%>
<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <%@ include file="/WEB-INF/views/user/blogSet/side.jsp" %>

            <div class="right">
                <h2>스킨 선택</h2>

                <div class="content">

                    <form action="/jdgr/blogSet/skin" method="post">
                        <div class="blogSkin_select">
                            <ul>
                                <% for(int i = 1; i <= 6; i++){ %>
                                    <% if (userSetblogVo.getSkin().equals(Integer.toString(i))) { %>
                                    <li>
                                        <input type="radio" id="skin<%= i %>" name="skinSelect" value="<%= i %>" checked>
                                        <label for="skin<%= i %>">
                                            <div class="color"><%= fontColors[i - 1] %></div>
                                            <div class="txt">
                                                배경색 <%= backgroundColors[i - 1] %>
                                                글자색 <%= fontColors[i - 1] %>
                                            </div>
                                        </label>
                                    </li>
                                    <% } else { %>
                                    <li>
                                        <input type="radio" id="skin<%= i %>" name="skinSelect" value="<%= i %>" >
                                        <label for="skin<%= i %>">
                                            <div class="color"><%= fontColors[i - 1] %></div>
                                            <div class="txt">
                                                배경색 <%= backgroundColors[i - 1] %>
                                                글자색 <%= fontColors[i - 1] %>
                                            </div>
                                        </label>
                                    </li>
                                    <% } %>
                                    
                                <% } %>
                            </ul>
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