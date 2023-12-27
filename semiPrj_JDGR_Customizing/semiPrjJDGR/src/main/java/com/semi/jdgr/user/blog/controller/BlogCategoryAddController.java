package com.semi.jdgr.user.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;

@WebServlet("/blogSet/categoryAdd")
public class BlogCategoryAddController extends HttpServlet {
	
	// 카테고리 그룹 추가하기
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
	        List<GroupVo> groupVoList = bs.createGroup(groupVo, blogVo);
			
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
