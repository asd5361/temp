package com.semi.jdgr.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class BlogDao {

	// 블로그 url 정보 가져오기
	public BlogVo getUserBlog(Connection conn, String getBlogUrl) throws Exception {

		// sql
		String sql = "SELECT * FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE B.BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, getBlogUrl);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		BlogVo blogUrlVo = null;
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
			String memNick = rs.getString("MEM_NICK");
			String memId = rs.getString("MEM_ID");
			
			blogUrlVo = new BlogVo();
			blogUrlVo.setBlogNo(blogNo);
			blogUrlVo.setMemNo(memNo);
			blogUrlVo.setMemNick(memNick);
			blogUrlVo.setMemId(memId);
			blogUrlVo.setBlogTitle(blogTitle);
			blogUrlVo.setOpenYn(openYn);
			blogUrlVo.setLayout(layout);
			blogUrlVo.setSkin(skin);
			blogUrlVo.setClockYn(clockYn);
			blogUrlVo.setMapYn(mapYn);
			blogUrlVo.setrCommentsYn(rCommentsYn);
			blogUrlVo.setFollowBlogYn(followBlogYn);
			blogUrlVo.setVisitorsCntYn(visitorsCntYn);
			blogUrlVo.setBlogImg(blogImg);
			blogUrlVo.setrComments(rComments);
			blogUrlVo.setVisitCnt(visitCnt);
			blogUrlVo.setBlogMain(blogMain);
			blogUrlVo.setRepYn(repYn);
			blogUrlVo.setBlogUrl(blogUrl);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogUrlVo;
	}

	// 카테고리 그룹 가져오기
	public List<GroupVo> getGroupList(Connection conn, BlogVo blogVo) throws Exception {
		
		// sql
		String sql = "SELECT * FROM MYBLOG_CATEGORY MC JOIN BLOG B ON B.BLOG_NO = MC.BLOG_NO WHERE B.BLOG_NO = ? AND MC.DEL_YN = 'N' ORDER BY MC.GROUP_ORDER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getBlogNo());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<GroupVo> groupVoList = new ArrayList<GroupVo>();
		while(rs.next()) {
			String groupNo = rs.getString("GROUP_NO");
			String groupName = rs.getString("GROUP_NAME");
			String groupOrder = rs.getString("GROUP_ORDER");
			
			GroupVo vo = new GroupVo();
			vo.setNo(groupNo);
			vo.setName(groupName);
			vo.setOrder(groupOrder);
			
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

	// 대표블로그 수정하기
	public int editBlogRep(Connection conn, BlogVo blogVo) throws Exception {

		// sql
		String sql = "UPDATE BLOG SET REP_YN = CASE WHEN BLOG_URL = ? THEN 'Y' ELSE 'N' END WHERE MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getBlogUrl());
		pstmt.setString(2, blogVo.getMemNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 블로그 만들기
	public int createBlog(Connection conn, BlogVo blogVo) throws Exception {

		// sql
		String sql = "INSERT INTO BLOG ( BLOG_NO , MEM_NO , BLOG_IMG , BLOG_TITLE , BLOG_URL ) VALUES ( SEQ_BLOG_NO.NEXTVAL , ? , ? , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getMemNo());
		pstmt.setString(2, blogVo.getBlogImg());
		pstmt.setString(3, blogVo.getBlogTitle());
		pstmt.setString(4, blogVo.getBlogUrl());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 모든 블로그 정보 리스트 가져오기
	public List<BlogVo> getBlogAllList(Connection conn) throws Exception {

		// sql
		String sql = "SELECT * FROM BLOG";
		PreparedStatement pstmt = conn.prepareStatement(sql);
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

	// 블로그 정보 수정
	public int editInfo(Connection conn, BlogVo blogVo) throws Exception {

		// sql
		String sql = "UPDATE BLOG SET BLOG_TITLE = ? , BLOG_IMG = ? WHERE BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getBlogTitle());
		pstmt.setString(2, blogVo.getBlogImg());
		pstmt.setString(3, blogVo.getBlogUrl());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 이미지 업데이트 안하는 정보 수정
	public int editImgNoInfo(Connection conn, BlogVo blogVo) throws Exception {
		// sql
		String sql = "UPDATE BLOG SET BLOG_TITLE = ? WHERE BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getBlogTitle());
		pstmt.setString(2, blogVo.getBlogUrl());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 블로그 레이아웃 정보 수정
	public int editLayout(Connection conn, BlogVo blogVo) throws Exception {

		// sql
		String sql = "UPDATE BLOG SET LAYOUT = ? , CLOCK_YN = ? , MAP_YN = ? , R_COMMENTS_YN = ? , FOLLOW_BLOG_YN = ? , VISITORS_CNT_YN = ? WHERE BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getLayout());
		pstmt.setString(2, blogVo.getClockYn());
		pstmt.setString(3, blogVo.getMapYn());
		pstmt.setString(4, blogVo.getrCommentsYn());
		pstmt.setString(5, blogVo.getFollowBlogYn());
		pstmt.setString(6, blogVo.getVisitorsCntYn());
		pstmt.setString(7, blogVo.getBlogUrl());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 블로그 스킨 정보 수정
	public int editSkin(Connection conn, BlogVo blogVo) throws Exception {

		// sql
		String sql = "UPDATE BLOG SET SKIN = ? WHERE BLOG_URL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogVo.getSkin());
		pstmt.setString(2, blogVo.getBlogUrl());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 블로그 그룹 카테고리 추가
	public int createGroup(Connection conn, GroupVo groupVo, String blogNo) throws Exception {
		
		// sql
		String sql = "INSERT INTO MYBLOG_CATEGORY (GROUP_NO, BLOG_NO, GROUP_NAME, GROUP_ORDER) VALUES (SEQ_GROUP_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		pstmt.setString(2, groupVo.getName());
		pstmt.setString(3, groupVo.getOrder());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	// 블로그 그룹 카테고리 삭제
	public int deleteGroup(Connection conn, GroupVo groupVo, String blogNo) throws Exception {
		// sql
		String sql = "UPDATE MYBLOG_CATEGORY SET DEL_YN = 'Y' WHERE BLOG_NO = ? AND GROUP_NAME = ? AND GROUP_ORDER = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogNo);
		pstmt.setString(2, groupVo.getName());
		pstmt.setString(3, groupVo.getOrder());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 블로그 그룹 카테고리 수정
	public int editGroup(Connection conn, GroupVo groupVo, String blogNo) throws Exception {
		// sql
		String sql = "UPDATE MYBLOG_CATEGORY SET GROUP_NAME = ? WHERE BLOG_NO = ? AND GROUP_ORDER = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, groupVo.getName());
		pstmt.setString(2, blogNo);
		pstmt.setString(3, groupVo.getOrder());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 메인 포스트 카테고리 가져오기
	public List<CategoryVo> getCategoryList(Connection conn) throws Exception {

		// sql
		String sql = "SELECT * FROM CATEGORY_LIST";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>();
		while(rs.next()) {
			String categoryNo = rs.getString("CATEGORY_NO");
			String categoryName = rs.getString("CATEGORY_NAME");
			
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setCategoryNo(categoryNo);
			categoryVo.setCategoryName(categoryName);
			
			categoryVoList.add(categoryVo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return categoryVoList;
	}
	
	// 구독한 블로그 정보 가져오기
	public List<BlogVo> getFollowBlogList(Connection conn, String blogUrl) throws Exception {
		
		// sql
		String sql = "SELECT B.BLOG_NO , B.MEM_NO , F.MEM_NO AS FOLLOW_USER_NO , F.BLOG_NO AS FOLLOW_BLOG_NO , (SELECT BL.BLOG_URL FROM BLOG BL JOIN MEMBER M ON M.MEM_NO = BL.MEM_NO WHERE BL.BLOG_NO = F.BLOG_NO) AS FOLLOW_BLOG_URL , (SELECT M.MEM_NICK FROM BLOG BL JOIN MEMBER M ON M.MEM_NO = BL.MEM_NO WHERE BL.BLOG_NO = F.BLOG_NO) AS FOLLOW_NICK , (SELECT BL.BLOG_TITLE FROM BLOG BL JOIN MEMBER M ON M.MEM_NO = BL.MEM_NO WHERE BL.BLOG_NO = F.BLOG_NO) AS FOLLOW_BLOG_TITLE FROM BLOG B JOIN FOLLOW F ON F.MEM_NO = B.MEM_NO WHERE B.BLOG_URL = ? ORDER BY B.BLOG_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blogUrl);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<BlogVo> blogVoList = new ArrayList<BlogVo>();
		while(rs.next()) {
			String followBlogNo = rs.getString("FOLLOW_BLOG_NO");
			String followUserNo = rs.getString("FOLLOW_USER_NO");
			String followBlogUrl = rs.getString("FOLLOW_BLOG_URL");
			String followNick = rs.getString("FOLLOW_NICK");
			String followblogTitle = rs.getString("FOLLOW_BLOG_TITLE");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogNo(followBlogNo);
			blogVo.setMemNo(followUserNo);
			blogVo.setBlogUrl(followBlogUrl);
			blogVo.setMemNick(followNick);
			blogVo.setBlogTitle(followblogTitle);
			
			blogVoList.add(blogVo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return blogVoList;
	}

	// 구독한 블로그 삭제
	public int deleteSubscribeList(Connection conn, FollowVo[] dataArray) throws Exception {
		
		// 데이터가 있는만큼 쿼리문 추가
		String addQuery = "";
		for(int i = 0; i < dataArray.length; i++) {
			addQuery += "(?, ?)";
			if(i < dataArray.length - 1) {
				addQuery += ", ";
			}
		}
		// sql
		String sql = "DELETE FROM FOLLOW WHERE (BLOG_NO, MEM_NO) IN (" + addQuery + ")";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 값 바인딩
		int idx = 1;
		for (FollowVo vo : dataArray) {
			pstmt.setString(idx++, vo.getFollowMem());
            pstmt.setString(idx++, vo.getMemNo());
		}
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	// ------------------------------------------------------------------------------- admin
	
	// 모든 블로그 조회
	public List<BlogVo> getAllBlogInfo(Connection conn, PageVo pvo) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.BLOG_NO ,B.OPEN_YN ,B.LAYOUT ,B.SKIN ,B.CLOCK_YN ,B.MAP_YN ,B.R_COMMENTS_YN ,B.FOLLOW_BLOG_YN ,B.VISITORS_CNT_YN ,B.BLOG_IMG ,B.R_COMMENTS ,B.VISIT_CNT ,B.BLOG_MAIN ,B.REP_YN ,B.BLOG_TITLE ,B.BLOG_URL ,M.MEM_NO ,M.MEM_NICK ,M.MEM_ID FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO ORDER BY B.BLOG_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
		pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<BlogVo> blogVoList = new ArrayList<BlogVo>();
		while(rs.next()) {
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String memNick = rs.getString("MEM_NICK");
			String memId = rs.getString("MEM_ID");
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
			String blogTitle = rs.getString("BLOG_TITLE");
			String blogUrl = rs.getString("BLOG_URL");
			
			BlogVo vo = new BlogVo(blogNo, memNo, memNick, memId, blogTitle, openYn, layout, skin, clockYn, mapYn, rCommentsYn, followBlogYn, visitorsCntYn, blogImg, rComments, visitCnt, blogMain, repYn, blogUrl);
			
			blogVoList.add(vo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVoList;
	}

	// 전체 블로그 개수
	public int selectBlogCount(Connection conn) throws Exception {
		
		// sql
		String sql = "SELECT COUNT(*) as cnt FROM BLOG";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		  
		ResultSet rs = pstmt.executeQuery();
		  
		// rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		  
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		  
		return cnt;
	}

	// 블로그 넘버로 블로그 정보 가져오기
	public BlogVo selectBlogByNo(Connection conn, String no) throws Exception {

		// sql
		String sql = "SELECT * FROM BLOG B JOIN MEMBER M ON M.MEM_NO = B.MEM_NO WHERE B.BLOG_NO = ? ORDER BY B.BLOG_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		BlogVo blogVo = null;
		if(rs.next()) {
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String memNick = rs.getString("MEM_NICK");
			String memId = rs.getString("MEM_ID");
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
			String blogTitle = rs.getString("BLOG_TITLE");
			String blogUrl = rs.getString("BLOG_URL");
			
			blogVo = new BlogVo(blogNo, memNo, memNick, memId, blogTitle, openYn, layout, skin, clockYn, mapYn, rCommentsYn, followBlogYn, visitorsCntYn, blogImg, rComments, visitCnt, blogMain, repYn, blogUrl);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVo;
	}

	public int selectSearchBlogCount(Connection conn, Map<String, String> param) throws Exception {

		// sql 동적으로 처리
		String addQuery = "";
		if(param.get("memId") != null && !param.get("memId").isEmpty()) {
			addQuery += " AND M.MEM_ID LIKE ?";
		}
		if(param.get("memNick") != null && !param.get("memNick").isEmpty()) {
			addQuery += " AND M.MEM_NICK LIKE ?";
		}
		if(param.get("blogTitle") != null && !param.get("blogTitle").isEmpty()) {
			addQuery += " AND B.BLOG_TITLE LIKE ?";
		}
		if(param.get("blogUrl") != null && !param.get("blogUrl").isEmpty()) {
			addQuery += " AND B.BLOG_URL LIKE ?";
		}
		if(param.get("blogRep") != null && !param.get("blogRep").isEmpty()) {
			addQuery += " AND B.REP_YN = ?";
		}
		
		// sql
		String sql = "SELECT COUNT(*) FROM BLOG B JOIN MEMBER M ON M.MEM_NO = B.MEM_NO WHERE 1 = 1" + addQuery;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 동적 바인딩
		int index = 1;
		if(param.get("memId") != null && !param.get("memId").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("memId") + "%");
		}
		if(param.get("memNick") != null && !param.get("memNick").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("memNick") + "%");
		}
		if(param.get("blogTitle") != null && !param.get("blogTitle").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("blogTitle") + "%");
		}
		if(param.get("blogUrl") != null && !param.get("blogUrl").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("blogUrl") + "%");
		}
		if(param.get("blogRep") != null && !param.get("blogRep").isEmpty()) {
			pstmt.setString(index++, param.get("blogRep"));
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}

		System.out.println("검색결과 개수 : " + cnt);
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}

	// 블로그 관리 검색하기
	public List<BlogVo> adminBlogSearch(Connection conn, Map<String, String> param, PageVo pvo) throws Exception {
		// sql 동적으로 처리
		String addQuery = "";
		if(param.get("memId") != null && !param.get("memId").isEmpty()) {
			addQuery += " AND M.MEM_ID LIKE ?";
		}
		if(param.get("memNick") != null && !param.get("memNick").isEmpty()) {
			addQuery += " AND M.MEM_NICK LIKE ?";
		}
		if(param.get("blogTitle") != null && !param.get("blogTitle").isEmpty()) {
			addQuery += " AND B.BLOG_TITLE LIKE ?";
		}
		if(param.get("blogUrl") != null && !param.get("blogUrl").isEmpty()) {
			addQuery += " AND B.BLOG_URL LIKE ?";
		}
		if(param.get("blogRep") != null && !param.get("blogRep").isEmpty()) {
			addQuery += " AND B.REP_YN = ?";
		}
		
		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.BLOG_NO ,B.OPEN_YN ,B.LAYOUT ,B.SKIN ,B.CLOCK_YN ,B.MAP_YN ,B.R_COMMENTS_YN ,B.FOLLOW_BLOG_YN ,B.VISITORS_CNT_YN ,B.BLOG_IMG ,B.R_COMMENTS ,B.VISIT_CNT ,B.BLOG_MAIN ,B.REP_YN ,B.BLOG_TITLE ,B.BLOG_URL ,M.MEM_NO ,M.MEM_NICK ,M.MEM_ID FROM BLOG B JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE 1 = 1" + addQuery + " ORDER BY B.BLOG_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 동적 바인딩
		int index = 1;
		if(param.get("memId") != null && !param.get("memId").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("memId") + "%");
		}
		if(param.get("memNick") != null && !param.get("memNick").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("memNick") + "%");
		}
		if(param.get("blogTitle") != null && !param.get("blogTitle").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("blogTitle") + "%");
		}
		if(param.get("blogUrl") != null && !param.get("blogUrl").isEmpty()) {
			pstmt.setString(index++, "%" + param.get("blogUrl") + "%");
		}
		if(param.get("blogRep") != null && !param.get("blogRep").isEmpty()) {
			pstmt.setString(index++, param.get("blogRep"));
		}
		pstmt.setInt(index++, pvo.getStartRow());
		pstmt.setInt(index++, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<BlogVo> blogVoList = new ArrayList<BlogVo>();
		while(rs.next()) {
			String blogNo = rs.getString("BLOG_NO");
			String memNo = rs.getString("MEM_NO");
			String memNick = rs.getString("MEM_NICK");
			String memId = rs.getString("MEM_ID");
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
			String blogTitle = rs.getString("BLOG_TITLE");
			String blogUrl = rs.getString("BLOG_URL");
			
			BlogVo blogVo = new BlogVo(blogNo, memNo, memNick, memId, blogTitle, openYn, layout, skin, clockYn, mapYn, rCommentsYn, followBlogYn, visitorsCntYn, blogImg, rComments, visitCnt, blogMain, repYn, blogUrl);
			blogVoList.add(blogVo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return blogVoList;
	}

}
