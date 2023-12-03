package com.semi.jdgr.admin.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/post/detail")
public class PostControllerJOJ extends HttpServlet{
	
	// 포스트 상세관리 (화면)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/admin/post/detail.jsp").forward(req, resp);
		
	}
	
	// 포스트 상세관리 (로직)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// data
		
		// service
		
		// result
		
	}

}
