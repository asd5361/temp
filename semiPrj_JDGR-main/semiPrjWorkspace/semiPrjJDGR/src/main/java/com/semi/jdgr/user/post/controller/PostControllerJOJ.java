package com.semi.jdgr.user.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.post.service.PostServiceJOJ;
import com.semi.jdgr.post.vo.PostVo;

@WebServlet("/post/detail")
public class PostControllerJOJ extends HttpServlet{
	
	// 포스트 상세보기 (화면)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
		// data
		String no = req.getParameter("no");
		
		// service
		PostServiceJOJ ps = new PostServiceJOJ();
		PostVo postDetailVo = ps.PostDetail(no);
		
		// result
		System.out.println(postDetailVo);
		req.setAttribute("PostDetailVo", postDetailVo);
		req.getRequestDispatcher("/WEB-INF/views/user/post/detail.jsp").forward(req, resp);
		
		}catch(Exception e) {
			System.out.println("포스트 상세보기 실패");
			e.printStackTrace();
			req.setAttribute("errorMsg", "포스트 상세보기 실패");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
		
		
	}// PostDetail
	
	// 포스트 상세보기 (로직)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// data
		
		// service
		
		// result
		
	}
	
	

}
