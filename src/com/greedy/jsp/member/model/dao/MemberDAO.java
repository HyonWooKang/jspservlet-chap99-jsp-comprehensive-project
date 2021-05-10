package com.greedy.jsp.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.greedy.jsp.common.config.ConfigLocation;
import com.greedy.jsp.member.model.dto.MemberDTO;

import static com.greedy.jsp.common.jdbc.jdbcTemplate.close;

public class MemberDAO {

	private final Properties prop;
	
	public MemberDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "member/member-mapper.xml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 암호화 처리 된 비밀번호 조회용 메소드
	 * @param con 연결객체
	 * @param requestMember 로그인 입력값(아이디,패스워드)
	 * @return 암호화된 비밀번호
	 */
	public String selectEncryptedPwd(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 결과 값은 하나만 받아오니 rset 사용
		
		String encPwd = null;
		
		String query = prop.getProperty("selectEncrytedPwd");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getId());
			
			rset = pstmt.executeQuery();
			
			// 테이블에서 if를 해야 한 줄 내려감 (메뉴 row에서 결과 테이블 row로)
			if(rset.next()) {
				encPwd = rset.getString("MEMBER_PWD");
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return encPwd;
	}


	/**
	 * 패스워드가 일치시 회원정보 조회용 메소드
	 * @param con 연격객체 정보
	 * @param requestMember 고객정보(아이디, 패스워스)
	 * @return 조회된 사용자 정보
	 */
	public MemberDTO selectLoginMember(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO loginMember = null;
		
		String query = prop.getProperty("selectLoginMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new MemberDTO();
				
				loginMember.setNo(rset.getInt("MEMBER_NO"));
				loginMember.setId(rset.getString("MEMBER_ID"));
				loginMember.setNickname(rset.getString("NICKNAME"));
				loginMember.setPhone(rset.getString("PHONE"));
				loginMember.setEmail(rset.getString("EMAIL"));
				loginMember.setAddress(rset.getString("ADDRESS"));
				loginMember.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginMember.setRole(rset.getString("MEMBER_ROLE"));
				loginMember.setStatus(rset.getString("MEMBER_STATUS"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return loginMember;
	}

}
