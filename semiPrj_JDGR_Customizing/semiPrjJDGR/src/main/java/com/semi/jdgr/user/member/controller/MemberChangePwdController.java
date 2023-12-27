package com.semi.jdgr.user.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import com.semi.jdgr.user.member.service.MemberService;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/member/changepwd")
public class MemberChangePwdController extends HttpServlet {
	private String nowEmail = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		nowEmail = req.getParameter("nowEmail");

		req.setAttribute("nowEmail", nowEmail);
		
		

		req.getRequestDispatcher("/WEB-INF/views/user/member/changepwd.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String newPwd = req.getParameter("newPwd");
			String newPwd2 = req.getParameter("newPwd2");

			if (!newPwd.equals(newPwd2)) {
				throw new Exception("비번이 서로 다름");
			}
			
			MemberService ms = new MemberService();
			int result = ms.changePwd(newPwd,nowEmail);
			
			if(result != 1) {
				throw new Exception();
			}
			resp.sendRedirect("/jdgr/member/login"); 
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
