package com.greedy.jsp.borad.model.service;

import java.awt.dnd.DropTargetContext;
import java.sql.Connection;
import java.util.List;

import com.greedy.jsp.borad.model.dao.BoardDAO;
import com.greedy.jsp.borad.model.dto.BoardDTO;
import com.greedy.jsp.borad.model.dto.PageInfoDTO;

import static com.greedy.jsp.common.jdbc.jdbcTemplate.getConnection;
import static com.greedy.jsp.common.jdbc.jdbcTemplate.close;

public class BoardService {

	private final BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	/**
	 * 페이징 처리를 위한 전체 게시물 수 조회용 메소드
	 * @return
	 */
	public int selectTotalCount() {
		
		Connection con = getConnection();
		
		int totalCount = boardDAO.selectTotalCount(con);
		
		close(con);
		
		return totalCount;
	}

	/**
	 * 게시물 전체 조회용 메소드
	 * @param pageInfo
	 * @return
	 */
	public List<BoardDTO> selectBoardList(PageInfoDTO pageInfo) {
		
		Connection con = getConnection();
		
		List<BoardDTO> boardList = boardDAO.selectBoardList(con, pageInfo);
		
		close(con);
		
		return boardList;
	}

}
