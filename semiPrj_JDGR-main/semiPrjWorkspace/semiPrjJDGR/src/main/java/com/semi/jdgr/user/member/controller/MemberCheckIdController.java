package com.semi.jdgr.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.user.member.service.MemberService;

@WebServlet("/member/checkid")
public class MemberCheckIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			// data
			String memberId = req.getParameter("joinId");
			
			MemberService ms = new MemberService();
			boolean isOk = ms.checkIdDup(memberId);
			
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
