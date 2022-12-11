package com.javatechie.awselasticbeanstalkexample;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.impl.UserSecurityService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@SpringBootApplication
@Controller
public class AwsElasticbeanstalkExampleApplication implements CommandLineRunner{


	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	

	
	// @GetMapping("/")
	// public String indexGoogle(){
	// 	return "google/loginStatus";
	// }
	@GetMapping("/")
	public String welcome(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
	
		return "welcome";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AwsElasticbeanstalkExampleApplication.class, args);
	
	}
	   @Override
		public void run(String... args) throws Exception {
		
//			  User user = new User(); 
//			  user.setId(Long.parseLong("1"));
//			   user.setEmail("lamaisondecreationkia@yahoo.com"); 
//			   user.setUsername("samba");
//			   user.setLastName("Samb");
//			   user.setFirstName("samba");
			   BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			   //user.setPassword(passwordEncoder.encode("Samba12345@")); 
//	           user.setPassword(passwordEncoder.encode("passer")); 
//			   Role roleR = new Role();
//			   roleR.setRoleId(Long.parseLong("1")); 
//			   roleR.setName(AppConstants.ROLE_1);//By Default
//			   Set<UserRole> userRoles = new HashSet<>(); userRoles.add(new
//			   UserRole(user, roleR)); 
//			   userService.createUser(user, userRoles);
		}
}
