package com.greedy.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {

	/* 로그아웃 */
	// a링크 버튼(로그아웃)이라서 Get방식으로 해야한다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session은 강제종료 혹은 시간이 만료돠면 사라진다.
		
		/* session 종료 = 로그아웃 */
		request.getSession().invalidate();
		
		/* 메인 페이지로 이동 */
		response.sendRedirect(request.getContextPath());
	}

}
