package com.semi.jdgr.heart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.service.HeartService;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.post.service.PostServiceJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/post/heart")
public class HeartController extends HttpServlet {

	// 공감기능
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// data
			PostVo postDetailVo = (PostVo) req.getSession().getAttribute("postDetailVo");
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");

			AlarmVo alarmVo = new AlarmVo();

			if (loginMember == null) {
				throw new Exception("로그인 먼저 진행하세요.");
			}

			String no = postDetailVo.getPostNo();
			String memberNo = loginMember.getMemNo();

			if (loginMember.getMemNo().equals(postDetailVo.getUserNo())) {
				throw new Exception("본인이 작성한 포스트 입니다.");
			}

			// service
			HeartService hs = new HeartService();

			// 공감 중복체크
			boolean isOk = hs.checkHeart(no, memberNo);

			// 공감 vo 불러오기 (불러온 값으로 JS처리)
//			HeartVo heartVo = hs.HeartList(no);
//			List<HeartVo> heartVoList = hs.HeartList(no);

			// 알람에 인서트
			PostServiceJOJ ps = new PostServiceJOJ();
			String userNo = ps.findUserNo(postDetailVo.getUserNick());

			alarmVo.setReceiverNo(userNo);
			alarmVo.setPostNo(postDetailVo.getPostNo());
			alarmVo.setSenderNo(loginMember.getMemNo());
			alarmVo.setAlarmType("HEART");
			int insert = hs.insertHeartAlarm(alarmVo);

			if (insert != 1) {
				throw new Exception("알람 인서트 실패");
			}

			HttpSession session = req.getSession();

			// result
			int add = 0;
			if (isOk) {
				add = hs.AddHeart(no, memberNo);
				if (add == 1) {
//						req.setAttribute("add", add);
					session.setAttribute("add", add);
					req.getSession().setAttribute("add", add);
				}
			} else {
				int del = hs.delHeart(no, memberNo);
				if (del == 1) {
//						req.setAttribute("del", del);
					session.setAttribute("del", del);
					req.getSession().setAttribute("del", del);
				} else {
					throw new Exception("공감 기능 오류 발생");
				}
			}

			resp.sendRedirect("/jdgr/post/detail?url=" + postDetailVo.getBlogUrl() + "&&GroupNo=" + postDetailVo.getGroupNo());

		} catch (Exception e) {
			System.out.println("공감 오류 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "공감 오류 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}

	}// doGet

}
