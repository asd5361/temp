package com.semi.jdgr.admin.blame.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.blame.service.AdminPostBlameService;
import com.semi.jdgr.admin.blame.service.AdminReplyBlameService;
import com.semi.jdgr.admin.blame.vo.AdminPostBlameVo;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;

@WebServlet("/admin/blame/p_blame_detail")
public class AdminPostBlameDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String pBlaNo = req.getParameter("no");
			String currPage = req.getParameter("currPage");
			
			//service
			AdminPostBlameService abs =  new AdminPostBlameService();
			
			AdminPostBlameVo vo = abs.selectBlameDetail(pBlaNo);
			
			//result(==view)
			req.setAttribute("pno",pBlaNo );
			req.setAttribute("currPage",currPage );
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/blame/p_blame_detail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("포스트 신고 상세조회 중 에러 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "신고 목록 상세조회 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}//doGet
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//data
			AdminPostBlameVo vo = new AdminPostBlameVo();
			vo.setpSancYn(req.getParameter("pSancYn"));
			vo.setpDelYn(req.getParameter("pDelYn"));
			vo.setpAnsDate(req.getParameter("pAnsDate"));

			//service
			AdminPostBlameService aps = new AdminPostBlameService();
			int result = aps.editBlame(vo);

			if(result != 1) {
				throw new Exception();
			}
			
			//view
			resp.sendRedirect("/jdgr/admin/blame/p_blame_list?pno=1");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 수정 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}	
	}
}//class
