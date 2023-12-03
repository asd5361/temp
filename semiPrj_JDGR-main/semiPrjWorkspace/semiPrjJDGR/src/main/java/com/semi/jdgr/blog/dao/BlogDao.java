package com.semi.jdgr.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class BlogDao {

	// 블로그 정보 가져오기
	public BlogVo getUserBlog(Connection conn, MemberVo memberVo, String getBlogUrl) throws Exception {

		// sql
		String sql = "SELECT * FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE M.MEM_NO = ? AND B.BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVo.getMemNo());
		pstmt.setString(2, getBlogUrl);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		BlogVo blogVo = null;
		if(rs.next()) {
			
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String blogTitle = rs.getString("BLOG_TITLE");
			String openYn = rs.getString("OPEN_YN");
			String layout = rs.getString("LAYOUT");
			String skin = rs.getString("SKIN");
			String clockYn = rs.getString("CLOCK_YN");
			String mapYn = rs.getString("MAP_YN");
			String rCommentsYn = rs.getString("R_COMMENTS_YN");
			String followBlogYn = rs.getString("FOLLOW_BLOG_YN");
			String visitorsCntYn = rs.getString("VISITORS_CNT_YN");
			String blogImg = rs.getString("BLOG_IMG");
			String rComments = rs.getString("R_COMMENTS");
			String visitCnt = rs.getString("VISIT_CNT");
			String blogMain = rs.getString("BLOG_MAIN");
			String repYn = rs.getString("REP_YN");
			String blogUrl = rs.getString("BLOG_URL");
			
			blogVo = new BlogVo();
			blogVo.setBlogNo(blogNo);
			blogVo.setMemNo(memNo);
			blogVo.setBlogTitle(blogTitle);
			blogVo.setOpenYn(openYn);
			blogVo.setLayout(layout);
			blogVo.setSkin(skin);
			blogVo.setClockYn(clockYn);
			blogVo.setMapYn(mapYn);
			blogVo.setrCommentsYn(rCommentsYn);
			blogVo.setFollowBlogYn(followBlogYn);
			blogVo.setVisitorsCntYn(visitorsCntYn);
			blogVo.setBlogImg(blogImg);
			blogVo.setrComments(rComments);
			blogVo.setVisitCnt(visitCnt);
			blogVo.setBlogMain(blogMain);
			blogVo.setRepYn(repYn);
			blogVo.setBlogUrl(blogUrl);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVo;
	}

	// 카테고리 그룹 가져오기
	public List<GroupVo> getGroupList(Connection conn, BlogVo blogVo) throws Exception {
		
		// sql
		String sql = "SELECT * FROM MYBLOG_CATEGORY MC JOIN BLOG B ON B.BLOG_NO = MC.BLOG_NO WHERE B.BLOG_NO = ? ORDER BY MC.GROUP_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getBlogNo());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<GroupVo> groupVoList = new ArrayList<GroupVo>();
		while(rs.next()) {
			String groupNo = rs.getString("GROUP_NO");
			String groupName = rs.getString("GROUP_NAME");
			
			GroupVo vo = new GroupVo();
			vo.setGroupNo(groupNo);
			vo.setGroupName(groupName);
			
			groupVoList.add(vo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return groupVoList;
	}

	// 블로그 리스트 가져오기
	public List<BlogVo> getBlogList(Connection conn, MemberVo memberVo) throws Exception {
		
		// sql
		String sql = "SELECT * FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE M.MEM_NO = ? ORDER BY B.BLOG_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVo.getMemNo());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<BlogVo> blogVoList = new ArrayList<BlogVo>();
		while(rs.next()) {
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String blogTitle = rs.getString("BLOG_TITLE");
			String openYn = rs.getString("OPEN_YN");
			String layout = rs.getString("LAYOUT");
			String skin = rs.getString("SKIN");
			String clockYn = rs.getString("CLOCK_YN");
			String mapYn = rs.getString("MAP_YN");
			String rCommentsYn = rs.getString("R_COMMENTS_YN");
			String followBlogYn = rs.getString("FOLLOW_BLOG_YN");
			String visitorsCntYn = rs.getString("VISITORS_CNT_YN");
			String blogImg = rs.getString("BLOG_IMG");
			String rComments = rs.getString("R_COMMENTS");
			String visitCnt = rs.getString("VISIT_CNT");
			String blogMain = rs.getString("BLOG_MAIN");
			String repYn = rs.getString("REP_YN");
			String blogUrl = rs.getString("BLOG_URL");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogNo(blogNo);
			blogVo.setMemNo(memNo);
			blogVo.setBlogTitle(blogTitle);
			blogVo.setOpenYn(openYn);
			blogVo.setLayout(layout);
			blogVo.setSkin(skin);
			blogVo.setClockYn(clockYn);
			blogVo.setMapYn(mapYn);
			blogVo.setrCommentsYn(rCommentsYn);
			blogVo.setFollowBlogYn(followBlogYn);
			blogVo.setVisitorsCntYn(visitorsCntYn);
			blogVo.setBlogImg(blogImg);
			blogVo.setrComments(rComments);
			blogVo.setVisitCnt(visitCnt);
			blogVo.setBlogMain(blogMain);
			blogVo.setRepYn(repYn);
			blogVo.setBlogUrl(blogUrl);
			
			blogVoList.add(blogVo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVoList;
	}

	// 대표블로그 가져오기
	public BlogVo getUserReqBlog(Connection conn, MemberVo memberVo) throws Exception {
		// sql
		String sql = "SELECT * FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE M.MEM_NO = ? AND B.REP_YN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVo.getMemNo());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		BlogVo blogVo = null;
		if(rs.next()) {
			
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String blogTitle = rs.getString("BLOG_TITLE");
			String openYn = rs.getString("OPEN_YN");
			String layout = rs.getString("LAYOUT");
			String skin = rs.getString("SKIN");
			String clockYn = rs.getString("CLOCK_YN");
			String mapYn = rs.getString("MAP_YN");
			String rCommentsYn = rs.getString("R_COMMENTS_YN");
			String followBlogYn = rs.getString("FOLLOW_BLOG_YN");
			String visitorsCntYn = rs.getString("VISITORS_CNT_YN");
			String blogImg = rs.getString("BLOG_IMG");
			String rComments = rs.getString("R_COMMENTS");
			String visitCnt = rs.getString("VISIT_CNT");
			String blogMain = rs.getString("BLOG_MAIN");
			String repYn = rs.getString("REP_YN");
			String blogUrl = rs.getString("BLOG_URL");
			
			blogVo = new BlogVo();
			blogVo.setBlogNo(blogNo);
			blogVo.setMemNo(memNo);
			blogVo.setBlogTitle(blogTitle);
			blogVo.setOpenYn(openYn);
			blogVo.setLayout(layout);
			blogVo.setSkin(skin);
			blogVo.setClockYn(clockYn);
			blogVo.setMapYn(mapYn);
			blogVo.setrCommentsYn(rCommentsYn);
			blogVo.setFollowBlogYn(followBlogYn);
			blogVo.setVisitorsCntYn(visitorsCntYn);
			blogVo.setBlogImg(blogImg);
			blogVo.setrComments(rComments);
			blogVo.setVisitCnt(visitCnt);
			blogVo.setBlogMain(blogMain);
			blogVo.setRepYn(repYn);
			blogVo.setBlogUrl(blogUrl);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVo;
	}

}
