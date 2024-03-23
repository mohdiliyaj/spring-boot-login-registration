package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	public Student findByStudentEmailAndStudentPassword(String email, String password);
	
	public Student findByStudentEmail(String email);
}
