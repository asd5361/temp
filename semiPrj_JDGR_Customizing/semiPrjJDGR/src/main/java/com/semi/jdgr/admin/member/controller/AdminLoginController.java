package com.semi.jdgr.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.admin.member.service.AdminService;
import com.semi.jdgr.admin.member.vo.AdminVo;

@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/member/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			
			AdminVo vo = new AdminVo();
			vo.setAdminId(memberId);
			vo.setAdminPwd(memberPwd);
			
			// service
			AdminService as = new AdminService();
			AdminVo loginMember = as.login(vo);

			HttpSession session = req.getSession();
			// result (==view)
			if(loginMember == null) {
				session.setAttribute("alertMsg", "아이디 또는 비밀번호가 틀렸습니다.");
				throw new Exception("로그인 실패 ...");
			}
			session.setAttribute("loginAdmin", loginMember);
			resp.sendRedirect("/jdgr/admin/userManagement/list"); 
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/jdgr/admin/login"); 
		}
		
	}
}
