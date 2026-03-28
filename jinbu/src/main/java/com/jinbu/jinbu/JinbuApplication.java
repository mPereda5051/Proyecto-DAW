package com.jinbu.jinbu;

import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@SpringBootApplication
public class JinbuApplication implements CommandLineRunner {

	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JinbuApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[] {
				new User("pep", "sui", "pep@gmail.com"),
				new User("test", "sui", "test@gmail.com")
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
