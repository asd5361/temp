package com.semi.jdgr.page.vo;

public class AdminReplyPageVo {
	
	private int listCount;		// 총 게시글 갯수
	private int currentPage;	// 현재페이지
	private int pageLimit;		// 페이징 영역 페이지갯수
	private int replyLimit;		// 한 페이지에 보여줄 게시글 갯수
	
	private int maxPage;		// 가장 마지막 페이지
	private int startPage;		// 페이징 영역 시작값
	private int endPage;		// 페이징 영역 마지막값
	
	private int startRow;		// 조회할 첫번째 행 번호 (ROWNUM)
	private int lastRow;		// 조회할 마지막 행 번호 (ROWNUM)
	
	//생성자
	public AdminReplyPageVo(int listCount , int currentPage , int pageLimit, int replyLimit) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.replyLimit = replyLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount/replyLimit);
		this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		this.startRow = (currentPage - 1) * replyLimit + 1;
		this.lastRow = startRow + replyLimit - 1;
	}
	
	

	public int getStartRow() {
		return startRow;
	}



	public int getLastRow() {
		return lastRow;
	}



	public int getListCount() {
		return listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return replyLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	
	

}
