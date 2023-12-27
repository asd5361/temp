package com.semi.jdgr.heart.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.dao.HeartDao;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.post.dao.PostDaoJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class HeartService {
	
//	// 공감 리스트 가져오기
//	public List<HeartVo> HeartList(String no) throws Exception {
//
//		// conn
//		Connection conn = JDBCTemplate.getConnection();
//
//		// dao
//		HeartDao dao = new HeartDao();
//		List<HeartVo> heartVoList = dao.HeartList(conn, no);
//
//		// close
//		JDBCTemplate.close(conn);
//
//		return heartVoList;
//	}
	
	// 공감 VO 가져오기
	public HeartVo HeartList(String no) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		HeartDao dao = new HeartDao();
		HeartVo heartVo = dao.HeartList(conn, no);
		
		// close
		JDBCTemplate.close(conn);
		
		return heartVo;
	}

	// 공감체크 기능
	public boolean checkHeart(String no, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		HeartDao dao = new HeartDao();
		boolean heartCheck = dao.checkHeart(conn, no, memberNo);

		// tx

		// close
		JDBCTemplate.close(conn);
		return heartCheck;

	}// checkHeartDup

	// 공감추가 기능
	public int AddHeart(String no, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		HeartDao dao = new HeartDao();
		int add = dao.AddHeart(conn, no, memberNo);

		// tx
		if (add == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);
		return add;

	}// AddHeart

	// 공감삭제 기능
	public int delHeart(String no, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		HeartDao dao = new HeartDao();
		int del = dao.delHeart(conn, no, memberNo);

		// tx
		if (del == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return del;

	}

//	// 공감수 (블로그 카테고리 상세보기용)
//	public PostVo PostDetailHeartCnt(PostVo postDetailVo) throws Exception {
//
//		// conn
//		Connection conn = JDBCTemplate.getConnection();
//
//		// dao
//		HeartDao dao = new HeartDao();
//		PostVo postDetailHeartCnt = dao.PostDetailHeartCnt(conn, postDetailVo);
//
//		// tx
//
//		// close
//		JDBCTemplate.close(conn);
//
//		return postDetailHeartCnt;
//
//	}// heartHit

	// 공감 알림테이블로 저장
	public int insertHeartAlarm(AlarmVo alarmVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		HeartDao dao = new HeartDao();
		int result = dao.insertHeartAlarm(conn, alarmVo);

		// tx
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}

}
