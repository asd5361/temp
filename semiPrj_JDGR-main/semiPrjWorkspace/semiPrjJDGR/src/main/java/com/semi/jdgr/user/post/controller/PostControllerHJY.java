package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/hjy/post/view")
public class PostControllerHJY extends HttpServlet {
	
	// 포스트 상세보기 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// url정보
			String getBlogUrl = req.getParameter("url");
			
			System.out.println(getBlogUrl);
			
			// 로그인 유저정보
			HttpSession session = req.getSession();
			MemberVo memberVo = (MemberVo) session.getAttribute("loginMember");

			// service
			BlogService bs = new BlogService();
			BlogVo blogVo = bs.getUserblog(memberVo, getBlogUrl); // 블로그 정보 가져오기
			List<GroupVo> groupVoList = bs.getGroupList(blogVo); // 카테고리그룹 가져오기

			// result
			if(blogVo == null) {
				throw new Exception("blogVo가 null");
			}

			req.setAttribute("loginMember", memberVo);
			req.setAttribute("loginMemberBlogVo", blogVo);
			req.setAttribute("blogClassName", "blog");
			req.setAttribute("groupVoList", groupVoList);
						
			req.getRequestDispatcher("/WEB-INF/views/user/blog/blogView.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "이 블로그는 없는 블로그입니다.");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}
		
	}
}
