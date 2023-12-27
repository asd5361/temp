package com.semi.jdgr.user.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;

@WebServlet("/blogSet/category")
public class BlogCategoryEditController extends HttpServlet {
	
	// 카테고리 설정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			String userUrl = req.getParameter("url");
			// service
			BlogService bs = new BlogService();
			List<GroupVo> groupVoList = bs.getEditGroupList(userUrl);
			
			// result
			BlogVo blogVo = new BlogVo();
			blogVo.setBlogUrl(userUrl);

			req.setAttribute("blogUserGroupData", groupVoList);
			req.setAttribute("blogUserData", blogVo);
			req.setAttribute("blogClassName", "blog_set");
			req.setAttribute("blogSideClassName", "category");
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/category.jsp").forward(req, resp);
			
		} catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/user/blogSet/category.jsp").forward(req, resp);
		}
		
		
	}
	
	// 카테고리 설정 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// data
			// 클라이언트로부터 전송된 JSON 데이터 읽기
	        BufferedReader reader = req.getReader();
	        StringBuilder jsonBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuilder.append(line);
	        }
	        // 데이터 객체로 변환
	        Gson gson = new Gson();
	        GroupVo groupVo = gson.fromJson(jsonBuilder.toString(), GroupVo.class);
	        BlogVo blogVo = gson.fromJson(jsonBuilder.toString(), BlogVo.class);
	        
			// service
	        BlogService bs = new BlogService();
	        List<GroupVo> groupVoList = bs.editGroup(groupVo, blogVo);
			
	        String groupVoListJsonData = gson.toJson(groupVoList);
	        
			// result
	        resp.setCharacterEncoding("UTF-8");
	        PrintWriter out = resp.getWriter();
			
			out.write(groupVoListJsonData);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
