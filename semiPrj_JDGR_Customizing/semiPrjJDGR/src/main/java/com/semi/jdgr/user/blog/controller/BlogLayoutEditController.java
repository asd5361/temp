package com.semi.jdgr.user.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;

@WebServlet("/blogSet/layout")
public class BlogLayoutEditController extends HttpServlet {
	
	// 레이아웃-위젯 설정화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String userUrl = req.getParameter("url");
			
			// service
			BlogService bs = new BlogService();
			BlogVo blogVo = bs.getUserblog(userUrl);
			
			// result
			if(blogVo == null) {
				throw new Exception("블로그 정보를 찾을 수 없습니다.");
			}
			
			req.setAttribute("blogUserData", blogVo);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "layout");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/layout.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/layout.jsp").forward(req, resp);
		}
	}
	
	// 레이아웃-위젯 설정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String layout = req.getParameter("layoutSelect");
			String comments = req.getParameter("comments");
			String followBlog = req.getParameter("followBlog");
			String visitorsCnt = req.getParameter("visitorsCnt");
			String clock = req.getParameter("clock");
			String map = req.getParameter("map");
			String blogUrl = req.getParameter("blogUrl");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setLayout(layout);
			blogVo.setrCommentsYn(comments);
			blogVo.setFollowBlogYn(followBlog);
			blogVo.setVisitorsCntYn(visitorsCnt);
			blogVo.setClockYn(clock);
			blogVo.setMapYn(map);
			blogVo.setBlogUrl(blogUrl);
			
			
			// service
			BlogService bs = new BlogService();
			BlogVo resultVo = bs.editLayout(blogVo);
			
			// result
			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "수정이 완료되었습니다!");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);
						
			req.setAttribute("blogUserData", resultVo);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "layout");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/layout.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
			// 팝업메세지 전달
			// 에러 메시지 가져오기
		    String errorMessage = e.getMessage();
		    
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("warningId", "display: flex;");
			popText.put("warningTitle", errorMessage);
			popText.put("warningContent", "다시 확인해주세요!");
			req.getSession().setAttribute("popText", popText);
			
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "layout");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/layout.jsp").forward(req, resp);
		}
	}
}
