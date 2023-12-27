package com.semi.jdgr.user.set.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/userSet/blog")
public class UserSetBlogController extends HttpServlet {
	
	// 대표블로그 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("blogClassName", "blog_set");
		req.getRequestDispatcher("/WEB-INF/views/user/userSet/userSetBlog.jsp").forward(req, resp);

	}
	
	// 대표블로그 수정
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String blogUrl = req.getParameter("blog");
			MemberVo loginMemberVo = (MemberVo) req.getSession().getAttribute("loginMember");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogUrl(blogUrl);
			blogVo.setMemNo(loginMemberVo.getMemNo());
			
			// service
			BlogService bs = new BlogService();
			int result = bs.editBlogRep(blogVo);
			
			// result
			if(result < 1) {
				throw new Exception("수정결과가 0이면 오류");
			}
			
			// 세션에 업데이트 
			List<BlogVo> loginMemberBlogVoList = (List<BlogVo>) req.getSession().getAttribute("loginMemberBlogVoList");
			for(BlogVo vo : loginMemberBlogVoList) {
				if(vo.getBlogUrl().equals(blogUrl)) {
					vo.setRepYn("Y");
				} else {
					vo.setRepYn("N");
				}
			}
			
			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "대표블로그가 변경되었습니다!");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);
			
			req.setAttribute("blogClassName", "blog_set");
			req.getRequestDispatcher("/WEB-INF/views/user/userSet/userSetBlog.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
			
			// 에러 메시지 가져오기
		    String errorMessage = e.getMessage();
		    
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("warningId", "display: flex;");
			popText.put("warningTitle", errorMessage);
			popText.put("warningContent", "다시 확인해주세요!");
			req.getSession().setAttribute("popText", popText);
			
			req.getRequestDispatcher("/WEB-INF/views/user/userSet/userSetBlog.jsp").forward(req, resp);
		}
	}
}
