package com.greedy.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberRegistServlet
 */
@WebServlet("/member/regist")
public class MemberRegistServlet extends HttpServlet {
	/* 회원가입은 Get, Do 방식 모두 사용함 */
	
	/* 회원가입 버튼을 클릭시 get방식 요청으로 들어오기 때문에 회원가입하는 form으로 포워딩 해주는 역할 (a태그, url 방식) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/registForm.jsp";		
		request.getRequestDispatcher(path).forward(request, response);
	}

	/* 회원 가입 폼을 작성한 후 post 요청을 할 경우 처리하는 역할 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
