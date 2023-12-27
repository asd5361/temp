package com.semi.jdgr.admin.blame.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.blame.service.AdminReplyBlameService;
import com.semi.jdgr.admin.blame.vo.AdminReplyBlameVo;

@WebServlet("/admin/blame/r_blame_update")
public class AdminReplyBlameUpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
		
		//data
		String pno = req.getParameter("pno");
		String currPage = req.getParameter("currPage");
		String sancYn = req.getParameter("sancYn");


		AdminReplyBlameVo vo = new AdminReplyBlameVo();
		vo.setrNo(pno);
		vo.setrSancYn(sancYn);
		
		
		//service
		AdminReplyBlameService ars = new AdminReplyBlameService();
		int results = ars.rBlameUpdate(vo);
				
		
		//result 
		if(results != 1) {
			throw new Exception();
		}
		resp.sendRedirect("/jdgr/admin/blame/r_blame_list?pno="+ currPage);
		
//		AdminReplyBlameVo vo = ars.updateBlameSanc(sancYn);
//		resp.sendRedirect("/admin/blame/r_blame_detail");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "제재 처리 저장 실패");
			req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
		}
	}
}
