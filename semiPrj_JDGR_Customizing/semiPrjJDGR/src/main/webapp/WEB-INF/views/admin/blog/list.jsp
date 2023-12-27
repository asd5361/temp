<%@page import="java.util.Map"%>
<%@page import="com.semi.jdgr.page.vo.PageVo"%>
<%@page import="com.semi.jdgr.blog.vo.BlogVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>

<%
	List<BlogVo> blogVoList = (List<BlogVo>) request.getAttribute("blogVoList");
	PageVo pvo = (PageVo)request.getAttribute("pvo");
	
	Map<String, String> param = (Map<String, String>) request.getAttribute("param");
%>

<!-- container -->
<div class="container">
    <!-- 제목 -->
    <div class="tit_box">
        <h2>블로그 관리 목록 조회</h2>
    </div>
    
    <!-- 검색박스 예시 -->
    <form action="/jdgr/admin/blog/search" method="get">
        <div class="search_box">
            <div class="search_item">
                <label for="sel_01">회원 ID</label>
                <div class="form_box">
                    <input type="text" id="inp_02" name="memId">
                </div>
            </div>
            <div class="search_item">
                <label for="sel_01">회원 닉네임</label>
                <div class="form_box">
                    <input type="text" id="inp_02" name="memNick">
                </div>
            </div>
            <div class="search_item">
                <label for="sel_01">블로그 타이틀</label>
                <div class="form_box">
                    <input type="text" id="inp_02" name="blogTitle">
                </div>
            </div>
            <div class="search_item">
                <label for="sel_01">블로그 URL</label>
                <div class="form_box">
                    <input type="text" id="inp_02" name="blogUrl">
                </div>
            </div>
            <div class="search_item">
                <label for="sel_01">대표블로그 설정</label>
                <div class="form_box">
                    <select id="" name="blogRep">
                        <option value="0">선택</option>
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                    </select>
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="btn_box_group right">
            <div class="btn_box">
                <button type="button" class="btn_gray">초기화</button>
            </div>
            <div class="btn_box">
                <button class="btn_black">검색</button>
            </div>
        </div>
    </form>
    
    

    <!-- 테이블 -->
    <div class="tbl_box data mt40">
        <table>
            <caption>테이블</caption>
            <colgroup>
                <col width="5%">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
                <col width="">
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">블로그 회원ID</th>
                    <th scope="col">블로그 회원 닉네임</th>
                    <th scope="col">블로그 타이틀</th>
                    <th scope="col">블로그 URL</th>
                    <th scope="col">레이아웃 구분</th>
                    <th scope="col">스킨 구분</th>
                    <th scope="col">방문자수 노출여부</th>
                    <th scope="col">방문자 수</th>
                    <th scope="col">공개여부</th>
                    <th scope="col">대표블로그 설정</th>
                </tr>
            </thead>
            <tbody>
            	<% for(BlogVo vo : blogVoList){ %>
                <tr>
                    <td><%= vo.getBlogNo() %></td>
                    <td><%= vo.getMemId() %></td>
                    <td><%= vo.getMemNick() %></td>
                    <td><%= vo.getBlogTitle() %></td>
                    <td class="agn_l">/jdbc/blog/view/<%= vo.getBlogUrl() %></td>
                    <td><%= vo.getLayout() %></td>
                    <td><%= vo.getSkin() %></td>
                    <td><%= vo.getrCommentsYn() %></td>
                    <td><%= vo.getVisitCnt() %></td>
                    <td><%= vo.getOpenYn() %></td>
                    <td><%= vo.getRepYn() %></td>
                </tr>
            	<% } %>
            </tbody>
        </table>
    </div>

    <div class="paging_box mt30">
        <ul>
            <% if(pvo.getStartPage() < pvo.getCurrentPage()){ %>
<%--             <li class="prev_all"><a href="/jdgr/admin/blog/list?pno=<%= pvo.getStartPage() %>" title="최신페이지로 이동"></a></li> --%>
            <li class="prev"><a href="/jdgr/admin/blog/list?pno=<%= pvo.getCurrentPage() - 1 %>" title="이전페이지로 이동"></a></li>
            <% } %>
            
            <% for(int i = pvo.getStartPage(); i < pvo.getEndPage() + 1; i++){ %>
	            <% if(pvo.getCurrentPage() == i){ %>
		            <li class="on"><a href=""><%= i %></a></li>
	            <% } else { %>
		            <li><a href="/jdgr/admin/blog/list?pno=<%= i %>"><%= i %></a></li>
	            <% } %>
            <% } %>
            
            <% if(pvo.getEndPage() > pvo.getCurrentPage()){ %>
	        <li class="next"><a href="/jdgr/admin/blog/list?pno=<%= pvo.getCurrentPage() + 1 %>" title="다음페이지로 이동"></a></li>
<%--             <li class="next_all"><a href="/jdgr/admin/blog/list?pno=<%= pvo.getEndPage() %>" title="마지막페이지로 이동"></a></li> --%>
			<% } %>
        </ul>
    </div>

</div>
<!-- //container -->

<script>
	// 해당 tr클릭시 파라미터 넘김
	const trArr = document.querySelectorAll('.tbl_box.data table tbody tr');
	trArr.forEach(tr => {
		tr.addEventListener('click', handleClick);
	})
	function handleClick(e){
		const trTag = e.currentTarget;
		const no = trTag.children[0].innerText;
		location.href = '/jdgr/admin/blog/detail?no=' + no + '&currPage=<%= pvo.getCurrentPage() %>';
	}
	
	// 검색어 남기기
	<% if(param != null){ %>
		function setSearchArea(){
	
			// blogRep 세팅
			const blogRepOptArr = document.querySelectorAll('select[name=blogRep] option');
			const selectBlogRep = "<%= param.get("blogRep") %>";
            
			for(let i = 0; i < blogRepOptArr.length; i++){
				if(blogRepOptArr[i].value === selectBlogRep){
					blogRepOptArr[i].selected = true;
					break;
				}
			}
	
			// 인풋태그 세팅
			const memIdTag = document.querySelector('input[name=memId]');
			memIdTag.value = "<%= param.get("memId") %>";

			const memNickTag = document.querySelector('input[name=memNick]');
			memNickTag.value = "<%= param.get("memNick") %>";

			const blogTitleTag = document.querySelector('input[name=blogTitle]');
			blogTitleTag.value = "<%= param.get("blogTitle") %>";

			const blogUrlTag = document.querySelector('input[name=blogUrl]');
			blogUrlTag.value = "<%= param.get("blogUrl") %>";
		}
		setSearchArea();
	
		function setPageArea(){
			
			const aTagArr = document.querySelectorAll('.paging_box ul li a');
            console.log(aTagArr);
			for(let i = 0; i < aTagArr.length; i++){
				aTagArr[i].href = aTagArr[i].href.replace("list", "search");
				aTagArr[i].href += "&memId=<%= param.get("memId") %>";
				aTagArr[i].href += "&memNick=<%= param.get("memNick") %>";
				aTagArr[i].href += "&blogTitle=<%= param.get("blogTitle") %>";
				aTagArr[i].href += "&blogUrl=<%= param.get("blogUrl") %>";
				aTagArr[i].href += "&blogRep=<%= param.get("blogRep") %>";
			}
			
		}
		setPageArea();
	<% } %>
</script>

<%@ include file="/WEB-INF/views/admin/common/footer.jsp" %>