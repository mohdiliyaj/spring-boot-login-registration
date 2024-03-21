package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.Student;
import in.ashokit.service.IStudentService;

@Controller
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@GetMapping("/")
	public String loadLogin(Model model) {
		model.addAttribute("student", new Student());
		return "index";
	}
	
	@PostMapping("/login")
	public String checkLogin(@ModelAttribute Student s, Model model) {
		System.out.println(s.getStudentEmail() +""+s.getStudentPassword());
		Student checkLogin = studentService.checkLogin(s);
		if(checkLogin.getStudentId() != null) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute("msg", "Invalid credentials");
			return "index";
		}
	}
	
	@GetMapping("/dashboard")
	public String getDashboard(Model model) {
		return "dashboard";
	}
	
	@GetMapping("/register")
	public String loadRegister(Model model) {
		model.addAttribute("student", new Student());
		return "register";
	}
	
	@PostMapping("/register")
	public String saveStudent(@ModelAttribute Student s, Model model) {
		boolean saveStudent = studentService.saveStudent(s);
		if(saveStudent) {
			model.addAttribute("sucmsg", "Registration successfully");
		}else {
			model.addAttribute("errmsg", "Error occured.. Please try again");
		}
		return "register";
	}
	
}
