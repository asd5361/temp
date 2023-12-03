package com.semi.jdgr.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.semi.jdgr.post.vo.PostVo;

public class PostDaoJOJ {
	
	// sql
	
	// rs
	
	// close

	// 포스트 상세보기 (화면)
	public PostVo PostDetail(Connection conn, String no) throws Exception {
		
		// sql
		String sql = "SELECT C.CATEGORY_NAME ,P.TITLE ,P.POST_IMG ,M.MEM_NICK ,P.ENROLL_DATE ,P.CONTENT ,H.POST_NO ,R.POST_NO FROM POST P JOIN CATEGORY_LIST C ON P.POST_NO = C.CATEGORY_NO JOIN HEART H ON P.POST_NO = H.POST_NO JOIN REPLY R ON P.POST_NO = REPLY_NO JOIN BLOG B ON P.POST_NO = B.BLOG_NO JOIN MEMBER M ON B.BLOG_NO = M.MEM_NO WHERE P.POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		while(rs.next()) {
			String categoryName = rs.getString("CATEGORY_NAME");
			String postTitle = rs.getString("TITLE");
			String postImg = rs.getString("POST_IMG");
			String userNick = rs.getString("MEM_NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String content = rs.getString("CONTENT");
			String heartCnt = rs.getString("POST_NO");
			String replyCnt = rs.getString("POST_NO");
			
			

		}
		
		// close
		
	}// PostDetail
	


}
