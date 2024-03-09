package com.storyteller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Story Teller API", version = "1.0", description = "Api details"))
public class StorytellerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorytellerApiApplication.class, args);
	}


}
