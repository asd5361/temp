package com.semi.jdgr.user.csboard.controller;

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

@WebServlet("/csboard/list/search")
public class CsboardSearchController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			CsboardService cs = new CsboardService();
			
			//data
			String searchValue = req.getParameter("searchValue");
			
			int listCount = cs.selectSearchCsboardCount(searchValue);		//전체 게시글 갯수
			String currentPage__ = req.getParameter("pno");
			if(currentPage__ == null) {
				currentPage__ = "1";
			}
			int currentPage = Integer.parseInt(currentPage__); //현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//service
			List<CsboardVo> csboardVoList = cs.search(searchValue,pvo);
			
			if(csboardVoList == null) {
				throw new Exception();
			}
			
			//view
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("csboardVoList", csboardVoList);
			req.setAttribute("pageVo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/user/csboard/csboardList.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "[ERROR] 1:1문의 검색 기능 에러 발생");
			req.getRequestDispatcher("/WEB-INF/views/admin/common/error.jsp").forward(req, resp);
		}
		
		
	}
}
