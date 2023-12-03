package com.semi.jdgr.user.reply.service;

import java.sql.Connection;

import com.semi.jdgr.user.reply.dao.ReplyDao;
import com.semi.jdgr.user.reply.vo.ReplyVo;
import com.semi.jdgr.util.JDBCTemplate;

public class ReplyService {

	public int write(ReplyVo vo) throws Exception {
	
	// conn
	Connection conn = JDBCTemplate.getConnection();
	
	// dao
	ReplyDao dao = new ReplyDao();
	int result = dao.write(conn, vo);
	
	// tx
	if(result == 1) {
		JDBCTemplate.commit(conn);
	}else {
		JDBCTemplate.rollback(conn);
	}
	
	// close
	JDBCTemplate.close(conn);
	
	return result;
}
	
}
