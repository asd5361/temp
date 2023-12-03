package com.semi.jdgr.blog.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.blog.dao.BlogDao;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class BlogService {

	// 블로그 정보 가져오기
	public BlogVo getUserblog(MemberVo memberVo, String getBlogUrl) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogVo = dao.getUserBlog(conn, memberVo, getBlogUrl);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVo;
	}
	
	// 카테고리 그룹정보 가져오기
	public List<GroupVo> getGroupList(BlogVo blogVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<GroupVo> groupVoList = dao.getGroupList(conn, blogVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return groupVoList;
	}

	// 블로그 리스트 가져오기
	public List<BlogVo> getBlogList(MemberVo memberVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		List<BlogVo> blogVoList = dao.getBlogList(conn, memberVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVoList;
	}
	
	// 대표블로그 정보 가져오기
	public BlogVo getUserReqblog(MemberVo memberVo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		BlogDao dao = new BlogDao();
		BlogVo blogVo = dao.getUserReqBlog(conn, memberVo);
		
		// close
		JDBCTemplate.close(conn);
		
		return blogVo;
	}
	
}
