package com.semi.jdgr.post.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.semi.jdgr.alarm.dao.AlarmDaoHJY;
import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.blog.dao.BlogDao;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.post.dao.PostDaoHJY;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.follow.dao.FollowDao;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class PostServiceHJY {

	// 포스트 작성
	public int postWrite(PostVo postVo, MemberVo loginMember) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// 비즈니스로직
		// 포스트 카테고리 선택 안할시
		if(postVo.getCategoryNo().equals("0")) {
			throw new Exception("구분을 선택해주세요.");
		}
		// 포스트 그룹 선택 안할시
		if(postVo.getGroupNo().equals("0")) {
			throw new Exception("카테고리를 선택해주세요.");
		}
		// 타이틀 빈칸 일시
		if(postVo.getPostTitle() == null || postVo.getPostTitle().isEmpty()) {
			throw new Exception("제목을 입력해주세요.");
		}
		// 내용 없을시
		if(postVo.getContent() == null || postVo.getContent().isEmpty()) {
			throw new Exception("내용을 입력해주세요.");
		}
		
		// dao
		PostDaoHJY dao = new PostDaoHJY();
		BlogDao blogDao = new BlogDao();
		BlogVo blogVo = blogDao.getUserBlog(conn, postVo.getBlogUrl());
		int result = dao.postWrite(conn, postVo, blogVo.getBlogNo()); // insert결과값
		
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		PostVo insertPostVo = dao.getNewPost(conn); // 포스트번호 가져오기
		
		
		// 알람에 insert
		
		// 포스트작성자의 구독블로그유저 리스트 가져오기
		FollowDao followDao = new FollowDao();
		List<FollowVo> followVoList = followDao.getFollowListByUserNo(conn, loginMember.getMemNo());// 포스트작성자의 구독자들 리스트
		
		AlarmDaoHJY alarmDao = new AlarmDaoHJY();
		AlarmVo alarmVo = new AlarmVo();
		alarmVo.setPostNo(insertPostVo.getPostNo()); // 포스트넘버
		alarmVo.setSenderNo(loginMember.getMemNo()); // 포스트 작성자의 멤버번호
		
		int alarmResult = 0;
		if(result == 1) { // 포스트 등록이 insert성공하면 실행
			alarmResult = alarmDao.postCreateAlarm(conn, alarmVo, followVoList); // 알람에 인서트하고 성공 결과값 받아오기
			// tx
			if(alarmResult == followVoList.size()) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 포스트 가져오기
	public PostVo getPostByNo(String postNo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		PostDaoHJY dao = new PostDaoHJY();
		PostVo postVo = dao.getPostByNo(conn, postNo);
		
		// close
		JDBCTemplate.close(conn);
		
		return postVo;
	}

	// 포스트 수정하기
	public int postEdit(PostVo postVo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// 비즈니스로직
		// 포스트 카테고리 선택 안할시
		if(postVo.getCategoryNo().equals("0")) {
			throw new Exception("구분을 선택해주세요.");
		}
		// 포스트 그룹 선택 안할시
		if(postVo.getGroupNo().equals("0")) {
			throw new Exception("카테고리를 선택해주세요.");
		}
		// 타이틀 빈칸 일시
		if(postVo.getPostTitle() == null || postVo.getPostTitle().isEmpty()) {
			throw new Exception("제목을 입력해주세요.");
		}
		// 내용 없을시
		if(postVo.getContent() == null || postVo.getContent().isEmpty()) {
			throw new Exception("내용을 입력해주세요.");
		}
		
		// dao
		PostDaoHJY dao = new PostDaoHJY();
		int result = dao.postEdit(conn, postVo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 포스트 삭제하기
	public int postDelete(String postNo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		PostDaoHJY dao = new PostDaoHJY();
		int result = dao.postDelete(conn, postNo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}


	

}
