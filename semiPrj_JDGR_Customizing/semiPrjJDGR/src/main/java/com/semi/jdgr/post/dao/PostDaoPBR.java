package com.semi.jdgr.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostDaoPBR {

	public List<PostVo> searchPostList(String type, String content, Connection conn, PageVo pvo) throws Exception {
		
		String sql =null;
		if(type.equals("writer")) {
			System.out.println("여기가 writer : ");
			sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM , T.* FROM ( SELECT M.MEM_NICK, P.CONTENT, P.POST_IMG, P.POST_NO FROM MEMBER M JOIN BLOG B ON M.MEM_NO = B.MEM_NO JOIN POST P ON B.BLOG_NO = P.BLOG_NO WHERE M.MEM_NICK LIKE '%' || ?|| '%') T ) WHERE (RNUM BETWEEN ? AND ?)";
		}else {
			System.out.println("여기가 나머지 : ");
			sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM , T.* FROM ( SELECT M.MEM_NICK , P.CONTENT, P.POST_IMG ,P.POST_NO FROM POST P JOIN BLOG  B ON  P .BLOG_NO = B .BLOG_NO JOIN CATEGORY_LIST CL ON CL.CATEGORY_NO = P.CATEGORY_NO JOIN MEMBER M ON M.MEM_NO = B.MEM_NO WHERE P." + type + " LIKE '%' || ?|| '%' AND P.DEL_YN = 'N' ORDER BY B.BLOG_NO ASC ) T ) WHERE (RNUM BETWEEN ? AND ?)";
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setInt(2, pvo.getStartRow());
		pstmt.setInt(3, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<PostVo> postVoList = new ArrayList<PostVo>();
		
		while (rs.next()) {

			String userNick = rs.getString("MEM_NICK");// 작성자닉네임
			String postContent = rs.getString("CONTENT");// 내용
			String postImg = rs.getString("POST_IMG");// 포스트 이미지
			String postNo= rs.getString("POST_NO");// 포스트 넘버

			PostVo postVo = new PostVo();
			postVo.setUserNick(userNick);
			postVo.setContent(postContent);
			postVo.setPostImg(postImg);
			postVo.setPostNo(postNo);

			postVoList.add(postVo);

		}
		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return postVoList;
	}

	public List<PostVo> searchPostList(String type, String content, Connection conn) throws SQLException {
		System.out.println(type);
		String sql =null;
		if(type.equals("writer")) {
			System.out.println("여기가 writer : ");
			sql = "SELECT M.MEM_NICK, P.CONTENT, P.POST_IMG, P.POST_NO FROM MEMBER M JOIN BLOG B ON M.MEM_NO = B.MEM_NO JOIN POST P ON B.BLOG_NO = P.BLOG_NO WHERE M.MEM_NICK LIKE '%' || ?|| '%'";
		}else {
			System.out.println("여기가 나머지 : ");
			sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM , T.* FROM ( SELECT M.MEM_NICK , P.CONTENT, P.POST_IMG ,P.POST_NO FROM POST P JOIN BLOG  B ON  P .BLOG_NO = B .BLOG_NO JOIN CATEGORY_LIST CL ON CL.CATEGORY_NO = P.CATEGORY_NO JOIN MEMBER M ON M.MEM_NO = B.MEM_NO WHERE P." + type + " LIKE '%' || ? || '%' AND P.DEL_YN = 'N' ORDER BY B.BLOG_NO ASC ) T )";			
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		ResultSet rs = pstmt.executeQuery();
		
		List<PostVo> postVoList = new ArrayList<PostVo>();
		
		while (rs.next()) {

			String userNick = rs.getString("MEM_NICK");// 작성자닉네임
			String postContent = rs.getString("CONTENT");// 내용
			String postImg = rs.getString("POST_IMG");// 포스트 이미지
			String postNo= rs.getString("POST_NO");// 포스트 넘버

			PostVo postVo = new PostVo();
			postVo.setUserNick(userNick);
			postVo.setContent(postContent);
			postVo.setPostImg(postImg);
			postVo.setPostNo(postNo);

			postVoList.add(postVo);

		}
		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return postVoList;
	}
	
	
}// class