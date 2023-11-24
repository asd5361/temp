
// header 팝업
const userInfoBtn = document.querySelector('header .util_box .after_login .user_box > a');
const alarmBtn = document.querySelector('.ico_alarm');

if (userInfoBtn && alarmBtn) {
    userInfoBtn.addEventListener('click', toggleHeaderPopup);
    alarmBtn.addEventListener('click', toggleHeaderPopup);
}

function toggleHeaderPopup(e) {
    e.preventDefault();
    const parentBox = this.parentNode;

    // 알람 삭제 버튼을 클릭한 경우 팝업을 열거나 닫지 않도록 처리
    if (e.target.classList.contains('delete')) {
        return;
    }

    parentBox.classList.toggle('on');

    if (parentBox.classList.contains('on')) {
        // 팝업이 열린 상태에서는 외부 클릭 시 닫기 기능 추가
        document.addEventListener('click', closeOnOutsideClick.bind(null, parentBox));
    } else {
        // 팝업이 닫힌 상태에서는 외부 클릭 시 닫기 기능 제거
        document.removeEventListener('click', closeOnOutsideClick);
    }
}

// 외부 클릭 시 팝업이 닫히도록 하는 함수 정의
function closeOnOutsideClick(parentBox, event) {
    if (!parentBox.contains(event.target)) {
        parentBox.classList.remove('on');
        document.removeEventListener('click', closeOnOutsideClick);
    }
}

// 알람 삭제 버튼에 대한 NodeList를 배열로 변환하여 처리
const alarmDeleteBtns = document.querySelectorAll('.pop_alarm ul li .delete');
const alarmDeleteBtnArray = Array.from(alarmDeleteBtns);

// 각 알람 삭제 버튼에 클릭 이벤트 리스너 추가
alarmDeleteBtnArray.forEach((deleteBtn) => {
    deleteBtn.addEventListener('click', handleAlarmDelete);
});

// 알람 삭제 처리 함수 정의
function handleAlarmDelete(e) {
    e.preventDefault();
    e.target.parentNode.remove(); // 삭제할지 disabled처리할지 고민중

    // 이 부분에서 팝업을 닫지 않도록 처리
    e.stopPropagation();
}


// 팝업
const body = document.querySelector('body');
const modalOpenButtons = document.querySelectorAll('.modal_open');
const modalCloseButtons = document.querySelectorAll('.modal_close');
const modals = document.querySelectorAll('.modal_box');

// 팝업 열기
modalOpenButtons.forEach(button => {
    button.addEventListener('click', (event) => {
        event.stopPropagation(); // 팝업을 열 때 이벤트 전파를 중단

        const targetID = button.getAttribute('data-target');
        const targetModal = document.querySelector(targetID);
        targetModal.style.display = 'flex';
        body.style.overflow = 'hidden';
        targetModal.querySelector('.modal_close').focus();

        // 영역 외 클릭 시 닫기
        const closeOnOutsideClick = (event) => {
            if (!targetModal.children[0].contains(event.target)) {
                // 클릭된 요소가 모달 내부가 아니면 닫기
                targetModal.style.display = 'none';
                body.style.overflow = '';
                document.removeEventListener('click', closeOnOutsideClick);
            }
        };

        // 클릭 이벤트 추가
        document.addEventListener('click', closeOnOutsideClick);
    });
});

// 팝업 닫기
modalCloseButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal_bg');
        modal.style.display = 'none';
        body.style.overflow = '';
    });
});

// tab
const tabButtons = document.querySelectorAll('.tab_group .tab_btns button');
const tabContents = document.querySelectorAll('.tab_group .tab_content');
const tabContentsDiv = document.querySelectorAll('.tab_group .tab_content > div');

if(tabButtons){
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const tabCode = this.getAttribute('rel');
    
            // 모든 탭 버튼과 컨텐츠 숨기기
            tabButtons.forEach(btn => btn.parentNode.classList.remove('on'));
            tabContentsDiv.forEach(content => content.classList.remove('on'));
    
            // 클릭한 탭 버튼과 해당하는 컨텐츠 표시
            this.parentNode.classList.add('on');
            document.querySelector('.' + tabCode).classList.add('on');
        });
    });
}


// 블로그 접기펴기
const blogBtn = document.querySelectorAll('.blog_layout .blog_left > div > .tit');
if(blogBtn){
    blogBtn.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            e.currentTarget.parentNode.classList.toggle('on');
        });
    });
}
const blogListBtn = document.querySelectorAll('.b_post_list > a');
if(blogListBtn){
    blogListBtn.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            e.currentTarget.parentNode.classList.toggle('on');
        });
    });
}

// 시계
const clockEl = document.querySelector('.clock');
const ampmTxt = document.querySelector('.ampm');

function updateClock() {
    const now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();
    let ampm = hours >= 12 ? '오후' : '오전';

    // 한 자리 숫자면 앞에 0을 추가
    hours = hours % 12 || 12;
    minutes = minutes < 10 ? '0' + minutes : minutes;
    seconds = seconds < 10 ? '0' + seconds : seconds;

    const timeString = hours + '시 ' + minutes + '분 ' + seconds + '초';
    const ampmString = ampm;
    clockEl.textContent = timeString;
    ampmTxt.textContent = ampmString;
}

// 1초마다 함수 호출
setInterval(updateClock, 1000);
// 페이지 로드 시 업데이트
updateClock();