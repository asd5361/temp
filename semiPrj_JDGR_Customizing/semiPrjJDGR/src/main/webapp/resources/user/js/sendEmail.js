function sendEmail() {
	const email1 = document.querySelector("input[name=email1]").value;
	const email2 = document.querySelector("input[name=email2]").value;
	const email = email1 + "@" + email2
	const checkNum = Math.floor(Math.random() * (900000) + 100000);

	console.log(checkNum);
	fetch("/jdgr/member/sendemail?email=" + email + "&checkNum=" + checkNum)
		.then((resp) => { return resp.json() })
		.then((data) => {
			const result = data.msg;
			const isOk = result === "ok";
			if (isOk) {
				document.getElementById("checkNum").value = checkNum;
				
			} else {
				alert("인증실패");
			}
		});

}

function sendEmailMypage() {
	const email1 = document.querySelector("input[name=email1]").value;
	const email2 = document.querySelector("input[name=email2]").value;
	const email = email1 + "@" + email2
	const checkNum = Math.floor(Math.random() * (900000) + 100000);

	console.log(checkNum);
	fetch("/jdgr/member/sendemail?email=" + email + "&checkNum=" + checkNum)
		.then((resp) => { return resp.json() })
		.then((data) => {
			const result = data.msg;
			const isOk = result === "ok";
			if (isOk) {
				document.getElementById("checkNum").value = checkNum;
				document.getElementById('cert').disabled = false;
				var confirmationMsg = document.getElementById('confirmationMsg');
	        if (confirmationMsg) {
	            confirmationMsg.style.display = 'block';
			    }
		
		        // '인증번호 입력' 부분을 보이게 설정
		        var certInputRow = document.getElementById('certInputRow');
		        if (certInputRow) {
		            certInputRow.style.display = '';
		        }

			} else {
				alert("인증번호 전송 실패");
			}
		});

}

function certification() {
	const nowCeckNum = document.querySelector("#checkNum").value;
	const inputCheckNum = document.querySelector("input[name=inputCheckNum]").value;


	if (nowCeckNum === inputCheckNum) {
		const email1 = document.querySelector("input[name=email1]").value;
		const email2 = document.querySelector("input[name=email2]").value;
		const email = email1 + "@" + email2;
		document.getElementById("nowEmail").value = email;
		
		alert("인증 성공");
	}
	else {
		alert("인증실패");

	}
	console.log(nowEmail);
}

function certificationMypage() {
	const nowCeckNum = document.querySelector("#checkNum").value;
	const inputCheckNum = document.querySelector("input[name=inputCheckNum]").value;


	if (nowCeckNum === inputCheckNum) {
		document.getElementById('pwd1').disabled = false;
		document.getElementById('pwd2').disabled = false;
		
		alert("인증 성공");
	}
	else {
		alert("인증실패");

	}
	console.log(nowEmail);
}

function idCertification(){
	certification();
	if(document.getElementById("nowEmail").value != null){
		// 동적으로 폼 생성
        const form = document.createElement("form");
        form.action = "/jdgr/member/printid";
        form.method = "GET"; 

        // nowEmail 값을 hidden input으로 추가
        const nowEmailInput = document.createElement("input");
        nowEmailInput.type = "hidden";
        nowEmailInput.name = "nowEmail";
        nowEmailInput.value = document.getElementById("nowEmail").value;

        form.appendChild(nowEmailInput);
        document.body.appendChild(form);

        // 폼 제출
        form.submit();
	}
}
function pwdCertification(){
	certification();
	if(document.getElementById("nowEmail").value != null){
		// 동적으로 폼 생성
        const form = document.createElement("form");
        form.action = "/jdgr/member/changepwd";
        form.method = "GET"; 


        const nowEmailInput = document.createElement("input");
        nowEmailInput.type = "hidden";
        nowEmailInput.name = "nowEmail";
        nowEmailInput.value = document.getElementById("nowEmail").value;

        form.appendChild(nowEmailInput);
        document.body.appendChild(form);

        // 폼 제출
        form.submit();
	}
}

function onChangeFruits(e) {
	let val = e.target.value;
	document.getElementById('input_addr').value = val;
	if ((document.querySelector("#input_addr").value) != "") {
		document.getElementById('input_addr').disabled = true;
	} else {
		document.getElementById('input_addr').disabled = false;

	}
}

