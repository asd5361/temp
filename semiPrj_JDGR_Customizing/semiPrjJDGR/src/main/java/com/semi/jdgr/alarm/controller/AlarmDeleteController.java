package com.semi.jdgr.alarm.controller;

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

import com.semi.jdgr.alarm.service.AlarmService;
import com.semi.jdgr.blog.service.BlogService;
import com.semi.jdgr.blog.vo.BlogVo;
import com.semi.jdgr.blog.vo.GroupVo;
import com.semi.jdgr.post.vo.CategoryVo;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/alarm/delete")
public class AlarmDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {

			
			String alarmNo = req.getParameter("alarmNo");
			
			AlarmService as = new AlarmService();
			int result = as.deleteAlarm(alarmNo);
			
			if (result != 1) {
				throw new Exception("읽음처리 실패");
			}
			out.write("{\"msg\" : \"ok\"}");
		
		} catch (Exception e) {
			out.write("{\"msg\" : \"fail\"}");
			e.printStackTrace();
		}
	}
}
