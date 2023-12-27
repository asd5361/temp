package com.semi.jdgr.user.blame.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi.jdgr.user.blame.service.PostBlameService;
import com.semi.jdgr.user.blame.service.ReplyBlameService;
import com.semi.jdgr.user.blame.vo.PostBlameVo;
import com.semi.jdgr.user.blame.vo.ReplyBlameVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.user.reply.vo.ReplyVo;

@WebServlet("/user/blame/r_blamepop")
public class ReplyBlameController extends HttpServlet{
	

	
//	//신고하기 모달 화면
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			
////			MemberVo memberVo = (MemberVo) req.getSession().getAttribute("loginMember");
////			if(memberVo == null) {
////				throw new Exception("로그인이 필요한 서비스입니다.");
////			}
//			
//			
//			//data
//			ReplyVo vo = new ReplyVo();
//			vo.setReplyNo(req.getParameter("replyNo"));	
//			vo.setReplyMem(req.getParameter("replyMem"));
//			vo.setCon(req.getParameter("con"));
//
//			// 댓글 정보를 모달에 전달
//			req.setAttribute("vo", vo);
//
//			
//			//service
//			ReplyBlameService rbs = new ReplyBlameService();	
//			List<ReplyBlameVo> rvo = rbs.blameList();			//신고 구분 목록 가져오기
//			
//			//result(==view)
//			if(rvo == null) {
//				req.setAttribute("alertMsg", "댓글 정보 불러오기 실패");
//				throw new Exception("댓글 신고 실패");
//			}
//			req.setAttribute("rvo", rvo);
//			req.setAttribute("vo", vo);
//			req.getRequestDispatcher("/WEB-INF/views/user/blame/r_blamepop.jsp").forward(req, resp);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			req.setAttribute("errorMsg", "신고");
//			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
//		}
//	
//		
//	}//doGet
	
	
	//신고하기 모달 화면
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			try {
				
//				MemberVo memberVo = (MemberVo) req.getSession().getAttribute("loginMember");
//				if(memberVo == null) {
//					throw new Exception("로그인이 필요한 서비스입니다.");
//				}
				
				
				//data
				String rNo = req.getParameter("rNo");
				
				
				//service
				ReplyBlameService pbs = new ReplyBlameService();
				ReplyBlameVo pbo = pbs.getReplyInfo(rNo);
				List<ReplyBlameVo> list = pbs.blameList();

				

				//result
				req.setAttribute("pbo", pbo);
				req.setAttribute("list", list);
				req.getRequestDispatcher("/WEB-INF/views/user/blame/p_blamepop.jsp").forward(req, resp);
				}catch(Exception e) {
					System.out.println("포스트 정보 불러오기 실패");
					e.printStackTrace();
					req.setAttribute("error", "포스트 정보 조회 실패");
					req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
				}
		
			
		}//doGet
	
	
//	//신고 로직
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		try {
//			//data
//			HttpSession session = req.getSession(false);
//			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
//
//			String loginMem = "1";
//			String rWriterNo = req.getParameter("rWriterNo");
//			String rBlaCon = req.getParameter("rBlaCon");
//			String rBlaListNo = req.getParameter("rBlaList");
//			String rBlaDetail = req.getParameter("rBlaDetail");
//			String replyNo = req.getParameter("replyNo");
//
//			ReplyBlameVo vo = new ReplyBlameVo();
//			vo.setrBlamerNo(loginMem);
//			vo.setrWriterNo(rWriterNo);
//			vo.setrBlaCon(rBlaCon);
//			vo.setrBlaList(rBlaListNo);
//			vo.setrBlaDetail(rBlaDetail);
//			vo.setrNo(replyNo);
//			
//			
//			//service
//			ReplyBlameService rbs = new ReplyBlameService();
//			int result = rbs.blame(vo);
//			
//			//result(==view)
//			if(result != 1) {
//				throw new Exception("result 값이 1이 아님");
//			}
//			
//			//나중에 포스트 합치면 필요한 값 넣어서 화면으로
//			System.out.println("성공");
////			resp.sendRedirect("/jdgr/home");
//			
//		}catch(Exception e) {
//			System.out.println("신고 중 에러 발생");
//			e.printStackTrace();
//			req.setAttribute("errorMsg", "신고");
//			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
//			
//		}
//		
//		
//		
//	}//doPost

		
		
		
		//신고 로직
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			try {
				//data
				HttpSession session = req.getSession(false);
				MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");

//				String loginMem = "1";
				String loginMem = loginMember.getMemNo();
				String replyNo = req.getParameter("replyNo");
				String replyMem = req.getParameter("replyMem");
				String Con = req.getParameter("Con");
				String rBlaList = req.getParameter("rBlaList");
				String rBlaDetail = req.getParameter("rBlaDetail");

				ReplyBlameVo rbo = new ReplyBlameVo();
				rbo.setrBlamerNo(loginMem);
				rbo.setrWriterNo(replyMem);
				rbo.setCon(Con);
				rbo.setrBlaList(rBlaList);
				rbo.setrBlaDetail(rBlaDetail);
				rbo.setrNo(replyNo);
				
				
				//service
				ReplyBlameService rbs = new ReplyBlameService();
				int result = rbs.blame(rbo);
				
				//result(==view)
				if(result != 1) {
					throw new Exception("result 값이 1이 아님");
				}
				
				//나중에 포스트 합치면 필요한 값 넣어서 화면으로
				System.out.println("성공");
				resp.sendRedirect("/jdgr/home");
				
			}catch(Exception e) {
				System.out.println("신고 중 에러 발생");
				e.printStackTrace();
				req.setAttribute("errorMsg", "신고");
				req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
				
			}
			
			
			
		}//doPost

	
	
}//class























