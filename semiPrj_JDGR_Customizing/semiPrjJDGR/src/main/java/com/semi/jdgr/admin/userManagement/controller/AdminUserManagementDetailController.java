package com.semi.jdgr.admin.userManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.userManagement.service.UserManagementService;

@WebServlet("/admin/userManagement/detail")
public class AdminUserManagementDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//data
			String umNo = req.getParameter("no");
			
			//service
			UserManagementService ums = new UserManagementService(); 
			MemberVo vo = ums.userManagementDetail(umNo);
			
			//view
			if(vo ==null) {
				throw new Exception();
			}
			
			req.setAttribute("pno", req.getParameter("currPage"));
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/userManagement/detail.jsp").forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 회원 상세 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//data
			MemberVo vo = new MemberVo();
			vo.setQuitYn(req.getParameter("quitYn"));
			vo.setLoginBan(req.getParameter("loginban"));
			vo.setBanYn(req.getParameter("banYn"));
			vo.setMemNo(req.getParameter("memNo"));


			//service
			UserManagementService ums = new UserManagementService(); 
			int result = ums.userManagementUpdate(vo);

			if(result != 1) {
				throw new Exception();
			}
			
			//view
			resp.sendRedirect("/jdgr/admin/userManagement/list?pno=1");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 회원 수정 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}	
		
	}
	
}
