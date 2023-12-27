package com.semi.jdgr.admin.blame.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.admin.blame.service.AdminPostBlameService;
import com.semi.jdgr.admin.blame.vo.AdminPostBlameVo;

@WebServlet("/admin/blame/p_blame_update")
public class AdminPostBlameUpdateController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//data
			String pno = req.getParameter("pno");
			String currPage = req.getParameter("currPage");
			String sancYn = req.getParameter("sancYn");
			
			AdminPostBlameVo vo = new AdminPostBlameVo();
			vo.setpNo(pno);
			vo.setpSancYn(sancYn);;
			
			//service
			AdminPostBlameService aps = new AdminPostBlameService();
			int results = aps.pBlameUpdate(vo);
			
			//result(==view)
			if(results != 1) {
				throw new Exception();
			}
			resp.sendRedirect("/jdgr/admin/blame/p_blame_list?pno="+ currPage);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "제재 처리 저장 실패");
			req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
		}
	}


}//class
