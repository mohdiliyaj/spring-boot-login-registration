package in.ashokit.service;

import in.ashokit.entity.Student;

public interface IStudentService {
	
	public boolean saveStudent(Student s);
	
	public Student checkLogin(Student s);
	
	public boolean getStudentByEmail(String email);
	
	public boolean sendForgetPassword(String email);
}
