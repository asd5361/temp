package com.semi.jdgr.admin.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;

@WebServlet("/admin/csboard/detail")
public class AdminCsboardDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//data
			String noticeNo = req.getParameter("no");
			System.out.println(req.getParameter("currPage"));
			//service
			CsboardService cs = new CsboardService();
			CsboardVo vo = cs.adminCsboardDetail(noticeNo);
			
			//result
			if(vo == null){
				throw new Exception();
			}
			req.setAttribute("vo", vo);
			req.setAttribute("pno", req.getParameter("currPage"));
			req.getRequestDispatcher("/WEB-INF/views/admin/csboard/csboardDetail.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 1:1문의 상세 목록 페이지 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);			
		}
	}
	
}
