package com.semi.jdgr.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostDaoHJY {

	// 포스트 작성
	public int postWrite(Connection conn, PostVo postVo, String blogNum) throws Exception {
		
		// sql
		String sql = "INSERT INTO POST (POST_NO, BLOG_NO, CATEGORY_NO, GROUP_NO, TITLE, CONTENT, POST_IMG) VALUES (SEQ_POST_NO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNum);
		pstmt.setString(2, postVo.getCategoryNo());
		pstmt.setString(3, postVo.getGroupNo());
		pstmt.setString(4, postVo.getPostTitle());
		pstmt.setString(5, postVo.getContent());
		pstmt.setString(6, postVo.getPostImg());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 최신업데이트 포스트 가져오기
	public PostVo getNewPost(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM POST WHERE POST_NO = (SELECT MAX(POST_NO) FROM POST)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		PostVo insertPostVo = null;
		if(rs.next()) {
			String postNo = rs.getString("POST_NO");
			String blogNo = rs.getString("BLOG_NO");
			String categoryNo = rs.getString("CATEGORY_NO");
			String groupNo = rs.getString("GROUP_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String open = rs.getString("OPEN");
			String inquiry = rs.getString("INQUIRY");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String delYn = rs.getString("DEL_YN");
			String postImg = rs.getString("POST_IMG");
			
			insertPostVo = new PostVo();
			insertPostVo.setPostNo(postNo);
			insertPostVo.setBlogNo(blogNo);
			insertPostVo.setCategoryNo(categoryNo);
			insertPostVo.setGroupNo(groupNo);
			insertPostVo.setPostTitle(title);
			insertPostVo.setContent(content);
			insertPostVo.setOpen(open);
			insertPostVo.setInquiry(inquiry);
			insertPostVo.setEnrollDate(enrollDate);
			insertPostVo.setModifyDate(modifyDate);
			insertPostVo.setPostDelYn(delYn);
			insertPostVo.setPostImg(postImg);
		}
		

		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return insertPostVo;
	}

	// 포스트 넘버로 가져오기
	public PostVo getPostByNo(Connection conn, String postNo) throws Exception {

		// sql
		String sql = "SELECT * FROM POST WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postNo);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		PostVo postVo = null;
		if(rs.next()) {
			String blogNo = rs.getString("BLOG_NO");
			String categoryNo = rs.getString("CATEGORY_NO");
			String groupNo = rs.getString("GROUP_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String open = rs.getString("OPEN");
			String inquiry = rs.getString("INQUIRY");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String delYn = rs.getString("DEL_YN");
			String postImg = rs.getString("POST_IMG");
			
			postVo = new PostVo();
			postVo.setPostNo(postNo);
			postVo.setBlogNo(blogNo);
			postVo.setCategoryNo(categoryNo);
			postVo.setGroupNo(groupNo);
			postVo.setPostTitle(title);
			postVo.setContent(content);
			postVo.setOpen(open);
			postVo.setInquiry(inquiry);
			postVo.setEnrollDate(enrollDate);
			postVo.setModifyDate(modifyDate);
			postVo.setPostDelYn(delYn);
			postVo.setPostImg(postImg);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return postVo;
	}

	// 포스트 수정하기
	public int postEdit(Connection conn, PostVo postVo) throws Exception {

		// sql
		String sql = "UPDATE POST SET CATEGORY_NO = ? , GROUP_NO = ? , TITLE = ? , CONTENT = ? , MODIFY_DATE = SYSDATE WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postVo.getCategoryNo());
		pstmt.setString(2, postVo.getGroupNo());
		pstmt.setString(3, postVo.getPostTitle());
		pstmt.setString(4, postVo.getContent());
		pstmt.setString(5, postVo.getPostNo());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 포스트 삭제하기
	public int postDelete(Connection conn, String postNo) throws Exception {
		// sql
		String sql = "UPDATE POST SET DEL_YN = 'Y' WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postNo);
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	

}
