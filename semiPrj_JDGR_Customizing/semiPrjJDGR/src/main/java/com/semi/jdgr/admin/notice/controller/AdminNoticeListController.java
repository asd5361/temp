package com.semi.jdgr.admin.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/admin/notice/list")
public class AdminNoticeListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			NoticeService ns = new NoticeService();
			int listCount = ns.selectAdminNoticeCount();
			//data
			String currentPage__ = req.getParameter("pno");
			if(currentPage__ == null) {
				currentPage__ = "1";
			}
			int currentPage = Integer.parseInt(currentPage__);	//현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//service
			List<NoticeVo> noticeVoList = ns.selectAdminNoticeList(pvo);
			
			//view
			if(noticeVoList == null) {
				throw new Exception();
			}
			req.setAttribute("noticeVoList", noticeVoList);
			req.setAttribute("pageVo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/admin/notice/noticeList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 공지사항 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
