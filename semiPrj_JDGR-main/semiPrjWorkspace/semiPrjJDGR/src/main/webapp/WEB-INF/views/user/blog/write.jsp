<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/user/common/header.jsp" %>
 <!-- summernote -->
<script src="/jdgr/resources/user/js/jquery-3.6.0.js"></script>
<script src="/jdgr/resources/user/summernote/summernote-lite.js"></script>
<script src="/jdgr/resources/user/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/jdgr/resources/user/summernote/summernote-lite.css">

<!-- main -->
<main>
    <div class="inner">

        <div class="write_box">

            <form action="/blog/write" method="post">
                <div class="inp_area">
                	<label for="">구분</label>
                    <select name="categoryNo">
                        <option value="1">사진, 게임 등</option>
                    </select>
                </div>
                <div class="inp_area">
                	<label for="">카테고리</label>
                    <select name="groupNo">
                        <option value="1">유저가 만든 카테고리 없으면 디폴트에 넣기</option>
                    </select>
                </div>
                <div class="inp_area">
                	<label for="">제목</label>
                	<input type="text" name="title" placeholder="오늘날짜">
                </div>
                <div class="write_area">
                    <textarea id="summernote" name="content"></textarea>
                </div>
            
            
	            <div class="btn_area mt20">
	                <button type="button" class="cancle">취소</button>
	                <button type="submit" class="complete">등록</button>
	            </div>
            </form>
        </div>

    </div>
</main>
<!-- //main -->

<script>
const fontArr = ['NotoSansKR', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'];
    $('#summernote').summernote({
        // height: '100', // 에디터 높이
        // minHeight: null, // 최소 높이
        // maxHeight: null, // 최대 높이
        focus: true, // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR", // 한글 설정
        placeholder: '',	//placeholder 설정
        toolbar: [
                    // [groupName, [list of button]]
                    ['fontname', ['fontname']],
                    ['fontsize', ['fontsize']],
                    ['style', ['bold','italic','underline','strikethrough','forecolor','backcolor','superscript','subscript','clear']],
                    ['color', ['forecolor','color']],
                    ['table', ['table']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']],
                    ['insert',['picture','link','video']],
                    ['view', ['fullscreen', 'help']]
                  ],
        fontNames: fontArr,
        fontNamesIgnoreCheck: fontArr,
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
        callbacks: {	//여기 부분이 이미지를 첨부하는 부분
            onImageUpload : function(files) {
            	for(let i = 0; i < files.length; i++){
                    uploadWriteImageFile(files[i], this);
                }
            },
            // onPaste: function (e) {
            //     var clipboardData = e.originalEvent.clipboardData;
            //     if (clipboardData && clipboardData.items && clipboardData.items.length) {
            //         var item = clipboardData.items[0];
            //         if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
            //             e.preventDefault();
            //         }
            //     }
            // }
        }
    });

    // 이미지 업로드 함수
    function uploadWriteImageFile(file, editor) {
		
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "/jdgr/blog/write/imageUpload",
			contentType : false,
			processData : false,
			success : function(x) {
// 				alert(x);
				$(editor).summernote('insertImage', x);
			}
		});
	}
</script>


<%@ include file="/WEB-INF/views/user/common/footer.jsp" %>
