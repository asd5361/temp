package com.semi.jdgr.admin.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.semi.jdgr.admin.member.vo.AdminVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminDao {

	public AdminVo login(Connection conn, AdminVo vo) throws Exception {
		
		String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAdminId());
		pstmt.setString(2, vo.getAdminPwd());
		ResultSet rs = pstmt.executeQuery();
		
		AdminVo loginMember =null;
		
		if(rs.next()) {
			String adminNo = rs.getString("ADMIN_NO");
			String adminId = rs.getString("ADMIN_ID");
			String adminPwd = rs.getString("ADMIN_PWD");
			String adminName = rs.getString("ADMIN_NAME");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			
			
			
			
			
			loginMember = new AdminVo();
			loginMember.setAdminNo(adminNo);
			loginMember.setAdminId(adminId);
			loginMember.setAdminPwd(adminPwd);
			loginMember.setAdminName(adminName);
			loginMember.setQuitYn(quitYn);
			loginMember.setEnrollDate(enrollDate);
					
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginMember;
	}

}
