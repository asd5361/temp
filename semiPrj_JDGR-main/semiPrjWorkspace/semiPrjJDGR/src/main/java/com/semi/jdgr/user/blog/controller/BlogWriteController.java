package com.semi.jdgr.user.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.PostVo;

@WebServlet("/user/blog/write")
public class BlogWriteController extends HttpServlet {
	
	// 블로그 글쓰기 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/blog/write.jsp").forward(req, resp);
	}
	
	// 블로그 글쓰기 기능
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			// data
			String categoryNo = req.getParameter("categoryNo");
			String groupNo = req.getParameter("groupNo");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			PostVo postVo = new PostVo();
			postVo.setCategoryNo(categoryNo);
			postVo.setGroupName(groupNo);
			postVo.setTitle(title);
			postVo.setContent(content);
			
			// service
			BlogService bs = new BlogService();
			//int result = bs.postWrite(postVo);
			
			// result
//			if(result != 1) {
//				throw new Exception("result가 1이아님");
//			}
			//req.getSession().setAttribute("errorMsg", "게시글이 작성완료되었습니다.");
			resp.sendRedirect("/jdgr/blog/list");
			
		} catch(Exception e) {
			
		}
		
	}
	
}
