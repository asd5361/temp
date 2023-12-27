package com.semi.jdgr.post.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.dao.PostDaoLYJ;
import com.semi.jdgr.post.dao.PostDaoPBR;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.util.JDBCTemplate;


public class PostServicePBR {

	public List<PostVo> searchPostList(String type, String content, PageVo pvo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		PostDaoPBR dao = new PostDaoPBR();
		List<PostVo> postVoList = dao.searchPostList(type, content, conn, pvo);

		// 공감수 조회하는 쿼리문 실행
		PostDaoLYJ daoLyj = new PostDaoLYJ();
		for (PostVo vo : postVoList) {
			String heartCnt = daoLyj.getheartCnt(conn, vo.getPostNo());
			vo.setHeartCnt(heartCnt);
		}

		// 댓글수 조회하는 쿼리문 실행
		for (PostVo vo : postVoList) {
			String replyCnt = daoLyj.getreplyCnt(conn, vo.getPostNo());
			vo.setReplyCnt(replyCnt);
		}
		JDBCTemplate.close(conn);

		return postVoList;

	}

	public List<PostVo> searchPostList(String type, String content) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		PostDaoPBR dao = new PostDaoPBR();
		List<PostVo> postVoList = dao.searchPostList(type, content, conn);

		// 공감수 조회하는 쿼리문 실행
		PostDaoLYJ daoLyj = new PostDaoLYJ();
		for (PostVo vo : postVoList) {
			String heartCnt = daoLyj.getheartCnt(conn, vo.getPostNo());
			vo.setHeartCnt(heartCnt);
		}

		// 댓글수 조회하는 쿼리문 실행
		for (PostVo vo : postVoList) {
			String replyCnt = daoLyj.getreplyCnt(conn, vo.getPostNo());
			vo.setReplyCnt(replyCnt);
		}
		JDBCTemplate.close(conn);

		return postVoList;

	
	}

}// class