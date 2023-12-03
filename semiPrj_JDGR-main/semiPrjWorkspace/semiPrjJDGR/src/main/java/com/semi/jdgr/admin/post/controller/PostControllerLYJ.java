package com.semi.jdgr.admin.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/post/list")
public class PostControllerLYJ extends HttpServlet{
	
	//관리자 포스트 목록 관리(조회하기) 화면 넘기기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/post/list.jsp").forward(req, resp);
	}
	
	
	
	//관리자 포스트 목록 관리(조회하기) 로직 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
}//class
