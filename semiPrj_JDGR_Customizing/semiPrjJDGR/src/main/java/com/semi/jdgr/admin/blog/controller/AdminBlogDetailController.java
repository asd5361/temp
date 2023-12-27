package com.semi.jdgr.admin.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;

@WebServlet("/admin/blog/detail")
public class AdminBlogDetailController extends HttpServlet {
	
	// 블로그 관리 상세페이지
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String no = req.getParameter("no");
			
			// service
			BlogService bs = new BlogService();
			BlogVo blogVo = bs.selectBlogByNo(no);
			
			// result
			req.setAttribute("blogVo", blogVo);
			req.setAttribute("currPage", req.getParameter("currPage"));
			req.getRequestDispatcher("/WEB-INF/views/admin/blog/detail.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
