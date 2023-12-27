package com.semi.jdgr.heart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class HeartDao {

//	// 공감 리스트 가져오기
//	public List<HeartVo> HeartList(Connection conn, String no) throws Exception {
//
//		// sql
//		String sql = "SELECT * FROM HEART WHERE POST_NO = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, no);
//		ResultSet rs = pstmt.executeQuery();
//
//		// rs
//		List<HeartVo> heartVoList = new ArrayList<HeartVo>();
//		while (rs.next()) {
//			String postNo = rs.getString("POST_NO");
//			String userNo = rs.getString("MEM_NO");
//
//			HeartVo heartVo = new HeartVo();
//			heartVo.setPostNo(postNo);
//			heartVo.setMemNo(userNo);
//
//			heartVoList.add(heartVo);
//		}
//
//		// close
//		JDBCTemplate.close(pstmt);
//		JDBCTemplate.close(rs);
//
//		return heartVoList;
//
//	}// HeartList
	
	// 공감 VO 가져오기
	public HeartVo HeartList(Connection conn, String no) throws Exception {

		// sql
		String sql = "SELECT * FROM HEART WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();

		// rs
		HeartVo heartVo = null;
		while (rs.next()) {
			String postNo = rs.getString("POST_NO");
			String userNo = rs.getString("MEM_NO");

			heartVo = new HeartVo();
			heartVo.setPostNo(postNo);
			heartVo.setMemNo(userNo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return heartVo;

	}// HeartList

	// 공감체크 기능
	public boolean checkHeart(Connection conn, String no, String memberNo) throws Exception {

		// sql
		String sql = "SELECT * FROM HEART WHERE POST_NO = ? AND MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, memberNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		boolean heartCheck = true;
		if (rs.next()) {
			heartCheck = false;
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return heartCheck;

	}// checkHeartDup

	// 공감추가 기능
	public int AddHeart(Connection conn, String no, String memberNo) throws Exception {

		// sql
		String sql = "INSERT INTO HEART (POST_NO, MEM_NO) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, memberNo);
		int add = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return add;

	}// AddHeary

	// 공감삭제 기능
	public int delHeart(Connection conn, String no, String memberNo) throws Exception {

		// sql
		String sql = "DELETE FROM HEART WHERE POST_NO = ? AND MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, memberNo);
		int del = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return del;

	}
	
//	// 공감수
//	public PostVo PostDetailHeartCnt(Connection conn, PostVo postDetailVo) throws Exception {
//
//		// sql
//		String sql = "SELECT COUNT(POST_NO) AS POST_NO FROM HEART WHERE POST_NO = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, postDetailVo.getPostNo());
//		ResultSet rs = pstmt.executeQuery();
//
//		// rs
//		PostVo heartCnt = null;
//		if (rs.next()) {
//			String postNo = rs.getString("POST_NO");
//
//			heartCnt = new PostVo();
//			heartCnt.setPostNo(postNo);
//		}
//
//		// close
//		JDBCTemplate.close(pstmt);
//		JDBCTemplate.close(rs);
//
//		return heartCnt;
//
//	}// heartCnt

	// 공감 알림테이블로 저장
	public int insertHeartAlarm(Connection conn, AlarmVo alarmVo) throws Exception {
//			String sql = "INSERT INTO MEMBER ( ALARM_NO ,RECEIVER_NO ,POST_NO ,SENDER_NO ,ALARM_TYPE ) VALUES ( SEQ_ALARM.NEXTVAL , ? , ? , ? , ?)";
		String sql = "INSERT INTO ALARM ( ALARM_NO ,RECEIVER_NO ,POST_NO ,SENDER_NO ,ALARM_TYPE) VALUES ( SEQ_ALARM.NEXTVAL , ? , ? , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, alarmVo.getReceiverNo());
		pstmt.setString(2, alarmVo.getPostNo());
		pstmt.setString(3, alarmVo.getSenderNo());
		pstmt.setString(4, alarmVo.getAlarmType());

		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

}
