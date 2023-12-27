package com.semi.jdgr.admin.csboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/admin/csboard/list")
public class AdminCsboardListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			CsboardService cs = new CsboardService();
			
			int listCount = cs.selectAdminCsboardCount();
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
			List<CsboardVo> csboardVoList = cs.selectAdminCsboardList(pvo);
			
			//view
			if(csboardVoList == null) {
				throw new Exception();
			}
			req.setAttribute("csboardVoList", csboardVoList);
			req.setAttribute("pageVo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/admin/csboard/csboardList.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 1:1문의 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
