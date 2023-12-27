package com.semi.jdgr.user.post.controller;

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
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.post.service.PostServiceHJY;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/post/edit")
public class PostEditController extends HttpServlet {
	
	// 포스트 수정하기 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String postNo = req.getParameter("postNo");
			BlogVo blogVo = (BlogVo) req.getSession().getAttribute("blogUrlVo");
			String blogUrl = req.getParameter("url");
			
			// service
			PostServiceHJY ps = new PostServiceHJY();
			PostVo postVo = ps.getPostByNo(postNo);
			postVo.setBlogUrl(blogUrl);
			BlogService bs = new BlogService();
			List<GroupVo> groupVoList = bs.getGroupList(blogVo); // 그룹 카테고리 가져오기
			List<CategoryVo> categoryVoList = bs.getCategoryList(); // 포스트 카테고리 가져오기
			
			// reuslt
			req.setAttribute("postVo", postVo);
			req.setAttribute("groupVoList", groupVoList);
			req.setAttribute("categoryVoList", categoryVoList);
			req.getRequestDispatcher("/WEB-INF/views/user/post/edit.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 포스트 수정하기 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// 로그인 멤버
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			
			// data
			String categoryNo = req.getParameter("categoryNo");
			String groupNo = req.getParameter("groupNo");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String postNo = req.getParameter("postNo");
			String blogUrl = req.getParameter("url");
			
			PostVo postVo = new PostVo();
			postVo.setCategoryNo(categoryNo);
			postVo.setGroupNo(groupNo);
			postVo.setPostTitle(title);
			postVo.setContent(content);
			postVo.setPostNo(postNo);
			postVo.setBlogUrl(blogUrl);
			
			
			// service
			PostServiceHJY ps = new PostServiceHJY();
			int result = ps.postEdit(postVo);
			
			// result
			if(result != 1) {
				throw new Exception("result가 1이아님");
			}

			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "포스트 수정이 완료되었습니다!");
			popText.put("completeContent", "");
			req.getSession().setAttribute("popText", popText);

			resp.sendRedirect("/jdgr/blog/view/" + blogUrl); // 임시로 블로그로 리다이렉트 포스트 카테고리그룹으로 해야됨
			
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
			resp.sendRedirect("/jdgr/home"); // 블로그홈으로 리다이렉트
		}
	}
}
