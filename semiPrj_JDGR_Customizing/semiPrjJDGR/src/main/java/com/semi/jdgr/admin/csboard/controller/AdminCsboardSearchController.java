package com.semi.jdgr.admin.csboard.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.csboard.service.CsboardService;
import com.semi.jdgr.csboard.vo.CsboardVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/admin/csboard/search")
public class AdminCsboardSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CsboardService cs = new CsboardService();
			
			//data
			CsboardVo searchVo = new CsboardVo();
			searchVo.setqTit(req.getParameter("title"));
			searchVo.setqCon(req.getParameter("content"));
			searchVo.setMemNick(req.getParameter("writer"));
			searchVo.setAnsewr(req.getParameter("ansContent"));
			searchVo.setQuestionCategory(req.getParameter("csSel"));
			searchVo.setAnsewrDate(req.getParameter("ansSel"));		//답변 유무 값

			int listCount = cs.adminSelectCsboardCount(searchVo);
			String currentPage__ = req.getParameter("pno");
			if(currentPage__ == null) {
				currentPage__ = "1";
			}

			int currentPage = Integer.parseInt(currentPage__); //현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

			
			
			//service
			List<CsboardVo> csboardVoList =  cs.adminCsboardSearchController(searchVo,pvo);
			
			if(csboardVoList == null) {
				throw new Exception();
			}
			
			//result
			req.setAttribute("searchVo", searchVo);
			req.setAttribute("pageVo", pvo);
			req.setAttribute("csboardVoList", csboardVoList);
			req.getRequestDispatcher("/WEB-INF/views/admin/csboard/csboardList.jsp").forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 관리자 1:1문의 검색 기능 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
		}
		
	}
}
