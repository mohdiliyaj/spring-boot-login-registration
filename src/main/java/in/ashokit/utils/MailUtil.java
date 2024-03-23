package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;


@Component
public class MailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	public boolean sendMail(String subject, String body, String email) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);
			message.setTo(email);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean sendForgetMail(String subject, String body, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(mimeMessage);			
			return true;
		}catch(Exception e ){
			e.printStackTrace();
		}
		return false;
	}
}
