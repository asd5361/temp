package com.semi.jdgr.admin.reply.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.admin.reply.dao.AdminReplyDao;
import com.semi.jdgr.admin.reply.vo.AdminReplyVo;
import com.semi.jdgr.page.vo.AdminReplyPageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminReplyService {



	// 댓글 목록 조회
	public List<AdminReplyVo> selectReplyList(AdminReplyPageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyDao dao = new AdminReplyDao();
		List<AdminReplyVo> adminReplyVoList = dao.selectReplyList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return adminReplyVoList;
		
	}//selectReplyList
	

	
	
	// 댓글 검색
	public List<AdminReplyVo> search(Map<String, String> m , AdminReplyPageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		AdminReplyDao dao = new AdminReplyDao();
		List<AdminReplyVo> boardVoList = dao.search(conn , m, pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return boardVoList;
	}
	
	
	// 댓글 갯수 조회 (검색값에 따라)
	public int selectSearchReplyCount(Map<String, String> m) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		AdminReplyDao dao = new AdminReplyDao();
		int cnt = dao.getReplyCountBySearch(conn , m);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}


	// 전체 댓글 갯수 조회
	public int selectReplyCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyDao dao = new AdminReplyDao();
		int cnt = dao.selectReplyCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}//selectBoardCount
	
	
	//게시글 상세조회(댓글 번호, 포스트 번호, 작성자, 내용, 작성일자, 수정일자, 공개여부(공개, 비공개)
	public AdminReplyVo selectReplyByNo(String no) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyDao dao = new AdminReplyDao();
		List<AdminReplyVo> adminReplyVoList = dao.selectReplyList(conn, pvo);
		
		
		// close
		JDBCTemplate.close(conn);
		
	}
	
	
	//댓글 삭제
	public int delete(String no, String memberNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		AdminReplyDao dao = new AdminReplyDao();
		int result = dao.delete(conn , no , memberNo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);

		return result;
	}//delete
	

	// 카테고리 리스트 조회
	public List<CategoryVo> getCategoryList() throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		AdminReplyDao dao = new AdminReplyDao();
		List<CategoryVo> voList = dao.getCategoryList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	
	//-----------------------↓↓↓↓↓↓jdbcWorkspace↓↓↓↓↓↓↓↓--------------------------------
//	// 게시글 검색 (닉네임)
//	public List<BoardVo> searchBoardByNick(String searchValue) throws Exception {
//		// conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		// DAO
//		List<BoardVo> voList = dao.searchBoardByNick(conn , searchValue);
//		
//		// close
//		JDBCTemplate.close(conn);
//		
//		return voList;
//	}
//
//	// 게시글 검색 (내용)
//	public List<BoardVo> searchBoardByContent(String searchValue) throws Exception {
//		// conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		// DAO
//		List<BoardVo> voList = dao.searchBoardByContent(conn , searchValue);
//		
//		// close
//		JDBCTemplate.close(conn);
//		
//		return voList;
//	}
	
//	// 게시글 검색 (제목)
//	public List<BoardVo> searchBoardByTitle(String searchValue) throws Exception {
//		
//		// conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		// DAO
//		List<BoardVo> voList = dao.searchBoardByTitle(conn , searchValue);
//		
//		// close
//		JDBCTemplate.close(conn);
//		
//		return voList;
//	}
}//class
