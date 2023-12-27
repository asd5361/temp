package com.semi.jdgr.user.member.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.user.member.dao.MemberDao;
import com.semi.jdgr.user.member.vo.MemberPostSanctionVo;
import com.semi.jdgr.user.member.vo.MemberReplySanctionVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class MemberService {

	MemberDao dao = null;

	public MemberService() {
		dao = new MemberDao();
	}

	public int join(MemberVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		// 비지니스 로직
		// 아이디 6글자 이상 12글자 미만
		// 비밀번호 일치여부
		// 비밀번호 8자 이상
		// 비밀번호 영문 한글 둘다 포함

		int result = dao.join(conn, vo);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public boolean checkIdDup(String joinId) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		boolean result = dao.checkIdDup(conn, joinId);

		JDBCTemplate.close(conn);

		return result;
	}

	public boolean checkNickDup(String joinNick) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		boolean result = dao.checkNickDup(conn, joinNick);

		JDBCTemplate.close(conn);

		return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao

		MemberVo loginMember = dao.login(conn, vo);

		// close
		JDBCTemplate.close(conn);

		return loginMember;
	}

	public int updateMemberInfo(MemberVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		int result = dao.updateMemberInfo(conn, vo);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return result;
	}

	public MemberVo printId(String nowEmail) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao

		MemberVo loginMember = dao.printId(conn, nowEmail);

		// close
		JDBCTemplate.close(conn);

		return loginMember;

	}

	public int changePwd(String newPwd, String nowEmail) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		int result = dao.updateMemberInfo(conn, newPwd, nowEmail);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return result;
	}

	public List<MemberReplySanctionVo> findMRSVoList() throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		MemberDao dao = new MemberDao();
		List<MemberReplySanctionVo> mrsVoList = dao.findMRSVoList(conn);

		// close
		JDBCTemplate.close(conn);

		return mrsVoList;
	}

	public List<MemberPostSanctionVo> findMPSVoList() throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		MemberDao dao = new MemberDao();
		List<MemberPostSanctionVo> mpsVoList = dao.findMPSVoList(conn);

		// close
		JDBCTemplate.close(conn);

		return mpsVoList;
	}

}
