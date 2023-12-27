package com.semi.jdgr.csboard.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.csboard.dao.CsboardDao;
import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class CsboardService {

	//문의글 상위 5개 불러오기
	public List<CsboardVo> selectCsboardTopList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVoList = dao.selectCsboardTopList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return csboardVoList;
	}

	public int selectCsboardCount() throws Exception {

		//conn
		Connection conn =JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int cnt = dao.selectCsboardCount(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	public List<CsboardVo> selectCsboardList(PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVo = dao.selectCsboardList(conn,pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return csboardVo;
	}

	public int selectSearchCsboardCount(String searchValue) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int cnt = dao.selectSearchCsboardCount(conn,searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	public List<CsboardVo> search(String searchValue, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVoList = dao.search(conn,searchValue,pvo);
		//close
		
		
		return csboardVoList;
	}

	public CsboardVo csboardDetail(String board) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		CsboardVo vo = dao.csboardDetail(conn,board);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

	public int CsboardWrite(CsboardVo vo) throws Exception{
		
		//conn
		Connection conn =JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int result = dao.CsboardWrite(conn,vo);
		
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
	
	public int selectAdminCsboardCount() throws Exception {
		
		//conn
		Connection conn =JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int cnt = dao.selectAdminCsboardCount(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	//관리자 문의글 전체 목록 조회 
	public List<CsboardVo> selectAdminCsboardList(PageVo pvo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVo = dao.selectAdminCsboardList(conn,pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return csboardVo;
	}

	public CsboardVo adminCsboardDetail(String board) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		CsboardVo vo = dao.adminCsboardDetail(conn,board);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}	
	
	//관리자 문의 답변 입력
	public int csboardAnsewr(CsboardVo vo, String dateColumn) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int result = dao.csboardAnsewr(conn,vo,dateColumn);

		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}
	//관리자 1:1 문의 검색 갯수
	public int adminSelectCsboardCount(CsboardVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		int cnt = dao.adminCSboardSearchCount(conn, vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}
	
	//관리자 1:1 문의 검색 
	public List<CsboardVo> adminCsboardSearchController(CsboardVo vo, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		CsboardDao dao = new CsboardDao();
		List<CsboardVo> csboardVoList = dao.adminCsboardSearch(conn, vo,pvo);
		
		//close
		JDBCTemplate.close(conn);
			
		return csboardVoList;
		
	}
	




}
