package com.semi.jdgr.user.follow.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.heart.vo.HeartVo;
import com.semi.jdgr.post.service.PostServiceJOJ;
import com.semi.jdgr.post.vo.PostVo;
import com.semi.jdgr.user.follow.service.FollowService;
import com.semi.jdgr.user.follow.vo.FollowVo;
import com.semi.jdgr.user.member.vo.MemberVo;

@WebServlet("/post/follow")
public class FollowController extends HttpServlet {

	// 구독기능
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

			String blogNo = postDetailVo.getBlogNo();
			String memberNo = loginMember.getMemNo();

			if (loginMember.getMemNo().equals(postDetailVo.getUserNo())) {
				throw new Exception("본인 블로그 입니다.");
			}

			// service
			FollowService fs = new FollowService();

			// 구독 중복체크
			boolean isOk = fs.checkFollow(blogNo, memberNo);

			// 구독 vo 불러오기
			FollowVo followVo = fs.FollowList(blogNo);
//			List<FollowVo> followVoList = fs.FollowList(blogNo);

			// 알람에 인서트
			PostServiceJOJ ps = new PostServiceJOJ();
			String userNo = ps.findUserNo(postDetailVo.getUserNick());

			alarmVo.setReceiverNo(userNo);
			alarmVo.setPostNo(postDetailVo.getPostNo());
			alarmVo.setSenderNo(loginMember.getMemNo());
			alarmVo.setAlarmType("FOLLOW");
			int insert = fs.insertFollowAlarm(alarmVo);

			if (insert != 1) {
				throw new Exception("알람 인서트 실패");
			}

			HttpSession session = req.getSession();

			// result
			int add = 0;
			if (isOk) {
				add = fs.AddFollow(blogNo, memberNo);
				if (add == 1) {
//					req.setAttribute("add", add);
					session.setAttribute("add", add);
					req.getSession().setAttribute("add", add);
				}
			} else {
				int del = fs.delFollow(blogNo, memberNo);
				if (del == 1) {
//					req.setAttribute("del", del);
					session.setAttribute("del", del);
					req.getSession().setAttribute("del", del);
				} else {
					throw new Exception("구독 기능 오류 발생");
				}
			}

			session.setAttribute("followVo", followVo);
			req.getSession().setAttribute("followVo", followVo);

//			resp.sendRedirect("/jdgr/post/detail?url=${blogUrlVo.blogUrl}&&categoryNo=groupVo.getNo() %>");
			resp.sendRedirect(
					"/jdgr/post/detail?url=" + postDetailVo.getBlogUrl() + "&&GroupNo=" + postDetailVo.getGroupNo());

		} catch (Exception e) {
			System.out.println("구독 오류 발생");
			e.printStackTrace();
			req.setAttribute("errorMsg", "구독 오류 발생");
			req.getRequestDispatcher("/WEB-INF/views/user/common/error.jsp").forward(req, resp);
		}

	}// doGet

}