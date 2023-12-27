package com.semi.jdgr.user.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;

@WebServlet("/blogSet/subscribe")
public class BlogSubscribeEditController extends HttpServlet {
	
	// 구독한 블로그 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String blogUrl = req.getParameter("url");
			BlogVo blogUserData = new BlogVo();
			blogUserData.setBlogUrl(blogUrl);
			
			// service
			BlogService bs = new BlogService();
			List<BlogVo> blogVoList = bs.getFollowBlogList(blogUrl); // 구독한 블로그 정보 가져오기
			
			
			// result
			req.setAttribute("blogUserData", blogUserData);
			req.setAttribute("followUserBlogVoList", blogVoList);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "subscribe");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/subscribe.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 구독한 블로그 수정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
