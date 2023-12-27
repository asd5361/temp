package com.semi.jdgr.admin.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.page.vo.PageVo;

@WebServlet("/admin/blog/list")
public class AdminBlogListController extends HttpServlet {
	
	// 블로그 관리 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BlogService bs = new BlogService();
			
			// data
			int listCount = bs.selectBlogCount(); //전체 게시글 갯수
			String currentPage_ = req.getParameter("pno");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_); //현재 페이지
			int pageLimit = 10;
			int boardLimit = 10;
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			// service
			List<BlogVo> blogVoList = bs.getAllBlogInfo(pvo);
			
			// result
			if(blogVoList == null) {
				throw new Exception("블로그 조회 실패");
			}
			
			req.setAttribute("blogVoList", blogVoList);
			req.setAttribute("pvo", pvo);
			req.getRequestDispatcher("/WEB-INF/views/admin/blog/list.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
