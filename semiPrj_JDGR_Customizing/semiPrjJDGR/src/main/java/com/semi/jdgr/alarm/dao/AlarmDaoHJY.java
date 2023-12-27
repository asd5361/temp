package com.semi.jdgr.alarm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AlarmDaoHJY {

	// 포스트 등록시 알람 insert
	public int postCreateAlarm(Connection conn, AlarmVo alarmVo, List<FollowVo> followVoList) throws Exception {

		// sql
		String sql = "INSERT INTO ALARM (ALARM_NO , RECEIVER_NO , POST_NO , SENDER_NO , ALARM_TYPE , ALARM_DATE) VALUES( SEQ_ALARM.NEXTVAL , ? , ? , ? , 'POST' , SYSDATE)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (FollowVo followVo : followVoList) {
			pstmt.setString(1, followVo.getFollowMem());
			pstmt.setString(2, alarmVo.getPostNo());
			pstmt.setString(3, alarmVo.getSenderNo());
			pstmt.addBatch();  // 각 행을 배치에 추가
		}
		
		int[] result = pstmt.executeBatch();
		int successCount = Arrays.stream(result).filter(r -> r >= 0).sum();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return successCount;
	}
	
}
