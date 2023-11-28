package com.semi.jdgr.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/blogDetail/view")
public class BlogDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("blogClassName", "blog");
		req.getRequestDispatcher("/WEB-INF/views/blog/blogDetail.jsp").forward(req, resp);
	}
}
