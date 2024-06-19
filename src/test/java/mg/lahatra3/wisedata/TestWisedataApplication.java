package mg.lahatra3.wisedata;

import org.springframework.boot.SpringApplication;

public class TestWisedataApplication {

	public static void main(String[] args) {
		SpringApplication.from(WisedataApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
