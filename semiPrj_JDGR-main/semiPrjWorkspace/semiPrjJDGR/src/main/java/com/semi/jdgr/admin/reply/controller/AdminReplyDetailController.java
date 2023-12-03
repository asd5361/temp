package com.semi.jdgr.admin.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.reply.service.AdminReplyService;
import com.semi.jdgr.admin.reply.vo.AdminReplyVo;

@WebServlet("/reply/reply_detail")
public class AdminReplyDetailController extends HttpServlet{
	
	// 댓글 상세조회
	// SELECT * FROM REPLY WHERE NO = ? AND STATUS = 'O'
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String no = req.getParameter("no");
			
			// service
			AdminReplyService ars = new AdminReplyService();
			AdminReplyVo vo = ars.selectReplyByNo(no);
			
			// result == view
			req.setAttribute("vo", vo);
			req.setAttribute("currPage", req.getParameter("currPage"));
			req.getRequestDispatcher("/WEB-INF/views/reply/reply_detail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR-B003] 댓글 상세조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "댓글 상세조회 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}

	}//doGet

}
