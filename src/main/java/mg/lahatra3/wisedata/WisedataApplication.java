package mg.lahatra3.wisedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import mg.lahatra3.wisedata.beans.SparkConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({
	SparkConfiguration.class
})
public class WisedataApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisedataApplication.class, args);
	}
}
