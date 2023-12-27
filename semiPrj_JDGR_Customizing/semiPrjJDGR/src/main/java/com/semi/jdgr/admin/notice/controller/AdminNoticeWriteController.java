package com.semi.jdgr.admin.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.admin.member.vo.AdminVo;
import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/admin/notice/write")
public class AdminNoticeWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("pno", req.getParameter("pno"));
		req.getRequestDispatcher("/WEB-INF/views/admin/notice/noticeCreateEdit.jsp").forward(req, resp);
					
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			AdminVo adminVo = (AdminVo) session.getAttribute("loginAdmin");
			
			
			//data
			String CurrentPage = req.getParameter("pno");
			System.out.println(CurrentPage);
			NoticeVo vo = new NoticeVo();
			vo.setAdminNo(adminVo.getAdminNo()); 
			vo.setDelYn(req.getParameter("del"));
			vo.setFixedYn(req.getParameter("fixed"));
			vo.setTitle(req.getParameter("title"));
			vo.setContent(req.getParameter("content"));

			//service
			NoticeService ns = new NoticeService();
			int result = ns.noticeWrite(vo);
			
			//view
			if(result != 1) {
				throw new Exception();
			}
			resp.sendRedirect("/jdgr/admin/notice/list?pno="+CurrentPage);	// 작성하기 누르기 전 보고 있던 페이지 번호
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 작성 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}	
	}
}
