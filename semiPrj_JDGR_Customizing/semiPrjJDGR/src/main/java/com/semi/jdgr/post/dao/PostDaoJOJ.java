package com.semi.jdgr.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostDaoJOJ {

	// 포스트 VO 준비 (포스트 넘버로 조회)
	public PostVo PostInfo(Connection conn, String pNo) throws Exception {

		// sql
		String sql = "SELECT P.POST_NO ,B.BLOG_URL ,Y.GROUP_NO ,C.CATEGORY_NO FROM POST P JOIN CATEGORY_LIST C ON P.CATEGORY_NO = C.CATEGORY_NO JOIN MYBLOG_CATEGORY Y ON P.GROUP_NO = Y.GROUP_NO JOIN BLOG B ON P.BLOG_NO = B.BLOG_NO WHERE P.OPEN = 'Y' AND P.DEL_YN = 'N' AND P.POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo postInfoVo = null;
		if (rs.next()) {
			String postNo = rs.getString("POST_NO");
			String blogUrl = rs.getString("BLOG_URL");
			String groupNo = rs.getString("GROUP_NO");
			String categoryNo = rs.getString("CATEGORY_NO");

			postInfoVo = new PostVo();
			postInfoVo.setPostNo(postNo);
			postInfoVo.setBlogUrl(blogUrl);
			postInfoVo.setGroupNo(groupNo);
			postInfoVo.setCategoryNo(categoryNo);
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return postInfoVo;
	}

	// 포스트 상세보기
	public PostVo PostDetail(Connection conn, String GroupNo, String BlogUrl) throws Exception {

		// sql
		String sql = "SELECT P.POST_IMG ,P.TITLE ,P.CONTENT ,P.POST_NO ,P.BLOG_NO ,B.BLOG_URL ,P.GROUP_NO ,C.CATEGORY_NO ,C.CATEGORY_NAME ,M.MEM_ID ,M.MEM_NO ,M.MEM_NICK ,P.OPEN ,P.DEL_YN ,TO_CHAR (P.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE ,TO_CHAR (P.MODIFY_DATE, 'YYYY-MM-DD') AS MODIFY_DATE ,P.INQUIRY AS HIT_CNT FROM POST P JOIN CATEGORY_LIST C ON P.CATEGORY_NO = C.CATEGORY_NO JOIN MYBLOG_CATEGORY Y ON P.GROUP_NO = Y.GROUP_NO JOIN BLOG B ON P.BLOG_NO = B.BLOG_NO JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE P.OPEN = 'Y' AND P.DEL_YN = 'N' AND B.BLOG_URL = ? AND P.GROUP_NO = ? ORDER BY P.POST_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, BlogUrl);
		pstmt.setString(2, GroupNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo postDetailVo = null;
		if (rs.next()) {
			String postImg = rs.getString("POST_IMG");
			String postTitle = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String postNo = rs.getString("POST_NO");
			String blogNo = rs.getString("BLOG_NO");
			String blogUrl = rs.getString("BLOG_URL");
			String groupNo = rs.getString("GROUP_NO");
			String categoryNo = rs.getString("CATEGORY_NO");
			String categoryName = rs.getString("CATEGORY_NAME");
			String userNo = rs.getString("MEM_NO");
			String userId = rs.getString("MEM_ID");
			String userNick = rs.getString("MEM_NICK");
			String open = rs.getString("OPEN");
			String delYn = rs.getString("DEL_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String inquiryCnt = rs.getString("HIT_CNT");

			postDetailVo = new PostVo();
			postDetailVo.setPostImg(postImg);
			postDetailVo.setPostNo(postNo);
			postDetailVo.setPostTitle(postTitle);
			postDetailVo.setContent(content);
			postDetailVo.setBlogNo(blogNo);
			postDetailVo.setBlogUrl(blogUrl);
			postDetailVo.setGroupNo(groupNo);
			postDetailVo.setCategoryNo(categoryNo);
			postDetailVo.setCategoryName(categoryName);
			postDetailVo.setUserNo(userNo);
			postDetailVo.setUserId(userId);
			postDetailVo.setUserNick(userNick);
			postDetailVo.setOpen(open);
			postDetailVo.setPostDelYn(delYn);
			postDetailVo.setModifyDate(modifyDate);
			postDetailVo.setEnrollDate(enrollDate);
			postDetailVo.setInquiry(inquiryCnt);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return postDetailVo;

	}// PostDetail
	
	// 포스트 상세보기 (pNo 받아서 값 전달하기) (+조회수 증가) (+공감수) (+댓글수)
	public PostVo GetPnoPostDetail(Connection conn, String pNo) throws Exception {
		
		
		// sql
		String sql = "SELECT P.POST_IMG ,P.TITLE ,P.CONTENT ,P.POST_NO ,P.BLOG_NO ,B.BLOG_URL ,P.GROUP_NO ,C.CATEGORY_NO ,C.CATEGORY_NAME ,M.MEM_ID ,M.MEM_NO ,M.MEM_NICK ,P.OPEN ,P.DEL_YN ,TO_CHAR (P.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE ,TO_CHAR (P.MODIFY_DATE, 'YYYY-MM-DD') AS MODIFY_DATE ,P.INQUIRY AS HIT_CNT FROM POST P JOIN CATEGORY_LIST C ON P.CATEGORY_NO = C.CATEGORY_NO JOIN MYBLOG_CATEGORY Y ON P.GROUP_NO = Y.GROUP_NO JOIN BLOG B ON P.BLOG_NO = B.BLOG_NO JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE P.OPEN = 'Y' AND P.DEL_YN = 'N' AND P.POST_NO = ? ORDER BY P.POST_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pNo);
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo postDetailVo = null;
		if (rs.next()) {
			String postImg = rs.getString("POST_IMG");
			String postTitle = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String postNo = rs.getString("POST_NO");
			String blogNo = rs.getString("BLOG_NO");
			String blogUrl = rs.getString("BLOG_URL");
			String groupNo = rs.getString("GROUP_NO");
			String categoryNo = rs.getString("CATEGORY_NO");
			String categoryName = rs.getString("CATEGORY_NAME");
			String userNo = rs.getString("MEM_NO");
			String userId = rs.getString("MEM_ID");
			String userNick = rs.getString("MEM_NICK");
			String open = rs.getString("OPEN");
			String delYn = rs.getString("DEL_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String inquiryCnt = rs.getString("HIT_CNT");

			postDetailVo = new PostVo();
			postDetailVo.setPostImg(postImg);
			postDetailVo.setPostNo(postNo);
			postDetailVo.setPostTitle(postTitle);
			postDetailVo.setContent(content);
			postDetailVo.setBlogNo(blogNo);
			postDetailVo.setBlogUrl(blogUrl);
			postDetailVo.setGroupNo(groupNo);
			postDetailVo.setCategoryNo(categoryNo);
			postDetailVo.setCategoryName(categoryName);
			postDetailVo.setUserNo(userNo);
			postDetailVo.setUserId(userId);
			postDetailVo.setUserNick(userNick);
			postDetailVo.setOpen(open);
			postDetailVo.setPostDelYn(delYn);
			postDetailVo.setModifyDate(modifyDate);
			postDetailVo.setEnrollDate(enrollDate);
			postDetailVo.setInquiry(inquiryCnt);

		}
		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return postDetailVo;

	}
	
	// 포스트 상세보기 (BlogUrl 받아서 값 전달하기) (+조회수 증가) (+공감수) (+댓글수)
	public PostVo GetUrlPostDetail(Connection conn, String BlogUrl) throws Exception {
		
		// sql
				String sql = "SELECT P.POST_IMG ,P.TITLE ,P.CONTENT ,P.POST_NO ,P.BLOG_NO ,B.BLOG_URL ,P.GROUP_NO ,C.CATEGORY_NO ,C.CATEGORY_NAME ,M.MEM_ID ,M.MEM_NO ,M.MEM_NICK ,P.OPEN ,P.DEL_YN ,TO_CHAR (P.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE ,TO_CHAR (P.MODIFY_DATE, 'YYYY-MM-DD') AS MODIFY_DATE ,P.INQUIRY AS HIT_CNT FROM POST P JOIN CATEGORY_LIST C ON P.CATEGORY_NO = C.CATEGORY_NO JOIN MYBLOG_CATEGORY Y ON P.GROUP_NO = Y.GROUP_NO JOIN BLOG B ON P.BLOG_NO = B.BLOG_NO JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE P.OPEN = 'Y' AND P.DEL_YN = 'N' AND B.BLOG_URL = ? ORDER BY P.POST_NO DESC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, BlogUrl);
				ResultSet rs = pstmt.executeQuery();

				// rs
				PostVo postDetailVo = null;
				if (rs.next()) {
					String postImg = rs.getString("POST_IMG");
					String postTitle = rs.getString("TITLE");
					String content = rs.getString("CONTENT");
					String postNo = rs.getString("POST_NO");
					String blogNo = rs.getString("BLOG_NO");
					String blogUrl = rs.getString("BLOG_URL");
					String groupNo = rs.getString("GROUP_NO");
					String categoryNo = rs.getString("CATEGORY_NO");
					String categoryName = rs.getString("CATEGORY_NAME");
					String userNo = rs.getString("MEM_NO");
					String userId = rs.getString("MEM_ID");
					String userNick = rs.getString("MEM_NICK");
					String open = rs.getString("OPEN");
					String delYn = rs.getString("DEL_YN");
					String modifyDate = rs.getString("MODIFY_DATE");
					String enrollDate = rs.getString("ENROLL_DATE");
					String inquiryCnt = rs.getString("HIT_CNT");

					postDetailVo = new PostVo();
					postDetailVo.setPostImg(postImg);
					postDetailVo.setPostNo(postNo);
					postDetailVo.setPostTitle(postTitle);
					postDetailVo.setContent(content);
					postDetailVo.setBlogNo(blogNo);
					postDetailVo.setBlogUrl(blogUrl);
					postDetailVo.setGroupNo(groupNo);
					postDetailVo.setCategoryNo(categoryNo);
					postDetailVo.setCategoryName(categoryName);
					postDetailVo.setUserNo(userNo);
					postDetailVo.setUserId(userId);
					postDetailVo.setUserNick(userNick);
					postDetailVo.setOpen(open);
					postDetailVo.setPostDelYn(delYn);
					postDetailVo.setModifyDate(modifyDate);
					postDetailVo.setEnrollDate(enrollDate);
					postDetailVo.setInquiry(inquiryCnt);

				}

				// close
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rs);

				return postDetailVo;
		
	}

	// 조회수 증가 (블로그 카테고리 상세보기용)
	public int PostDetailIncreaseHit(Connection conn, PostVo postDetailVo) throws Exception {
		// sql
		String sql = "UPDATE POST SET INQUIRY = INQUIRY+1 WHERE POST_NO = ? AND OPEN = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postDetailVo.getPostNo());
		int result = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return result;

	}// increaseHit

	// 관리자 상세보기
	public PostVo AdminPostDetail(Connection conn, String no) throws Exception {

		// sql
		String sql = "SELECT P.POST_IMG ,P.POST_NO ,P.BLOG_NO ,M.MEM_ID ,P.OPEN ,P.INQUIRY ,P.DEL_YN ,TO_CHAR (P.MODIFY_DATE, 'YYYY-MM-DD') AS MODIFY_DATE ,H.POST_NO ,TO_CHAR (P.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE ,R.POST_NO ,P.TITLE ,P.CONTENT FROM POST P JOIN CATEGORY_LIST C ON P.POST_NO = C.CATEGORY_NO JOIN HEART H ON P.POST_NO = H.POST_NO JOIN REPLY R ON P.POST_NO = REPLY_NO JOIN BLOG B ON P.POST_NO = B.BLOG_NO JOIN MEMBER M ON B.BLOG_NO = M.MEM_NO WHERE P.POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo adminPostDetailVo = null;
		if (rs.next()) {
			String postImg = rs.getString("POST_IMG");
			String postNo = rs.getString("POST_NO");
			String blogNo = rs.getString("BLOG_NO");
			String userId = rs.getString("MEM_ID");
			String open = rs.getString("OPEN");
			String inquiry = rs.getString("INQUIRY");
			String postDelYn = rs.getString("DEL_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String postTitle = rs.getString("TITLE");
			String content = rs.getString("CONTENT");

			adminPostDetailVo = new PostVo();
			adminPostDetailVo.setPostImg(postImg);
			adminPostDetailVo.setPostNo(postNo);
			adminPostDetailVo.setBlogNo(blogNo);
			adminPostDetailVo.setUserId(userId);
			adminPostDetailVo.setOpen(open);
			adminPostDetailVo.setInquiry(inquiry);
			adminPostDetailVo.setPostDelYn(postDelYn);
			adminPostDetailVo.setModifyDate(modifyDate);
			adminPostDetailVo.setEnrollDate(enrollDate);
			adminPostDetailVo.setPostTitle(postTitle);
			adminPostDetailVo.setContent(content);
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return adminPostDetailVo;

	}// AdminPostDetail
	
	// 관리자 상세보기 (공개여부 , 삭제여부 수정)
	public int AdminPostEdit(Connection conn, PostVo vo) throws Exception {

		// sql
		String sql = "UPDATE POST SET OPEN = ? , DEL_YN = ? WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getOpen());
		pstmt.setString(2, vo.getPostDelYn());
		pstmt.setString(3, vo.getPostNo());
		int result = pstmt.executeUpdate();

		// rs

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

	// 댓글수
	public PostVo PostDetailReplyCnt(Connection conn, PostVo postDetailVo) throws Exception {

		// sql
		String sql = "SELECT COUNT(POST_NO) AS POST_NO FROM REPLY WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postDetailVo.getPostNo());
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo replyCnt = null;
		if (rs.next()) {
			String postNo = rs.getString("POST_NO");

			replyCnt = new PostVo();
			replyCnt.setPostNo(postNo);
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return replyCnt;

	}// FollowCnt

	// 공감수
	public PostVo PostDetailHeartCnt(Connection conn, PostVo postDetailVo) throws Exception {

		// sql
		String sql = "SELECT COUNT(POST_NO) AS POST_NO FROM HEART WHERE POST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postDetailVo.getPostNo());
		ResultSet rs = pstmt.executeQuery();

		// rs
		PostVo heartCnt = null;
		if (rs.next()) {
			String postNo = rs.getString("POST_NO");

			heartCnt = new PostVo();
			heartCnt.setPostNo(postNo);
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return heartCnt;

	}// heartCnt

	public String findUserNo(Connection conn, String userNick) throws Exception {
		String sql = "SELECT MEM_NO FROM MEMBER WHERE MEM_NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userNick);

		ResultSet rs = pstmt.executeQuery();
		String userNo = null;
		if (rs.next()) {
			userNo = rs.getString("MEM_NO");
		}

		return userNo;
	}
	
	// 전체 포스트 갯수
		public int getPostVoListCount(Connection conn, String GroupNo) throws Exception {
			
			
			// sql
			String sql = "SELECT COUNT(*) as cnt FROM POST WHERE GROUP_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, GroupNo);
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

		// 페이지에 맞는 포스트 List 가져오기
		public List<PostVo> getPostVoList(Connection conn, String categoryNo, PageVo pvo) throws Exception {

			// sql
			String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT P.POST_IMG ,P.TITLE ,P.CONTENT ,P.POST_NO ,P.BLOG_NO ,B.BLOG_URL ,P.GROUP_NO ,C.CATEGORY_NO ,C.CATEGORY_NAME ,M.MEM_ID ,M.MEM_NO ,M.MEM_NICK ,P.OPEN ,P.DEL_YN ,TO_CHAR (P.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE ,TO_CHAR (P.MODIFY_DATE, 'YYYY-MM-DD') AS MODIFY_DATE ,P.INQUIRY AS HIT_CNT FROM POST P JOIN CATEGORY_LIST C ON P.CATEGORY_NO = C.CATEGORY_NO JOIN MYBLOG_CATEGORY Y ON P.GROUP_NO = Y.GROUP_NO JOIN BLOG B ON P.BLOG_NO = B.BLOG_NO JOIN MEMBER M ON B.MEM_NO = M.MEM_NO WHERE P.OPEN = 'Y' AND P.DEL_YN = 'N' AND P.GROUP_NO = ? ORDER BY P.POST_NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryNo);
			pstmt.setInt(2, pvo.getStartRow());
			pstmt.setInt(3, pvo.getLastRow());
			ResultSet rs = pstmt.executeQuery();
			
			// rs
			List<PostVo> postVoList = new ArrayList<PostVo>();
			while(rs.next()) {
				String postImg = rs.getString("POST_IMG");
				String postTitle = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String postNo = rs.getString("POST_NO");
				String blogNo = rs.getString("BLOG_NO");
				String blogUrl = rs.getString("BLOG_URL");
				String groupNo = rs.getString("GROUP_NO");
				String categoryName = rs.getString("CATEGORY_NAME");
				String userNo = rs.getString("MEM_NO");
				String userId = rs.getString("MEM_ID");
				String userNick = rs.getString("MEM_NICK");
				String open = rs.getString("OPEN");
				String delYn = rs.getString("DEL_YN");
				String modifyDate = rs.getString("MODIFY_DATE");
				String enrollDate = rs.getString("ENROLL_DATE");
				String inquiryCnt = rs.getString("HIT_CNT");

				PostVo postDetailVo = new PostVo();
				postDetailVo.setPostImg(postImg);
				postDetailVo.setPostNo(postNo);
				postDetailVo.setPostTitle(postTitle);
				postDetailVo.setContent(content);
				postDetailVo.setBlogNo(blogNo);
				postDetailVo.setBlogUrl(blogUrl);
				postDetailVo.setGroupNo(groupNo);
				postDetailVo.setCategoryNo(categoryNo);
				postDetailVo.setCategoryName(categoryName);
				postDetailVo.setUserNo(userNo);
				postDetailVo.setUserId(userId);
				postDetailVo.setUserNick(userNick);
				postDetailVo.setOpen(open);
				postDetailVo.setPostDelYn(delYn);
				postDetailVo.setModifyDate(modifyDate);
				postDetailVo.setEnrollDate(enrollDate);
				postDetailVo.setInquiry(inquiryCnt);
				
				postVoList.add(postDetailVo);
			}
			
			// close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return postVoList;
		}


}// class
