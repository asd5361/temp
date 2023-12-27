package com.semi.jdgr.admin.blame.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.admin.blame.dao.AdminReplyBlameDao;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;
import com.semi.jdgr.page.vo.AdminBlamePageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminReplyBlameService {

	// 신고 목록 조회
	public List<AdminReplyBlameVo> selectBlameList(AdminBlamePageVo pvo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		List<AdminReplyBlameVo> blameVoList = dao.selectBlameList(conn, pvo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blameVoList;
		
	}//selectBlameList
	
	
	// 전체 신고 갯수 조회
	public int selectBlameCount() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		int cnt = dao.selectBlameCount(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}//selectBlameCount
	
	
//	//신고 항목 카테고리 조회(신고항목별, 제재 여부, 답변 여부, 처리 여부)
//	public List<AdminReplyBlameVo> getBlameList(AdminBlamePageVo pvo) throws Exception {
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		AdminReplyBlameDao dao = new AdminReplyBlameDao();
//		List<AdminReplyBlameVo> voList = dao.getBlameList(conn, pvo);
//		
//		//close
//		JDBCTemplate.close(conn);
//		
//		return voList;
//	}//getCategoryList
	
	
	//신고 목록 상세조회
	public AdminReplyBlameVo selectBlameDetail(String no) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		AdminReplyBlameVo vo = dao.selectBlameDetail(conn, no);

		
		// close
		JDBCTemplate.close(conn);
		
		
		return vo;
	}//selectBlameByNo
	
	
	//제재 여부, 답변 여부, 처리여부 null값에서 데이터 입력(게시글 수정)
	public int editBlame(AdminReplyBlameVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		int result = dao.updateBlame(conn , vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	
	
	
	// 신고 목록 검색(신고 번호 / 포스트 번호(신고되지 않은 포스트는 조회되지 않게) / 작성자(신고되지 않은 포스트 작성자 조회되지 않게)
	// 신고자(신고하지 않은 일반 유저 조회되지 않게) / 제목 / 날짜 설정.. / 리스트 / 상세내용 / 답변일자.. / 
	public List<AdminReplyBlameVo> searchBlame(AdminReplyBlameVo arbv, AdminBlamePageVo pvo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		List<AdminReplyBlameVo> blameVoList = dao.searchBlame(conn , arbv, pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return blameVoList;
	}//search
	
	
	// 게시글 갯수 조회 (검색값에 따라)
	public int selectSearchBlameCount(AdminReplyBlameVo arbv) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		int cnt = dao.selectSearchBlameCount(conn , arbv);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}


	public int rBlameUpdate(AdminReplyBlameVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		
		//dao
		AdminReplyBlameDao dao = new AdminReplyBlameDao();
		int result = dao.rBlameUpdate(conn, vo);
		int result2 = dao.rSancUpdate(conn, vo);
		
		int results = 0;
		
		if(result ==1 && result2 == 1) {
			JDBCTemplate.commit(conn);
			results = 1;
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		
		return results;
		
		//close
	
	}

	

	
	
	
}//class

//상세조회 해서 답변 작성하고 저장하는 것....
