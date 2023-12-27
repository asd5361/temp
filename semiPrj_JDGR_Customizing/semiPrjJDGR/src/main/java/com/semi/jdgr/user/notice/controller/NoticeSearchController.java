package com.semi.jdgr.user.notice.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/notice/list/search")
public class NoticeSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			NoticeService ns = new NoticeService();
			
			//data
			String searchValue = req.getParameter("searchValue");
			
			int listCount = ns.selectSearchNoticeCount(searchValue);		//전체 게시글 갯수
			String currentPage__ = req.getParameter("pno");
			if(currentPage__ == null) {
				currentPage__ = "1";
			}
			int currentPage = Integer.parseInt(currentPage__); //현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//service
			List<NoticeVo> noticeVoList = ns.search(searchValue,pvo);
			
			if(noticeVoList == null) {
				throw new Exception();
			}
			//view
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("noticeVoList", noticeVoList);
			req.setAttribute("pageVo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/user/notice/noticeList.jsp").forward(req, resp);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 공지사항 검색 기능 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
