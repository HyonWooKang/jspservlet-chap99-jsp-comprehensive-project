package com.greedy.jsp.borad.model.dao;

import static com.greedy.jsp.common.jdbc.jdbcTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.jsp.borad.model.dto.BoardDTO;
import com.greedy.jsp.borad.model.dto.CategoryDTO;
import com.greedy.jsp.borad.model.dto.PageInfoDTO;
import com.greedy.jsp.common.config.ConfigLocation;
import com.greedy.jsp.member.model.dto.MemberDTO;

public class BoardDAO {
	
	private final Properties prop;
	
	public BoardDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "board/board-mapper.xml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int selectTotalCount(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectTotalCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				totalCount = rset.getInt("COUNT(*)");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return totalCount;
	}
	
	
	public List<BoardDTO> selectBoardList(Connection con, PageInfoDTO pageInfo) {
	      
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      List<BoardDTO> boardList = null;
	      
	      String query = prop.getProperty("selectBoardList");
	      
	      try {
	         pstmt = con.prepareStatement(query);
	         pstmt.setInt(1, pageInfo.getStartRow());
	         pstmt.setInt(2, pageInfo.getEndRow());
	      
	         rset = pstmt.executeQuery();
	         
	         boardList = new ArrayList<>();
	         
	         while(rset.next()) {
	            BoardDTO board = new BoardDTO();
	            board.setCategory(new CategoryDTO());
	            board.setWriter(new MemberDTO());
	            
	            board.setNo(rset.getInt("BOARD_NO"));
	            board.setCategoryCode(rset.getInt("CATEGORY_CODE"));
	            board.getCategory().setName(rset.getString("CATEGORY_NAME"));
	            board.setTitle(rset.getString("BOARD_TITLE"));
	            board.setBody(rset.getString("BOARD_BODY"));
	            board.setWriterMemberNo(rset.getInt("BOARD_WRITER_MEMBER_NO"));
	            board.getWriter().setNickname(rset.getString("NICKNAME"));
	            board.setCount(rset.getInt("BOARD_COUNT"));
	            board.setCreatedDate(rset.getDate("CREATED_DATE"));
	            board.setStatus(rset.getString("BOARD_STATUS"));
	            
	            boardList.add(board);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return boardList;
	   }

}
