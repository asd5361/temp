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

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/member/join.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			
			String joinId = req.getParameter("joinId");
			String joinName = req.getParameter("joinId");
			String joinNick = req.getParameter("joinId");
			String joinPwd = req.getParameter("joinId");
			String joinPwd2 = req.getParameter("joinId");
			String joinTel = req.getParameter("joinId");
			
			MemberVo vo = new MemberVo();
			vo.setMemId(joinId);
			vo.setMemName(joinName);
			vo.setMemNick(joinNick);
			vo.setMemPwd(joinPwd);
			vo.setMemPwd2(joinPwd2);
			vo.setMemPhoneNum(joinTel);
			
			MemberService ms = new MemberService();
			int result = ms.join(vo);
			
			if(result ==1) {
				HttpSession session = req.getSession();
				session.setAttribute("alertMsg", "회원가입이 성공하였습니다.");
				resp.sendRedirect("/jdgr/member/login");
			}else {
				throw new Exception("MemberJoinController 오류");
			}
			
		
		
		} catch (Exception e) {
			System.out.println(":::회원가입 에러:::");
			e.printStackTrace();
//			req.setAttribute("errorMsg", "회원가입");
//			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		
		}
	}
}
