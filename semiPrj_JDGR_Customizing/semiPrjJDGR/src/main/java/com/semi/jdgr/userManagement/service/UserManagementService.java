package com.semi.jdgr.userManagement.service;

import java.sql.Connection;
import java.util.List;

import com.semi.jdgr.notice.dao.NoticeDao;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.userManagement.dao.UserManagementDao;
import com.semi.jdgr.util.JDBCTemplate;

public class UserManagementService {

	public int selectAdminUserManagementCount() throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		int cnt = dao.selectAdminUserManagementCount(conn);

		//close
		JDBCTemplate.close(conn);

		return cnt;
	}

	public List<MemberVo> selectAdminUserManagementList(PageVo pvo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		List<MemberVo> UserManagementVoList = dao.selectAdminUserManagementListt(conn,pvo);

		//close
		JDBCTemplate.close(conn);

		return UserManagementVoList;
	}

	//회원 상세 조회
	public MemberVo userManagementDetail(String umNo) throws Exception  {

		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		MemberVo vo = dao.userManagementDetail(conn,umNo);

		//close
		JDBCTemplate.close(conn);

		return vo;
	}
	//검색 총갯스
	public int adminUserManagementSearchCount(MemberVo searchVo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		int cnt = dao.adminUserManagementSearchCount(conn,searchVo);

		//close
		JDBCTemplate.close(conn);

		return cnt;
	}

	public List<MemberVo> adminUserManagementeSearch(MemberVo searchVo, PageVo pvo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		List<MemberVo> userManagementVoList = dao.adminUserManagementeSearch(conn,searchVo, pvo);

		//close
		JDBCTemplate.close(conn);

		return userManagementVoList;
	}

	public int userManagementUpdate(MemberVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		UserManagementDao dao = new UserManagementDao();
		int result = dao.userManagementUpdate(conn,vo);

		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}

		//close
		JDBCTemplate.close(conn);

		return result;
	}

}
