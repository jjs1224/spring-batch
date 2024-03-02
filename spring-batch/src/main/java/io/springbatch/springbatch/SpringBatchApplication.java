package io.springbatch.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnableBatchProcessing 스프링배치활성화 어노테이션으로 선언이 필요함.
 */
@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
