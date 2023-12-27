package com.semi.jdgr.user.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.user.follow.vo.FollowVo;

@WebServlet("/blogSet/subscribe/delete")
public class BlogSubscribeDeleteController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// data
			BufferedReader reader = req.getReader();
		    Gson gson = new Gson();
		    FollowVo[] dataArray = gson.fromJson(reader, FollowVo[].class);
		    
		    for (FollowVo item : dataArray) {
		        System.out.println(item);
		    }
		    
			// service
		    BlogService bs = new BlogService();
			int result = bs.deleteSubscribeList(dataArray);
		    
			// result
		    
		    // JSON 문자열 생성
	        String jsonData = "{\"result\": OK}";
	        // 응답 전송
	        resp.getWriter().write(jsonData);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

        
	}
}
