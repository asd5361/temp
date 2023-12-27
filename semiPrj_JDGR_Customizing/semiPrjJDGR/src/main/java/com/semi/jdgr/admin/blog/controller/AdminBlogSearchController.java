package com.semi.jdgr.admin.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/admin/blog/search")
public class AdminBlogSearchController extends HttpServlet {
	
	// 블로그 관리 검색하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BlogService bs = new BlogService();
			
			// data
			String memId = req.getParameter("memId");
			String memNick = req.getParameter("memNick");
			String blogTitle = req.getParameter("blogTitle");
			String blogUrl = req.getParameter("blogUrl");
			String blogRep = req.getParameter("blogRep");
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("memId", memId);
			param.put("memNick", memNick);
			param.put("blogTitle", blogTitle);
			param.put("blogUrl", blogUrl);
			param.put("blogRep", blogRep);
			
			int listCount = bs.selectSearchBlogCount(param); // 검색값에 따른 블로그 개수 조회
			int currentPage = 1;
			if(req.getParameter("pno") != null) {
				currentPage = Integer.parseInt(req.getParameter("pno"));
			}
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			// service
			List<BlogVo> blogVoList = bs.adminBlogSearch(param, pvo);
			
			// result
			req.setAttribute("blogVoList", blogVoList);
			req.setAttribute("pvo", pvo);
			req.setAttribute("param", param);
			req.getRequestDispatcher("/WEB-INF/views/admin/blog/list.jsp").forward(req, resp);
			
			System.out.println(param);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
