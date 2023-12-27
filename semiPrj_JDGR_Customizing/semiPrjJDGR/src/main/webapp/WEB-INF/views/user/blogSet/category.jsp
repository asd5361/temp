<%@page import="com.semi.jdgr.blog.vo.GroupVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
<% 
	// 블로그 관리정보 vo
	List<GroupVo> blogUserGroupData = (List<GroupVo>) request.getAttribute("blogUserGroupData");
%>
<!-- main -->
<main>
    <div class="inner">

        <div class="blog_set_layout">

            <%@ include file="/WEB-INF/views/user/blogSet/side.jsp" %>

            <div class="right">
                <h2>카테고리 설정</h2>

                <div class="content">

                    <div class="category_box">

                        <div class="category_left">
                            <div class="btn_area">
                                <div class="left">
                                    <button type="button" onclick="categoryAdd();">카테고리 추가</button>
                                </div>
                                <div class="right">
                                    <button type="button" onclick="selectDelete();">선택삭제</button>
                                </div>
                            </div>
                            <ul>
                                <% for(int i = 1; i <= blogUserGroupData.size(); i++){ %>
                                <li class="category">
                                    <input type="radio" id="category<%= i %>" name="userCategoryNum" value="<%= blogUserGroupData.get(i - 1).getOrder() %>" >
                                    <input type="hidden" name="userCategoryValue" value="<%= blogUserGroupData.get(i - 1).getName() %>">
                                    <label for="category<%= i %>"><%= blogUserGroupData.get(i - 1).getName() %></label>
                                </li>
                                <% } %>
                            </ul>
                        </div>

                        <div class="category_right">
                            <dl>
                                <dt>카테고리 명</dt>
                                <dd><input type="text" name="groupName" name="editName"></dd>
                            </dl>
                            <div class="btn_area">
                                <button type="button" onclick="selectEdit();">저장하기</button>
                            </div>
                            <input type="hidden" name="blogUrl" value="${blogUserData.blogUrl}">
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>
</main>
<!-- //main -->
<script>

    function handleCategoryChange(e) {
        const categoryHiddenInput = e.target.closest('li').querySelector('input[name=userCategoryValue]');
    
        const categoryInp = document.querySelector('input[name=groupName]');
        categoryInp.value = categoryHiddenInput.value;
        categoryInp.focus();

        if(categoryInp.value == 'line'){
            categoryInp.disabled = true;
        } else {
            categoryInp.disabled = false;
        }
        
    }

    const categoryUl = document.querySelector('.category_box .category_left ul');

    
    // 이벤트 위임을 통해 부모 요소에 이벤트 리스너 추가
    categoryUl.addEventListener('change', function (e) {
        const targetInput = e.target.closest('input[name=userCategoryNum]');
        if (targetInput) {
            handleCategoryChange(e);
        }
    });

    function categoryAdd(){
    	const categoryLastLi = document.querySelector('.category_left ul li:last-child input[type=radio]');
        let nextValue;
    	if(categoryLastLi){
    		nextValue = Number(categoryLastLi.value)    		
    	} else {
    		nextValue = 0;
    	}
        nextValue++;

        const categoryLi = document.createElement('li');
        categoryLi.className = 'category';

        categoryLi.innerHTML = `
            <input type="radio" id="category` + nextValue + `" name="userCategoryNum" value="` + nextValue + `">
            <input type="hidden" name="userCategoryValue" value="카테고리">
            <label for="category` + nextValue + `">카테고리</label>
        `;
        categoryUl.appendChild(categoryLi);

        const userCategoryNum = categoryLi.querySelector('input[name=userCategoryNum]').value;
        const userCategoryValue = categoryLi.querySelector('input[name=userCategoryValue]').value;
        //const editCategoryName = document.querySelector('input[name=groupName]').value;
        const userUrl = document.querySelector('input[name=blogUrl]').value;

        addAjax(userCategoryNum, userCategoryValue, userUrl);
    }

    // function lineAdd(){
    //     const categoryLastLi = document.querySelector('.category_left ul li:last-child input[type=radio]');
    //     let nextValue = Number(categoryLastLi.value);
    //     nextValue++;

    //     const categoryLi = document.createElement('li');
    //     categoryLi.className = 'line';

    //     categoryLi.innerHTML = `
    //         <input type="radio" id="line` + nextValue + `" name="userCategoryNum" value="` + nextValue + `">
    //         <input type="hidden" name="userCategoryValue" value="line">
    //         <label for="line` + nextValue + `"></label>
    //     `;
    //     categoryUl.appendChild(categoryLi)

    //     const userCategoryNum = categoryLi.querySelector('input[name=userCategoryNum]').value;
    //     const userCategoryValue = categoryLi.querySelector('input[name=userCategoryValue]').value;
    //     //const editCategoryName = document.querySelector('input[name=groupName]').value;
    //     const userUrl = document.querySelector('input[name=blogUrl]').value;

    //     addAjax(userCategoryNum, userCategoryValue, userUrl);
    // }

    function selectDelete(){
        let selectCategoryInp = null;
        selectCategory = document.querySelectorAll('input[name=userCategoryNum]');
        for (const el of selectCategory) {
            if(el.checked){
                selectCategoryInp = el;
            }
        }
        // console.log(selectCategoryInp);
        // console.log(selectCategoryInp.parentNode);
        selectCategoryInp.parentNode.remove();
        const userCategoryNum = selectCategoryInp.value;
        const userCategoryValue = selectCategoryInp.parentNode.querySelector('input[name=userCategoryValue]').value;
        const userUrl = document.querySelector('input[name=blogUrl]').value;

        deleteAjax(userCategoryNum, userCategoryValue, userUrl);
    }

    function selectEdit(){
        let selectCategoryInp = null;
        selectCategory = document.querySelectorAll('input[name=userCategoryNum]');
        const editCategory = document.querySelector('input[name=groupName]');
        for (const el of selectCategory) {
            if(el.checked){
                selectCategoryInp = el;
                
            }
        }

        const userCategoryValueInp = selectCategoryInp.parentNode.querySelector('input[name=userCategoryValue]');
        const userCategoryLable = selectCategoryInp.parentNode.querySelector('label');
        userCategoryValueInp.value = editCategory.value;
        userCategoryLable.innerHTML = editCategory.value;
        
        const userCategoryNum = selectCategoryInp.value;
        const userCategoryValue = selectCategoryInp.parentNode.querySelector('input[name=userCategoryValue]').value;
        const userUrl = document.querySelector('input[name=blogUrl]').value;

        editAjax(userCategoryNum, userCategoryValue, userUrl);
    }

    function addAjax(userCategoryNum, userCategoryValue, userUrl){
        const dataToSend = {
            order: userCategoryNum,
            name: userCategoryValue,
            blogUrl: userUrl
        }
        // 서버한테 요청보내기
        fetch("/jdgr/blogSet/categoryAdd", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // JSON 데이터를 전송할 것이므로 Content-Type을 지정
            },
            body: JSON.stringify(dataToSend) // JSON 형태로 데이터 변환
        })
        .then( (resp) => { return resp.json() } ) // 데이터 가공
        .then( (x) => { // 가공된 데이터로 할수있는거 하기
            
        } );
        
    }

    function deleteAjax(userCategoryNum, userCategoryValue, userUrl){
        const dataToSend = {
            order: userCategoryNum,
            name: userCategoryValue,
            blogUrl: userUrl
        }
        // 서버한테 요청보내기
        fetch("/jdgr/blogSet/categoryDelete", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // JSON 데이터를 전송할 것이므로 Content-Type을 지정
            },
            body: JSON.stringify(dataToSend) // JSON 형태로 데이터 변환
        })
        .then( (resp) => { return resp.json() } ) // 데이터 가공
        .then( (x) => { // 가공된 데이터로 할수있는거 하기
            
        } );
        
    }

    function editAjax(userCategoryNum, userCategoryValue, userUrl){
        const dataToSend = {
            order: userCategoryNum,
            name: userCategoryValue,
            blogUrl: userUrl
        }
        // 서버한테 요청보내기
        fetch("/jdgr/blogSet/category", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // JSON 데이터를 전송할 것이므로 Content-Type을 지정
            },
            body: JSON.stringify(dataToSend) // JSON 형태로 데이터 변환
        })
        .then( (resp) => { return resp.json() } ) // 데이터 가공
        .then( (x) => { // 가공된 데이터로 할수있는거 하기
            
        } );
        
    }
</script>
<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>