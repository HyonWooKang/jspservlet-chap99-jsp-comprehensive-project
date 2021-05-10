window.onload = function() {
	
	/* 회원가입 버튼 */
	/* 화면에 랜더링(그려진)된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 미리 if문으로 태그가 존재하는지 확인하자 */
	if(document.getElementById("regist")){
		const $regist = document.getElementById("regist");
		$regist.onclick = function() {
			location.href = "/jsp/member/regist";
		}
	}

	/* 로그아웃 버튼 */
	if(document.getElementById("logout")){
		const $logout = document.getElementById("logout");
		$logout.onclick = function() {
			location.href = "/jsp/member/logout";
		}	
	}

}