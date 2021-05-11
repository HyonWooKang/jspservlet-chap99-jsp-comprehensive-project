package com.greedy.jsp.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptRequestWrapper extends HttpServletRequestWrapper {
	// 요청되어 있는 정보를 감싸는 클래스
	
	/* 부모쪽에 기본 생성자가 존재하지 않기 때문에 request를 전달해주는 생성자가 필요하다. */
	public EncryptRequestWrapper(HttpServletRequest request) {
		
		super(request);	
	}

	/* implements 들어가서 필요한 것 돌라서 쓰기
	 * = 우리는 사용자가 전달한 parameter 값을 정제해서 써야함 (password)
	 */
	@Override
	public String getParameter(String key) {
		
		String value = "";
		
		// regitFrom 체크
		if("memberPwd".equals(key)) {
			/* 스프링 시큐리티 중 BCrypt 암호화를 제공해주는 라이브러리 */
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			// 암호화를 풀 수 없으니 matches()로 평문과 비교해준다.
			value = passwordEncoder.encode(super.getParameter(key));
			//key는 memberPwd에 입력된 값

		} else {
			
			value = super.getParameter(key);
		}
		
		return value;
	}
	

	

}
