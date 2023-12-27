package com.semi.jdgr.admin.blame.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.blame.service.AdminReplyBlameService;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;
import com.semi.jdgr.admin.reply.service.AdminReplyService;
import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;

@WebServlet("/admin/blame/r_blame_detail")
public class AdminReplyBlameDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String rBlaNo = req.getParameter("no");
			String currPage = req.getParameter("currPage");
			
			//service
			AdminReplyBlameService abs = new AdminReplyBlameService();
			
			AdminReplyBlameVo vo = abs.selectBlameDetail(rBlaNo);
			//result(==view)
			req.setAttribute("pno",rBlaNo );
			req.setAttribute("currPage",currPage );
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/blame/r_blame_detail.jsp").forward(req, resp);

		}catch(Exception e) {
			System.out.println("신고 상세조회 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "신고 목록 상세조회 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			
		}
	
	}//doGet
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//data
			AdminReplyBlameVo vo = new AdminReplyBlameVo();
			vo.setrSancYn(req.getParameter("rSancYn"));
			vo.setrDelYn(req.getParameter("rDelYn"));
			vo.setrAnsDate(req.getParameter("rAnsDate"));

			//service
			AdminReplyBlameService ars = new AdminReplyBlameService();
			int result = ars.editBlame(vo);

			if(result != 1) {
				throw new Exception();
			}
			
			//view
			resp.sendRedirect("/jdgr/admin/blame/r_blame_list?pno=1");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 수정 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}	
	}
	
	
}//class
