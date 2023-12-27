package com.semi.jdgr.post.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.dao.HeartDao;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.dao.PostDaoJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostServiceJOJ {

	// 포스트 VO 준비 (포스트 넘버로 조회)
	public PostVo PostInfo(String pNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		PostVo postInfoVo = dao.PostInfo(conn, pNo);

		// close
		JDBCTemplate.close(conn);

		return postInfoVo;

	}// PostDetail

	// 포스트 상세보기 (블로그 카테고리 상세보기용) (+조회수 증가) (+공감수) (+댓글수)
	public PostVo PostDetail(String groupNo, String blogUrl, String pNo, int cnt) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();

		PostVo postDetailVo = null;
		if ((groupNo == null) && (blogUrl == null)) {
			postDetailVo = dao.GetPnoPostDetail(conn, pNo);
		} else if (groupNo == null) {
			postDetailVo = dao.GetUrlPostDetail(conn, blogUrl);
		} else {
			postDetailVo = dao.PostDetail(conn, groupNo, blogUrl);
		}
		
		int result = 0;
		if(cnt != 1) {
			result = dao.PostDetailIncreaseHit(conn, postDetailVo);
		}
		
		// tx
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);
		return postDetailVo;

	}// PostDetail

	// 댓글수 (블로그 카테고리 상세보기용)
	public PostVo PostDetailReplyCnt(PostVo postDetailVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		PostVo PostDetailReplyCnt = dao.PostDetailReplyCnt(conn, postDetailVo);

		// tx

		// close
		JDBCTemplate.close(conn);

		return PostDetailReplyCnt;

	}// ReplyCnt

	// 공감수 (블로그 카테고리 상세보기용)
	public PostVo PostDetailHeartCnt(PostVo postDetailVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		PostVo postDetailHeartCnt = dao.PostDetailHeartCnt(conn, postDetailVo);

		// tx

		// close
		JDBCTemplate.close(conn);

		return postDetailHeartCnt;

	}// heartHit

	// 관리자 상세보기
	public PostVo AdminPostDetail(String no) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		PostVo adminPostDetailVo = dao.AdminPostDetail(conn, no);

		// close
		JDBCTemplate.close(conn);

		return adminPostDetailVo;

	}// AdminPostDetail

	// 관리자 상세보기 (공개여부 , 삭제여부 수정)
	public int AdminPostEdit(PostVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		int result = dao.AdminPostEdit(conn, vo);

		// tx
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}

	public String findUserNo(String userNick) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		PostDaoJOJ dao = new PostDaoJOJ();
		String UserNo = dao.findUserNo(conn, userNick);

		if (UserNo != null) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return UserNo;
	}

	// 전체 포스트 갯수
	public int getPostVoListCount(String GroupNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		int listCount = dao.getPostVoListCount(conn, GroupNo);

		// close
		JDBCTemplate.close(conn);

		return listCount;
	}

	// 페이지에 맞는 포스트 List 가져오기
	public List<PostVo> getPostVoList(String categoryNo, PageVo pvo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		PostDaoJOJ dao = new PostDaoJOJ();
		List<PostVo> postVoList = dao.getPostVoList(conn, categoryNo, pvo);

		// close
		JDBCTemplate.close(conn);

		return postVoList;
	}

}// class
