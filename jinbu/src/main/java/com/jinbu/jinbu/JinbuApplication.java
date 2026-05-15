package com.jinbu.jinbu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class JinbuApplication implements CommandLineRunner {

	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JinbuApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("test").isEmpty()) {
			User[] users = new User[] {
					new User("pep", bCryptPasswordEncoder().encode("sui"), "pep@gmail.com"),
					new User("test", bCryptPasswordEncoder().encode("sui"), "test@gmail.com")
			};
			for (User user : users) {
				userRepository.save(user);
			}
		}
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
