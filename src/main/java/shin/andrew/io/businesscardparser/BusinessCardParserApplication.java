package shin.andrew.io.businesscardparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BusinessCardParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessCardParserApplication.class, args);
	}

}
