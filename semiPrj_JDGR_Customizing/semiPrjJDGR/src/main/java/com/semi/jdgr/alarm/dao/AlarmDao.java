package com.semi.jdgr.alarm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AlarmDao {

	public List<AlarmVo> selectAlarmList(Connection conn, String memNo) throws Exception {
		String sql = "SELECT * FROM ALARM WHERE RECEIVER_NO = ? AND ACTIVE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memNo);
		ResultSet rs = pstmt.executeQuery();

		List<AlarmVo> alarmVoList = new ArrayList<AlarmVo>();

		while (rs.next()) {
			String alarmNo = rs.getString("ALARM_NO");
			String receiverNo = rs.getString("RECEIVER_NO");
			String postNo = rs.getString("POST_NO");
			String senderNo = rs.getString("SENDER_NO");
			String alarmType = rs.getString("ALARM_TYPE");
			String alarmDate = rs.getString("ALARM_DATE");
			String activeYn = rs.getString("ACTIVE_YN");
			String alarmContent = null;
			
			switch (alarmType) {
			case "POST": {
				alarmContent = "을(를) 포스팅하셨습니다.";
				break;
			}
			case "REPL": {
				alarmContent = "글에 댓글을 달았습니다.";
				break;
			}
			case "HEART": {
				alarmContent = "글에 공감을 하셨습니다.";
				break;
			}
			case "FOLLOW": {
				alarmContent = "내 블로그를 구독하셨습니다.";
				break;
			}
			}

			AlarmVo alarmVo = new AlarmVo();

			alarmVo.setAlarmNo(alarmNo);
			alarmVo.setReceiverNo(receiverNo);
			alarmVo.setPostNo(postNo);
			alarmVo.setSenderNo(senderNo);
			alarmVo.setActiveYn(activeYn);
			alarmVo.setAlarmDate(alarmDate);
			alarmVo.setAlarmType(alarmContent);
			alarmVoList.add(alarmVo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return alarmVoList;
	}

	public String getUserNick(Connection conn, String receiverNo) throws Exception {
		String sql = "SELECT MEM_NICK FROM MEMBER WHERE MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, receiverNo);
		ResultSet rs = pstmt.executeQuery();
		String userNick = null;
		if (rs.next()) {
			userNick = rs.getString("MEM_NICK");
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return userNick;
	}

	public String getPostTitle(Connection conn, String postNo) throws Exception {
		String sql = "SELECT TITLE FROM POST WHERE POST_NO= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postNo);
		ResultSet rs = pstmt.executeQuery();
		String postTitle = null;
		if (rs.next()) {
			postTitle = rs.getString("TITLE");
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return postTitle;
	}

	public String getBlogUrl(Connection conn, String senderNo) throws Exception {
		String sql = "SELECT BLOG_URL FROM BLOG WHERE MEM_NO = ? AND REP_YN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, senderNo);
		ResultSet rs = pstmt.executeQuery();
		String blogUrl = null;
		if (rs.next()) {
			blogUrl = rs.getString("BLOG_URL");
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return blogUrl;
	}
	
	public int deleteAlarm(Connection conn, String alarmNo) throws Exception {
		String sql = "UPDATE ALARM SET ACTIVE_YN = 'Y' WHERE ALARM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, alarmNo);

		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;

	}


}
