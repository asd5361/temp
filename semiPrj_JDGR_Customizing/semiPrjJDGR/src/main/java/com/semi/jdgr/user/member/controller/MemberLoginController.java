package com.semi.jdgr.user.member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.jdgr.alarm.dao.AlarmDao;
import com.semi.jdgr.alarm.service.AlarmService;
import com.semi.jdgr.alarm.vo.AlarmVo;
import com.semi.jdgr.user.member.service.MemberService;
import com.semi.jdgr.user.member.vo.MemberPostSanctionVo;
import com.semi.jdgr.user.member.vo.MemberReplySanctionVo;
import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.DateTemplate;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			// data
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");

			MemberVo vo = new MemberVo();
			vo.setMemId(memberId);
			vo.setMemPwd(memberPwd);
			
			HttpSession session = req.getSession();
			
			MemberService ms = new MemberService();
			List<MemberReplySanctionVo> mrsVoList = ms.findMRSVoList();
			List<MemberPostSanctionVo> mpsVoList = ms.findMPSVoList();

			for (MemberReplySanctionVo mrsVo : mrsVoList) {
				if (memberId.equals(mrsVo.getMemId())) {
					for (MemberPostSanctionVo mpsVo : mpsVoList) {
						if (memberId.equals(mpsVo.getMemId())) {
							//둘다 내역이 있으면 투데이에 양쪽 밴데이를 더해서 엔드데이로 만들기
							LocalDate today = DateTemplate.findToday();
							long mpsBanDay = mpsVo.getBanDay();
							long mrsBanDay = mrsVo.getBanDay();
							int banDay = (int) (mpsBanDay + mrsBanDay);
							LocalDate endDate = today.plusDays(banDay);
							int comparisonResult = endDate.compareTo(today);
							if (comparisonResult >= 0) {
								session.setAttribute("alertMsg", endDate + " 까지 정지된 ID입니다.");
								System.out.println("에지님 정지 끝나는 날 : " +endDate);
								throw new Exception("정지된 회원");
							} 
						} 
						
					}
					session.setAttribute("alertMsg", mrsVo.getSancDate() + " 까지 정지된 ID입니다.");
					throw new Exception("정지된 회원");
				}
			}
			for (MemberPostSanctionVo mpsVo : mpsVoList) {
				System.out.println(mpsVo.getMemId());// 지울꺼
				if (memberId.equals(mpsVo.getMemId())) {
					session.setAttribute("alertMsg", mpsVo.getSancDate() + " 까지 정지된 ID입니다.");
					throw new Exception("정지된 회원");
				}
			}
			// service
			MemberVo loginMember = ms.login(vo);
			
			// result (==view)
			if (loginMember == null) {
				session.setAttribute("alertMsg", "아이디 또는 비밀번호가 틀렸습니다.");
				throw new Exception("로그인 실패 ...");
			}
			

			session.setAttribute("loginMember", loginMember);
			resp.sendRedirect("/jdgr/home");

		} catch (Exception e) {
			e.printStackTrace();

			resp.sendRedirect("/jdgr/member/login");

		}

	}
}
