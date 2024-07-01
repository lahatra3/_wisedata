package mg.lahatra3.wisedata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.extern.slf4j.Slf4j;
import mg.lahatra3.wisedata.beans.SparkConfiguration;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({
	SparkConfiguration.class
})
public class WisedataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WisedataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Wise data...");
	}
}
