package sg.edu.nus.iss.ssf_REST_newsAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ssf_REST_newsAPI.services.NewsService;

@SpringBootApplication
public class SsfRestNewsApiApplication implements CommandLineRunner{

	
	@Autowired
	private NewsService newsSvc;

	public static void main(String[] args) {
		SpringApplication.run(SsfRestNewsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		newsSvc.getHeadline("sg", "business");

	}

}
