package com.nhom2.DAO;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestParam;

public class MailDAO {
	@Autowired
	JavaMailSender mailer;
	
	public Boolean send(@RequestParam("from") String from,@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
		Boolean result=false;
		try {
			//Prepare
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper =  new MimeMessageHelper(mail);
			helper.setFrom(from,from);
			helper.setTo(to);
			helper.setReplyTo(from,from);
			helper.setSubject(subject);
			helper.setText(body,true);
			//send
			mailer.send(mail);
			result = true;
		}
		catch(Exception e){
			result= false;
		}
		return result;
		
	}
	//Hàm lấy chuỗi ngẫu nhiên.
	public String getVerifyCode(int n) {
		String AlphaNumbericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "abcdefghijklmnopqrstuvxyz";
		
		StringBuilder sb = new StringBuilder(n);
		
		for(int i=0;i<n;i++) {
			int index = (int) (AlphaNumbericString.length() * Math.random());
			sb.append(AlphaNumbericString.charAt(index));
		}
		return sb.toString();
	}
}
