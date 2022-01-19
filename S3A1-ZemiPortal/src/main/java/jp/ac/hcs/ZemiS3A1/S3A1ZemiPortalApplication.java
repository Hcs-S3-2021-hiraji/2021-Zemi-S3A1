package jp.ac.hcs.ZemiS3A1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@ServletComponentScan
public class S3A1ZemiPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3A1ZemiPortalApplication.class, args);
	}

}
