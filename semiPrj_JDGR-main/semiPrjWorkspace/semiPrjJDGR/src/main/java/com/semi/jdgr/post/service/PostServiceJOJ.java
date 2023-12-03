package com.semi.jdgr.post.service;

import java.sql.Connection;

import com.semi.jdgr.post.dao.PostDaoJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostServiceJOJ {
	
	// conn
	
	// dao
	
	// close

	// 포스트 상세보기 (화면)
	public PostVo PostDetail(String no) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		PostVo postDetailVo = dao.PostDetail(conn, no);
		
		// close
		JDBCTemplate.close(conn);
		
		return postDetailVo;
		
	}// PostDetail
	



	


}
