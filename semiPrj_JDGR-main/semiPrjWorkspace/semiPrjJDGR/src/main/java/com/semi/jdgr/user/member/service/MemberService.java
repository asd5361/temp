package com.semi.jdgr.user.member.service;

import java.sql.Connection;

import com.semi.jdgr.user.member.dao.MemberDao;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class MemberService {

	public int join(MemberVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		//비지니스 로직
		//아이디 6글자 이상 12글자 미만
		//비밀번호 일치여부
		//비밀번호 8자 이상
		//비밀번호 영문 한글 둘다 포함
		
		MemberDao dao = new MemberDao();
		int result = dao.join(conn,vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public boolean checkIdDup(String memberId) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDao dao = new MemberDao();
		boolean result = dao.checkIdDup(conn, memberId);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberDao dao = new MemberDao();
		MemberVo loginMember = dao.login(conn ,vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return loginMember;	
	}
	
}
