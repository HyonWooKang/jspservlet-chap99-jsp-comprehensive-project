package com.greedy.jsp.borad.model.dto;

public class PageInfoDTO {

	private int pageNe;		 // 요청한 페이지번호
	private int totalCount;  // 전체 게시물 수
	private int limit;		 // 한 페이지에 보여줄 게시물 수 
	private int buttonAmount;// 한 번에 보여줄 페이징 버튼의 개수
	private int maxPage;	 // 가장 마지막 페이지
	
	// 하단의 페이지 번호 (1~5페이지, 다음은 6~10페이지)
	private int startPage;	 // 한번에 보여줄 페이징 버튼의 시작하는 페이지 수
	private int endPage;	 // 한번에 오벼줄 페이징 버튼의 마지막 페이지 수
	
	// 페이지 자를 때 사용하는 번호 (ex. 11~15번까지 가져오기)
	private int startRow;	 // DB 조회시 최신글부터 조회해야하는 행의 시작수
	private int endRow;		 // DB 조회시 최신글부터 조회해야 하는 행의 마지막 수 
	
	public PageInfoDTO() {}

	public PageInfoDTO(int pageNe, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage,
			int startRow, int endRow) {
		super();
		this.pageNe = pageNe;
		this.totalCount = totalCount;
		this.limit = limit;
		this.buttonAmount = buttonAmount;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public int getPageNe() {
		return pageNe;
	}

	public void setPageNe(int pageNe) {
		this.pageNe = pageNe;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getButtonAmount() {
		return buttonAmount;
	}

	public void setButtonAmount(int buttonAmount) {
		this.buttonAmount = buttonAmount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	@Override
	public String toString() {
		return "PageInfoDTO [pageNe=" + pageNe + ", totalCount=" + totalCount + ", limit=" + limit + ", buttonAmount="
				+ buttonAmount + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}
	
}
