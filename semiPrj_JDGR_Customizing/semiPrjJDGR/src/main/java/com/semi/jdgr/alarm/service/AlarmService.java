package com.semi.jdgr.alarm.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.alarm.dao.AlarmDao;
import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AlarmService {

	public List<AlarmVo> selectAlarmList(String memNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		AlarmDao dao = new AlarmDao();
		List<AlarmVo> alarmVoList = dao.selectAlarmList(conn, memNo);

		// close
		

		for (AlarmVo alarmVo : alarmVoList) {
			String userNick = dao.getUserNick(conn, alarmVo.getSenderNo());
			alarmVo.setUserNick(userNick);
		}
		
		for (AlarmVo alarmVo : alarmVoList) {
			String blogUrl = dao.getBlogUrl(conn, alarmVo.getSenderNo());
			alarmVo.setBlogUrl(blogUrl);
		}
		// close
		for (AlarmVo alarmVo : alarmVoList) {
			if(alarmVo.getPostNo() != null) {				
			String postTitle = dao.getPostTitle(conn, alarmVo.getPostNo());
			alarmVo.setPostTitle(postTitle);
			}
		}
		
		
		System.out.println();
		JDBCTemplate.close(conn);
		return alarmVoList;
	}

	public int deleteAlarm(String alarmNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		AlarmDao dao = new AlarmDao();
		int result = dao.deleteAlarm(conn, alarmNo);
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}

}
