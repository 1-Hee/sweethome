package com.ssafy.home.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.ssafy.home.member.dto.Member;

@Component
public class VerifyEmail {

	public void sendEmail(Member user2) {
		// 수신자 이메일
		String recipient = user2.getEmail()+"@"+user2.getDomain();
		System.out.println(recipient);
		// 발신할 코드 (우리의 경우 사용자의 비밀번호)
		String code = user2.getPassword();
		String userName = user2.getName();

		// 1. 발신자의 메일 계정과 비밀번호 설정
		final String user = "tmdqjaqhdl3@naver.com";
		final String password = "poi7788249";
		
		
		// 2. Property에 SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

		// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
		// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Subject
			message.setSubject("위드 홈(With Home) " + userName +"님의 비밀번호 찾기");

			// Text
			  message.setText("위드 홈(With Home) 서비스를 이용해주셔서 감사합니다.\n\n"

                + "귀하의 비밀번호는" + code +  "입니다.\n\n"
                + "본 이메일은 발신 전용입니다.\n\n"
                + "Copyright With Home, Inc. All rights reservred\n\n"
                + "With Home은 삼성화재 유성캠퍼스에 위치하고 있습니다.");
			Transport.send(message); // send message

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}