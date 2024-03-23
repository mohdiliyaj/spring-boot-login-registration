package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.entity.Student;
import in.ashokit.repo.StudentRepo;
import in.ashokit.utils.MailUtil;

@Service
public class StudentService implements IStudentService {

	private StudentRepo studentRepo;
	private MailUtil mailUtil;

	public StudentService(StudentRepo studentRepo, MailUtil mailUtil) {
		this.studentRepo = studentRepo;
		this.mailUtil = mailUtil;
	}

	@Override
	public boolean saveStudent(Student s) {
		Student save = studentRepo.save(s);
		if (save.getStudentId() != null) {
			String subject = "Account created successfully";
			String body = "Hi " + save.getStudentName() + ", \n\n" +
				    "You have successfully created the account in the Ashok IT website.\n" +
				    "Contact us for more details...! Happy learning \n\n" +
				    "Thank you,\n" +
				    "AshokIT";
			
			mailUtil.sendMail(subject, body, save.getStudentEmail()); 	
			return true;
		}
		return false;
	}

	@Override
	public Student checkLogin(Student s) {
		return studentRepo.findByStudentEmailAndStudentPassword(s.getStudentEmail(), s.getStudentPassword());
	}
	
	
	@Override
	public boolean getStudentByEmail(String email) {
		 Student byStudentEmail = studentRepo.findByStudentEmail(email);
		 return byStudentEmail != null ? true : false;
	}
	
	@Override
	public boolean sendForgetPassword(String email) {
		
		Student byStudentEmail = studentRepo.findByStudentEmail(email);
		if(byStudentEmail != null) {
			
			String subject = "Recover your password - AshokIT";
			String body = "<h1>Password Reset Request</h1>"
					+ "<p>Hello "+ byStudentEmail.getStudentName() +"</p><br/>"
					+ "<p>We have received a request to recover your password.</p>"
					+ "<p>Your password is : <b>" + byStudentEmail.getStudentPassword() +"</b></p>"
					+ "<p>If you have any questions or concerns, please contact us.</p><br/>"
					+ "<p>Thank you,</p>"
					+ "<p>AshokIT</p>";
			
			mailUtil.sendForgetMail(subject, body, email);
			return true;
		}else {
			return false;
		}
	}

}
