package com.semi.jdgr.user.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.notice.service.NoticeService;
import com.semi.jdgr.notice.vo.NoticeVo;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//data
			String boardno = req.getParameter("no");	//글번호
			String currPage = req.getParameter("currPage");	//상세 보기 끝나고 목록으로 돌아갈때 던져 줄 페이지
			
			//service
			NoticeService ns = new NoticeService();
			NoticeVo vo = ns.noticeDetail(boardno);
			
			//view
			if(vo == null) {
				throw new Exception();
			}
			
			req.setAttribute("noticeVo", vo);
			req.setAttribute("currPage", currPage);
			req.getRequestDispatcher("/WEB-INF/views/user/notice/noticeDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 공지사항 상세 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
