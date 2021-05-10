package com.greedy.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greedy.jsp.member.model.dto.MemberDTO;
import com.greedy.jsp.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		// 파라미터는 하나의 값만 가져올 수 있고, 복수일 경우 List 사용
		// dto가 2가지의 타입일 경우, MAP을 써준다
		
		System.out.println("memberId : " + memberId);
		System.out.println("memberPwd" + memberPwd);
	
		// DTO로 값 넘겨주기
		MemberDTO requestMember = new MemberDTO();
		requestMember.setId(memberId);
		requestMember.setPwd(memberPwd);
		
		
		MemberService memberService = new MemberService();
		
		// 로그인에 대한 1명의 유저 값을 받아오기
		MemberDTO loginMember = memberService.loginCheck(requestMember);
	
		
		if(loginMember != null) {
			// session에 담는 이유?
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
		
			System.out.println(request.getContextPath());
			// forward로 보내면 새로고침시 처음부터 모든 프로세스가 다시 실행되기 때문에 안됨
			response.sendRedirect(request.getContextPath());
			
		} else {
			request.setAttribute("message", "로그인 실패!");
			// 에러 페이지로 이동하고 끝이라 forward로 보내도 됨
			request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(request, response);
		}
		
		
	}

}
