package com.semi.jdgr.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.jdgr.user.member.service.MemberService;

@WebServlet("/member/sendemail")
public class MemberSendEmailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String checkNum = req.getParameter("checkNum");
		String host = "smtp.naver.com";            
		final String user = "vkfkdjafuf2@naver.com";   
		final String password = "qjafuf2";         
		System.out.println(email);
		String to = email;
		

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // TLS 프로토콜 명시

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(user, password);
		    }
		});
		boolean sendOk = false;
		PrintWriter out = resp.getWriter();
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("[jdgr] 이메일 인증번호입니다.");
			
			
			// Text
			message.setText("인증번호는 "+checkNum+" 입니다.");

			// send the message
			Transport.send(message);
			sendOk = true;

			if(sendOk) {
				out.write("{\"msg\" : \"ok\"}");
				System.out.println("메세지 보내기 성공");
			}else {
				throw new MessagingException();
			}
		} catch (MessagingException e) {
			resp.sendRedirect("/jdgr/member/join");
			out.write("{\"msg\" : \"fail\"}");
			e.printStackTrace();
		}
		
	}
}
