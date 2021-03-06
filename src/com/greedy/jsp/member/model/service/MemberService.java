package com.greedy.jsp.member.model.service;

import java.sql.Connection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greedy.jsp.member.model.dao.MemberDAO;
import com.greedy.jsp.member.model.dto.MemberDTO;
import static com.greedy.jsp.common.jdbc.jdbcTemplate.getConnection;
import static com.greedy.jsp.common.jdbc.jdbcTemplate.commit;
import static com.greedy.jsp.common.jdbc.jdbcTemplate.rollback;

public class MemberService {

	/* 의존 관계에 있는 객체가 불변을 유지할 수 있도록 final 필드를 선언 */
	private final MemberDAO memberDAO;
	// final을 넣어주면 다른 곳에서 의존관계가 있는 변수로 바꾸거나 새로 생성할 수 없다.
	// (충돌방지) 그래서 아래에 있는 MemberService() 생성자로 접근해야 한다.
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	/* 주소 값이 하나만 생성되어 닫기 전까지 하나로 유지되고
	 * 닫아버리면 외부에서 변조를 위해 접근해도 접속이 되지 않음
	 */
	
	public MemberDTO loginCheck(MemberDTO requestMember) {
		
		Connection con = getConnection();
		// return용 변수
		MemberDTO loginMember = null;
		
		String encPwd = memberDAO.selectEncryptedPwd(con, requestMember);
		
		System.out.println(encPwd);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		/* 로그인 요청한 원문 비밀번호와 저장되어 있는 암호화된 비밀번호와 일치하는지 확인 */
		// 에러나면 라이브러리 commons-logging-1.2.jar 추가 + spring security crypto 추가
		if(passwordEncoder.matches(requestMember.getPwd(), encPwd)) {
			/* 비밀번호가 일치하는 경우에만 회원 정보를 조회해온다. */
//			System.out.println("확인");
			loginMember = memberDAO.selectLoginMember(con,requestMember);
			
			System.out.println("loginMember : " + loginMember);
		}
		
		return loginMember;
	}

	/**
	 * 회원가입용 메소드
	 * @param requestMember
	 * @return
	 */
	public int registMember(MemberDTO requestMember) {
		
		Connection con = getConnection();
		
		int result = memberDAO.insertMember(con, requestMember);
		
		/* insert, update, delete의 경우에는 실행 결과에 따라서 transaction 처리 */
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return result;
	}
	
	

}
