package com.semi.jdgr.user.reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.semi.jdgr.user.reply.vo.ReplyVo;
import com.semi.jdgr.util.JDBCTemplate;

public class ReplyDao {

	public int write(Connection conn, ReplyVo vo) {
	
      //SQL
      String sql = "";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, vo.getCon());
      int result = pstmt.executeUpdate();
      
      //close
      JDBCTemplate.close(pstmt);
      return result;
	
	}

}
