package com.semi.jdgr.user.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.page.vo.PageVo;
import com.semi.jdgr.post.service.PostServiceJOJ;
import com.semi.jdgr.post.vo.PostVo;

@WebServlet("/post/list")
public class PostListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Thread.currentThread().setName("ajax 관련 쓰레드");
		
		try {
			
			PostServiceJOJ ps = new PostServiceJOJ();
			
			// data
			int cnt = 1;
			// 클라이언트로부터 전송된 JSON 데이터 읽기
			String pNo = req.getParameter("pNo");
			String groupNo = req.getParameter("GroupNo");
			String blogUrl = req.getParameter("url");
			if(blogUrl.equals("null")) {
				blogUrl = null;
			}
			PostVo postDetailVo = ps.PostDetail(groupNo, blogUrl, pNo, cnt); 
			
			if ((groupNo == null) && (blogUrl == null)) {
				blogUrl = postDetailVo.getBlogUrl();
				groupNo = postDetailVo.getGroupNo();
			} else if((groupNo == null)){
				groupNo = postDetailVo.getGroupNo();
			}else if((blogUrl == null)){
				blogUrl = postDetailVo.getBlogUrl();
			}
			
			int listCount = ps.getPostVoListCount(groupNo); // 전체 포스트 갯수
			String currentPage_ = req.getParameter("pnum");
			if(currentPage_ == null) {
				currentPage_ = "1";
			}
			int currentPage = Integer.parseInt(currentPage_); // 현재 페이지
			int pageLimit = 10;
			int boardLimit = 5;
			
			// service
			PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			List<PostVo> postVoList = ps.getPostVoList(groupNo, pvo); // 페이지에 맞는 포스트 List 가져오기
			
			// result
			Gson gson = new Gson();
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("postVoList", postVoList);
			map.put("pvo", pvo);
			map.put("PgroupNo", groupNo);
			map.put("PblogUrl", blogUrl);
			
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			
			String str = gson.toJson(map);
			
			out.write(str);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
