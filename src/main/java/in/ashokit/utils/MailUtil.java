package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class MailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	public boolean sendMail(String subject, String body, String email) {
		boolean isSent = false;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);
			message.setTo(email);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			isSent = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
}
