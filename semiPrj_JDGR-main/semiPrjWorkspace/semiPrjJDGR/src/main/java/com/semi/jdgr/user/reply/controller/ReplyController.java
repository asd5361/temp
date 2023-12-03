package com.semi.jdgr.user.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.user.reply.service.ReplyService;
import com.semi.jdgr.user.reply.vo.ReplyVo;

@WebServlet("/reply")
public class ReplyController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			//로그인 안되어있으면 에러페이지로 보내기
			ReplyVo loginMember = (ReplyVo) req.getSession().getAttribute("loginMember");
			if(loginMember == null) {
				req.setAttribute("errorMsg"	, "잘못된 접근입니다. (로그인 하고 오세요)");
				req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			}
			
			ReplyService res = new ReplyService();
			req.getRequestDispatcher("/WEB-INF/views/reply/write.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성하기 (화면) 에러 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}//doGet
	
	// 게시글 작성 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
					
			//인코딩
//			req.setCharacterEncoding("UTF-8");	//필터에서 인코딩 처리 해줌
			
			HttpSession session = req.getSession();
			
			// data
			String con = req.getParameter("con");
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new Exception("로그인 안했음");
			}
			
			ReplyVo vo = new ReplyVo();
			vo.setCon(con);
			
			// service
			ReplyService res = new ReplyService();
			int result = res.write(vo);
			
			// result == view
			if(result != 1) {
				throw new Exception("result 가 1이 아님 ,,,,");
			}
			
			req.getSession().setAttribute("alertMsg", "게시글 작성 성공 !");
			resp.sendRedirect("/jdgr/post//");
			
		}catch(Exception e) {
			System.out.println("[ERROR-B002] 게시글 작성 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
}//class
