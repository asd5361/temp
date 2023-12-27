package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.post.service.PostServiceHJY;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/post/delete")
public class PostDeleteController extends HttpServlet {
	
	// 포스트 삭제하기 로직
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// data
			String postNo = req.getParameter("postNo");
			String blogUrl = req.getParameter("url");
			
			// service
			PostServiceHJY ps = new PostServiceHJY();
			int result = ps.postDelete(postNo);
			
			// result
			if(result != 1) {
				throw new Exception("result가 1이아님");
			}

			// 팝업메세지 전달
			Map<String, String> popText = new HashMap<String, String>();
			popText.put("completeId", "display: flex;");
			popText.put("completeTitle", "포스트 삭제가 완료되었습니다!");
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
