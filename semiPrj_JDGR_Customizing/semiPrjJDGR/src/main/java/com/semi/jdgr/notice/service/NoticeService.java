package com.semi.jdgr.notice.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.notice.dao.NoticeDao;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.util.JDBCTemplate;

public class NoticeService {
	
	
	//공지사항 상위 5개 조회하기
	public List<NoticeVo> selectNoticeTopList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> noticeVoList = dao.selectNoticeTopList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		
		return noticeVoList;
	}
	//공지사항 게시글 조회
	public List<NoticeVo> selectNoticeList(PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> noticeVoList = dao.selectNoticeList(conn,pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return noticeVoList;
	}
	public int selectNoticeCount() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int cnt = dao.selectNoticeCount(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	public List<NoticeVo> search(String searchValue, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> noticeVoList = dao.search(conn, searchValue,pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return noticeVoList;
	}
	public int selectSearchNoticeCount(String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int cnt = dao.selectSearchNoticeCount(conn,searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	//공지사항 상세 조회 + 조회수 증가
	public NoticeVo noticeDetail(String boardno) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int result = dao.increaseHit(conn,boardno);
		 NoticeVo vo = null;
		 
		if(result == 1) {
			vo = dao.noticeDetail(conn,boardno);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}
	public int selectAdminNoticeCount() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int cnt = dao.selectAdminNoticeCount(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	//관리자 게시글 전체 조회
	public List<NoticeVo> selectAdminNoticeList(PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> noticeVoList = dao.selectAdminNoticeList(conn,pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return noticeVoList;
	}
	//게시글 수정
	public int notideUpdate(NoticeVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int result = dao.noticeUpdate(conn,vo);
		
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
	public int noticeWrite(NoticeVo vo) throws Exception {
		
		//conn
		Connection conn =JDBCTemplate.getConnection();
		//dao
		NoticeDao dao = new NoticeDao();
		int result = dao.noticeWrite(conn,vo);
		
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
	public int adminNoticeSearchCount(NoticeVo searchVo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		int cnt = dao.adminNoticeSearchCount(conn,searchVo);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	public List<NoticeVo> adminNoticeSearch(NoticeVo searchVo, PageVo pvo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		List<NoticeVo> noticeVoList = dao.adminNoticeSearch(conn,searchVo, pvo);
		
		//close
		JDBCTemplate.close(conn);
		
		return noticeVoList;
	}
	public NoticeVo adminNoticeDetail(String noticeNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		NoticeDao dao = new NoticeDao();
		 NoticeVo vo = dao.adminNoticeDetail(conn,noticeNo);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

}
