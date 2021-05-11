package com.greedy.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.jsp.member.model.dto.MemberDTO;
import com.greedy.jsp.member.model.service.MemberService;

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
		
		/* 입력받은 값의 정제
		 * 유효성 체크는 보통 js에서 하는데, 필요 시 controller에 일부 추가 할 수 있다.
		 * 예를 들면 1차, 2차, 3차 체크를 해줄 수 있다.
		 */
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone").replace("-", "");
		// 전화번호의 하이픈을 지우는 처리
		String email = request.getParameter("email");
		String address = request.getParameter("zipCode") + "$" + request.getParameter("address1") + "$"
						+ request.getParameter("address2");
		/* 특수문자($)를 사용하는 이유는, DB에 저장해두고 구분자로 사용하여 잘라내기를 하기 위함
		 * ==> split으로 잘라내서 배열로 받아오면 된다.
		 * 단, 구분자는 고객들이 사용하지 않는 문자로 처리해야함
		 */
		
		// 값을 DTO로 보내기
		MemberDTO requestMember = new MemberDTO();
		requestMember.setId(memberId);
		requestMember.setPwd(memberPwd);
		requestMember.setNickname(nickname);
		requestMember.setPhone(phone);
		requestMember.setEmail(email);
		requestMember.setAddress(address);
		
		System.out.println("memberController requestMember : " + requestMember);
		
		// insert는 결과 값이 정수형으로 들어온다. (받아올때)
		int result = new MemberService().registMember(requestMember);
		
		System.out.println("result : " + result);
		// 테스트 시 반복되는 db 접근을 막기 위해 주석처리
		
		String page = "";
		
		if(result > 0) {
			// forward 방식이면 주소가 안바뀌고 Servlet mapping("/member/regist")을 계속 봄
			page = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertMember");
			// successCode를 주고, 해당 페이지에서 요청을 보낸다는 구분자 insertMember를 설정하였다.
			
		} else {
			
			page = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "회원 가입 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
