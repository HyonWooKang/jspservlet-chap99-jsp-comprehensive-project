package com.greedy.jsp.common.paging;

import com.greedy.jsp.borad.model.dto.PageInfoDTO;

public class Pagination {
	
	public static PageInfoDTO getPageInfo(int pageNo, int totalCount, int limit, int buttonAmount) {
		
		/* PageNo와 totalCount는 매개변수로 넘어온 상태이다. */
		
		int maxPage;		// 전체 페이지에서 가장 마지막 페이지
		int startPage;		// 한 번에 표시될 페이지 버튼의 시작할 페이지
		int endPage;		// 합 너에 표시될 페이지 버튼의 끝나는 페이지
		int startRow;
		int endRow;
		
		/* 총 페이지 수 계산 */
		//maxPage = (int)((double)totalCount / limit + 0.9);
		maxPage = (int)Math.ceil((double)totalCount / limit); // ceil은 오름 처리
		
		/* 현재 페이지에 보여줄 시작 페이지 수 */
		/* 예를들어 10개씩 보여주려고 한다. = 1, 11, 21, 31... */
		//startPage = ((int)((double)pageNo / buttonAmount + 0.9) -1) * buttonAmount + 1;
		// 예를 들어보자.             (  1    /     5  = 0.2 + 0.9 = 1.1) => 1 - 1 = 0 * 5 + 1; = 1 page 
		startPage = (int)(Math.ceil((double) pageNo / buttonAmount) - 1) * buttonAmount + 1;
		
		/* 목록 아래쪽에 보여질 마지막 페이지 */
		endPage = startPage + buttonAmount - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		/* 마지막 페이지는 0이 될 수 없기 때문에 게시물이 아무 것도 존재하지 않으면 max 페이지와 endPage는 0이 된다. */
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		/* 조회할 시작 번호와 마지막 행 번호 계산 */
		startRow = (pageNo - 1) * limit + 1; //4페이지 (4-1) * 10 + 1 = 31
		endRow = startRow + limit - 1;
		
		PageInfoDTO pageInfo = new PageInfoDTO(pageNo, totalCount, limit, buttonAmount, maxPage, startPage, endPage, startRow, endRow);
		
		return pageInfo;
	}
	
}
