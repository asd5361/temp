package com.semi.jdgr.user.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.user.member.service.MemberService;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/member/mypage")
public class MemberMyPageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/member/mypage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			String name = req.getParameter("name");
			String cert = req.getParameter("cert");
			String pwd1 = req.getParameter("pwd1");
			String pwd2 = req.getParameter("pwd2");
			String nick = req.getParameter("nick");
			String phone= req.getParameter("phone");
			MemberVo vo = loginMember;
			
			vo.setMemName(name);
			vo.setMemNick(nick);
			vo.setMemPhoneNum(phone);
			
			if(pwd1 != "" && pwd2 != "") {
				vo.setMemPwd(pwd1);
				vo.setMemPwd2(pwd2);
			}		
			
			MemberService ms = new MemberService();
			int result = ms.updateMemberInfo(vo);
			
			if(result != 1) {
				session.setAttribute("alertMsg", "마이페이지 정보 저장에 실패하였습니다.");
				throw new Exception("마이페이지 저장 실패 ...");
			}
			session.setAttribute("loginMember", vo);
			resp.sendRedirect("/jdgr/home"); 
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
