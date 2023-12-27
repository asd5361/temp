package com.semi.jdgr.user.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;

@WebServlet("/csboard/detail")
public class CsboardDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//data
			String board = req.getParameter("no");
			String currPage = req.getParameter("currPage");
			
			//service
			CsboardService cs = new CsboardService();
			CsboardVo vo = cs.csboardDetail(board);
			
			//view
			if(vo == null) {
				throw new Exception();
			}
			req.setAttribute("csboardVo", vo);
			req.setAttribute("currPage", currPage);
			req.getRequestDispatcher("/WEB-INF/views/user/csboard/csboardDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 1:1문의 상세 조회 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
	}
}
