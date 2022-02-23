package com.fp.pi.member;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserMailSendService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SqlSession ss;
	private MemberMapper mDAO;
	
	
	// 이메일 난수 만드는 메서드
		private String init() {
			Random ran = new Random();
			StringBuffer sb = new StringBuffer();
			int num = 0;

			do {
				num = ran.nextInt(75) + 48;
				if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
					sb.append((char) num);
				} else {
					continue;
				}

			} while (sb.length() < size);
			if (lowerCheck) {
				return sb.toString().toLowerCase();
			}
			return sb.toString();
		}

		// 난수를 이용한 키 생성
		private boolean lowerCheck;
		private int size;

		public String getKey(boolean lowerCheck, int size) {
			this.lowerCheck = lowerCheck;
			this.size = size;
			return init();
		}
	
	
	
	
	public void mailSendWithUserKey(String m_email, HttpServletRequest req) {
		
		String key = getKey(false, 20);
		mDAO = ss.getMapper(MemberMapper.class);
		mDAO.GetKey(m_email, key); 
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요. Pumping Iron입니다.</h2><br><br>" 
				+ "<h3>" + m_email + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다." 
				+ "<a href='http://localhost" + req.getContextPath() + "/user/key_alter?m_email="+ m_email +"&m_key="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] Pumping Iron 회원가입 인증메일입니다.", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(m_email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}



	// 이메일 인증하면 m_key를 Y값으로
	public int alter_userKey_service(String m_email, String key) {

		int resultCnt = 0;
		
		mDAO = ss.getMapper(MemberMapper.class);
		
		resultCnt = mDAO.alter_userKey(m_email, key);
		
		return resultCnt;	
	}

}
