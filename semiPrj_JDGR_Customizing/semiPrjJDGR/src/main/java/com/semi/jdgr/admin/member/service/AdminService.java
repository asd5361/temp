package com.semi.jdgr.admin.member.service;

import java.sql.Connection;

import com.semi.jdgr.admin.member.dao.AdminDao;
import com.semi.jdgr.admin.member.vo.AdminVo;
import com.semi.jdgr.util.JDBCTemplate;

public class AdminService {

	public AdminVo login(AdminVo vo) throws Exception {
		//conn
				Connection conn = JDBCTemplate.getConnection();
				
				//dao
				AdminDao dao = new AdminDao();
				AdminVo loginMember = dao.login(conn ,vo);
				
				//close
				JDBCTemplate.close(conn);
				
				return loginMember;	
			
	}

}
