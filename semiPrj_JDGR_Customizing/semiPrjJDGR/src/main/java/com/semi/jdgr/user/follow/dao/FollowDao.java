package com.semi.jdgr.user.follow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.util.JDBCTemplate;

public class FollowDao {

	// 유저 넘버로 구독자 리스트 가져오기
	public List<FollowVo> getFollowListByUserNo(Connection conn, String userNo) throws Exception {

		// sql
		String sql = "SELECT F.MEM_NO, B.MEM_NO AS FOLLOW_BLOG_MEM FROM FOLLOW F JOIN BLOG B ON B.BLOG_NO = F.BLOG_NO WHERE F.MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		List<FollowVo> followVoList = new ArrayList<FollowVo>();
		while (rs.next()) {
			String followBlogMem = rs.getString("FOLLOW_BLOG_MEM");
			String MemNo = rs.getString("MEM_NO");

			FollowVo vo = new FollowVo();
			vo.setFollowMem(followBlogMem);
			vo.setMemNo(MemNo);

			followVoList.add(vo);
		}

		// close

		return followVoList;
	}

//	// 구독 리스트 가져오기
//	public List<FollowVo> FollowList(Connection conn, String blogNo) throws Exception {
//
//		// sql
//		String sql = "SELECT * FROM FOLLOW WHERE BLOG_NO = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, blogNo);
//		ResultSet rs = pstmt.executeQuery();
//
//		// rs
//		List<FollowVo> followVoList = new ArrayList<FollowVo>();
//		while (rs.next()) {
//			String FollowMem = rs.getString("BLOG_NO");
//			String userNo = rs.getString("MEM_NO");
//
//			FollowVo followVo = new FollowVo();
//			followVo.setFollowMem(FollowMem);
//			followVo.setMemNo(userNo);
//
//			followVoList.add(followVo);
//		}
//
//		// close
//		JDBCTemplate.close(pstmt);
//		JDBCTemplate.close(rs);
//
//		return followVoList;
//
//	}// FollowList

	// 구독 VO 가져오기
	public FollowVo FollowList(Connection conn, String blogNo) throws Exception {

		// sql
		String sql = "SELECT * FROM FOLLOW WHERE BLOG_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		FollowVo followVo = null;
		while (rs.next()) {
			String FollowMem = rs.getString("BLOG_NO");
			String userNo = rs.getString("MEM_NO");

			followVo = new FollowVo();
			followVo.setFollowMem(FollowMem);
			followVo.setMemNo(userNo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return followVo;

	}// FollowList

	// 구독체크 기능
	public boolean checkFollow(Connection conn, String blogNo, String memberNo) throws Exception {

		// sql
		String sql = "SELECT * FROM FOLLOW WHERE BLOG_NO = ? AND MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		pstmt.setString(2, memberNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		boolean followCheck = true;
		if (rs.next()) {
			followCheck = false;
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return followCheck;

	}// checkFollow

	// 구독추가 기능
	public int AddFollow(Connection conn, String blogNo, String memberNo) throws Exception {

		// sql
		String sql = "INSERT INTO FOLLOW (BLOG_NO, MEM_NO) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		pstmt.setString(2, memberNo);
		int add = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return add;

	}// AddHeary

	// 구독삭제 기능
	public int delFollow(Connection conn, String blogNo, String memberNo) throws Exception {

		// sql
		String sql = "DELETE FROM FOLLOW WHERE BLOG_NO = ? AND MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		pstmt.setString(2, memberNo);
		int del = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return del;

	}

	// 구독 알림테이블로 저장
	public int insertFollowAlarm(Connection conn, AlarmVo alarmVo) throws Exception {
//		String sql = "INSERT INTO MEMBER ( ALARM_NO ,RECEIVER_NO ,POST_NO ,SENDER_NO ,ALARM_TYPE ) VALUES ( SEQ_ALARM.NEXTVAL , ? , ? , ? , ?)";
		String sql = "INSERT INTO ALARM ( ALARM_NO ,RECEIVER_NO ,SENDER_NO ,ALARM_TYPE) VALUES ( SEQ_ALARM.NEXTVAL , ? , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, alarmVo.getReceiverNo());
		pstmt.setString(2, alarmVo.getSenderNo());
		pstmt.setString(3, alarmVo.getAlarmType());

		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

}
