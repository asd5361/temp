package com.semi.jdgr.user.follow.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.dao.HeartDao;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.post.dao.PostDaoJOJ;
import com.semi.jdgr.user.follow.dao.FollowDao;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.util.JDBCTemplate;

public class FollowService {
	
	// 구독 VO 가져오기
	public FollowVo FollowList(String blogNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		FollowDao dao = new FollowDao();
		FollowVo followVoList = dao.FollowList(conn, blogNo);

		// close
		JDBCTemplate.close(conn);

		return followVoList;
		
	}// FollowList

	// 구독체크 기능
	public boolean checkFollow(String blogNo, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		FollowDao dao = new FollowDao();
		boolean followCheck = dao.checkFollow(conn, blogNo, memberNo);

		// tx

		// close
		JDBCTemplate.close(conn);
		return followCheck;

	}// checkHeartDup

	// 구독추가 기능
	public int AddFollow(String blogNo, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		FollowDao dao = new FollowDao();
		int add = dao.AddFollow(conn, blogNo, memberNo);

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

	// 구독삭제 기능
	public int delFollow(String blogNo, String memberNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		FollowDao dao = new FollowDao();
		int del = dao.delFollow(conn, blogNo, memberNo);

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

	// 구독 알림테이블로 저장
	public int insertFollowAlarm(AlarmVo alarmVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		FollowDao dao = new FollowDao();
		int result = dao.insertFollowAlarm(conn, alarmVo);

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
