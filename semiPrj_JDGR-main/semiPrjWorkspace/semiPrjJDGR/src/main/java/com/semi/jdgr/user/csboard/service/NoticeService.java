package com.semi.jdgr.user.csboard.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.notice.dao.NoticeDao;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.post.vo.PageVo;
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

}
