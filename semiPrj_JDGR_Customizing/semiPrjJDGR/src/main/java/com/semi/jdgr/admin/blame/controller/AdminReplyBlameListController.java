package com.semi.jdgr.admin.blame.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.blame.service.AdminReplyBlameService;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;
import com.semi.jdgr.page.vo.AdminBlamePageVo;

@WebServlet("/admin/blame/r_blame_list")
public class AdminReplyBlameListController extends HttpServlet{

	//신고 목록 조회 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			AdminReplyBlameService abs = new AdminReplyBlameService();
			
			//data
			int listCount = abs.selectBlameCount();
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_);	//현재 페이지
			int pageLimit = 5;
			int blameLimit = 10;
			AdminBlamePageVo pvo = new AdminBlamePageVo(listCount, currentPage, pageLimit, blameLimit);
			
			//service
			List<AdminReplyBlameVo> blameVoList = abs.selectBlameList(pvo);
			
			//result(==view)
			req.setAttribute("blameVoList", blameVoList);
			req.setAttribute("pvo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/admin/blame/r_blame_list.jsp").forward(req, resp);

		}catch(Exception e) {
			System.out.println("[ERROR-BLAME001]신고 목록 조회 중 에러 발생 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "신고 목록 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}//doGet
	
}//class
