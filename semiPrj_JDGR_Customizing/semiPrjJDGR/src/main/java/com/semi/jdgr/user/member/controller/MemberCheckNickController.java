package com.semi.jdgr.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.user.member.service.MemberService;

@WebServlet("/member/check/nick")
public class MemberCheckNickController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			// data
			String joinNick = req.getParameter("joinNick");
			
			MemberService ms = new MemberService();
			boolean isOk = ms.checkNickDup(joinNick);
			
			if(isOk) {
				out.write("{\"msg\" : \"ok\"}");
			}else {
				throw new Exception();
			}
			
		
		} catch (Exception e) {
			out.write("{\"msg\" : \"aaaa\"}");
			e.printStackTrace();
		}
	}
}
