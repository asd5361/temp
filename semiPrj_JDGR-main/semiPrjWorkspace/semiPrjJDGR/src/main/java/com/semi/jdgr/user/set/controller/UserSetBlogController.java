package com.semi.jdgr.user.set.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userSet/blog")
public class UserSetBlogController extends HttpServlet {
	
	// 블로그 정보 수정/저장 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("blogClassName", "blog_set");
		req.getRequestDispatcher("/WEB-INF/views/user/userSet/userSetBlog.jsp").forward(req, resp);

	}
}
