package com.greedy.jsp.borad.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.jsp.borad.model.dto.BoardDTO;
import com.greedy.jsp.borad.model.dto.PageInfoDTO;
import com.greedy.jsp.borad.model.service.BoardService;
import com.greedy.jsp.common.paging.Pagination;

/**
 * Servlet implementation class BoardSelectListServlet
 */
@WebServlet("/board/list")
public class BoardSelectListServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 페이징 처리 진행 (페이지를 조회해와서 n개씩 잘라서 페이지 생성) */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		/* 페이지 번호에 따라 분기처리 (안하면 계속 1페이지로 간다) (""은 빈문자열이다) */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage); // 빈 문자열이 아니면 pageNo에 값을 숫자로 넣어준다.
		}
		
		/* 위에서 검증한 내용 중 페이지가 0이하일 경우에 대한 검증 */
		if(pageNo <= 0) {		
			pageNo = 1;
		}

		
		// 다른 페이지 작성 후 이어서 작성
		
		
		/* 전체 게시물 수가 필요함 */
		/* 데이터베이스에서 먼저 전체 게시물 수를 조회 */
		BoardService boardService = new BoardService();
		int totalCount = boardService.selectTotalCount();
		
		System.out.println("totalCount 체크 : " + totalCount);
		
		/* 한 페이지에 보여줄 게시물 수 */
		int limit = 10;
		
		/* 합 번에 보여줄 페이징 버튼의 수 */
		int buttonAmount = 5;
		
		
		// DTO, pagination DAO 작성
		
		
		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환 */
		PageInfoDTO pageInfo = Pagination.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		// 결과 확인
		System.out.println(pageInfo);
		
		/* 조회해온다. */
		List<BoardDTO> boardList = boardService.selectBoardList(pageInfo);
		
		System.out.println("boardList : " + boardList);
		
	}

}
