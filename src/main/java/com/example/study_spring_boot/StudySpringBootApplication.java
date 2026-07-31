package com.example.study_spring_boot;

import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.repository.SpringDataPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringBootApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(SpringDataPostRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new Post("두 번째 글 hht", "내용 2 hht"));
				repository.save(new Post("첫 번째 글 hht", "내용 1 jjt"));
				repository.save(new Post("세 번째 글", "내용 3"));
			}
		};
	}

}
