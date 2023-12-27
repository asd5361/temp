package com.semi.jdgr.user.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.user.member.service.MemberService;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/member/printid")
public class MemberPrintIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String nowEmail = req.getParameter("nowEmail");
			
			MemberService ms = new MemberService();
			MemberVo prindIdVo = ms.printId(nowEmail);

			if(prindIdVo == null ) {
				throw new Exception("아이디 찾기 실패");
			}
			
            req.setAttribute("printIdVo", prindIdVo);
            
			req.getRequestDispatcher("/WEB-INF/views/user/member/printid.jsp").forward(req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/jdgr/member/findId"); 
			
		}
	}
}
