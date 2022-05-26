package com.getarrays.userservice;

import com.getarrays.userservice.domain.AppUser;
import com.getarrays.userservice.domain.Role;
import com.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveAppUser(new AppUser(null,"Robson","rob","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Ribson","rib","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Thoibi","thoi","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Tomba","tom","1234",new ArrayList<>()));

			userService.addRoletoAppUser("rob","ROLE_USER");
			userService.addRoletoAppUser("rob","ROLE_MANAGER");
			userService.addRoletoAppUser("rib","ROLE_MANAGER");
			userService.addRoletoAppUser("thoi","ROLE_ADMIN");
			userService.addRoletoAppUser("tom","ROLE_SUPER_ADMIN");
			userService.addRoletoAppUser("tom","ROLE_ADMIN");
			userService.addRoletoAppUser("tom","ROLE_USER");


		};
	}

}
