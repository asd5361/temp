package com.semi.jdgr.admin.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;

@WebServlet("/admin/notice/detail")
public class AdminNoticeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//data
			String noticeNo = req.getParameter("no");
			
			//service
			NoticeService ns = new NoticeService();
			NoticeVo vo = ns.adminNoticeDetail(noticeNo);
			
			//view
			if(vo ==null) {
				throw new Exception();
			}
			
			req.setAttribute("pno", req.getParameter("currPage"));
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/notice/noticeDetail.jsp").forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 상세 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//data
			NoticeVo vo = new NoticeVo();
			vo.setNoticeNo(req.getParameter("no"));
			vo.setDelYn(req.getParameter("del"));
			vo.setFixedYn(req.getParameter("fixed"));
			vo.setTitle(req.getParameter("title"));
			vo.setContent(req.getParameter("content"));

			//service
			NoticeService ns = new NoticeService();
			int result = ns.notideUpdate(vo);

			if(result != 1) {
				throw new Exception();
			}
			
			//view
			resp.sendRedirect("/jdgr/admin/notice/list?pno=1");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 수정 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}	
	}
}
