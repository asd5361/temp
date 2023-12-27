package com.semi.jdgr.userManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class UserManagementDao {

	public int selectAdminUserManagementCount(Connection conn) throws SQLException {
		
		//sql
		String sql = "SELECT COUNT(*) FROM MEMBER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		if(rs.next()) {
			cnt = rs.getInt(1);	//첫번째 열을 가져오겠다 라는 뜻, 계산에 써야되기 때문에 String이 아닌 Int로 받아줌
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
//회원 전체 목록 조회
	public List<MemberVo> selectAdminUserManagementListt(Connection conn, PageVo pvo) throws SQLException {
		String sql="SELECT * FROM (SELECT ROWNUM RNUM, M.* FROM (SELECT * FROM MEMBER ORDER BY MEM_NO DESC)M )WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pvo.getStartRow());
		pstmt.setInt(2, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<MemberVo> UserManagementVoList = new ArrayList<MemberVo>();
		
		//rs
		while(rs.next()) {
			MemberVo vo = new MemberVo();
			
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setMemName(rs.getString("MEM_NAME"));
			vo.setMemId(rs.getString("MEM_ID"));
			vo.setMemPwd(rs.getString("MEM_PWD"));
			vo.setMemNick(rs.getString("MEM_NICK"));
			vo.setMemPhoneNum(rs.getString("MEM_PHONE_NUM"));
			vo.setMemEmail(rs.getString("MEM_EMAIL"));
			vo.setQuitYn(rs.getString("QUIT_YN"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
			
			UserManagementVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return UserManagementVoList;
	}

	public MemberVo userManagementDetail(Connection conn, String umNo) throws SQLException {
		//sql
		String sql ="SELECT * FROM MEMBER WHERE MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,umNo);
		ResultSet rs = pstmt.executeQuery();
		MemberVo vo = null;
		
		//rs
		if(rs.next()) {
			vo =new MemberVo();
			
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setMemName(rs.getString("MEM_NAME"));
			vo.setMemId(rs.getString("MEM_ID"));
			vo.setMemPwd(rs.getString("MEM_PWD"));
			vo.setMemNick(rs.getString("MEM_NICK"));
			vo.setMemPhoneNum(rs.getString("MEM_PHONE_NUM"));
			vo.setMemEmail(rs.getString("MEM_EMAIL"));
			vo.setQuitYn(rs.getString("QUIT_YN"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));
		}
		
		//result
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	//검색 총 갯수
	public int adminUserManagementSearchCount(Connection conn, MemberVo searchVo) throws SQLException {
		//sql
		String sql ="SELECT COUNT(*) FROM MEMBER WHERE MEM_ID LIKE '%'||?||'%' AND MEM_NAME LIKE '%'||?||'%' AND MEM_NICK LIKE '%'||?||'%' ORDER BY MEM_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchVo.getMemId() );
		pstmt.setString(2, searchVo.getMemName() );
		pstmt.setString(3, searchVo.getMemNick() );


		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		
		//rs
		while(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);		
		
		return cnt;
	}
	public List<MemberVo> adminUserManagementeSearch(Connection conn, MemberVo searchVo, PageVo pvo) throws SQLException {
		//sql
		String sql="SELECT * FROM ( SELECT ROWNUM RNUM, N.* FROM ( SELECT * FROM MEMBER WHERE MEM_ID LIKE '%'||?||'%' AND MEM_NAME LIKE '%'||?||'%' AND MEM_NICK LIKE '%'||?||'%' ORDER BY MEM_NO DESC)N ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchVo.getMemId() );
		pstmt.setString(2, searchVo.getMemName() );
		pstmt.setString(3, searchVo.getMemNick() );
		pstmt.setInt(4, pvo.getStartRow());
		pstmt.setInt(5, pvo.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<MemberVo> userManagementVoList = new ArrayList<MemberVo>();
		
		//rs
		while(rs.next()) {
			MemberVo vo = new MemberVo();
			
			vo.setMemNo(rs.getString("MEM_NO"));
			vo.setMemName(rs.getString("MEM_NAME"));
			vo.setMemId(rs.getString("MEM_ID"));
			vo.setMemPwd(rs.getString("MEM_PWD"));
			vo.setMemNick(rs.getString("MEM_NICK"));
			vo.setMemPhoneNum(rs.getString("MEM_PHONE_NUM"));
			vo.setMemEmail(rs.getString("MEM_EMAIL"));
			vo.setQuitYn(rs.getString("QUIT_YN"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			vo.setUpdateDate(rs.getString("UPDATE_DATE"));

			
			
			userManagementVoList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return userManagementVoList;
	}
	public int userManagementUpdate(Connection conn, MemberVo vo) throws SQLException {
		/* 임시로 로그인 정지와 제재여부는 안 건듬*/
		//sql
		String sql = "UPDATE MEMBER SET QUIT_YN = ?,UPDATE_DATE = SYSDATE WHERE MEM_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getQuitYn());
		pstmt.setString(2, vo.getMemNo());
//		pstmt.setString(2, vo.getLoginBan());
//		pstmt.setString(3, vo.getBanYn());
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}
