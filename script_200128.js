/**
 * 
 */

function formCheck(){
	var memo_content = document.getElementById("memo_content");
	var memo_pw = document.getElementById("memo_pw");
	
	if(memo_content.value.length == 0){
		alert("내용을 입력해 주세요");
		return false;
	}
	
	if(memo_pw.value.length == 0){
		alert("비밀번호를 입력해 주세요");
		return false;
	}
	
	return true;
}

function insertMemoForm(){
	location.href="insertMemoForm";
}

function delMemo(key){
	var pwCheck = document.getElementById("pwCheck"+key);
	if(pwCheck.value.length == 0){
		alert("비밀번호를 입력해 주세요");
		return false;
	}
	var deleteForm = document.getElementById("deleteForm");
	var memo_no = document.getElementById("memo_no");
	var memo_pw = document.getElementById("memo_pw");
	
	memo_no.value = key;
	memo_pw.value = pwCheck.value;
	deleteForm.submit();
}